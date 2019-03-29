package com.design.rpg.model.stragety;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

/**
 * Created by liying on 2019/3/30.
 */
public interface MonsterGenerateStrategy {
    MonsterModel createMonster(HumanModel humanModel);
}
