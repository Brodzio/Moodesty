package com.example.moodesty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.moodesty.contacts.Contacts;
import com.example.moodesty.paintings.Paintings;
import com.example.moodesty.movies.Movie;
import com.example.moodesty.records.VoiceRecorder;

public class Menu extends AppCompatActivity {

    private ImageView ivPaintings, ivMovies, ivMic, ivContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        setImages();
        setListeners();
    }

    private void setImages() {
        ivPaintings = findViewById(R.id.ivPaintigs);
        ivContacts = findViewById(R.id.ivContacts);
        ivMic = findViewById(R.id.ivMic);
        ivMovies = findViewById(R.id.ivMovies);
    }

    private void setListeners() {
        ivContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Contacts.class);
                startActivity(intent);
            }
        });

        ivMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Movie.class);
                startActivity(intent);
            }
        });

        ivPaintings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Paintings.class);
                startActivity(intent);
            }
        });

        ivMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, VoiceRecorder.class);
                startActivity(intent);
            }
        });
    }


}
