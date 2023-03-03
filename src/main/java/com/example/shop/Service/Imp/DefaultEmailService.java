package com.example.shop.Service.Imp;

import com.example.shop.DTO.email.ContextPage;
import com.example.shop.DTO.email.EmailContext;
import com.example.shop.Entity.User;
import com.example.shop.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultEmailService{


    public final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;
    private final UserRepository userRepository;


    public void sendSimpleEmail(EmailContext emailContext, String email) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(emailContext.getContext().stream().collect(Collectors.toMap(ContextPage::getKey, ContextPage::getValue)));
        String emailContent = templateEngine.process(emailContext.getTemplateLocation(), context);

        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject(emailContext.getSubject());
        mimeMessageHelper.setText(emailContent, true);
        emailSender.send(message);
    }

    public void sendMail(EmailContext email) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        if(email.getContext() != null && email.getContext().size() != 0) {
            context.setVariables(email.getContext().stream().collect(Collectors.toMap(ContextPage::getKey, ContextPage::getValue)));
        }
        String emailContent = templateEngine.process(email.getTemplateLocation(), context);

        mimeMessageHelper.setTo(userRepository.getUsersByMailingListIsTrue().stream().map(User::getEmail).toArray(String[]::new));
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setText(emailContent, true);
        emailSender.send(message);
    }
}
