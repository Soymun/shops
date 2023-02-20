package com.example.shop.Facade;

import com.example.shop.DTO.Product.ProductDTO;
import com.example.shop.DTO.TypeOfFood.TypeOfFoodDto;
import com.example.shop.Mappers.ProductsMapper;
import com.example.shop.Mappers.TypeOfFoodMapper;
import com.example.shop.Service.Imp.ProductServiceImp;
import com.example.shop.Service.Imp.TypeOfFoodServiceImpl;
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
import java.util.Objects;

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
        String mimeType = Objects.requireNonNull(file.getContentType()).split("/")[1];
        if(mimeType.equals("png") || mimeType.equals("jpeg")) {
            if (file1.createNewFile()) {
                BufferedOutputStream writer =new BufferedOutputStream(Files.newOutputStream(Paths.get(file1.getAbsolutePath())));
                writer.write(file.getBytes());
                writer.flush();
                writer.close();
                ProductDTO product = productServiceImp.getProductById(id);
                product.setUrlToPngFile(file.getOriginalFilename());
                productServiceImp.updateProduct(productsMapper.productDtoToProduct(product));
            } else {
                BufferedOutputStream writer =new BufferedOutputStream(Files.newOutputStream(Paths.get(file1.getAbsolutePath())));
                writer.write(file.getBytes());
                writer.flush();
                writer.close();
            }
        }
    }

    public void saveFileTypeOfFood(Long id, MultipartFile file) throws IOException {
        File file1 = new File(url + file.getOriginalFilename());
        String mimeType = Objects.requireNonNull(file.getContentType()).split("/")[1];
        if(mimeType.equals("png") || mimeType.equals("jpeg")) {
            if (file1.createNewFile()) {
                BufferedOutputStream writer =new BufferedOutputStream(Files.newOutputStream(Paths.get(file1.getAbsolutePath())));
                writer.write(file.getBytes());
                writer.flush();
                writer.close();
                TypeOfFoodDto product = typeOfFoodService.getTypeOfFoodById(id);
                product.setUrlToPhoto(file.getOriginalFilename());
                typeOfFoodService.updateTypeOfFood(typeOfFoodMapper.typeOfFoodToTypeOfFoodUpdateDto(product));
            } else {
                BufferedOutputStream writer =new BufferedOutputStream(Files.newOutputStream(Paths.get(file1.getAbsolutePath())));
                writer.write(file.getBytes());
                writer.flush();
                writer.close();
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
