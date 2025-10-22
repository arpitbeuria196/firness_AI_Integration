package com.fitness.activity_AI.Service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AI_Client_Service {

    Client client = Client.builder().apiKey("AIzaSyDX6JeHBaYouT_5LdiOHfmZMk-3hBv__s8").build();

    public String recommendations(String activitySummary)
    {
        String question = "You are a personal fitness assistant. " +
                "Based on the following activity details, suggest the next best workout or recovery step:\n\n"
                + activitySummary;

        GenerateContentResponse response = client.models.generateContent(
                "gemini-2.5-flash",
                question,null
        );

        System.out.println(response.text());
        return response.text();
    }


}
