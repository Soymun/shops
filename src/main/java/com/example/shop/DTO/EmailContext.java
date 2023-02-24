package com.example.shop.DTO;

import lombok.Data;

import java.util.Map;

@Data
public class EmailContext {

    private String from;
    private String subject;
    private String email;
    private String fromDisplayName;
    private String emailLanguage;
    private String displayName;
    private String templateLocation;
    private Map<String, Object> context;
}
