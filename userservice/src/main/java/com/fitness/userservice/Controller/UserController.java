package com.fitness.userservice.Controller;


import com.fitness.userservice.Dto.UserDTO;
import com.fitness.userservice.Dto.UserResponse;
import com.fitness.userservice.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

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

}
