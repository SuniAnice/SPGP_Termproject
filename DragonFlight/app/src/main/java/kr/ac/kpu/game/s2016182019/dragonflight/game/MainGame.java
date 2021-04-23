package kr.ac.kpu.game.s2016182019.dragonflight.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.kpu.game.s2016182019.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2016182019.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016182019.dragonflight.framework.Recycleable;
import kr.ac.kpu.game.s2016182019.dragonflight.ui.view.GameView;
import kr.ac.kpu.game.s2016182019.dragonflight.utils.CollisionHelper;

public class MainGame {

    private static final String TAG = MainGame.class.getSimpleName();
    // singleton
    static MainGame instance;
    private Player player;
    public float frameTime;

    private  boolean initialized;

    public static MainGame get() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    private static ArrayList<GameObject> objects = new ArrayList<>();
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

        //Random rand = new Random();

        player = new Player(w/2, h-300);
        objects.add(player);
        objects.add(new EnemyGenerator());

        initialized = true;

        return true;
    }

    public void update() {
        if (!initialized) return;
        Log.d("TAG", "Update()");
        for (GameObject o : objects) {
            o.update();
        }

        for (GameObject o1 : objects) {
            if (!(o1 instanceof Enemy)) {
                continue;
            }
            boolean removed = false;
            Enemy enemy = (Enemy) o1;
            for (GameObject o2 : objects) {
                if (!(o2 instanceof Bullet)) {
                    continue;
                }
                Bullet bullet = (Bullet) o2;
                if (CollisionHelper.collides(enemy, bullet)) {
                    //Log.d(TAG, "Collision! " + o1 + "-" + o2);
                    remove(enemy);
                    remove(bullet);
                    recycle(bullet);
                    removed = true;
                    break;
                }

            }
            if (removed) {
                continue;
            }
            if (CollisionHelper.collides((BoxCollidable)enemy, player)) {
                //Log.d(TAG, "Collision : Enemy - player");
            }
        }
    }

    public void draw(Canvas canvas) {
        if (!initialized) return;
        for (GameObject o : objects) {
            o.draw(canvas);
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

    public void add(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                objects.add(gameObject);

            }
        });
    }

    public void remove(GameObject gameObject) {
        if (gameObject instanceof Recycleable) {
            ((Recycleable) gameObject).recycle();
            recycle(gameObject);
        }
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                objects.remove(gameObject);
            }
        });
    }

}
