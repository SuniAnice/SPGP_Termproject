package kr.ac.kpu.game.s2016182019.cookierun.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182019.cookierun.framework.view.GameView;
import kr.ac.kpu.game.s2016182019.cookierun.game.MainGame;

public class HorizontalScrollBackground implements GameObject {
    private final Bitmap bitmap;
    private final float speed;
    private float scroll;

    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();

    public HorizontalScrollBackground(int resId, int speed) {
        this.speed = speed * GameView.MULTIPLIER;
        bitmap = GameBitmap.load(resId);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        srcRect.set(0, 0, w, h);
        float l = 0;
        float t = 0;
        float r = GameView.view.getWidth();
        float b = r * h / w;
        dstRect.set(l, t, r, b);

    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        float amount = speed * game.frameTime;
        scroll -= amount;
    }

    @Override
    public void draw(Canvas canvas) {
        int vw = GameView.view.getWidth();
        int vh = GameView.view.getHeight();
        int iw = bitmap.getWidth();
        int ih = bitmap.getHeight();
        int dw = vh * iw / ih;

        int currw = (int) scroll % dw;
        if (currw > 0) currw -= dw;

        while (currw < vw) {
            dstRect.set(currw, 0, dw + currw, vh);
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
            currw += dw;
        }


    }
}
