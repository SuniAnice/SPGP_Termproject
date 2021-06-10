package kr.ac.kpu.game.s2016182019.termproject.game.object;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.kpu.game.s2016182019.termproject.R;
import kr.ac.kpu.game.s2016182019.termproject.framework.BoxCollidable;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameBitmap;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameObject;
import kr.ac.kpu.game.s2016182019.termproject.framework.game.MainGame;
import kr.ac.kpu.game.s2016182019.termproject.game.Scene.MainScene;

public class ItemUI implements GameObject, BoxCollidable {
    private final int x;
    private int y;
    public final int index;
    private int gy = -1;
    private GameBitmap bitmap;

    public static int[] items = {
            R.mipmap.item1, R.mipmap.item2, R.mipmap.item3,
    };

    public ItemUI(int x, int y, int index, int gy) {
        this.x = x;
        this.y = y;
        this.index = index;
        this.bitmap = new GameBitmap(items[index]);
        this.gy = gy;
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        if (gy > 0) {
            y += game.frameTime * 500;
            gy -= game.frameTime * 500;
        }
    }

    public void get() {
        switch (index) {
            case 0:
                MainScene.scene.player.defence++;
                break;
            case 1:
                MainScene.scene.player.attack++;
                break;
            case 2:
                MainScene.scene.player.fire_orb++;
                break;
            default:
                break;
        }
    }


    @Override
    public void draw(Canvas canvas) {
        if (bitmap != null)
            bitmap.draw(canvas, x, y, 0.8f);
    }

    @Override
    public void getBoundingRect(RectF rect) {
        bitmap.getBoundingRect(x, y, rect, 0.8f);
    }
}
