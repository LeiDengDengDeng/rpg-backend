package com.design.rpg.model.factory;

import com.design.rpg.vo.*;

/**
 * @author deng
 * @date 2019/03/30
 */
public final class StateInfoVOFactory {
    private StateInfoVOFactory(){}

    public static StateInfoVO createMoveStateInfoVO() {
        return new MoveStateInfoVO();
    }

    public static StateInfoVO createMonsterAttackStateInfoVO(int humanHPChange) {
        return new MonsterAttackStateInfoVO(humanHPChange);
    }

    public static StateInfoVO createHumanReviveStateInfoVO(int humanHPChange, int moneyChange) {
        return new HumanReviveStateInfoVO(humanHPChange, moneyChange);
    }

    public static StateInfoVO createHumanWinStateInfoVO(int humanHPChange, int monsterHPChange, int moneyChange, int expUp) {
        return new HumanWinStateInfoVO(humanHPChange, monsterHPChange, moneyChange, expUp);
    }

    public static StateInfoVO createHumanAttackStateInfoVO(int humanHPChange, int monsterHPChange) {
        return new HumanAttackStateInfoVO(humanHPChange, monsterHPChange);
    }

    public static StateInfoVO createWaitingStateInfoVO() {
        return new WaitingStateInfoVO();
    }
}
