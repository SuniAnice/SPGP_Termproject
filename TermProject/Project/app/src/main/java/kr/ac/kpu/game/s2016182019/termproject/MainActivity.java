package kr.ac.kpu.game.s2016182019.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.ac.kpu.game.s2016182019.termproject.framework.game.MainGame;

public class MainActivity extends AppCompatActivity {

    private MainGame basegame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        basegame = new MainGame();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}