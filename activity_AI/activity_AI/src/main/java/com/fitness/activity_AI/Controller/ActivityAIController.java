package com.fitness.activity_AI.Controller;


import com.fitness.activity_AI.Entity.AI_Recommend;
import com.fitness.activity_AI.Service.Activity_AI_Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class ActivityAIController {

    private final Activity_AI_Service activityAiService;

    @GetMapping("/recommend/id")
    public ResponseEntity<Optional<AI_Recommend>> getRecommendById(@PathVariable Long id)
    {
       Optional<AI_Recommend> aiRecommend = activityAiService.getRecommendById(id);
        return new ResponseEntity<>(aiRecommend, HttpStatus.OK);
    }

    @GetMapping("/recommend")
    public List<AI_Recommend> getAll()
    {
        return activityAiService.getAll();
    }
}
