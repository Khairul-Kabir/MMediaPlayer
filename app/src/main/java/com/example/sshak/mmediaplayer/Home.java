package com.example.sshak.mmediaplayer;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends Activity {

    Button gallery,mp3Player,videoPlayer,fm,onlinePlayer,about,help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initialization();
        onClick();
    }

    private void initialization() {
        mp3Player = (Button)findViewById(R.id.btnMp3Player);
        Typeface mp3Font = Typeface.createFromAsset(getAssets(),"font/SpecialElite.ttf");
        mp3Player.setTypeface(mp3Font);

        gallery = (Button)findViewById(R.id.btnGallery);
        Typeface galleryFont = Typeface.createFromAsset(getAssets(),"font/SpecialElite.ttf");
        gallery.setTypeface(galleryFont);

        videoPlayer = (Button)findViewById(R.id.btnVideoPlayer);
        Typeface videoFont = Typeface.createFromAsset(getAssets(),"font/SpecialElite.ttf");
        videoPlayer.setTypeface(videoFont);

        fm = (Button)findViewById(R.id.btnFM);
        Typeface FmFont = Typeface.createFromAsset(getAssets(),"font/SpecialElite.ttf");
        fm.setTypeface(FmFont);

        onlinePlayer = (Button)findViewById(R.id.btnOnlinePlayer);
        Typeface onlineFont = Typeface.createFromAsset(getAssets(),"font/SpecialElite.ttf");
        onlinePlayer.setTypeface(onlineFont);

        about = (Button)findViewById(R.id.btnAbout);
        Typeface aboutFont = Typeface.createFromAsset(getAssets(),"font/SpecialElite.ttf");
        about.setTypeface(aboutFont);

        help = (Button)findViewById(R.id.btnHelp);
        Typeface helpFont = Typeface.createFromAsset(getAssets(),"font/SpecialElite.ttf");
        help.setTypeface(helpFont);
    }

    private void onClick() {
        mp3Player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,SongList.class);
                startActivity(intent);
            }
        });

        videoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,VideoList.class);
                startActivity(intent);
            }
        });
    }
}
