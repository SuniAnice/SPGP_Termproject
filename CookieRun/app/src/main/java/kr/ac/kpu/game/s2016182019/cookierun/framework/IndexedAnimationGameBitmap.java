package kr.ac.kpu.game.s2016182019.cookierun.framework;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182019.cookierun.framework.view.GameView;

public class IndexedAnimationGameBitmap extends AnimationGameBitmap {

    private final int frameHeight;
    private ArrayList<Rect> srcRects;

    public IndexedAnimationGameBitmap(int resId, float framesPerSecond, int frameCount) {
        super(resId, framesPerSecond, frameCount);
        this.frameWidth = 270;
        this.frameHeight = 270;
    }

    public void setIndices(int... indices) {
        srcRects = new ArrayList<Rect>();
        for (int index : indices) {
            int x = index % 100;
            int y = index / 100;
            int l = 2 + x * 272;
            int t = 2 + y * 272;
            int r = l + 270;
            int b = t + 270;
            srcRects.add(new Rect(l, t, r, b));
        }
        frameCount = indices.length;
    }

    @Override
    public void draw(Canvas canvas, float x, float y) {
        int elapsed = (int)(System.currentTimeMillis() - createOn);
        frameIndex = Math.round(elapsed * framesPerSecond * 0.001f) % frameCount;

        int fw = frameWidth;
        int h = frameHeight;
        float hw = fw / 2 * GameView.MULTIPLIER;
        float hh = h / 2 * GameView.MULTIPLIER;


        dstRect.set(x - hw, y - hh, x + hw, y + hh);

        canvas.drawBitmap(bitmap, srcRects.get(frameIndex), dstRect, null);
    }
}
