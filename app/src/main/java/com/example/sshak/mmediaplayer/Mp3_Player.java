package com.example.sshak.mmediaplayer;


import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class Mp3_Player extends AppCompatActivity implements View.OnClickListener{

    static MediaPlayer mediaPlayer;
    ArrayList<File> songList;
    int position;
    Uri uri;
    String songName;
    SeekBar seekBar;
    Thread seekBarUpdate;
    TextView currentSongName;
    ImageButton btnPlay,btnFF,btnNext,btnFB,btnPrev;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3__player);

        initialization();

        seekBarUpdate = new Thread(){
            @Override
            public void run() {
                int totalDuration = mediaPlayer.getDuration();
                int currentPosition = 0;
                while (currentPosition<totalDuration){
                    try {
                        sleep(500);
                        currentPosition=mediaPlayer.getCurrentPosition();
                        seekBar.setProgress(currentPosition);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        songList = (ArrayList)bundle.getParcelableArrayList("all_song");
        position = bundle.getInt("song_position",0);
        uri = Uri.parse(songList.get(position).toString());
        songName = songList.get(position).getName().toString();
        mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);
        mediaPlayer.start();
        currentSongName.setText(songName);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBarUpdate.start();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }

    private void initialization() {
        btnPlay = (ImageButton)findViewById(R.id.btnPlay);
        btnFF = (ImageButton)findViewById(R.id.btnFF);
        btnNext = (ImageButton)findViewById(R.id.btnNext);
        btnFB = (ImageButton)findViewById(R.id.btnFB);
        btnPrev = (ImageButton)findViewById(R.id.btnPrev);
        seekBar = (SeekBar)findViewById(R.id.seekBarStatus);
        currentSongName = (TextView)findViewById(R.id.txtNowPlaying);

        btnPlay.setOnClickListener(this);
        btnFF.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnFB.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnPlay:
                if(mediaPlayer.isPlaying()){
                    btnPlay.setImageResource(R.drawable.play);
                    mediaPlayer.pause();
                }else {
                    btnPlay.setImageResource(R.drawable.pause);
                    mediaPlayer.start();
                }
                break;
            case R.id.btnFF:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()+5000);
                break;
            case R.id.btnFB:
                mediaPlayer.seekTo(mediaPlayer.getCurrentPosition()-5000);
                break;
            case R.id.btnNext:
                mediaPlayer.stop();
                mediaPlayer.release();
                position = (position+1)%songList.size();
                uri = Uri.parse(songList.get(position).toString());
                songName = songList.get(position).getName().toString();
                mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);
                mediaPlayer.start();
                currentSongName.setText(songName);
                seekBar.setMax(mediaPlayer.getDuration());
                break;
            case R.id.btnPrev:
                mediaPlayer.stop();
                mediaPlayer.release();
                if(position-1<0){
                    position=songList.size()-1;
                }else {
                    position=position-1;
                }
                uri = Uri.parse(songList.get(position).toString());
                songName = songList.get(position).getName().toString();
                mediaPlayer = MediaPlayer.create(getApplicationContext(),uri);
                mediaPlayer.start();
                currentSongName.setText(songName);
                seekBar.setMax(mediaPlayer.getDuration());
                break;
        }
    }
}
