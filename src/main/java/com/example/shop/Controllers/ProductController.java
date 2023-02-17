package com.example.shop.Controllers;

import com.example.shop.DTO.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ProductController {

    @PreAuthorize("hasAuthority('SELL')")
    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO){
        return null;
    }

    @PreAuthorize("hasAuthority('SELL')")
    @GetMapping("/getAllProductById")
    public ResponseEntity<?> getAllProductById(@RequestBody GetAllProductByIdDTO getAllProductByIdDTO){
        return null;
    }

    @PreAuthorize("hasAuthority('SELL')")
    @PostMapping("/saveProductImage")
    public String saveFile(Long id, MultipartFile file) throws IOException {
//        String url = "D:\\IdeaProject\\shop\\src\\main\\resources\\file\\";
//        File file1 = new File(url + file.getOriginalFilename());
//        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//        if(mimeType.equals("png") || mimeType.equals("jpql")) {
//            if (file1.createNewFile()) {
//                file.transferTo(file1);
//                Product product = productServiceImp.getProductById(id);
//                product.setUrlToPngFile(file1.getPath());
//                productServiceImp.save(product);
//            } else {
//                BufferedWriter writer = Files.newBufferedWriter(Paths.get(url + file.getOriginalFilename()));
//                writer.write("");
//                writer.flush();
//                file.transferTo(file1);
//            }
//            return "Suggest";
//        }
//        else {
//            return "Bad image";
//        }
        return null;
    }

    @GetMapping("/getOrderListProduct")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getOrderList(@RequestBody ProductListDTO productGetDTO){
        return null;
    }

    @GetMapping("/getSortedListProduct")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getSortedList(@RequestBody ProductSortedDTO productGetDTO){
        return null;
    }

    @PostMapping("/addComment")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> addComment(@RequestBody CommentDTO commentDTO){
        return null;
    }

    @PostMapping("/addLike")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> addLike(@RequestBody GetAllProductByIdDTO getAllProductByIdDTO){
        return null;
    }

    @GetMapping("/getProduct/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getOneProduct(@PathVariable(name = "id") Long id){
        return null;
    }

    @DeleteMapping("/deleteProduct/{userId}/{id}")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Long id, @PathVariable(name = "userId") Long userId){
        return null;
    }

    @PatchMapping("/updateProduct/{id}")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> updateProduct(@PathVariable(name = "id") Long id, @RequestBody ProductDTO productDTO){
        return null;
    }
}
