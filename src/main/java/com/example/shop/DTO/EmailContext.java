package com.example.shop.DTO;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class EmailContext {

    private String subject;
    private String fromDisplayName;
    private String emailLanguage;
    private String displayName;
    private String templateLocation;
    private List<ContextPage> context;
}
