package kr.ac.kpu.game.s2016182019.termproject.game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

import java.util.Random;

import kr.ac.kpu.game.s2016182019.termproject.R;
import kr.ac.kpu.game.s2016182019.termproject.framework.AnimationGameBitmap;
import kr.ac.kpu.game.s2016182019.termproject.framework.BaseGame;
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
    public int turn = 5;
    private int movingBlocks = 0;

    public Block blocks[][];
    private int selectX;
    private int selectY;

    public static kr.ac.kpu.game.s2016182019.termproject.game.Board get() {
        if (instance == null) {
            instance = new kr.ac.kpu.game.s2016182019.termproject.game.Board();
        }
        return instance;
    }

    public Board() {
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
                blocks[i][j] = new Block((int)(gridX * (i + 0.5) + offsetX), (int)(gridY * (j + 0.5) + offsetY), r.nextInt(7));
            }
        }
    }
    @Override
    public void update() {
        BaseGame game = BaseGame.get();
        movingBlocks = 0;
        Random r = new Random();

        if (canMove) {
            // 연속된 블럭 체크
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (blocks[i][j] != null)
                    {
                        Block.blockType tIndex = blocks[i][j].type;
                        // 가로
                        {
                            int count = 1;
                            while (i + count < 8) {
                                if (blocks[i + count][j].type == tIndex) {
                                    count++;
                                } else {
                                    break;
                                }
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
                            while (j + count < 8) {
                                if (blocks[i][j + count].type == tIndex){
                                    count++;
                                } else {
                                    break;
                                }
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
                            while (i + count < 8 && j + count < 8) {
                                if (blocks[i + count][j + count].type == tIndex){
                                    count++;
                                } else {
                                    break;
                                }
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
                            while (i - count > 0 && j + count < 8) {
                                if (blocks[i - count][j + count].type == tIndex){
                                    count++;
                                } else {
                                    break;
                                }
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
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (blocks[i][j] != null) {
                    if (blocks[i][j].boom) {
                        switch (blocks[i][j].type){
                            case Red:
                                MainScene.scene.player.manaRed++;
                                break;
                            case Green:
                                MainScene.scene.player.manaGreen++;
                                break;
                            case Blue:
                                MainScene.scene.player.manaBlue++;
                                break;
                            case Black:
                                MainScene.scene.player.manaBlack++;
                                break;
                            case White:
                                MainScene.scene.player.manaWhite++;
                                break;
                            case Sword:
                                if (MainScene.scene.enemy.hp > 0)
                                    MainScene.scene.enemy.hp--;
                                break;
                            case Shield:
                                if (MainScene.scene.enemy.currAttack > 0)
                                    MainScene.scene.enemy.currAttack--;
                                break;
                        }
                        Effector e = new Effector(new AnimationGameBitmap(R.mipmap.explosion,8,8),blocks[i][j].x, blocks[i][j].y, 0.9f);
                        MainScene.scene.add(MainScene.Layer.effect, e);
                        blocks[i][j] = null;
                        movingBlocks++;
                        continue;
                    }
                    if (j < 7) {
                        if (blocks[i][j + 1] == null) {
                            blocks[i][j].moveto((int)(gridX * (i + 0.5) + offsetX), (int)(gridY * (j + 1.5) + offsetY));
                            blocks[i][j + 1] = blocks[i][j];
                            blocks[i][j] = null;
                            movingBlocks++;
                            continue;
                        }
                    }
                    if (blocks[i][j].isMoving) {
                        movingBlocks++;
                    }
                    blocks[i][j].update();

                }
            }
        }

        for (int i = 0; i< 8;i++){
            if (blocks[i][0] == null){
                blocks[i][0] = new Block((int)(gridX * (i + 0.5) + offsetX), (int)(gridY * (-0.5) + offsetY), r.nextInt(7));
                blocks[i][0].moveto((int)(gridX * (i + 0.5) + offsetX), (int)(gridY * (0.5) + offsetY));
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
                if (block != null)
                    block.draw(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        BaseGame game = BaseGame.get();
        float x = event.getX();
        float y = event.getY();

        if (canMove && myTurn){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (CollisionHelper.collides(blocks[i][j], x, y))
                    {
                        if (selected != null)
                        {
                            if (selected == blocks[i][j]) {
                                selected.select(false);
                                selected = null;
                            }
                            else if (Math.abs(i - selectX) <= 1 && Math.abs(j - selectY) <= 1) {
                                selected.select(false);
                                swap(selected, blocks[i][j]);
                                Block temp = selected;
                                blocks[selectX][selectY] = blocks[i][j];
                                blocks[i][j] = temp;
                                selected = null;
                                canMove = false;
                                turn--;
                                if (turn == 0) {
                                    turn = MainScene.scene.enemy.turn;
                                    MainScene.scene.enemy.doAttack();
                                }
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
