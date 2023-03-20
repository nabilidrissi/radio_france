package com.nabilRadioFrance.radio;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nabilRadioFrance.radio.R;

import wseemann.media.FFmpegMediaMetadataRetriever;



public class MainActivity extends AppCompatActivity {


    private TextView nowPlaying;
    private ImageView playStop;
    private BroadcastReceiver broadcastReceiver;
    private String nowPlayingData = "";
    private Bundle extras;

    public static  String url;
    private String nameStation;
    private TextView nameRadio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playStop = findViewById(R.id.playStopBtn);
        nowPlaying = findViewById(R.id.radioStationNowPlaying);
        nameRadio=findViewById(R.id.radioStationName);
        setIsPlaying(false);
        //geting the data from the intent
        extras = getIntent().getExtras();
        url=extras.getString("url");
        nameStation=extras.getString("nomRadio");
        //pass the radio name in the textview
        nameRadio.setText(nameStation);
        //get the permission to the phone call
        //processPhoneListenerPermission();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
                if (tm != null) {
                    if (tm.getCallState() == TelephonyManager.CALL_STATE_RINGING) {
                        if (getIsPlaying()) {
                            stop();
                        }
                        System.exit(0);
                    }
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.PHONE_STATE");
        registerReceiver(broadcastReceiver, filter);
        loadNowPlaying();
        play();
        playStop.setOnClickListener(view -> {

            if (isNetworkAvailable()) {
                if (getIsPlaying()) {
                    stop();
                } else {
                    play();
                }
            } else {
                Toast.makeText(getApplicationContext(), "No internet", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadNowPlaying() {
        Thread t = new Thread() {
            public void run() {
                try {
                    while (!isInterrupted()) {
                        runOnUiThread(() -> reloadShoutCastInfo());
                        Thread.sleep(20000);
                    }
                } catch (InterruptedException ignored) {
                }
            }
        };
        t.start();
    }

    private void reloadShoutCastInfo() {
        if (isNetworkAvailable()) {
            AsyncTaskRunner runner = new AsyncTaskRunner();
            runner.execute();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            FFmpegMediaMetadataRetriever mmr = new FFmpegMediaMetadataRetriever();
            mmr.setDataSource(url);
            nowPlayingData = mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ICY_METADATA);
            mmr.release();
            return null;
        }


    }

    private void processPhoneListenerPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 121);
        }
    }

    @SuppressLint("MissingPermission")
    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (cm != null) {
            networkInfo = cm.getActiveNetworkInfo();
        }
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
    }



    @Override
    public void onBackPressed() {

                   /* if (getIsPlaying()) {
                        stop();
                    }
        Intent intent = new Intent(this, ListeRadio.class);
        startActivity(intent);*/
                   System.exit(0);

    }

    private void setIsPlaying(boolean status) {
        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("isPlaying", MODE_PRIVATE).edit();
        editor.putBoolean("isPlaying", status);
        editor.apply();
    }

    private boolean getIsPlaying() {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("isPlaying", MODE_PRIVATE);
        return prefs.getBoolean("isPlaying", false);
    }

    private void play() {
        setIsPlaying(true);
        Intent servicePlayIntent = new Intent(this, MyService.class);
        servicePlayIntent.putExtra("playStop", "play");
        startService(servicePlayIntent);
        playStop.setImageResource(R.drawable.ic_pause);
        Toast.makeText(getApplicationContext(), "Chargement en cours ...", Toast.LENGTH_LONG).show();
    }

    private void stop() {
        setIsPlaying(false);
        Intent serviceStopIntent = new Intent(this, MyService.class);
        serviceStopIntent.putExtra("playStop", "stop");
        startService(serviceStopIntent);
        playStop.setImageResource(R.drawable.ic_play);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(getApplicationContext(), MyService.class));
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}
