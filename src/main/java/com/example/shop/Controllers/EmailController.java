package com.example.shop.Controllers;

import com.example.shop.DTO.email.EmailContext;
import com.example.shop.Facade.FileFacade;
import com.example.shop.Response.ResponseDto;
import com.example.shop.Service.Imp.DefaultEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.IOException;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins="http://localhost:3000")
@RequiredArgsConstructor
public class EmailController {

    private final FileFacade fileFacade;
    private final DefaultEmailService defaultEmailService;

    @GetMapping(value = "/send")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> sendEmailAttachment(@RequestBody EmailContext email) throws MessagingException {

        defaultEmailService.sendMail(email);

        return ResponseEntity.status(201).build();
    }

    @PostMapping("/file")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> saveFile(MultipartFile file) throws IOException {
        fileFacade.saveThymeleaf(file);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/file")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getFiles(){
        return ResponseEntity.ok(ResponseDto.builder().data(fileFacade.getThymeleafList()).build());
    }
}
