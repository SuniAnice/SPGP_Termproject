package kr.ac.kpu.game.s2016182019.samplegame;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    public static final int BALL_COUNT = 10;

    private long lastFrame;
    public static float frameTime;
    public static GameView view;

//    ArrayList<Ball> balls = new ArrayList<>();
    ArrayList<GameObject> objects = new ArrayList<>();
    Player player;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        GameView.view = this;
        initResources();
        startUpdating();
    }

    private void startUpdating() {
        doGameFrame();
    }

    private void doGameFrame() {
//        update();
        for (GameObject o : objects) {
            o.update();
        }

//        draw();
        invalidate();

        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            @Override
            public void doFrame(long time) {
                if (lastFrame == 0){
                    lastFrame = time;
                }
                frameTime = (float) (time - lastFrame) / 1_000_000_000;
                doGameFrame();
                lastFrame = time;

            }
        });

//        postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                doGameFrame();
//            }
//        },15);
    }

    private void initResources() {
        player = new Player(100, 100, 0, 0);
        Random rand = new Random();
        for (int i = 0; i < BALL_COUNT; i++) {
            float x = rand.nextInt(1000);
            float y = rand.nextInt(1000);
            float dx = rand.nextFloat() * 1000 - 500;
            float dy = rand.nextFloat() * 1000 - 500;
            Ball b = new Ball(x, y, dx, dy);
            objects.add(b);
        }
        objects.add(player);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (GameObject o : objects) {
            o.draw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            player.moveTo(event.getX(), event.getY());
            return  true;
        }
        return false;
    }
}
