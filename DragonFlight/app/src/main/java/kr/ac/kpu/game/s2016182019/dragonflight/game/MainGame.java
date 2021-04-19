package kr.ac.kpu.game.s2016182019.dragonflight.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.kpu.game.s2016182019.dragonflight.framework.BoxCollidable;
import kr.ac.kpu.game.s2016182019.dragonflight.framework.GameObject;
import kr.ac.kpu.game.s2016182019.dragonflight.ui.view.GameView;
import kr.ac.kpu.game.s2016182019.dragonflight.utils.CollisionHelper;

public class MainGame {

    private static final String TAG = MainGame.class.getSimpleName();
    // singleton
    static MainGame instance;
    private Player player;

    public static MainGame get() {
        if (instance == null) {
            instance = new MainGame();
        }
        return instance;
    }

    ArrayList<GameObject> objects = new ArrayList<>();
    public float frameTime;

    private  boolean initialized;

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
            for (GameObject o2 : objects) {
                if (!(o2 instanceof Bullet) || (o2 instanceof Player)) {
                    continue;
                }
                if (CollisionHelper.collides((BoxCollidable) o1, (BoxCollidable)o2)) {
                    Log.d(TAG, "Collision! " + o1 + "-" + o2);
                }
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
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                objects.remove(gameObject);
            }
        });


    }
}
