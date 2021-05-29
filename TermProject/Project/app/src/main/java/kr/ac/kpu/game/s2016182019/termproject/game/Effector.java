package kr.ac.kpu.game.s2016182019.termproject.game;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182019.termproject.R;
import kr.ac.kpu.game.s2016182019.termproject.framework.AnimationGameBitmap;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameObject;

public class Effector implements GameObject {

    private final AnimationGameBitmap bitmap;
    private final int x;
    private final int y;

    public float lifeTime = 0.9f;

    public Effector(int x, int y) {
        bitmap = new AnimationGameBitmap(R.mipmap.explosion,8,8);
        this.x = x;
        this.y = y;
    }


    @Override
    public void update() {
        MainGame game = MainGame.get();
        lifeTime -= game.frameTime;
        if (lifeTime < 0)
           game.remove(this);
    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, x, y);
    }
}
