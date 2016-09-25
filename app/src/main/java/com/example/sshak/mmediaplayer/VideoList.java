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

public class VideoList extends AppCompatActivity {


    ListView listView;

    String[] videos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        initialization();
    }

    private void initialization() {
        listView = (ListView)findViewById(R.id.listViewPlayList);
        final ArrayList<File> videoList = songsFromSDCard(Environment.getExternalStorageDirectory());
        videos = new String[videoList.size()];
        for(int i=0;i<videoList.size();i++){
            videos[i]=videoList.get(i).getName().toString();
        }
        ArrayAdapter<String> adpSongs = new ArrayAdapter<String>(getApplicationContext(),R.layout.mp3_layout,R.id.mp3_text_layout,videos);
        listView.setAdapter(adpSongs);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(VideoList.this,Mp3_Player.class).putExtra("song_position",position).putExtra("all_song",videoList);
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
                if(singleFile.getName().endsWith(".mp4")||singleFile.getName().endsWith(".wmv")||singleFile.getName().endsWith(".mkv")||singleFile.getName().endsWith(".webm")){

                    arrayList.add(singleFile);
                }
            }
        }
        return arrayList;
    }
}
