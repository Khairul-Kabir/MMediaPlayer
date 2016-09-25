package com.example.sshak.mmediaplayer;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;

public class SongList extends AppCompatActivity {


    ListView listView;

    String[] songs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        initialization();
    }

    private void initialization() {
        listView = (ListView)findViewById(R.id.listViewPlayList);
        final ArrayList<File> songList = songsFromSDCard(Environment.getExternalStorageDirectory());
        songs = new String[songList.size()];
        for(int i=0;i<songList.size();i++){
            songs[i]=songList.get(i).getName().toString().replace(".mp3","").replace(".wav","").replace(".aac","");
        }
        ArrayAdapter<String> adpSongs = new ArrayAdapter<String>(getApplicationContext(),R.layout.mp3_layout,R.id.mp3_text_layout,songs);
        listView.setAdapter(adpSongs);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SongList.this,Mp3_Player.class).putExtra("song_position",position).putExtra("all_song",songList);
                startActivity(intent);
            }
        });
    }

    public ArrayList<File> songsFromSDCard(File root){

        ArrayList<File> arrayList = new ArrayList<File>();
        File[] files = root.listFiles();
        for(File singleFile:files){
            if(singleFile.isDirectory()&& !singleFile.isHidden()){
                arrayList.addAll(songsFromSDCard(singleFile));

            }else {
                if(singleFile.getName().endsWith(".mp3")||singleFile.getName().endsWith(".wav")||singleFile.getName().endsWith(".aac")){

                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }
}
