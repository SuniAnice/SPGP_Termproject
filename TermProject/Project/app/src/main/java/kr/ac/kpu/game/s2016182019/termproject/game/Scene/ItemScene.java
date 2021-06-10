package kr.ac.kpu.game.s2016182019.termproject.game.Scene;

import android.view.MotionEvent;

import java.util.Random;

import kr.ac.kpu.game.s2016182019.termproject.R;
import kr.ac.kpu.game.s2016182019.termproject.framework.GameObject;
import kr.ac.kpu.game.s2016182019.termproject.framework.game.MainGame;
import kr.ac.kpu.game.s2016182019.termproject.framework.view.GameView;
import kr.ac.kpu.game.s2016182019.termproject.game.UI.Text;
import kr.ac.kpu.game.s2016182019.termproject.game.object.ImageObject;
import kr.ac.kpu.game.s2016182019.termproject.game.object.ItemUI;

public class ItemScene extends Scene {

    enum Layer {
        bg, COUNT
    }
    public static ItemScene scene;
    public void add(ItemScene.Layer layer, GameObject obj) {
        add(layer.ordinal(), obj);
    }
    @Override
    public void start() {
        super.start();
        transparent = true;
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();
        initLayers(TitleScene.Layer.COUNT.ordinal());

        add(ItemScene.Layer.bg, new ImageObject(R.mipmap.itemscene,w/2, 0 , 0.8f, h/2));

        Random r = new Random();
        add(Layer.bg, new ItemUI(w / 2 - 500, 0, r.nextInt(ItemUI.items.length), h/2 + 100));
        add(Layer.bg, new ItemUI(w / 2, 0, r.nextInt(ItemUI.items.length), h/2 + 100));
        add(Layer.bg, new ItemUI(w / 2 + 500, 0, r.nextInt(ItemUI.items.length), h/2 + 100));


    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            MainGame.get().popScene();
            MainGame.get().push(new ChooseScene());
        }
        return super.onTouchEvent(e);
    }
}
