package com.example.shop.Controllers;

import com.example.shop.DTO.TypeOfFood.TypeOfFoodCreateDto;
import com.example.shop.DTO.TypeOfFood.TypeOfFoodUpdateDto;
import com.example.shop.Facade.FileFacade;
import com.example.shop.Response.ResponseDto;
import com.example.shop.Service.Imp.TypeOfFoodServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class TypeOfFoodController {

    private final TypeOfFoodServiceImpl typeOfFoodService;

    private final FileFacade fileFacade;


    @PostMapping("/type/food")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> createTypeOfFood(@RequestBody TypeOfFoodCreateDto typeOfFoodCreateDto){
        return ResponseEntity.ok(ResponseDto.builder().data(typeOfFoodService.createTypeOfFood(typeOfFoodCreateDto)).build());
    }

    @PostMapping("/type/food/file/{id}")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> saveFile(@PathVariable Long id, MultipartFile file) throws IOException {
        fileFacade.saveFileTypeOfFood(id, file);
        return ResponseEntity.status(201).build();
    }


    @GetMapping("/type/food/{id}")
    public ResponseEntity<?> getTypeOfFoodById(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(typeOfFoodService.getTypeOfFoodById(id)).build());
    }

    @PatchMapping("/type/food")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> updateTypeOfFood(@RequestBody TypeOfFoodUpdateDto typeOfFoodCreateDto){
        return ResponseEntity.ok(ResponseDto.builder().data(typeOfFoodService.updateTypeOfFood(typeOfFoodCreateDto)).build());
    }

    @DeleteMapping("/type/food/{id}")
    @PreAuthorize("hasAuthority('SELL')")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        typeOfFoodService.deleteTypeOfFoodById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/food/root/{id}")
    public ResponseEntity<?> getTypeOfIdByRoot(@PathVariable Long id){
        return ResponseEntity.ok(ResponseDto.builder().data(typeOfFoodService.getSubTypeOfFoodByRootIdTypeOfFood(id)).build());
    }

    @GetMapping("/type/food/root")
    public ResponseEntity<?> getTypeOfFoodRoot(){
        return ResponseEntity.ok(ResponseDto.builder().data(typeOfFoodService.getRootTypeOfFood()).build());
    }
}
