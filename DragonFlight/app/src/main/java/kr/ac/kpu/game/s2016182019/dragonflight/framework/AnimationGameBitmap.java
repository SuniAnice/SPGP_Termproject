package kr.ac.kpu.game.s2016182019.dragonflight.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182019.dragonflight.ui.view.GameView;

public class AnimationGameBitmap extends GameBitmap {
    private Bitmap bitmap;
    private final int imageWidth;
    private final int imageHeight;
    private final int frameWidth;
    private final long createOn;
    private final float framesPerSecond;
    private int frameCount;
    private int frameIndex;

    public AnimationGameBitmap(int resId, float framesPerSecond, int frameCount) {
        super(resId);
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



    public void draw(Canvas canvas, float x, float y) {
        int elapsed = (int)(System.currentTimeMillis() - createOn);
        frameIndex = Math.round(elapsed * framesPerSecond * 0.001f) % frameCount;

        int fw = frameWidth;
        int h = imageHeight;
        float hw = fw / 2 * GameView.MULTIPLIER;
        float hh = h / 2 * GameView.MULTIPLIER;
        Rect src = new Rect(fw * frameIndex, 0, fw * frameIndex + fw, h);
        RectF dst = new RectF(x - hw, y - hh, x + hw, y + hh);

        canvas.drawBitmap(bitmap, src, dst, null);

    }

    public int getWidth() {
        return frameWidth;
    }

    public int getHeight() {
        return imageHeight;
    }
}
