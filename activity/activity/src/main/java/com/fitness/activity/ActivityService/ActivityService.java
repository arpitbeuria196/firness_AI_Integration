package com.fitness.activity.ActivityService;


import com.fitness.activity.Config.UserClient;
import com.fitness.activity.Dto.ActivityDTO;
import com.fitness.activity.Dto.ActivityResponse;
import com.fitness.activity.Entity.Activity;
import com.fitness.activity.Repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    public final ModelMapper modelMapper;

    private final ActivityRepository activityRepository;

    private final UserClient userClient;

    private final KafkaTemplate<String,ActivityResponse> kafkaTemplate;


    public ActivityResponse createActivity(ActivityDTO activityDTO) {

        boolean userExists = userClient.validateUser(activityDTO.getUserId());

        if (!userExists) {
            throw new RuntimeException("User not found with ID: " + activityDTO.getUserId());
        }

        Activity activity = modelMapper.map(activityDTO, Activity.class);
        activity.setCreatedAt(LocalDateTime.now());
        activity.setUpdatedAt(LocalDateTime.now());

        Activity saved = activityRepository.save(activity);
        ActivityResponse activityResponse =  modelMapper.map(saved, ActivityResponse.class);

        kafkaTemplate.send("activity-topic",activityResponse);
        System.out.println("Activity event published to Kafka: " + activityResponse.getType());
        return  activityResponse;


    }
    public ActivityResponse getActivityById(String id) {
        Activity activity = activityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Activity not found with ID: " + id));
        return modelMapper.map(activity, ActivityResponse.class);
    }

    public List<ActivityResponse> getAllActivity() {
        List<Activity> activities = activityRepository.findAll();
        return activities.stream()
                .map(activity -> modelMapper.map(activity, ActivityResponse.class))
                .collect(Collectors.toList());
    }
}
