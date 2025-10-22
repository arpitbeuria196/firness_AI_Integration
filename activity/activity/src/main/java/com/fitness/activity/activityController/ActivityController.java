package com.fitness.activity.activityController;


import com.fitness.activity.ActivityService.ActivityService;
import com.fitness.activity.Dto.ActivityDTO;
import com.fitness.activity.Dto.ActivityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;


    @PostMapping("/create")
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody ActivityDTO activityDTO)
    {
       ActivityResponse activityResponse = activityService.createActivity(activityDTO);

       return new ResponseEntity<>(activityResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable String id)
    {
        ActivityResponse activityResponse = activityService.getActivityById(id);

        return new ResponseEntity<>(activityResponse,HttpStatus.OK);
    }

    @GetMapping
    public List<ActivityResponse> getAllActivity()
    {
        List<ActivityResponse> activities = activityService.getAllActivity();

        return  activities;
    }

}
