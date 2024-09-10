package com.juanabanto.test.request;

import com.juanabanto.test.validator.EmailValidator;
import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    private String name;
    @EmailValidator
    private String email;
    private String password;
    private List<PhoneRequest> phones;
}
