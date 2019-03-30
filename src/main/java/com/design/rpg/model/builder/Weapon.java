package com.design.rpg.model.builder;

import com.design.rpg.form.EquipmentType;
import com.design.rpg.form.HumanType;
import lombok.Data;

/**
 * @author deng
 * @date 2019/03/16
 */
@Data
public class Weapon extends Equipment{
    HumanType weaponType;

    public Weapon(){
        super();
        this.type= EquipmentType.WEAPON;
    }
}
