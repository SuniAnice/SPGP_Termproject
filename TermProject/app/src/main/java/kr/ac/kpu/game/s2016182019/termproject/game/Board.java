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

import static kr.ac.kpu.game.s2016182019.termproject.utils.SwapHelper.swap;

public class Board implements GameObject {
    static kr.ac.kpu.game.s2016182019.termproject.game.Board instance;
    private final int x;
    private final int y;
    private final GameBitmap bitmap;
    private final float gridX;
    private final float gridY;
    private final float offsetX;
    private final float offsetY;
    private Block selected = null;

    public boolean myTurn = true;
    public boolean canMove = true;
    private float delay = 0;
    private int movingBlocks = 0;

    private Block blocks[][];
    private int selectX;
    private int selectY;

    public static kr.ac.kpu.game.s2016182019.termproject.game.Board get() {
        if (instance == null) {
            instance = new kr.ac.kpu.game.s2016182019.termproject.game.Board();
        }
        return instance;
    }

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
        MainGame game = MainGame.get();
        movingBlocks = 0;

        if (canMove) {
            // 연속된 블럭 체크
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    int tIndex = blocks[i][j].index;
                    // 가로
                    {
                        int count = 1;
                        while (blocks[i + count][j].index == tIndex && i + count < 8) {
                            count++;
                        }
                        if (count >= 3) {
                            for (int k = 0; k < count; k++) {
                                blocks[i + k][j].boom = true;
                            }
                        }
                    }
                    // 세로
                    {
                        int count = 1;
                        while (blocks[i][j + count].index == tIndex && j + count < 8) {
                            count++;
                        }
                        if (count >= 3) {
                            for (int k = 0; k < count; k++) {
                                blocks[i][j + k].boom = true;
                            }
                        }
                    }
                    // 대각선1
                    {
                        int count = 1;
                        while (blocks[i + count][j + count].index == tIndex && i + count < 8 && j + count < 8) {
                            count++;
                        }
                        if (count >= 3) {
                            for (int k = 0; k < count; k++) {
                                blocks[i + k][j + k].boom = true;
                            }
                        }
                    }
                    // 대각선2
                    {
                        int count = 1;
                        while (blocks[i - count][j + count].index == tIndex && i - count > 0 && j + count < 8) {
                            count++;
                        }
                        if (count >= 3) {
                            for (int k = 0; k < count; k++) {
                                blocks[i - k][j + k].boom = true;
                            }
                        }
                    }
                }
            }
        }

        for (Block[] temp : blocks) {
            for (Block block : temp) {
                if (block.boom) {
                    block = null;
                    movingBlocks++;
                    continue;
                }
                if (block.isMoving)
                {
                    movingBlocks++;
                }
                block.update();
            }
        }

        // 움직이고 있는 블럭 체크
        if (movingBlocks != 0) {
            canMove = false;
        } else {
            canMove = true;
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

        if (canMove && myTurn){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (CollisionHelper.collides(blocks[i][j], x, y))
                    {
                        if (selected != null)
                        {
                            if (selected != blocks[i][j] && Math.abs(i - selectX) <= 1 && Math.abs(j - selectY) <= 1) {
                                selected.select(false);
                                swap(selected, blocks[i][j]);
                                Block temp = selected;
                                blocks[selectX][selectY] = blocks[i][j];
                                blocks[i][j] = temp;
                                selected = null;
                                canMove = false;
                            }
                            else
                            {
                                selected.select(false);
                                selected = blocks[i][j];
                                blocks[i][j].select(true);
                                selectX = i;
                                selectY = j;
                            }
                        }
                        else
                        {
                            selected = blocks[i][j];
                            blocks[i][j].select(true);
                            selectX = i;
                            selectY = j;
                        }

                        return true;
                    }
                }
            }
        }


        return false;
    }

}
