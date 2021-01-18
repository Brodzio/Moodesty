package com.example.moodesty.contacts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moodesty.R;

public class Contacts extends AppCompatActivity {

    ImageView ivMood, ivLocation, ivWeb, ivPhone;
    Button btnCreate;
    final int CREATE_CONTACT = 1;
    String name = "", number = "", web = "", location = "", mood = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ivMood = findViewById(R.id.ivMood);
        ivLocation = findViewById(R.id.ivLocation);
        ivWeb = findViewById(R.id.ivWeb);
        ivPhone = findViewById(R.id.ivPhone);
        btnCreate = findViewById(R.id.btnCreate);

        ivWeb.setVisibility(View.GONE);
        ivMood.setVisibility(View.GONE);
        ivLocation.setVisibility(View.GONE);
        ivPhone.setVisibility(View.GONE);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Contacts.this, CreateContact.class);
                startActivityForResult(intent, CREATE_CONTACT);

            }
        });

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });

        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + web));
                startActivity(intent);
            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + location));
                startActivity(intent);
            }
        });

        ivMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_CONTACT) {
            if (resultCode == RESULT_OK) {
                ivWeb.setVisibility(View.VISIBLE);
                ivMood.setVisibility(View.VISIBLE);
                ivLocation.setVisibility(View.VISIBLE);
                ivPhone.setVisibility(View.VISIBLE);

                name = data.getStringExtra("name");
                web = data.getStringExtra("web");
                location = data.getStringExtra("location");
                number = data.getStringExtra("number");
                mood = data.getStringExtra("mood");

                if (mood.equals("happy")) {
                    ivMood.setImageResource(R.drawable.happy);
                }
                if (mood.equals("sad")) {
                    ivMood.setImageResource(R.drawable.sad);
                }
                if (mood.equals("neutral")) {
                    ivMood.setImageResource(R.drawable.neutral);
                }
            } else {
                Toast.makeText(this, "No data was passed!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}