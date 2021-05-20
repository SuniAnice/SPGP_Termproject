package kr.ac.kpu.game.s2016182019.termproject.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.kpu.game.s2016182019.termproject.framework.GameObject;
import kr.ac.kpu.game.s2016182019.termproject.framework.view.GameView;


public class MainGame {

    // singleton
    static kr.ac.kpu.game.s2016182019.termproject.game.MainGame instance;
    public float frameTime;

    private  boolean initialized;
    private Board board;
    public Player player;

    public static kr.ac.kpu.game.s2016182019.termproject.game.MainGame get() {
        if (instance == null) {
            instance = new kr.ac.kpu.game.s2016182019.termproject.game.MainGame();
        }
        return instance;
    }

    private static ArrayList<ArrayList<GameObject>> layers;

    private static HashMap<Class, ArrayList<GameObject>> recycleBin = new HashMap<>();

    public void recycle(kr.ac.kpu.game.s2016182019.termproject.framework.GameObject object) {
        Class clazz = object.getClass();
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if (array == null) {
            array = new ArrayList<>();
            recycleBin.put(clazz, array);
        }
        array.add(object);
    }

    public kr.ac.kpu.game.s2016182019.termproject.framework.GameObject get(Class clazz) {
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if (array == null || array.isEmpty()) return null;
        return array.remove(0);
    }



    public boolean initResources() {
        if (initialized) {
            return false;
        }
        int w = GameView.view.getWidth();
        int h = GameView.view.getHeight();

        initLayers(Layer.ENEMY_COUNT.ordinal());

        board = new Board();
        add(Layer.board, board);

        player = new Player();
        add(Layer.player, player);

        initialized = true;

        return true;
    }

    public ArrayList<GameObject> objectsAt(int layerIndex) {
        return layers.get(layerIndex);
    }

    public ArrayList<GameObject> objectsAt(Layer layer) {
        return objectsAt(layer.ordinal());
    }

    public enum Layer {
        bg,  player, board ,ui, controller, ENEMY_COUNT
    }

    private void initLayers(int layerCount) {
        layers = new ArrayList<>();
        for (int i = 0; i < layerCount; i++) {
            layers.add(new ArrayList<>());
        }
    }

    public void update() {
        if (!initialized) return;
        Log.d("TAG", "Update()");
        for (ArrayList<GameObject> objects : layers) {
            for (kr.ac.kpu.game.s2016182019.termproject.framework.GameObject o : objects) {
                o.update();
            }
        }

    }

    public void draw(Canvas canvas) {
        if (!initialized) return;
        for (ArrayList<GameObject> objects : layers) {
            for (kr.ac.kpu.game.s2016182019.termproject.framework.GameObject o : objects) {
                o.draw(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            board.onTouchEvent(event);
            return true;
        }
        return false;
    }

    public void add(Layer layer, kr.ac.kpu.game.s2016182019.termproject.framework.GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layer.ordinal());
                objects.add(gameObject);

            }
        });
    }

    public void remove(kr.ac.kpu.game.s2016182019.termproject.framework.GameObject gameObject) {
        remove(gameObject, true);
    }
    public void remove(kr.ac.kpu.game.s2016182019.termproject.framework.GameObject gameObject, boolean delayed) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject> objects: layers) {
                    boolean removed = objects.remove(gameObject);
                    if (removed) {
                        if (gameObject instanceof kr.ac.kpu.game.s2016182019.termproject.framework.Recycleable) {
                            ((kr.ac.kpu.game.s2016182019.termproject.framework.Recycleable) gameObject).recycle();
                            recycle(gameObject);
                        }
                        //Log.d(TAG, "Removed: " + gameObject);
                        break;
                    }
                }
            }
        };
        if (delayed) {
            GameView.view.post(runnable);
        } else {
            runnable.run();
        }
    }
}
