package kr.ac.kpu.game.s1234567.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mainTextView;
    private ImageView mainImageView;
    private int CurrentImage = 3;
    private static int[] Imagearray =
            {
                    R.mipmap.cat1,
                    R.mipmap.cat2,
                    R.mipmap.cat3,
                    R.mipmap.cat4,
                    R.mipmap.cat5
            };
    private ImageButton prevButton;
    private ImageButton nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTextView = findViewById(R.id.mainTextView);
        mainTextView.setText("Program started");

        mainImageView = findViewById(R.id.mainImageView);

        prevButton = findViewById(R.id.helloButton);
        nextButton = findViewById(R.id.worldButton);


        showImage();

//        Button helloButton = findViewById(R.id.helloButton);
//        helloButton.setOnClickListener(this);
//
//        Button worldButton = findViewById(R.id.worldButton);
//        worldButton.setOnClickListener(this);
    }

    public void onBtnPrev(View view) {
        if (CurrentImage == 1)
        {
            return; // early return
        }

        CurrentImage--;
        showImage();

    }

    private void showImage() {
        mainTextView.setText(CurrentImage + " / " + Imagearray.length);
        mainImageView.setImageResource(Imagearray[CurrentImage - 1]);

        prevButton.setEnabled(CurrentImage != 1);
        nextButton.setEnabled(CurrentImage != Imagearray.length);
    }

    public void onBtnNext(View view) {
        if (CurrentImage == 5)
        {
            return;
        }
        CurrentImage++;
        showImage();

    }
}









