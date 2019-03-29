package com.design.rpg.vo;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * websocket上绑定的实时信息
 *
 * @author deng
 * @date 2019/03/28
 */
@AllArgsConstructor
@Getter
public class InfoVO {
    private StateInfoVO stateInfoVO;
    private HumanModel humanModel;
    private MonsterModel monsterModel;
}
