package com.design.rpg.model.strategy;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

import java.util.Map;

/**
 * Created by liying on 2019/3/28.
 */
public class DoctorFirstSkillATKStrategy implements HumanATKStrategy {

    /**
     * 治疗术
     * @param humanModel
     * @param monsterModel
     * @return
     */
    @Override
    public void calculateATK(HumanModel humanModel, MonsterModel monsterModel) {
        Map<Class,Integer> cdMap=humanModel.getMyCDMap();
        //当前cd设为cd值
        cdMap.replace(this.getClass(),humanModel.getCdMap().getOrDefault(this.getClass(),4)+1);
        //其余cd都-1
        cdMap.replaceAll((k,v)->v==0?v:v-1);
        //若还在cd
        if(cdMap.get(this.getClass())>0){return;}


        int HP=(int)(humanModel.getMaxHP()*0.2)+humanModel.getHP();
        if(HP>=humanModel.getMaxHP()){
            humanModel.setHP(humanModel.getMaxHP());
        }else{
            humanModel.setHP(HP);
        }

    }
}
