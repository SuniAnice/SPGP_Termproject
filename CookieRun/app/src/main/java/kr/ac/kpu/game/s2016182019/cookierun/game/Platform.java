package kr.ac.kpu.game.s2016182019.cookierun.game;

import java.util.Random;

import kr.ac.kpu.game.s2016182019.cookierun.R;
import kr.ac.kpu.game.s2016182019.cookierun.framework.ImageObject;
import kr.ac.kpu.game.s2016182019.cookierun.framework.view.GameView;

public class Platform extends ImageObject {
    public static int SPEED = 150;

    public float getRight() {
        return dstRect.right;
    }

    public enum Type{
        T_10x2, T_2x2, T_3x1, RANDOM;
        int resId() {
            switch (this) {
                case T_10x2: return R.mipmap.cookierun_platform_480x48;
                case T_2x2: return R.mipmap.cookierun_platform_124x120;
                case T_3x1: return R.mipmap.cookierun_platform_120x40;
                default: return 0;
            }
        }
    }
    public Platform(Type type, float x, float y){
//        int resId= type ==;
        if (type == Type.RANDOM) {
            Random r = new Random();
            type = r.nextInt(2) == 0 ? Type.T_10x2 : Type.T_2x2;
        }
        init(type.resId(), x, y);
        final float UNIT = 40 * GameView.MULTIPLIER;
        float w = UNIT * 10;
        float h = UNIT * 2;
        dstRect.set(x, y, x + w, x + h);
    }

    @Override
    public void update() {
        MainGame game = MainGame.get();
        float dx = SPEED * game.frameTime;
        dstRect.offset(-dx, 0);
        if (getRight() < 0) {
            game.remove(this);
        }
    }
}
