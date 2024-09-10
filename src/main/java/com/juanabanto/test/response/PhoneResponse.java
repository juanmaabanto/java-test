package com.juanabanto.test.response;

import lombok.Data;

@Data
public class PhoneResponse {
    private String number;
    private String cityCode;
    private String countryCode;
}
