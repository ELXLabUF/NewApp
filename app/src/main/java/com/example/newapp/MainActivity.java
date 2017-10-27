package com.example.newapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends FragmentActivity {

    private TextView mTextView;
    private static final int REQUEST_PERMISSIONS = 1;
    private static String[] PERMISSIONS_LIST = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.VIBRATE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });

        verifyStoragePermissions(this);



    }



    public void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionRecord = ActivityCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO);

        if (permission != PackageManager.PERMISSION_GRANTED || permissionRecord != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_LIST,
                    REQUEST_PERMISSIONS
            );
            SharedPreferences preferences = getSharedPreferences("prefName", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putBoolean("systemPermissionsGiven", false);
            edit.apply();
        } else {

            SharedPreferences settings = getSharedPreferences("prefName", MODE_PRIVATE);
            Boolean needToDeleteAudio = settings.getBoolean("audioFilesDeleted", false);

            SharedPreferences preferences = getSharedPreferences("prefName", MODE_PRIVATE);
            SharedPreferences.Editor edit = preferences.edit();
            edit.putBoolean("systemPermissionsGiven", true);
            edit.apply();

            if(!needToDeleteAudio){
                deleteOldAudioFiles();
                edit.putBoolean("audioFilesDeleted", true);
                edit.apply();
            }
        }
    }

    void deleteOldAudioFiles(){
        int i = 0;
        int j = 0;
        boolean stillMoreFiles = true;
        String currentFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "chapter_recording.wav";
        while(stillMoreFiles) {
            File file = new File(currentFileName);
            if (!file.exists() && j != 0) {
                i++;
                j = 0;
            } else if(!file.exists() && j == 0){
                stillMoreFiles = false;
            } else {
                boolean deleted = file.delete();
                j++;
            }
            currentFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "chapter_recording.wav";
        }
    }
}
