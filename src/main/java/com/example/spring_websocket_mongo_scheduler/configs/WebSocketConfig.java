package com.example.spring_websocket_mongo_scheduler.configs;

import com.example.spring_websocket_mongo_scheduler.configs.dao.CustomMessageDAO;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

//https://github.com/Kvazar103/react-project/tree/JAVA_delete_websocket  !!!!frontend app
@Configuration //для бінів
@EnableWebSocket //робить пул для сокетів, щоб їх впроваджувати
@AllArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {//зробили конфігураційний клас

    private CustomMessageDAO customMessageDAO;
    @Bean //будуємо бін для того що впровадити в webSocketHandler
    public WebSocketConnection webSocketConnection(){ //тут ми кажемо який тип буде обробляти наші повідомлення
        return new WebSocketConnection(customMessageDAO);
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //в цьому методі ми повинні зареєструвати клас яка буде відповідати за проходження повідомлень
        // (і на яку саме урлу він буде реагувати)
        registry.addHandler(webSocketConnection(),"/chat")//повідомлення буде оброблять коли будуть підписуватися на урлу /chat
                //за замовчуванням вебсокети доступні лише на одному хості і порті
                .setAllowedOrigins("*");//це позволяє щоб було доступно все

    }
}
