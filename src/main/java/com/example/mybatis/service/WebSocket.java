package com.example.mybatis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket")
@Slf4j
public class WebSocket {

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        log.info("有新的链接加入={}", webSocketSet.size());
    }

    @OnClose
    public void opClose(Session session) {
        webSocketSet.remove(this);
        log.info("webSocketSet断开链接,总数={}", webSocketSet.size());
    }

    @OnMessage
    public void opMessage(String message, Session session) {
        log.info("来自客户端的信息={}", message);
    }

    //广播
    public void sendMessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {

            }
        }
    }
}
