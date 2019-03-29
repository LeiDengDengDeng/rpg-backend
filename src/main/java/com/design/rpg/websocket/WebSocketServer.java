package com.design.rpg.websocket;

import com.design.rpg.vo.InfoVO;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

import javax.websocket.EncodeException;
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
@ServerEndpoint("/websocket")
@Component
@CommonsLog
public class WebSocketServer {
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        log.info("有新窗口开始监听:" + sid + ",当前在线人数为" + webSocketSet.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        log.info("有一连接关闭！当前在线人数为" + webSocketSet.size());
    }

    public void sendObject(Object object) throws IOException, EncodeException {
//        this.session.getBasicRemote().sendText(new JSONPObject());
    }

    public void sendMessage(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

    public static void sendMsg(String msg) {
        for (WebSocketServer item : webSocketSet) {
            try {
                if (msg.equals("obj")) {
                    item.sendObject(new InfoVO());
                } else {
                    item.sendMessage(msg);
                }
            } catch (IOException e) {
                continue;
            } catch (EncodeException e) {
                e.printStackTrace();
            }
        }
    }
}
