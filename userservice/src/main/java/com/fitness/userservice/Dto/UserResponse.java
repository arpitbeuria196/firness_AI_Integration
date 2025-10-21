package com.fitness.userservice.Dto;


import com.fitness.userservice.Enum.UserRole;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserResponse {

    private Long id;

    private String email;
    private String firstName;
    private String lastName;

    private UserRole userRole;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
