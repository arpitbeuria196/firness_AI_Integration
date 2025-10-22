package com.fitness.activity_AI.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collation = "ai_Remommend")
public class AI_Recommend {

    @Id
    private String id;
    private String activityId;
    private String userId;  // optional, for personalization
    private String activitySummary;
    private String aiRecommendation;
    private LocalDateTime createdAt;
}
