package com.design.rpg.model.command;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

import java.util.Map;

/**
 * Created by liying on 2019/3/28.
 */
public class DoctorFirstSkillATKCommand implements HumanATKCommand {


    /**
     * 治疗术
     * @param humanModel
     * @param monsterModel
     * @return
     */
    @Override
    public void calculateATK(HumanModel humanModel, MonsterModel monsterModel) {
        Map<Class,Integer> cdMap=humanModel.getMyCDMap();
        //若还在cd
        if(cdMap.get(this.getClass())>0){return;}

        int HP=(int)(humanModel.getMaxHP()*0.2)+humanModel.getHp()+humanModel.getAtk();
        if(HP>=humanModel.getMaxHP()){
            humanModel.setHp(humanModel.getMaxHP());
        }else{
            humanModel.setHp(HP);
        }
        //当前cd设为cd值
        cdMap.replace(this.getClass(),this.getCD()+1);
        //其余cd都-1
        cdMap.replaceAll((k,v)->v==0?v:v-1);

    }

    @Override
    public String getName() {
        return "十全大补";
    }

    @Override
    public String getDescription() {
        return "恢复自身血量";
    }

    @Override
    public int getCD() {
        return 2;
    }

    @Override
    public char getBindKey() {
        return 'J';
    }
}
