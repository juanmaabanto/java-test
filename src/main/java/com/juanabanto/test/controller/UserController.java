package com.juanabanto.test.controller;

import com.juanabanto.test.mapper.UserMapper;
import com.juanabanto.test.model.User;
import com.juanabanto.test.request.UserRequest;
import com.juanabanto.test.response.UserResponse;
import com.juanabanto.test.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Registrar un nuevo usuario", description = "Permite registrar un nuevo usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Un error de negocio"),
            @ApiResponse(responseCode = "500", description = "Un error de excepción")
    })
    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody UserRequest request) {

        User user = userService.saveUser(UserMapper.mapToEntity(request));
        return new ResponseEntity<>(UserMapper.mapToResponse(user), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene una lista de todos los usuarios registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"),
            @ApiResponse(responseCode = "500", description = "Un error de excepción")
    })
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List<User> users = userService.getAll();
        List<UserResponse> responses = users.stream().map(UserMapper::mapToResponse).collect(Collectors.toList());

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Operation(summary = "Buscar usuario por email", description = "Busca un usuario por su email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Un error de excepción")
    })
    @GetMapping("/{email}")
    public ResponseEntity<UserResponse> findByEmail(
            @Parameter(description = "Email del usuario", required = true) @PathVariable String email) {
        Optional<User> user = userService.findByEmail(email);

        if (user.isPresent()) {
            UserResponse response = UserMapper.mapToResponse(user.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
