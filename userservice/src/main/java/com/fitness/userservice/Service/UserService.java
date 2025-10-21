package com.fitness.userservice.Service;


import com.fitness.userservice.Dto.UserDTO;
import com.fitness.userservice.Dto.UserResponse;
import com.fitness.userservice.Entity.User;
import com.fitness.userservice.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserResponse registerUser(UserDTO userDTO) {

        log.info("Before Registering an User in Mysql DB");
        User user = userRepository.findByEmail(userDTO.getEmail());
        if(user!=null)
        {
            throw new RuntimeException("User is already registered!");
        }

        User userEntity = new User();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());



        User savedUser = userRepository.save(userEntity);

        log.info("After Registering an User in Mysql DB");

        return modelMapper.map(savedUser,UserResponse.class);


    }

    public Boolean validateUser(Long id) {
        log.info("Before Validating User");
        Optional<User> user = userRepository.findById(id);

        if(user.isPresent())
        {
            return true;
        }
        log.info("After validating User");
        return false;

    }

    public UserResponse getUserById(Long id) {

        log.info("Before getting User By Id");
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found! with this id,"+id));

        log.info("After getting User By Id");
        return modelMapper.map(user,UserResponse.class);
    }

    public List<UserResponse> getAllUsers() {
        log.info("Before getting All Users");
        List<User> users = userRepository.findAll();

        return users.stream().map((user)->{
            return modelMapper.map(user,UserResponse.class);
        }).collect(Collectors.toUnmodifiableList());
    }
}
