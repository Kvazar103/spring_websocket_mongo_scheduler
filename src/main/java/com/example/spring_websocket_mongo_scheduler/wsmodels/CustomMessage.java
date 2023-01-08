package com.example.spring_websocket_mongo_scheduler.wsmodels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomMessage {
    @Id
    private String id;
    private String sessionId;
    private String payload;

    public CustomMessage(String sessionId, String payload) {
        this.sessionId = sessionId;
        this.payload = payload;
    }
}
