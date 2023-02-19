package com.example.shop.Controllers;

import com.example.shop.DTO.Product.ProductCreateDto;
import com.example.shop.DTO.Product.ProductUpdateDto;
import com.example.shop.Response.ResponseDto;
import com.example.shop.Facade.FileFacade;
import com.example.shop.Service.Imp.ProductServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImp productServiceImp;

    private final FileFacade fileService;

    @PreAuthorize("hasAuthority('SELL')")
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody ProductCreateDto productDTO){
        return ResponseEntity.ok(ResponseDto.builder().data(productServiceImp.save(productDTO)).build());
    }

    @PreAuthorize("hasAuthority('BUY')")
    @GetMapping("/type/food/product/{id}")
    public ResponseEntity<?> getByTypeOfFoodId(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(productServiceImp.getProductByTypeOfFood(id)).build());
    }

    @PreAuthorize("hasAuthority('SELL')")
    @PostMapping("/file/{id}")
    public ResponseEntity<?> saveFile(@PathVariable Long id, MultipartFile file) throws IOException {
        fileService.saveFile(id, file);
        return ResponseEntity.status(201).build();
    }

    @PreAuthorize("hasAuthority('BUY')")
    @PostMapping("/download/{nameFile}")
    public ResponseEntity<?> download(HttpServletRequest request, HttpServletResponse response, @PathVariable String nameFile) throws Exception {
        fileService.downloadFile(request, response, nameFile);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/product/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getProduct(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(productServiceImp.getProductById(id)).build());
    }

    @DeleteMapping("/product/{id}")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Long id){
        productServiceImp.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/product")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> updateProduct(@RequestBody ProductUpdateDto productUpdateDto){
        return ResponseEntity.ok(ResponseDto.builder().data(productServiceImp.updateProduct(productUpdateDto)).build());
    }
}
