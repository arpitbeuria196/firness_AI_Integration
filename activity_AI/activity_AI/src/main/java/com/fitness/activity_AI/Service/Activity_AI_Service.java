package com.fitness.activity_AI.Service;

import com.fitness.activity_AI.Entity.AI_Recommend;
import com.fitness.activity_AI.Entity.ActivityResponse;
import com.fitness.activity_AI.repository.ActivityAIRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class Activity_AI_Service {

    private final AI_Client_Service aiClientService;

    private final ActivityAIRepository activityAIRepository;

    @KafkaListener(topics = "activity-topic",groupId = "activity-ai-group")
    public void consumerSctivity(ActivityResponse activityResponse)
    {
        log.info("🎯 Received activity from Kafka: {}", activityResponse);

        String activitySummary = String.format(
                "User performed %s for %d minutes, burned %d calories",
                activityResponse.getType(),
                activityResponse.getDuration(),
                activityResponse.getCaloriesBurned()
        );

        String suggestions = aiClientService.recommendations(activitySummary);
        AI_Recommend aiRecommend = new AI_Recommend();

        aiRecommend.setAiRecommendation(suggestions);
        aiRecommend.setActivityId(activityResponse.getId());
        aiRecommend.setUserId(activityResponse.getUserId());
        aiRecommend.setActivitySummary(activitySummary);
        aiRecommend.setCreatedAt(LocalDateTime.now());


        activityAIRepository.save(aiRecommend);


        System.out.println("💡 AI Suggestion: " + suggestions);
    }

    public Optional<AI_Recommend> getRecommendById(Long id) {
        return activityAIRepository.findById(String.valueOf(id));
    }

    public List<AI_Recommend> getAll() {
        return activityAIRepository.findAll();
    }
}
