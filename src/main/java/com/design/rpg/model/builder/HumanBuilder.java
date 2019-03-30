package com.design.rpg.model.builder;

import com.design.rpg.model.HumanModel;

/**
 * Created by liying on 2019/3/31.
 */
public interface HumanBuilder {
    void buildAttributes();
    void buildRole();
    void buildCDMap();
    HumanModel createHuman();
}
