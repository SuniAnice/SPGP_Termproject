package kr.ac.kpu.game.s2016182019.termproject.game;

import android.graphics.Canvas;

import kr.ac.kpu.game.s2016182019.termproject.R;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameBitmap;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameObject;

public class Block implements GameObject {
    private int x;
    private int y;
    private int index;
    private GameBitmap bitmap;
    int bitmaps[] = {
            R.mipmap.red, R.mipmap.green, R.mipmap.blue, R.mipmap.black,
    };

    public Block(int x, int y, int index) {
        initialize(x, y, index);
    }

    public void initialize(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.bitmap = new GameBitmap(bitmaps[index]);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, x, y);
    }
}
