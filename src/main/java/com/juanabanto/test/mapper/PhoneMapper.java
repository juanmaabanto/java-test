package com.juanabanto.test.mapper;

import com.juanabanto.test.model.Phone;
import com.juanabanto.test.request.PhoneRequest;
import com.juanabanto.test.response.PhoneResponse;

public class PhoneMapper {
    public static Phone mapToEntity(PhoneRequest request) {
        Phone phone = new Phone();
        phone.setNumber(request.getNumber());
        phone.setCityCode(request.getCityCode());
        phone.setCountryCode(request.getCountryCode());
        return phone;
    }

    public static PhoneResponse mapToResponse(Phone phone) {
        PhoneResponse response = new PhoneResponse();
        response.setNumber(phone.getNumber());
        response.setCityCode(phone.getCityCode());
        response.setCountryCode(phone.getCountryCode());
        return response;
    }
}
