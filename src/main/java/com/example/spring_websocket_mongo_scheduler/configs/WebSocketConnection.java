package com.example.spring_websocket_mongo_scheduler.configs;

import com.example.spring_websocket_mongo_scheduler.configs.dao.CustomMessageDAO;
import com.example.spring_websocket_mongo_scheduler.wsmodels.CustomMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;

public class WebSocketConnection extends TextWebSocketHandler {
    //Цей клас позволяє хендлити текстові повідомленння

    private CustomMessageDAO customMessageDAO;

    public WebSocketConnection(CustomMessageDAO customMessageDAO) {
        this.customMessageDAO = customMessageDAO;
    }

    private Map<String,WebSocketSession> sessionMap=new HashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("connection done "+ session.getId());//можем дивитися що є конекшин по певні сесії
        sessionMap.put(session.getId(),session);
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("connection close "+session.getId());//бачить хто відключається
        //видаляєм зайві сесії з нашого масиву
        Set<Map.Entry<String, WebSocketSession>> entries = sessionMap.entrySet();
        Iterator<Map.Entry<String,WebSocketSession>> iterator=entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,WebSocketSession> next=iterator.next();
            if(next.getKey().equals(session.getId())){
                iterator.remove();
            }
        }
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {//для того, щоб обробляти повідомлення
        System.out.println(message.getPayload());//відхоплюєм те що нам прийшло для того щоб подивитися
        customMessageDAO.save(new CustomMessage(session.getId(), message.getPayload()));//зберігаємо повідомлення які приходять
        sessionMap.forEach((s, webSocketSession) -> { //s - це ключ ітеруємого обєкта в мапі а webSocketSession це значення
            try {
                webSocketSession.sendMessage(new TextMessage(message.getPayload()+" "+new Date(System.currentTimeMillis())));//відправляє повідомлення всім підписникам чату
            } catch (IOException e) {
                System.out.println("session "+session.getId()+" already closed ");
            }
        });
    }
}
