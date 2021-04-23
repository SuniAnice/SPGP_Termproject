package kr.ac.kpu.game.s2016182019.dragonflight.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182019.dragonflight.R;
import kr.ac.kpu.game.s2016182019.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2016182019.dragonflight.framework.GameBitmap;
import kr.ac.kpu.game.s2016182019.dragonflight.framework.GameObject;

public class Bullet implements GameObject, BoxCollidable {
    private final float x;
    private final GameBitmap bitmap;
    private float y;
    private final int speed;

    public Bullet(float x, float y, int speed) {
        this.x =x;
        this.y = y;
        this.speed = -speed;

        this.bitmap = new GameBitmap(R.mipmap.laser_1);
    }
    @Override
    public void update() {
        MainGame game = MainGame.get();
        y += speed * game.frameTime;

        if (y < 0) {
            game.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, x, y);
    }

    @Override
    public void getBoundingRect(RectF rect) {
        bitmap.getBoundingRect(x, y, rect);
    }
}
