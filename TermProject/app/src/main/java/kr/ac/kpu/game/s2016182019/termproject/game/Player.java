package kr.ac.kpu.game.s2016182019.termproject.game;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182019.termproject.R;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameBitmap;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameObject;

public class Player implements GameObject {

    private final UIManager playerUI;
    public int maxHp = 100;
    public int hp = 100;

    public int manaRed = 0;
    public int manaGreen = 0;
    public int manaBlue = 0;
    public int manaBlack = 0;
    public int manaWhite = 0;
    public int attack = 0;
    public int defence = 0;


    public Player() {
        initialize();
        playerUI = new UIManager();
        playerUI.setSkill(1,0);
        playerUI.setSkill(2,1);
        playerUI.setSkill(3,0);
        playerUI.setSkill(4,1);
    }

    private void initialize() {
        manaRed = 0;
        manaGreen = 0;
        manaBlue = 0;
        manaBlack = 0;
        manaWhite = 0;
        attack = 0;
        defence = 0;
    }

    @Override
    public void update() {
        playerUI.update();
    }

    @Override
    public void draw(Canvas canvas) {
        playerUI.draw(canvas);
    }
}
