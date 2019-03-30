package com.design.rpg.controller;

import com.design.rpg.exception.ServiceException;
import com.design.rpg.form.HumanType;
import com.design.rpg.model.GameModel;
import com.design.rpg.model.builder.InitDoctorBuilder;
import com.design.rpg.vo.InfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                // TODO：修改
                return gameModel.loadHumanModel(userId, new InitDoctorBuilder().createHuman());
            case ASSASSIN:
                return gameModel.loadHumanModel(userId, new InitDoctorBuilder().createHuman());
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
}
