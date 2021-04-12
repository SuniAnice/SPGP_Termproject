package kr.ac.kpu.game.s2016182019.samplegame.framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182019.samplegame.R;
import kr.ac.kpu.game.s2016182019.samplegame.ui.view.GameView;

public class AnimationGameBitmap extends GameBitmap {
    public static final int PIXEL_MULTIPLIER = 4;
    private final Bitmap bitmap;
    private final int imageWidth;
    private final int imageHeight;
    private final int frameWidth;
    private final long createOn;
    private final float framesPerSecond;
    private int frameCount;
    private int frameIndex;

    public AnimationGameBitmap(int resId, float framesPerSecond, int frameCount) {
        Resources res = GameView.view.getResources();
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = false;
        bitmap = BitmapFactory.decodeResource(res, resId, opts);


        imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();
        if (frameCount == 0) {
            frameCount = imageWidth / imageHeight;
        }
        this.framesPerSecond = framesPerSecond;
        this.frameCount = frameCount;

        frameWidth = imageWidth / frameCount;
        createOn = System.currentTimeMillis();
        frameIndex = 0;




    }

//    public void update() {
//        int elapesd = (int)(System.currentTimeMillis() - createOn);
//        frameIndex = Math.round(elapesd * framesPerSecond * 0.001f) % frameCount;
//    }

    public void draw(Canvas canvas, float x, float y) {
        int elapesd = (int)(System.currentTimeMillis() - createOn);
        frameIndex = Math.round(elapesd * framesPerSecond * 0.001f) % frameCount;

        int fw = frameWidth;
        int h = imageHeight;
        int hw = fw / 2 * 4;
        int hh = h / 2 * 4;
        Rect src = new Rect(fw * frameIndex, 0, fw * frameIndex + fw, h);
        RectF dst = new RectF(x - hw, y - hh, x + hw, y + hh);

        canvas.drawBitmap(bitmap, src, dst, null);

    }

    public int getWidth() {
        return frameWidth * PIXEL_MULTIPLIER;
    }

    public int getHeight() {
        return imageHeight * PIXEL_MULTIPLIER;
    }
}
