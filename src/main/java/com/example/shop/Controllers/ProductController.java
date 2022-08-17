package com.example.shop.Controllers;

import com.example.shop.DTO.GetAllProductByIdDTO;
import com.example.shop.DTO.ProductDTO;
import com.example.shop.Entity.Product;
import com.example.shop.Entity.User;
import com.example.shop.Service.Imp.ProductServiceImp;
import com.example.shop.Service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class ProductController {

    private UserServiceImp userServiceImp;

    private ProductServiceImp productServiceImp;

    private final String url = "D:\\IdeaProject\\shop\\src\\main\\resources\\file\\";

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
}
