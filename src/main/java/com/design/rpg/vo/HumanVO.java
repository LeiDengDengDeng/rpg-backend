package com.design.rpg.vo;

import lombok.Data;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.Serializable;

/**
 * @author deng
 * @date 2019/03/04
 */
@Data
public class HumanVO implements Encoder {
    // 基本信息
    // 背包信息

    private int HP;

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }


}
