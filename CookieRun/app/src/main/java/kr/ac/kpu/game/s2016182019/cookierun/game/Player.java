package kr.ac.kpu.game.s2016182019.cookierun.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182019.cookierun.R;
import kr.ac.kpu.game.s2016182019.cookierun.framework.BoxCollidable;
import kr.ac.kpu.game.s2016182019.cookierun.framework.GameObject;
import kr.ac.kpu.game.s2016182019.cookierun.framework.IndexedAnimationGameBitmap;


public class Player implements GameObject, BoxCollidable {
    private static final int BULLET_SPEED = 1500;
    private static final float FIRE_INTERVAL = 1.0f / 7.5f;
    private static final float LASER_DURATION = FIRE_INTERVAL / 3;
    private static final float GRAVITY = 2000;
    private static final float JUMP_POWER = 1200;
    private final IndexedAnimationGameBitmap charBitmap;
    private final float ground_y;
    private float fireTime;
    private int imageWidth;
    private int imageHeight;
    private float x, y;
    private float tx, ty;
    private float speed;
    private float vertSpeed;
    private int[] ANIM_INDICES_RUNNING = { 100, 101, 102, 103 };
    private int[] ANIM_INDICES_JUMP = { 7, 8 };
    private int[] ANIM_INDICES_DOUBLE_JUMP = { 1, 2, 3, 4 };


    private enum State {
         running, jump, doublejump, slide, hit
    }

    public void setState(State state) {
        this.state = state;
        int[] indices = ANIM_INDICES_RUNNING;
        switch (state) {
            case running: indices = ANIM_INDICES_RUNNING; break;
            case jump: indices = ANIM_INDICES_JUMP; break;
            case doublejump: indices = ANIM_INDICES_DOUBLE_JUMP; break;
        }
        charBitmap.setIndices(indices);
    }

    private State state = State.running;


    public Player(float x, float y) {
        this.x = x;
        this.y = y;
        this.ground_y = y;
        this.tx = x;
        this.ty = y;
        this.speed = 800;
        this.charBitmap = new IndexedAnimationGameBitmap(R.mipmap.cookie, 9f, 0);
        setState(State.running);

        this.fireTime = 0.0f;
    }

    public void moveTo(float x, float y) {
        this.tx = x;
    }

    public void update() {
        MainGame game = MainGame.get();

        if (state == State.jump || state == State.doublejump) {
            float y = this.y + vertSpeed * game.frameTime;
//            charBitmap.move(0, y - this.y);
            vertSpeed += GRAVITY * game.frameTime;
            if (y >= ground_y) {
                y = ground_y;
                setState(State.running);

            }
            this.y = y;
        }
    }



    public void draw(Canvas canvas) {
        charBitmap.draw(canvas, x, y);

    }

    @Override
    public void getBoundingRect(RectF rect) {
        //charBitmap.getBoundingRect(x, y, rect);
    }

    public void jump() {
        //if (state != State.running && state != State.jump && state != State.slide) return;
        if (state == State.running) {
            setState(State.jump);
            vertSpeed = -JUMP_POWER;

        } else if (state == State.jump) {
            setState(State.doublejump);
            vertSpeed = -JUMP_POWER;
        }
        return;
    }
}
