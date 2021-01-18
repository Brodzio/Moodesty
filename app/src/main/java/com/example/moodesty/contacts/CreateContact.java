package com.example.moodesty.contacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.moodesty.R;

public class CreateContact extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etNumber, etWeb, etLocation;
    ImageView ivHappy, ivSad, ivNeutral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        etName = findViewById(R.id.etName);
        etLocation = findViewById(R.id.etLocation);
        etWeb = findViewById(R.id.etWeb);
        etNumber = findViewById(R.id.etNumber);

        ivHappy = findViewById(R.id.ivHappy);
        ivNeutral = findViewById(R.id.ivNeutral);
        ivSad = findViewById(R.id.ivSad);

        ivSad.setOnClickListener(this);
        ivHappy.setOnClickListener(this);
        ivNeutral.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (etName.getText().toString().isEmpty() || etLocation.getText().toString().isEmpty() || etWeb.getText().toString().isEmpty() || etNumber.getText().toString().isEmpty())
        {
            Toast.makeText(this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent();
            intent.putExtra("name", etName.getText().toString().trim());
            intent.putExtra("location", etLocation.getText().toString().trim());
            intent.putExtra("web", etWeb.getText().toString().trim());
            intent.putExtra("number", etNumber.getText().toString().trim());

            if (v.getId() == R.id.ivHappy)
            {
                intent.putExtra("mood", "happy");
            }
            else if (v.getId() == R.id.ivNeutral)
            {
                intent.putExtra("mood", "neutral");
            }
            else if (v.getId() == R.id.ivSad)
            {
                intent.putExtra("mood", "sad");
            }

            setResult(RESULT_OK, intent);
            CreateContact.this.finish();
        }
    }
}
