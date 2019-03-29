package com.design.rpg.controller;

import com.design.rpg.form.HumanType;
import com.design.rpg.model.DoctorHumanModel;
import com.design.rpg.model.GameModel;
import com.design.rpg.model.strategy.NormalATKStrategy;
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
    public void create(@RequestParam String userId, @RequestParam HumanType humanType) {
        gameModel.loadHumanModel(userId, new DoctorHumanModel());
    }

    @PostMapping("/move")
    public void move() {
        gameModel.move();
    }

    @PostMapping("/attack")
    public void attack(@RequestParam char key) {
        // TODO:普攻、单个技能、组合技能
        gameModel.humanAttack(new NormalATKStrategy());
    }
}
