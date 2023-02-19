package com.example.shop.Response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ResponseDto {

    private Object data;

    private String error;
}
