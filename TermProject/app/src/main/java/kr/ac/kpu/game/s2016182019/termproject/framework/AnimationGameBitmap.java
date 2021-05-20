package kr.ac.kpu.game.s2016182019.termproject.framework;

import android.graphics.Canvas;
import android.graphics.Rect;

import kr.ac.kpu.game.s2016182019.termproject.framework.view.GameView;


public class AnimationGameBitmap extends GameBitmap {
    private final int imageWidth;
    private final int imageHeight;
    protected int frameWidth;
    protected final long createOn;
    protected final float framesPerSecond;
    protected int frameCount;
    protected int frameIndex;

    protected Rect srcRect = new Rect();

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
        srcRect.set(fw * frameIndex, 0, fw * frameIndex + fw, h);
        dstRect.set(x - hw, y - hh, x + hw, y + hh);

        canvas.drawBitmap(bitmap, srcRect, dstRect, null);

    }

    public int getWidth() {
        return frameWidth;
    }

    public int getHeight() {
        return imageHeight;
    }
}
