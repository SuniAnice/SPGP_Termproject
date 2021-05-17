package kr.ac.kpu.game.s2016182019.cookierun.game;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182019.cookierun.framework.GameObject;
import kr.ac.kpu.game.s2016182019.cookierun.framework.view.GameView;

public class StageMap implements GameObject {
    @Override
    public void update() {
        MainGame game = MainGame.get();
        ArrayList<GameObject> objects = game.objectsAt(MainGame.Layer.platform);
        float rightMost = 0;
        for (GameObject obj : objects) {
            Platform platform = (Platform) obj;
            float right = platform.getRight();
            if (rightMost < right) {
                rightMost = right;
            }
        }
        float vw = GameView.view.getWidth();
        if (rightMost < vw) {
            // create Platform
            float tx = rightMost, ty = 20;
            Platform platform = new Platform(Platform.Type.RANDOM, tx, ty);
            game.add(MainGame.Layer.platform, platform);
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
