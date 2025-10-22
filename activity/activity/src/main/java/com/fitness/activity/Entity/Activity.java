package com.fitness.activity.Entity;

import org.springframework.data.mongodb.core.mapping.Document;


@Document("activities")
public class Activity {

    private Long id;
    private Long userId;
    private ActivityType type;
}
