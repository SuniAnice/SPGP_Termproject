package kr.ac.kpu.game.s2016182019.termproject.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

import java.util.Random;

import kr.ac.kpu.game.s2016182019.termproject.R;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameBitmap;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameObject;
import kr.ac.kpu.game.s2016182019.termproject.framework.view.GameView;
import kr.ac.kpu.game.s2016182019.termproject.utils.CollisionHelper;

public class Board implements GameObject {
    private final int x;
    private final int y;
    private final GameBitmap bitmap;
    private final float gridX;
    private final float gridY;
    private final float offsetX;
    private final float offsetY;
    private Block selected = null;

    private Block blocks[][];

    public Board() {
        MainGame game = MainGame.get();
        this.x = GameView.view.getWidth() / 2;
        this.y = GameView.view.getHeight() / 2;
        this.bitmap = new GameBitmap(R.mipmap.board);

        RectF bound = new RectF();

        bitmap.getBoundingRect(x, y, bound);

        gridX = (bound.right - bound.left) / 8;
        gridY = (bound.bottom - bound.top) / 8;
        offsetX = bound.left;
        offsetY = bound.top;

        blocks = new Block[8][8];

        Random r = new Random();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                blocks[i][j] = new Block((int)(gridX * (i + 0.5) + offsetX), (int)(gridY * (j + 0.5) + offsetY), r.nextInt(4));
            }
        }
    }
    @Override
    public void update() {
        for (Block[] temp : blocks) {
            for (Block block : temp) {
                block.update();
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        bitmap.draw(canvas, x, y);
        for (Block[] temp : blocks) {
            for (Block block : temp) {
                block.draw(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        for (Block[] temp : blocks) {
            for (Block block : temp) {
                if (CollisionHelper.collides(block, x, y))
                {
                    if (selected != null)
                    {
                        selected.select(false);
                        selected = block;
                        block.select(true);
                    }
                    else
                    {
                        selected = block;
                        block.select(true);
                    }

                    return true;
                }
            }
        }

        return false;
    }
}
