package kr.ac.kpu.game.s2016182019.termproject.game;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182019.termproject.R;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameBitmap;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameObject;

public class Player implements GameObject {

    private final UIManager playerUI;
    public int maxHp = 100;
    public int hp = 100;

    public Enemy enemy;

    public int manaRed = 0;
    public int manaGreen = 0;
    public int manaBlue = 0;
    public int manaBlack = 0;
    public int manaWhite = 0;
    public int attack = 0;
    public int defence = 0;


    public Player() {
        playerUI = new UIManager();
        playerUI.setSkill(1,0);
        playerUI.setSkill(2,1);
        playerUI.setSkill(3,2);
        playerUI.setSkill(4,3);
    }


    public void initialize() {
        manaRed = 0;
        manaGreen = 0;
        manaBlue = 0;
        manaBlack = 0;
        manaWhite = 0;
        attack = 0;
        defence = 0;
        hp += Math.floor(maxHp / 10);
        hp = Math.max(hp,maxHp);
    }

    public boolean useMana(int red, int green, int blue, int black, int white)
    {
        if (manaRed >= red && manaGreen >= green && manaBlue >= blue && manaBlack >= black && manaWhite >= white) {
            manaRed -= red;
            manaGreen -= green;
            manaBlue -= blue;
            manaBlack -= black;
            manaWhite -= white;
            return true;
        }
        return false;
    }

    @Override
    public void update() {
        playerUI.update();
    }

    @Override
    public void draw(Canvas canvas) {
        playerUI.draw(canvas);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return playerUI.onTouchEvent(event);
    }
}
