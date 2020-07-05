package com.sys.appsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class VideoRecord extends AppCompatActivity {
private static final int MY_PERMISSION_REQUEST=1;
ArrayList<String> arrayList;
ListView listView;
ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_record);
if(ContextCompat.checkSelfPermission(VideoRecord.this,
        Manifest.permission.READ_EXTERNAL_STORAGE )!= PackageManager.PERMISSION_GRANTED){
    if(ActivityCompat.shouldShowRequestPermissionRationale(VideoRecord.this,
            Manifest.permission.READ_EXTERNAL_STORAGE)){
        String s[]=new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(VideoRecord.this,s,MY_PERMISSION_REQUEST) ;
    }
    else{
        String s[]=new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(VideoRecord.this,s,MY_PERMISSION_REQUEST);
    }
        }
else{
    doStuff();
}
    }
    public void doStuff(){
listView= (ListView)findViewById(R.id.listview);
arrayList= new ArrayList<>();
getVideo();
adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arrayList);
listView.setAdapter(adapter);
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
});
    }
    public void getVideo(){
        ContentResolver contentResolver= getContentResolver();
        Uri vidUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Cursor vidcursor = contentResolver.query(vidUri,null,null,null,null);
        if(vidcursor!=null && vidcursor.moveToFirst()){
            int vidTitle=vidcursor.getColumnIndex(MediaStore.Video.Media.TITLE);
            int vidDuration=vidcursor.getColumnIndex(MediaStore.Video.Media.DURATION);
            do{
                String ct=vidcursor.getString(vidTitle);
                String cd=vidcursor.getString(vidDuration);
                arrayList.add(ct+"\n" +cd);
            }while(vidcursor.moveToNext());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {
     switch (requestCode){
         case MY_PERMISSION_REQUEST:{
             if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                 if(ContextCompat.checkSelfPermission(VideoRecord.this,
                         Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                     Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
                     doStuff();
                 }
             }else{
                 Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show();
                 finish();
             }
             return;
         }
     }
    }
}
