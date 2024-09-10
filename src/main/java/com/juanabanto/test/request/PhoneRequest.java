package com.juanabanto.test.request;

import lombok.Data;

@Data
public class PhoneRequest {
    private String number;
    private String cityCode;
    private String countryCode;
}
