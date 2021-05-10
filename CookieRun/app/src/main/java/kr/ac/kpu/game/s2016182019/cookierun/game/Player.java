package kr.ac.kpu.game.s2016182019.cookierun.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182019.cookierun.R;
import kr.ac.kpu.game.s2016182019.cookierun.framework.BoxCollidable;
import kr.ac.kpu.game.s2016182019.cookierun.framework.GameBitmap;
import kr.ac.kpu.game.s2016182019.cookierun.framework.GameObject;
import kr.ac.kpu.game.s2016182019.cookierun.framework.MainGame;


public class Player implements GameObject, BoxCollidable {
    private static final int BULLET_SPEED = 1500;
    private static final float FIRE_INTERVAL = 1.0f / 7.5f;
    private static final float LASER_DURATION = FIRE_INTERVAL / 3;
    private float fireTime;
    private int imageWidth;
    private int imageHeight;
    private float x, y;
    private float tx, ty;
    private float speed;
    private GameBitmap planeBitmap;
    private GameBitmap fireBitmap;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.tx = x;
        this.ty = y;
        this.speed = 800;
        //this.planeBitmap = new GameBitmap(R.mipmap.fighter);
        //this.fireBitmap = new GameBitmap(R.mipmap.laser_0);
        this.fireTime = 0.0f;
    }

    public void moveTo(float x, float y) {
        this.tx = x;
    }

    public void update() {
        MainGame game = MainGame.get();
        float dx = speed * game.frameTime;
        if (tx <= x) {
            dx = -dx;
        }
        x += dx;
        if ((dx > 0 && x > tx) || (dx < 0 && x < tx)) {
            x = tx;
        }
        fireTime += game.frameTime;
        if (fireTime >= FIRE_INTERVAL) {
            fireBullet();
            fireTime -= FIRE_INTERVAL;
        }
    }

    private void fireBullet() {

    }

    public void draw(Canvas canvas) {
        planeBitmap.draw(canvas, x, y);
        if (fireTime < LASER_DURATION) {
            fireBitmap.draw(canvas, x, y - 50);
        }
    }

    @Override
    public void getBoundingRect(RectF rect) {
        planeBitmap.getBoundingRect(x, y, rect);
    }
}
