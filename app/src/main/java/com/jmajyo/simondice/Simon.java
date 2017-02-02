package com.jmajyo.simondice;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Simon {
    private static final int ONE_SECOND = 1000;
    private int level;                      //current level
    private final int[] sounds;             //sound to play
    private final Button[] buttons;         //game buttons
    private List<Integer> moves;            //game moves (sounds & button to highlight
    private MediaPlayer mpSound;
    private Context context;                //weakify
    private int speed=2000;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Simon(Context context, int[] sounds, Button[] buttons) {
        this.context = context;
        this.sounds = sounds;
        this.buttons = buttons;
    }

    public void nextMove(){
        level = level + 1;

        moves.add(nextRandomMove());

        playMoves();
    }

    private void playMoves() {
        for (Integer i : moves) {
            Log.d("Simon","move " + i);

            playSound(i);

            //TODO: click button

            SystemClock.sleep(speed);    //sleep the system one second
        }
    }

    public void playSound(Integer i) {
        if (mpSound != null){
            mpSound.release();
            mpSound = null;
        }
        mpSound = MediaPlayer.create(context, sounds[i]);
        mpSound.start();
    }

    public void reset(){
        level = 0;
        moves = new LinkedList<>();
    }

    private int nextRandomMove(){
        Random rnd = new Random();
        return rnd.nextInt(4);      // Go from 0 to 3
    }

    public boolean checkMoves(List<Integer> myMoves) {
        boolean check = true;

        if(myMoves == null){
            return false;
        }

        for (int i =0; i< myMoves.size(); i++) {
            Integer simonMoves = moves.get(i);
            Integer myMove = myMoves.get(i);
            if(!myMove.equals(simonMoves)){
                check = false;
                break;
            }
        }
        SystemClock.sleep(ONE_SECOND);
        return check;
    }

    public int getLevel() {
        return level;
    }
}
