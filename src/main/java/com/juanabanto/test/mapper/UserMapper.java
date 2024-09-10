package com.juanabanto.test.mapper;

import com.juanabanto.test.model.Phone;
import com.juanabanto.test.model.User;
import com.juanabanto.test.request.UserRequest;
import com.juanabanto.test.response.PhoneResponse;
import com.juanabanto.test.response.UserResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserMapper {
    public static User mapToEntity(UserRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setCreated(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(UUID.randomUUID().toString());
        user.setActive(true);

        if (request.getPhones() != null) {
            List<Phone> phones = request.getPhones().stream()
                    .map(PhoneMapper::mapToEntity)
                    .collect(Collectors.toList());
            user.setPhones(phones);
        }

        return user;
    }

    public static UserResponse mapToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setCreated(user.getCreated());
        response.setModified(user.getModified());
        response.setLastLogin(user.getLastLogin());
        response.setToken(user.getToken());
        response.setActive(user.isActive());

        if (user.getPhones() != null) {
            List<PhoneResponse> phones = user.getPhones().stream()
                    .map(PhoneMapper::mapToResponse)
                    .collect(Collectors.toList());
            response.setPhones(phones);
        }

        return response;
    }
}
