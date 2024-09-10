package com.juanabanto.test.service.impl;

import com.juanabanto.test.exception.CustomException;
import com.juanabanto.test.model.User;
import com.juanabanto.test.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveUserWithExistsEmail() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail("jmanuelabanto@gmail.com");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        CustomException thrown = assertThrows(CustomException.class, () -> {
            userService.saveUser(user);
        });

        assertEquals("El correo ya esta registrado.", thrown.getMessage());
    }

    @Test
    public void testSaveUserOK() {
        User user = new User();
        user.setEmail("jmanuelabanto@gmail.com");
        user.setName("Juan Abanto");
        user.setPassword("123456");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User newUser = userService.saveUser(user);

        assertNotNull(newUser);
        assertEquals("jmanuelabanto@gmail.com", newUser.getEmail());
        assertEquals("Juan Abanto", newUser.getName());

        verify(userRepository, times(1)).findByEmail(user.getEmail());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetAll() {
        User user1 = new User();
        User user2 = new User();
        List<User> users = List.of(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

}
