package kr.ac.kpu.game.s2016182019.cookierun.framework;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.kpu.game.s2016182019.cookierun.R;
import kr.ac.kpu.game.s2016182019.cookierun.framework.utils.CollisionHelper;
import kr.ac.kpu.game.s2016182019.cookierun.framework.view.GameView;
import kr.ac.kpu.game.s2016182019.cookierun.game.Player;
import kr.ac.kpu.game.s2016182019.cookierun.game.Score;


public class MainGame {

    private static final String TAG = MainGame.class.getSimpleName();
    // singleton
    static MainGame instance;
    private Player player;
    public float frameTime;

    private  boolean initialized;
    private Score score;

    public static MainGame get() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    private static ArrayList<ArrayList<GameObject>> layers;

    private static HashMap<Class, ArrayList<GameObject>> recycleBin = new HashMap<>();

    public void recycle(GameObject object) {
        Class clazz = object.getClass();
        ArrayList<GameObject> array = recycleBin.get(clazz);
        if (array == null) {
            array = new ArrayList<>();
            recycleBin.put(clazz, array);
        }
        array.add(object);
    }

    public GameObject get(Class clazz) {
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

        //Random rand = new Random();

        player = new Player(w/2, h-300);
        //layers.get(Layer.player.ordinal()).add(player);
        add(Layer.player, player);
        //add(Layer.controller, new EnemyGenerator());

        int margin = (int) (20 * GameView.MULTIPLIER);
        score = new Score(w - margin, margin);
//        score.setScore(123459);
        add(Layer.ui, score);

        HorizontalScrollBackground bg1 = new HorizontalScrollBackground(R.mipmap.cookie_run_bg_1, 20);
        add(Layer.bg, bg1);

        HorizontalScrollBackground bg2 = new HorizontalScrollBackground(R.mipmap.cookie_run_bg_2, 40);
        add(Layer.bg, bg2);

        HorizontalScrollBackground bg3 = new HorizontalScrollBackground(R.mipmap.cookie_run_bg_3, 80);
        add(Layer.bg, bg3);

        initialized = true;

        return true;
    }

    public enum Layer {
        bg, enemy, bullet, player, bg2, ui, controller, ENEMY_COUNT
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
            for (GameObject o : objects) {
                o.update();
            }
        }

    }

    public void draw(Canvas canvas) {
        if (!initialized) return;
        for (ArrayList<GameObject> objects : layers) {
            for (GameObject o : objects) {
                o.draw(canvas);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE) {
            player.moveTo(event.getX(), event.getY());
            return true;
        }
        return false;
    }

    public void add(Layer layer, GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                ArrayList<GameObject> objects = layers.get(layer.ordinal());
                objects.add(gameObject);

            }
        });
    }

    public void remove(GameObject gameObject) {
        remove(gameObject, true);
    }
    public void remove(GameObject gameObject, boolean delayed) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (ArrayList<GameObject> objects: layers) {
                    boolean removed = objects.remove(gameObject);
                    if (removed) {
                        if (gameObject instanceof Recycleable) {
                            ((Recycleable) gameObject).recycle();
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
