package kr.ac.kpu.game.s2016182019.termproject.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Random;

import kr.ac.kpu.game.s2016182019.termproject.R;
import kr.ac.kpu.game.s2016182019.termproject.framework.BoxCollidable;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameBitmap;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameObject;

public class SkillUI implements GameObject, BoxCollidable {
    private final int x;
    private final int y;
    private int skillIndex;

    public int[] skills = {
            R.mipmap.skill1, R.mipmap.skill2, R.mipmap.skill3, R.mipmap.skill4
    };
    private GameBitmap bitmap;

    public SkillUI(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        if (bitmap != null)
            bitmap.draw(canvas, x, y);
    }

    public void setSkill(int index) {
        skillIndex = index;
        this.bitmap = new GameBitmap(skills[index]);
    }

    @Override
    public void getBoundingRect(RectF rect) {
        bitmap.getBoundingRect(x, y, rect);
    }

    public void useSkill() {
        MainGame game = MainGame.get();

        switch (skillIndex) {
            case 0:
                if (game.player.useMana(0,5,5,5,5))
                {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (game.board.blocks[i][j] != null) {
                                if (game.board.blocks[i][j].type == Block.blockType.Blue || game.board.blocks[i][j].type == Block.blockType.Green)
                                {
                                    game.board.blocks[i][j].change(Block.blockType.Red);
                                }
                            }
                        }
                    }
                }

                break;
            case 1:
                if (game.player.useMana(10,0,0,0,0))
                {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (game.board.blocks[i][j] != null) {
                                if (game.board.blocks[i][j].type == Block.blockType.Red)
                                {
                                    game.board.blocks[i][j].change(Block.blockType.Sword);
                                }
                            }
                        }
                    }
                }
                break;
            case 2:
                if (game.player.useMana(0,0,0,0,10))
                {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (game.board.blocks[i][j] != null) {
                                if (game.board.blocks[i][j].type == Block.blockType.White)
                                {
                                    game.board.blocks[i][j].change(Block.blockType.Shield);
                                }
                            }
                        }
                    }
                }
                break;
            case 3:
                if (game.player.useMana(0,0,0,10,0))
                {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (game.board.blocks[i][j] != null) {
                                Random r = new Random();
                                game.board.blocks[i][j].change(Block.blockType.values()[r.nextInt(7)]);
                            }
                        }
                    }
                }
                break;
        }
    }
}
