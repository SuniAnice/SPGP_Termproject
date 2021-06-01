package kr.ac.kpu.game.s2016182019.termproject.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.kpu.game.s2016182019.termproject.R;
import kr.ac.kpu.game.s2016182019.termproject.framework.AnimationGameBitmap;
import kr.ac.kpu.game.s2016182019.termproject.framework.BaseGame;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameBitmap;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameObject;

public class Enemy implements GameObject {
    private final Player player;
    private final GameBitmap backGroundBitmap;
    private AnimationGameBitmap enemyBitmap;
    private final GameBitmap uiBitmap;
    private final Text healthText;
    private final Text attackText;
    private final Text turnText;
    public int hp = 50;

    public int attack = 5;
    public int currAttack;
    public int turn = 5;

    public int wave = 0;

    public enum Type {
        T_ORC, T_SLIME, T_GRASSHOPPER;

        int turn() {
            int t = 5;
            switch (this) {
                case T_ORC: t = 7; break;
                case T_SLIME: t = 5; break;
                case T_GRASSHOPPER: t = 3; break;
            }
            return t;
        }

        int hp() {
            int t = 40;
            switch (this) {
                case T_ORC: t = 70; break;
                case T_SLIME: t = 40; break;
                case T_GRASSHOPPER: t = 50; break;
            }
            return t;
        }

        int attack() {
            int t = 5;
            switch (this) {
                case T_ORC: t = 10; break;
                case T_SLIME: t = 5; break;
                case T_GRASSHOPPER: t = 3; break;
            }
            return t;
        }

        AnimationGameBitmap bitmap() {
            switch (this) {
                case T_ORC: return new AnimationGameBitmap(R.mipmap.orc,5,4);
                case T_SLIME:  return new AnimationGameBitmap(R.mipmap.slime,10,8);
                case T_GRASSHOPPER:  return new AnimationGameBitmap(R.mipmap.grasshopper,6,8);
                default:
                    return new AnimationGameBitmap(R.mipmap.slime,10,8);
            }

        }
    }

    public Enemy() {
        BaseGame game = BaseGame.get();
        player = MainScene.scene.player;
        backGroundBitmap = new GameBitmap(R.mipmap.forest);
        uiBitmap = new GameBitmap(R.mipmap.enemyui);
        healthText = new Text(2000, 580);
        attackText = new Text(2000, 735);
        turnText = new Text(2000, 895);
        Random r = new Random();
        initialize(r.nextInt(3), wave++);
    }

    public void initialize(int index, int wave) {
        BaseGame game = BaseGame.get();
        Type t;
        t = Type.values()[index];
        float multi = (float) (1 + 0.05 * wave);

        this.turn = t.turn();
        this.attack = Math.round(t.attack() * multi);
        this.hp = Math.round(t.hp() * multi);
        this.enemyBitmap = t.bitmap();

        MainScene.scene.board.turn = this.turn;

        currAttack = attack;
    }

    public void doAttack() {
        BaseGame game = BaseGame.get();
        player.hp -= currAttack;
        currAttack = attack;
        Effector e = new Effector(new AnimationGameBitmap(R.mipmap.damage, 5, 5),260,150, 0.8f);
        MainScene.scene.add(MainScene.Layer.effect, e);
    }

    @Override
    public void update() {
        BaseGame game = BaseGame.get();
        healthText.setNum(hp);
        attackText.setNum(currAttack);
        turnText.setNum(MainScene.scene.board.turn);

        if (hp == 0)
        {
            Random r = new Random();
            initialize(r.nextInt(3), wave++);
            MainScene.scene.player.initialize();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        backGroundBitmap.draw(canvas, 1750,250, 0.6f);
        enemyBitmap.draw(canvas,1825, 350, 1.1f);
        uiBitmap.draw(canvas, 1875, 780, 0.45f);
        healthText.draw(canvas,1.3f);
        attackText.draw(canvas, 1.3f);
        turnText.draw(canvas, 1.3f);
    }
}
