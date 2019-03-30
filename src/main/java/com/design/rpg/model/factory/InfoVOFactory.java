package com.design.rpg.model.factory;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;
import com.design.rpg.vo.InfoVO;
import com.design.rpg.vo.StateInfoVO;

/**
 * @author deng
 * @date 2019/03/30
 */
public final class InfoVOFactory {
    private InfoVOFactory() {
    }

    public static InfoVO createInfoVO(StateInfoVO stateInfoVO, HumanModel humanModel, MonsterModel monsterModel) {
        return new InfoVO(stateInfoVO, humanModel, monsterModel);
    }
}
