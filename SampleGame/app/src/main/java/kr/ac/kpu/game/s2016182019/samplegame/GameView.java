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
    private Bitmap bitmap;
    private long lastFrame;
    private float frameTime;

    private Ball b1 = new Ball(100, 100, 100, 200);
    private Ball b2 = new Ball(800, 1500, -50, -100);

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initResources();
        startUpdating();
    }

    private void startUpdating() {
        doGameFrame();
    }

    private void doGameFrame() {
//        update();
        b1.x += b1.dx * frameTime;
        b1.y += b1.dy * frameTime;
        b2.x += b2.dy * frameTime;
        b2.y += b2.dy * frameTime;
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
        Resources res = getResources();
        bitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, b1.x, b1.y, null);
        canvas.drawBitmap(bitmap, b2.x, b2.y, null);
        //Log.d(TAG, "Drawing at: " + x + "," + y + " FrameTime = " + frameTime);
    }
}
