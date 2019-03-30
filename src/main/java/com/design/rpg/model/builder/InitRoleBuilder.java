package com.design.rpg.model.builder;

import com.design.rpg.form.ColorType;
import com.design.rpg.form.EquipmentType;
import com.design.rpg.form.HumanType;
import com.design.rpg.model.factory.EquipmentFactory;

/**
 * Created by liying on 2019/3/31.
 */
public class InitRoleBuilder implements RoleBuilder{

    Role role;

    @Override
    public void buildHead() {
        Head head=(Head) EquipmentFactory.createEquipment(HumanType.DOCTOR, EquipmentType.HEAD, ColorType.GREEN);
        role.setHead(head);
    }

    @Override
    public void buildBody() {
        Body body=(Body)EquipmentFactory.createEquipment(HumanType.DOCTOR, EquipmentType.BODY, ColorType.GREEN);
        role.setBody(body);

    }

    @Override
    public void buildWeapon() {
        role.setWeapon(null);
    }

    @Override
    public Role createRole() {
        role=new Role();
        buildBody();
        buildHead();
        buildWeapon();
        return role;
    }
}
