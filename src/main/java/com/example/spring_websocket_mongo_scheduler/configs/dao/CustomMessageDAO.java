package com.example.spring_websocket_mongo_scheduler.configs.dao;

import com.example.spring_websocket_mongo_scheduler.wsmodels.CustomMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomMessageDAO extends MongoRepository<CustomMessage,String> {
    //MongoRepository таке саме які і JpaRepository тільки для монго
}
