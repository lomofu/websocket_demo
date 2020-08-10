package com.example.websocket.ws;

import com.alibaba.fastjson.JSON;
import com.example.websocket.dto.ClientDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint("/websocket/{id}")
public class WebSocketServer {
    private static final AtomicInteger atomicInteger = new AtomicInteger();
    private static final ConcurrentHashMap<String, Session> concurrentHashMap = new ConcurrentHashMap<>();
    private final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    private static void addUser() {
        atomicInteger.incrementAndGet();
    }

    private static int getUser() {
        return atomicInteger.get();
    }

    private static void subUser() {
        atomicInteger.decrementAndGet();
    }

    private static void broadcast(String message) {
        concurrentHashMap.forEach(
                (k, v) -> {
                    try {
                        sendMessage(v, message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static void p2p(String id, String message) throws IOException {
        sendMessage(concurrentHashMap.get(id), message);

    }

    public synchronized static void sendMessage(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    @OnOpen
    public void OnOpen(Session session, @PathParam("id") String id) throws IOException {
        addUser();
        concurrentHashMap.put(id, session);
        log.info("new client is coming {},now have {} user", id, getUser());
        sendMessage(session, UUID.randomUUID().toString());
    }

    @OnMessage
    public void OnMessage(String message) throws IOException {
        ClientDto clientDto = Optional.ofNullable(JSON.parseObject(message,ClientDto.class))
                .orElseThrow(() -> new RuntimeException("null objeceadt"));
        if (clientDto.isBroadcast()) {
            broadcast(clientDto.getMessage());
        } else {
            p2p(clientDto.getSentToUserId(), clientDto.getMessage());
        }
    }

    @OnClose
    public void OnClose(Session session, @PathParam("id") String id) {
        subUser();
        log.info("the connect is close,{} is close u", id);
    }
}
