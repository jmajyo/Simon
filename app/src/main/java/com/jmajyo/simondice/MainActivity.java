package com.jmajyo.simondice;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int ONE_SECOND = 1000;
    private static final int TWO_SECOND = 2000;
    private static final int HALF_SECOND = 500;

    private MediaPlayer mpSound =null;

    private Button buttonGreen;
    private Button buttonRed;
    private Button buttonYellow;
    private Button buttonBlue;
    private Button buttonStart;
    Simon simon;
    List<Integer> myMoves;
    int[] sounds={
            R.raw.sounds_green,
            R.raw.sound_red,
            R.raw.sounds_yellow,
            R.raw.sounds_blue,
            R.raw.game_over
    };
    Button[] buttons;

    private static final int GREEN_BUTTON = 0;
    private static final int RED_BUTTON = 1;
    private static final int YELLOW_BUTTON = 2;
    private static final int BLUE_BUTTON = 3;
    private static final int GAME_OVER = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGreen = (Button) findViewById(R.id.activity_main___green_button);
        buttonRed = (Button) findViewById(R.id.activity_main___red_button);
        buttonYellow = (Button) findViewById(R.id.activity_main___yellow_button);
        buttonBlue = (Button) findViewById(R.id.activity_main___blue_button);
        buttonStart = (Button) findViewById(R.id.activity_main___start_button);

        buttons = new Button[]{
                buttonGreen,
                buttonRed,
                buttonYellow,
                buttonBlue
        };

        disabledButons();
        simon = new Simon(MainActivity.this,sounds, buttons);


         buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //simon = new Simon(MainActivity.this,sounds, buttons);
                simon.reset();
                simon.nextMove();

                myMoves = new LinkedList<>();
                buttonGreen.setEnabled(true);
                buttonRed.setEnabled(true);
                buttonYellow.setEnabled(true);
                buttonBlue.setEnabled(true);
            }
        });

        buttonGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButton(GREEN_BUTTON);
            }
        });
        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButton(RED_BUTTON);
            }
        });
        buttonYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButton(YELLOW_BUTTON);
            }
        });
        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleButton(BLUE_BUTTON);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_main_action___noob) {
            simon.setSpeed(TWO_SECOND);
        }
        else if (id == R.id.menu_main_action___medium){
            simon.setSpeed(ONE_SECOND);
        }
        else if (id == R.id.menu_main_action___pro){
            simon.setSpeed(HALF_SECOND);
        }

        return super.onOptionsItemSelected(item);
    }

    private void disabledButons() {
        buttonGreen.setEnabled(false);
        buttonRed.setEnabled(false);
        buttonYellow.setEnabled(false);
        buttonBlue.setEnabled(false);
    }

    private void handleButton(int button) {
        simon.playSound(button);
        myMoves.add(button);

        boolean correct = simon.checkMoves(myMoves);
        if(correct == true){
            if(myMoves.size() == simon.getLevel()){
                simon.nextMove();
                myMoves = new LinkedList<Integer>();
            }
        }else{
            disabledButons();
            simon.playSound(GAME_OVER);
            simon.reset();
            myMoves = new LinkedList<Integer>();
        }
    }
}