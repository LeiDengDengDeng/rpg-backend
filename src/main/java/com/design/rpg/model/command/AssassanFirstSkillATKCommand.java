package com.design.rpg.model.command;

import com.design.rpg.model.HumanModel;
import com.design.rpg.model.MonsterModel;

import java.util.Map;

/**
 * Created by liying on 2019/3/28.
 */
public class AssassanFirstSkillATKCommand implements HumanATKCommand {


    /**
     * 伤害技
     * @param humanModel
     * @param monsterModel
     * @return
     */
    @Override
    public void calculateATK(HumanModel humanModel, MonsterModel monsterModel) {
        Map<Class,Integer> cdMap=humanModel.getMyCDMap();
        //若还在cd
        if(cdMap.get(this.getClass())>0){return;}

        int attack=humanModel.getAtk()+humanModel.getAgility()*2;
        int defence=monsterModel.getDef();
        int hurt=attack>defence?attack-defence:0;
        monsterModel.setHp(monsterModel.getHp()-hurt);
        //当前cd设为cd值
        cdMap.replace(this.getClass(),this.getCD()+1);
        //其余cd都-1
        cdMap.replaceAll((k,v)->v==0?v:v-1);

    }

    @Override
    public String getName() {
        return "孟美岐之刺";
    }

    @Override
    public String getDescription() {
        return "对怪物造成少量伤害";
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
