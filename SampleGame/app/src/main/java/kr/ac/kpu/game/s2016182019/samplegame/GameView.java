package kr.ac.kpu.game.s2016182019.samplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.logging.Handler;

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();

    private long lastFrame;
    public static float frameTime;
    public static GameView view;

    private Ball b1, b2;

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
        b1.update();
        b2.update();

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
        b1 = new Ball(100, 100, 100, 200);
        b2 = new Ball(800, 1500, -50, -100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        b1.draw(canvas);
        b2.draw(canvas);

    }
}
