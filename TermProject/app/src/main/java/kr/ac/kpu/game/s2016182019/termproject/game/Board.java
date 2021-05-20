package kr.ac.kpu.game.s2016182019.termproject.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Random;

import kr.ac.kpu.game.s2016182019.termproject.R;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameBitmap;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameObject;
import kr.ac.kpu.game.s2016182019.termproject.framework.view.GameView;

public class Board implements GameObject {
    private final int x;
    private final int y;
    private final GameBitmap bitmap;

    private Block blocks[][];

    public Board() {
        MainGame game = MainGame.get();
        this.x = GameView.view.getWidth() / 2;
        this.y = GameView.view.getHeight() / 2;
        this.bitmap = new GameBitmap(R.mipmap.board);

        RectF bound = new RectF();

        bitmap.getBoundingRect(x, y, bound);

        blocks = new Block[8][8];

        Random r = new Random();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                blocks[i][j] = new Block((int)((bound.right - bound.left) / 8 * (i + 0.5) + bound.left), (int)((bound.bottom - bound.top) / 8 * (j + 0.5) + bound.top), r.nextInt(4));
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
}
