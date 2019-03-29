package com.design.rpg.websocket;

import lombok.extern.apachecommons.CommonsLog;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author deng
 * @date 2019/03/28
 */
@ServerEndpoint("/websocket/{userId}")
@Component
@CommonsLog
public class WebSocketServer {
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;
    private String userId;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        webSocketSet.add(this);
        log.info("有新窗口开始监听:" + userId + ",当前在线人数为" + webSocketSet.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        log.info("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }


    public void sendMessage(String message) {
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendObject(String userId, Object object) {
        sendMsg(userId, new JSONObject(object).toString());
    }

    public static void sendMsg(String userId, String message) {
        for (WebSocketServer item : webSocketSet) {
            if (userId == null) {
                item.sendMessage(message);
            } else if (item.userId.equals(userId)) {
                item.sendMessage(message);
            }
        }
    }
}
