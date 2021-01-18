package com.example.moodesty.records;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.moodesty.R;

import java.io.File;
import java.io.IOException;

public class VoiceRecorder extends AppCompatActivity {

    private Button btnRecordVoice, btnStopVoice, btnPlayVoice;
    private MediaRecorder audioRecorder;
    private String outPutFile = "play.3gp";
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recorder);

        btnRecordVoice = findViewById(R.id.btnRecordVoice);
        btnStopVoice = findViewById(R.id.btnStopVoice);
        btnPlayVoice = findViewById(R.id.btnPlayVoice);

        btnPlayVoice.setEnabled(false);
        btnStopVoice.setEnabled(false);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.RECORD_AUDIO }, 10);
        }

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, 112);
        }

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, 112);
        }



        btnRecordVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFilePath();
                try {
                    audioRecorder.prepare();
                    audioRecorder.start();
                } catch (IllegalStateException ies) {
                    ies.printStackTrace();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }



                btnRecordVoice.setEnabled(false);
                btnStopVoice.setEnabled(true);
                showToast("Recording started!");
            }
        });

        btnStopVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                audioRecorder.stop();
                audioRecorder.release();
                audioRecorder = null;
                btnRecordVoice.setEnabled(true);
                btnStopVoice.setEnabled(false);
                btnPlayVoice.setEnabled(true);
                showToast("Audio Recorder successfully!");
            }
        });

        btnPlayVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(fileName);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    showToast("Playing audio!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void getFilePath() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, 112);
        }

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, 112);
        }

        audioRecorder = new MediaRecorder();
        audioRecorder.reset();
        audioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        audioRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        audioRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        audioRecorder.setAudioEncodingBitRate(16);
        audioRecorder.setAudioSamplingRate(44100);

        File path = new File(Environment.getExternalStorageDirectory(), outPutFile);


        fileName = path.getAbsolutePath();

        audioRecorder.setOutputFile(fileName);

    }
}
