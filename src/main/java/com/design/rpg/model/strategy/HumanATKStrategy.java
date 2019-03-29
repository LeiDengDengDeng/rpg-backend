package com.design.rpg.model.strategy;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

/**
 * @author deng
 * @date 2019/03/27
 */
public interface HumanATKStrategy {
    /**
     * 计算攻击杀伤力
     *
     * @return 攻击值
     */
    void calculateATK(HumanModel humanModel, MonsterModel monsterModel);
}
