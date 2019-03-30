package com.design.rpg.model.adapter;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

/**
 * @author deng
 * @date 2019/03/31
 */
public class MonsterAdapter extends MonsterModel {
    private HumanModel humanModel;

    public MonsterAdapter(HumanModel humanModel) {
        this.humanModel = humanModel;
    }

    @Override
    public void attack(HumanModel humanModel) {
        int dmg = this.humanModel.getATK() - humanModel.getDEF() > 0 ? this.humanModel.getATK() - humanModel.getDEF() : 0;
        humanModel.setHP(humanModel.getHP() - dmg);
    }
}
