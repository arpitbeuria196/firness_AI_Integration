package com.fitness.activity_AI.repository;

import com.fitness.activity_AI.Entity.AI_Recommend;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityAIRepository extends MongoRepository<AI_Recommend,String> {

}
