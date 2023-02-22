package com.example.shop.Controllers;

import com.example.shop.DTO.Product.ProductCreateDto;
import com.example.shop.DTO.Product.ProductUpdateDto;
import com.example.shop.Response.ResponseDto;
import com.example.shop.Facade.FileFacade;
import com.example.shop.Service.Imp.ProductServiceImp;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Метод для создания продукта.", notes = "Могут пользоваться продавцы.Возвращает id продукта")
    @PreAuthorize("hasAuthority('SELL')")
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody ProductCreateDto productDTO){
        return ResponseEntity.ok(ResponseDto.builder().data(productServiceImp.save(productDTO)).build());
    }

    @ApiOperation(value = "Метод для получения продукта по type_of_food id.", notes = "Могут пользоваться все.Возвращает list ProductDto")
    @PreAuthorize("hasAuthority('BUY')")
    @GetMapping("/type/food/product/{id}")
    public ResponseEntity<?> getByTypeOfFoodId(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(productServiceImp.getProductByTypeOfFood(id)).build());
    }

    @ApiOperation(value = "Метод для сохранения/обнавления фотографии у продукта.", notes = "Могут пользоваться продавцы.Возвращает код 201")
    @PreAuthorize("hasAuthority('SELL')")
    @PostMapping("/file/{id}")
    public ResponseEntity<?> saveFile(@PathVariable Long id, MultipartFile file) throws IOException {
        fileService.saveFile(id, file);
        return ResponseEntity.status(201).build();
    }

    @ApiOperation(value = "Метод для получения фотографии.", notes = "Могут пользоваться все.Возвращает фото")
    @PreAuthorize("hasAuthority('BUY')")
    @PostMapping("/download/{nameFile}")
    public ResponseEntity<?> download(HttpServletRequest request, HttpServletResponse response, @PathVariable String nameFile) throws Exception {
        fileService.downloadFile(request, response, nameFile);
        return ResponseEntity.status(201).build();
    }

    @ApiOperation(value = "Метод для получения продукта по id.", notes = "Могут пользоваться все.Возвращает ProductDto")
    @GetMapping("/product/{id}")
    @PreAuthorize("hasAuthority('BUY')")
    public ResponseEntity<?> getProduct(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(productServiceImp.getProductById(id)).build());
    }

    @ApiOperation(value = "Метод для удаления продукта по id.", notes = "Могут пользоваться продавцы.Возвращает код 204")
    @DeleteMapping("/product/{id}")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> deleteProduct(@PathVariable(name = "id") Long id){
        productServiceImp.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Метод для изменения продукта по id.", notes = "Могут пользоваться продавцы.Возвращает код ProductDto")
    @PatchMapping("/product")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> updateProduct(@RequestBody ProductUpdateDto productUpdateDto){
        return ResponseEntity.ok(ResponseDto.builder().data(productServiceImp.updateProduct(productUpdateDto)).build());
    }
}
