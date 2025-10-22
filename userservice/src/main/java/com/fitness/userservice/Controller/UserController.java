package com.fitness.userservice.Controller;


import com.fitness.userservice.Dto.UserDTO;
import com.fitness.userservice.Dto.UserResponse;
import com.fitness.userservice.Service.JWTService;
import com.fitness.userservice.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserDTO userDTO)
    {
       UserResponse userResponse = userService.registerUser(userDTO);

       return  new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/validate/{id}")
    public ResponseEntity<Boolean> validateUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.validateUser(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id)
    {
        UserResponse userResponse = userService.getUserById(id);
        return new ResponseEntity<>(userResponse,HttpStatus.OK);
    }
    
    @GetMapping("/users")
    public List<UserResponse> getAllUsers()
    {
        return  userService.getAllUsers();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserDTO userDTO) {
        // Simple validation (you can later integrate with DB)
        if ("arpit".equals(userDTO.getFirstName()) && "password".equals(userDTO.getPassword())) {
            String token = jwtService.generateToken(userDTO.getFirstName());
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials"));
        }
    }

}
