package kr.ac.kpu.game.s2016182019.termproject.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182019.termproject.R;
import kr.ac.kpu.game.s2016182019.termproject.framework.BoxCollidable;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameBitmap;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameObject;

public class Block implements GameObject, BoxCollidable {
    private int x;
    private int y;
    private int index;
    private GameBitmap bitmap;
    private boolean selected;
    public int bitmaps[] = {
            R.mipmap.red, R.mipmap.green, R.mipmap.blue, R.mipmap.black,
    };
    private GameBitmap highlight;

    public Block(int x, int y, int index) {
        initialize(x, y, index);
    }

    public void initialize(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.bitmap = new GameBitmap(bitmaps[index]);
        this.highlight = new GameBitmap(R.mipmap.highlight);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, x, y);
        if (selected)
        {
            highlight.draw(canvas,x,y);
        }
    }

    @Override
    public void getBoundingRect(RectF rect) {
        bitmap.getBoundingRect(x, y, rect);
    }

    public void select(boolean b) {
        selected = b;
    }
}
