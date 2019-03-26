package com.design.rpg.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author deng
 * @date 2019/03/27
 */
@RestController
@RequestMapping("/game")
public class GameController {
    @PostMapping("/move")
    public void move() {

    }

    @PostMapping("/attack")
    public void attack() {
        // TODO:普攻、单个技能、组合技能
    }
}
