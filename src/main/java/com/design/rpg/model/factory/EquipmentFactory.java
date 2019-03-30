package com.design.rpg.model.factory;

import com.design.rpg.form.ColorType;
import com.design.rpg.form.EquipmentType;
import com.design.rpg.form.HumanType;
import com.design.rpg.model.builder.Body;
import com.design.rpg.model.builder.Equipment;
import com.design.rpg.model.builder.Head;
import com.design.rpg.model.builder.Weapon;

import java.util.UUID;

/**
 * Created by liying on 2019/3/30.
 */
public class EquipmentFactory {
    private static final String BODY_PIC="http://120.77.205.70:8078/picture/ab173165-b792-4be0-8f83-e48b53f8977bWechatIMG17843.png";
    private static final String WEAPON_KNIFE_PIC="http://120.77.205.70:8078/picture/59af115c-3a18-4ab8-9ba3-5fd76625a1e4WechatIMG17842.png";
    private static final String WEAPON_SWORD_PIC="http://120.77.205.70:8078/picture/861c2a58-9db7-401c-bcf5-fa90d746385cWechatIMG17845.png";
    private static final String WEAPON_WAND_PIC="http://120.77.205.70:8078/picture/0fea3d91-58eb-487f-94f0-b1d457d79cd5WechatIMG17840.png";
    private static final String HEAD_PIC="http://120.77.205.70:8078/picture/8eca1251-cd45-4aa3-a9f4-f8255aa731d1WechatIMG17843.png";



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
    static private Weapon createWeapon(HumanType humanType, ColorType colorType){
        Weapon weapon=new Weapon();
        setAttribute(colorType,weapon);
        weapon.setWeaponType(humanType);
        switch (humanType){
            case DOCTOR:
                weapon.setName(weapon.getName()+"法杖");
                weapon.setUrl(WEAPON_WAND_PIC);
                break;
            case ASSASSIN:
                weapon.setUrl(WEAPON_KNIFE_PIC);
                weapon.setName(weapon.getName()+"匕首");
                break;
            case SOLDIER:
                weapon.setUrl(WEAPON_SWORD_PIC);
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
        head.setName(head.getName()+"头盔");
        head.setUrl(HEAD_PIC);
        return head;
    }

    static private Body createBody(ColorType colorType){
        Body body=new Body();
        setAttribute(colorType,body);
        body.setName(body.getName()+"外套");
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
