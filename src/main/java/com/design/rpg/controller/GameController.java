package com.design.rpg.controller;

import com.design.rpg.exception.ServiceException;
import com.design.rpg.form.HumanType;
import com.design.rpg.form.Response;
import com.design.rpg.model.GameModel;
import com.design.rpg.model.HumanModel;
import com.design.rpg.model.builder.InitAssassinBuilder;
import com.design.rpg.model.builder.InitDoctorBuilder;
import com.design.rpg.model.builder.InitSoldierBuilder;
import com.design.rpg.vo.HumanVO;
import com.design.rpg.vo.InfoVO;
import com.design.rpg.vo.StrengthResultVO;
import com.design.rpg.vo.factory.HumanVOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author deng
 * @date 2019/03/27
 */
@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameModel gameModel;

    @PostMapping("/create")
    public InfoVO create(@RequestParam String userId, @RequestParam HumanType humanType) {
        switch (humanType) {
            case DOCTOR:
                return gameModel.loadHumanModel(userId, new InitDoctorBuilder().createHuman());
            case SOLDIER:
                return gameModel.loadHumanModel(userId, new InitSoldierBuilder().createHuman());
            case ASSASSIN:
                return gameModel.loadHumanModel(userId, new InitAssassinBuilder().createHuman());
        }
        throw new ServiceException(ServiceException.HUMAN_WRONG_TYPE);
    }

    @PostMapping("/move")
    public void move() {
        gameModel.move();
    }

    @PostMapping("/attack")
    public void attack(@RequestParam char key) {
        gameModel.humanAttack(key);
    }

    @PostMapping("/equipment/{id}/split")
    public Response<HumanVO> splitEquipment(@PathVariable String id) {
        HumanModel humanModel = gameModel.getHumanModel();
        humanModel.splitEquipment(id);
        return Response.okResponse(HumanVOFactory.createHumanVO(humanModel));
    }

    @PostMapping("/equipment/{id}/equip")
    public Response<HumanVO> equipEquipment(@PathVariable String id) {
        HumanModel humanModel = gameModel.getHumanModel();
        humanModel.equip(id);
        return Response.okResponse(HumanVOFactory.createHumanVO(humanModel));
    }

    @PostMapping("/equipment/{id}/strength")
    public Response<StrengthResultVO> strengthEquipment(@PathVariable String id) {
        HumanModel humanModel = gameModel.getHumanModel();
        boolean successFlag = humanModel.strengthEquipment(id);
        return Response.okResponse(new StrengthResultVO(HumanVOFactory.createHumanVO(humanModel), successFlag));
    }

}
