package com.design.rpg.model.command;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

import java.util.Map;

/**
 * Created by liying on 2019/3/28.
 */
public class DoctorSecondSkillATKCommand implements HumanATKCommand {

    /**
     * 魔法攻击
     * @param humanModel
     * @param monsterModel
     * @return
     */
    @Override
    public void calculateATK(HumanModel humanModel, MonsterModel monsterModel) {
        Map<Class,Integer> cdMap=humanModel.getMyCDMap();
        //若还在cd
        if(cdMap.get(this.getClass())>0){return;}

        int attack=(int)(humanModel.getATK()+humanModel.getSpirit()*0.6);
        int defence=monsterModel.getDEF();
        int hurt=attack>defence?attack-defence:0;
        monsterModel.setHP(monsterModel.getHP()-hurt);
        //当前cd设为cd值
        cdMap.replace(this.getClass(),humanModel.getCdMap().getOrDefault(this.getClass(),4)+1);
        //其余cd都-1
        cdMap.replaceAll((k,v)->v==0?v:v-1);

    }
}
