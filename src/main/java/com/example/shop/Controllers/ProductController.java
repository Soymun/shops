package com.example.shop.Controllers;

import com.example.shop.DTO.*;
import com.example.shop.Entity.Product;
import com.example.shop.Entity.User;
import com.example.shop.Service.Imp.ProductServiceImp;
import com.example.shop.Service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@RestController
public class ProductController {

    private final UserServiceImp userServiceImp;

    private final ProductServiceImp productServiceImp;

    @Autowired
    public ProductController(UserServiceImp userServiceImp, ProductServiceImp productServiceImp) {
        this.userServiceImp = userServiceImp;
        this.productServiceImp = productServiceImp;
    }

    @PreAuthorize("hasAuthority('SELL')")
    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){
        User user = userServiceImp.findUserById(productDTO.getUserId());
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setAbout(productDTO.getAbout());
        product.setLikes(0L);
        product.setPrice(productDTO.getPrice());
        user.getProducts().add(product);
        return ResponseEntity.ok(userServiceImp.updateUser(user));
    }

    @PreAuthorize("hasAuthority('SELL')")
    @GetMapping("/getAllProductById")
    public ResponseEntity<?> getAllProductById(@RequestBody GetAllProductByIdDTO getAllProductByIdDTO){
        User user = userServiceImp.findUserById(getAllProductByIdDTO.getId());
        return ResponseEntity.ok(user.getProducts());
    }

    @PreAuthorize("hasAuthority('SELL')")
    @PostMapping("/saveProductImage")
    public String saveFile(Long id, MultipartFile file) throws IOException {
        String url = "D:\\IdeaProject\\shop\\src\\main\\resources\\file\\";
        File file1 = new File(url + file.getOriginalFilename());
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType.equals("png") || mimeType.equals("jpql")) {
            if (file1.createNewFile()) {
                file.transferTo(file1);
                Product product = productServiceImp.getProductById(id);
                product.setUrlToPngFile(file1.getPath());
                productServiceImp.save(product);
            } else {
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(url + file.getOriginalFilename()));
                writer.write("");
                writer.flush();
                file.transferTo(file1);
            }
            return "Suggest";
        }
        else {
            return "Bad image";
        }
    }

    @GetMapping("/getOrderListProduct")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getOrderList(@RequestBody ProductListDTO productGetDTO){
        try {
            return ResponseEntity.ok(productServiceImp.getOrderList(productGetDTO));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getSortedListProduct")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getSortedList(@RequestBody ProductSortedDTO productGetDTO){
        try {
            return ResponseEntity.ok(productServiceImp.getSortedListProduct(productGetDTO));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/addComment")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO){
        return ResponseEntity.ok(productServiceImp.addComment(commentDTO.getProduct(), commentDTO.getComment()));
    }

    @PostMapping("/addLike")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> addLike(@RequestBody GetAllProductByIdDTO getAllProductByIdDTO){
        return ResponseEntity.ok(productServiceImp.addLikes(getAllProductByIdDTO.getId()));
    }

    @GetMapping("/getProduct/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getOneProduct(@PathVariable(name = "id") Long id){
        Product product = productServiceImp.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/deleteProduct/{userId}/{id}")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Long id, @PathVariable(name = "userId") Long userId){
        User user = userServiceImp.findUserById(userId);
        user.getProducts().stream().filter(n -> Objects.equals(n.getId(), id)).findFirst().ifPresent(product -> ResponseEntity.ok(productServiceImp.deleteProduct(product)));
        return ResponseEntity.ok(false);
    }

    @PatchMapping("/updateProduct/{id}")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "id") Long id, @RequestBody ProductDTO productDTO){
        User user = userServiceImp.findUserById(productDTO.getUserId());
        Product product = user.getProducts().stream().filter(n -> Objects.equals(n.getId(), id)).findFirst().orElse(new Product());
        if(productDTO.getName() != null){
            product.setName(productDTO.getName());
        }
        if(productDTO.getAbout() != null){
            product.setAbout(productDTO.getAbout());
        }
        if(productDTO.getPrice() != null){
            product.setPrice(productDTO.getPrice());
        }
        productServiceImp.save(product);
        return ResponseEntity.ok("Suggest");
    }
}
