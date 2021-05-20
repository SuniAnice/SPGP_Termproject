package kr.ac.kpu.game.s2016182019.cookierun.game;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182019.cookierun.R;
import kr.ac.kpu.game.s2016182019.cookierun.framework.GameBitmap;
import kr.ac.kpu.game.s2016182019.cookierun.framework.ImageObject;
import kr.ac.kpu.game.s2016182019.cookierun.framework.IndexedGameBitmap;

public class Jelly extends ImageObject {
    private final IndexedGameBitmap ibmp;

    public Jelly(int index, float x, float y) {
        super(R.mipmap.jelly, x, y);
        ibmp = new IndexedGameBitmap(R.mipmap.jelly, 66, 66, 30, 2, 2);
        ibmp.setIndex(index);
    }
    @Override
    public void update() {
        MainGame game = MainGame.get();
        float dx = Platform.SPEED * game.frameTime;
        dstRect.offset(-dx, 0);
        if (getRight() < 0) {
            game.remove(this);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        ibmp.draw(canvas, dstRect);
    }
}
