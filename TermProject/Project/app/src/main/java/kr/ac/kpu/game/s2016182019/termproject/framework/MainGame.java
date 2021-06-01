package kr.ac.kpu.game.s2016182019.termproject.framework;

import kr.ac.kpu.game.s2016182019.termproject.game.MainScene;

public class MainGame extends BaseGame {
    private boolean initialized;

    public static MainGame get() {
        return (MainGame) instance;
    }


    @Override
    public boolean initResources() {
        if (initialized) {
            return false;
        }

        push(new MainScene());

        initialized = true;
        return true;

    }
}
