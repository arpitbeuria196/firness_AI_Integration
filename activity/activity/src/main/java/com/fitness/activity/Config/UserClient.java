package com.fitness.activity.Config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userservice", url = "http://localhost:8081/api/user")
public interface UserClient {


    @GetMapping("/validate/{id}")
    boolean validateUser(@PathVariable("id") Long id);
}
