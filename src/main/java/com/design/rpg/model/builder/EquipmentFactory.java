package com.design.rpg.model.builder;

import com.design.rpg.form.ColorType;
import com.design.rpg.form.EquipmentType;
import com.design.rpg.form.HumanType;

import java.util.UUID;

/**
 * Created by liying on 2019/3/30.
 */
public class EquipmentFactory {
    private static final String BODY_PIC="";
    private static final String WEAPON_PIC="";
    private static final String HEAD_PIC="";



    public static Equipment createEquipment(HumanType humanType, EquipmentType equipmentType, ColorType colorType) {
        switch (equipmentType){
            case BODY:
                return createBody(colorType);
            case HEAD:
                return createHead(colorType);
            case WEAPON:
                return createWeapon(humanType,colorType);
            default:
                return createWeapon(humanType,colorType);
        }
    }
    public static Equipment createEquipmentByRandom( ColorType colorType) {
        EquipmentType equipmentType;
        HumanType humanType =HumanType.ASSASSIN;
        double randomHuman=Math.random();
        double randomEquipment=Math.random();
        if(randomEquipment<0.33){
            equipmentType=EquipmentType.BODY;
        }else if(randomEquipment<0.66){
            equipmentType=EquipmentType.HEAD;
        }else{
            equipmentType=EquipmentType.WEAPON;
            if(randomHuman<0.33){
                humanType=HumanType.DOCTOR;
            }else if(randomHuman<0.66){
                humanType=HumanType.ASSASSIN;
            }else{
                humanType=HumanType.SOLDIER;
            }
        }
        return createEquipment(humanType,equipmentType,colorType);
    }
    static private Weapon createWeapon(HumanType humanType,ColorType colorType){
        Weapon weapon=new Weapon();
        setAttribute(colorType,weapon);
        weapon.setWeaponType(humanType);
        weapon.setUrl(WEAPON_PIC);
        switch (humanType){
            case DOCTOR:
                weapon.setName(weapon.getName()+"法杖");
                break;
            case ASSASSIN:
                weapon.setName(weapon.getName()+"匕首");
                break;
            case SOLDIER:
                weapon.setName(weapon.getName()+"剑");
                break;
            default:
                break;
        }
        return weapon;
    }
    static private Head createHead(ColorType colorType){
        Head head=new Head();
        setAttribute(colorType,head);
        head.setUrl(HEAD_PIC);
        return head;
    }

    static private Body createBody(ColorType colorType){
        Body body=new Body();
        setAttribute(colorType,body);
        body.setUrl(BODY_PIC);
        return body;
    }

    static private void setAttribute(ColorType colorType,Equipment equipment){
        int base=0;
        int mul=0;
        String name="";
        switch (colorType){
            //1-3
            case GREEN:
                base=1;
                mul=3;
                name="普通的";
                break;
            //3-6
            case BLUE:
                base=3;
                mul=4;
                name="精英的";
                break;
            //6-10
            case PURPLE:
                base=6;
                mul=5;
                name="孟美岐的";
                break;
            default:
                base=1;
                mul=1;
        }
        equipment.setPhysique(base+(int)Math.random()*mul);
        equipment.setAgility(base+(int)Math.random()*mul);
        equipment.setEndurance(base+(int)Math.random()*mul);
        equipment.setLuck(base+(int)Math.random()*mul);
        equipment.setPower(base+(int)Math.random()*mul);
        equipment.setSpirit(base+(int)Math.random()*mul);
        equipment.setUuid(UUID.randomUUID().toString());
        equipment.setColorType(colorType);
        equipment.setName(name);
    }




}
