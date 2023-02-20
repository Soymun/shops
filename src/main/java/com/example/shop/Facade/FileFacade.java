package com.example.shop.Facade;

import com.example.shop.DTO.Product.ProductDTO;
import com.example.shop.DTO.TypeOfFood.TypeOfFoodDto;
import com.example.shop.Mappers.ProductsMapper;
import com.example.shop.Mappers.TypeOfFoodMapper;
import com.example.shop.Service.Imp.ProductServiceImp;
import com.example.shop.Service.Imp.TypeOfFoodServiceImpl;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class FileFacade {

    @Value("${file.root}")
    private String url;

    private final ProductServiceImp productServiceImp;

    private final TypeOfFoodServiceImpl typeOfFoodService;

    private final TypeOfFoodMapper typeOfFoodMapper;

    private final ProductsMapper productsMapper;

    @Autowired
    public FileFacade(ProductServiceImp productServiceImp, TypeOfFoodServiceImpl typeOfFoodService, TypeOfFoodMapper typeOfFoodMapper, ProductsMapper productsMapper) {
        this.productServiceImp = productServiceImp;
        this.typeOfFoodService = typeOfFoodService;
        this.typeOfFoodMapper = typeOfFoodMapper;
        this.productsMapper = productsMapper;
    }

    public void saveFile(Long id, MultipartFile file) throws IOException {
        File file1 = new File(url + file.getOriginalFilename());
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType.equals("png") || mimeType.equals("jpql")) {
            if (file1.createNewFile()) {
                file.transferTo(file1);
                ProductDTO product = productServiceImp.getProductById(id);
                product.setUrlToPngFile(file1.getPath());
                productServiceImp.updateProduct(productsMapper.productDtoToProduct(product));
            } else {
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(url + file.getOriginalFilename()));
                writer.write("");
                writer.flush();
                file.transferTo(file1);
            }
        }
    }

    public void saveFileTypeOfFood(Long id, MultipartFile file) throws IOException {
        File file1 = new File(url + file.getOriginalFilename());
        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType.equals("png") || mimeType.equals("jpql")) {
            if (file1.createNewFile()) {
                file.transferTo(file1);
                TypeOfFoodDto product = typeOfFoodService.getTypeOfFoodById(id);
                product.setUrlToPhoto(file1.getPath());
                typeOfFoodService.updateTypeOfFood(typeOfFoodMapper.typeOfFoodToTypeOfFoodUpdateDto(product));
            } else {
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(url + file.getOriginalFilename()));
                writer.write("");
                writer.flush();
                file.transferTo(file1);
            }
        }
    }

    public void downloadFile(HttpServletRequest request, HttpServletResponse response, String nameFile) throws Exception {
            File file = new File(url + nameFile);
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            response.setContentType(mimeType);

            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

            response.setContentLength((int) file.length());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
}
