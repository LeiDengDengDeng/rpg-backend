package com.design.rpg.vo.factory;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;
import com.design.rpg.util.Converter;
import com.design.rpg.vo.HumanVO;
import com.design.rpg.vo.InfoVO;
import com.design.rpg.vo.MonsterVO;
import com.design.rpg.vo.StateInfoVO;

/**
 * @author deng
 * @date 2019/03/30
 */
public final class InfoVOFactory {
    private InfoVOFactory() {
    }

    public static InfoVO createInfoVO(StateInfoVO stateInfoVO, HumanModel humanModel, MonsterModel monsterModel) {
        HumanVO humanVO = (HumanVO) Converter.map(humanModel, HumanVO.class);
        humanVO.setCurLevelMaxExp(humanModel.getLevelExp());
        return new InfoVO(stateInfoVO, humanVO, (MonsterVO) Converter.map(monsterModel, MonsterVO.class));
    }
}
