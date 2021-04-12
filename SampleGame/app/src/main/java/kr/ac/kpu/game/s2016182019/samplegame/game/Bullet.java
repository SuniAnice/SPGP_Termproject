package kr.ac.kpu.game.s2016182019.samplegame.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182019.samplegame.R;
import kr.ac.kpu.game.s2016182019.samplegame.framework.GameObject;
import kr.ac.kpu.game.s2016182019.samplegame.ui.view.GameView;

public class Bullet implements GameObject {
    private static int imageWidth;
    private static int imageHeight;
    private final float radius;
    private final long createOn;
    private final float angle;
    private float x, y;
    private float dx, dy;
    private int frameIndex;
    private static Bitmap bitmap;
    private static float FRAME_RATE = 8.5f;


    public Bullet(float x, float y, float tx, float ty) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.radius = 10.0f;

        MainGame game = MainGame.get();
        //float speed = 1000;
        float delta_x = tx - this.x;
        float delta_y = ty - this.y;
        angle = (float) Math.atan2(delta_y , delta_x);
        float move_dist = 1000;
        this.dx = (float) (move_dist * Math.cos(angle));
        this.dy = (float) (move_dist * Math.sin(angle));

        if (bitmap == null){
            Resources res = GameView.view.getResources();
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.laser_light);
            imageWidth = bitmap.getWidth();
            imageHeight = bitmap.getHeight();
        }
        createOn = System.currentTimeMillis();
    }

    public void update() {
        MainGame game = MainGame.get();
        x += dx * game.frameTime;
        y += dy * game.frameTime;
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        int frameWidth = w / 10;

        boolean toBeDeleted = false;

        if (x < 0 || x > w - frameWidth) {
            toBeDeleted = true;
        }
        if (y < 0 || y > h - imageHeight) {
            toBeDeleted = true;
        }
        if (toBeDeleted) {
            game.remove(this);
        }
        int elapesd = (int)(System.currentTimeMillis() - createOn);
        frameIndex = Math.round(elapesd * FRAME_RATE * 0.001f) % 10;
    }

    public void draw(Canvas canvas) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int fw = w / 10;
        int hw = 100;
        int hh = 124;
        Rect src = new Rect(fw * frameIndex, 0, fw * frameIndex + fw, h);
        RectF dst = new RectF(x - hw, y - hh, x + hw, y + hh);

        float degree = (float) (angle * 180 / Math.PI) + 90;

        canvas.save();
        canvas.rotate(degree, x, y);
        canvas.drawBitmap(bitmap, src, dst, null);
        canvas.restore();
    }
}
