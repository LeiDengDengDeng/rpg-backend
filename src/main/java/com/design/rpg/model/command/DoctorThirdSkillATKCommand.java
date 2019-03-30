package com.design.rpg.model.command;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

import java.util.Map;

/**
 * Created by liying on 2019/3/28.
 */
public class DoctorThirdSkillATKCommand implements HumanATKCommand {

    /**
     * 部分魔法攻击加轻微治疗(吸血)
     * @param humanModel
     * @param monsterModel
     * @return
     */
    @Override
    public void calculateATK(HumanModel humanModel, MonsterModel monsterModel) {

        Map<Class,Integer> cdMap=humanModel.getMyCDMap();
        //若还在cd
        if(cdMap.get(this.getClass())>0){return;}

        //攻击
        int attack=(int)(humanModel.getATK()+humanModel.getSpirit()*0.25);
        int defence=monsterModel.getDEF();
        int hurt=attack>defence?attack-defence:0;
        monsterModel.setHP(monsterModel.getHP()-hurt);
        //自愈
        int HP=(int)(humanModel.getMaxHP()*0.1)+humanModel.getHP();
        if(HP>=humanModel.getMaxHP()){
            humanModel.setHP(humanModel.getMaxHP());
        }else{
            humanModel.setHP(HP);
        }
        //当前cd设为cd值
        cdMap.replace(this.getClass(),humanModel.getCdMap().getOrDefault(this.getClass(),4)+1);
        //其余cd都-1
        cdMap.replaceAll((k,v)->v==0?v:v-1);

    }
}
