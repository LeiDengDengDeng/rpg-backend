package com.design.rpg.model.command;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

/**
 * @author deng
 * @date 2019/03/27
 */
public interface HumanATKCommand {
    /**
     * 计算攻击杀伤力
     *
     * @return 攻击值
     */
    void calculateATK(HumanModel humanModel, MonsterModel monsterModel);

    String getName();

    String getDescription();

    int getCD();

    char getBindKey();
}
