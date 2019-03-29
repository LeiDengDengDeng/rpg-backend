package com.design.rpg.controller;

import com.design.rpg.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author deng
 * @date 2019/03/28
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private WebSocketServer webSocketServer;

    //推送数据接口
    @RequestMapping("/socket/push")
    public String pushToWeb(@RequestParam String message) {
        WebSocketServer.sendMsg(message);
        return "ok";
    }
}
