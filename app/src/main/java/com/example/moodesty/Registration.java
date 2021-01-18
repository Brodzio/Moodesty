package com.example.moodesty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moodesty.db.DBHelper;
import com.example.moodesty.db.Users;

public class Registration extends AppCompatActivity {

    private Button btnRegisterApp, btnCancelRegistration;
    private EditText etLogin, etPassword;
    DBHelper DBhelper;
    SQLiteDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btnRegisterApp = findViewById(R.id.btnRegisterApp);
        btnCancelRegistration = findViewById(R.id.btnCancelRegistration);
        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        DBhelper = new DBHelper(this);
        DB = DBhelper.getWritableDatabase();

        getSupportActionBar().setTitle(R.string.register);

        btnRegisterApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    addDBentry();
                    finish();
                }
            }
        });

        btnCancelRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(getString(R.string.cancel));
                finish();
            }
        });
    }

    private void addDBentry() {

        ContentValues values = new ContentValues();

        values.put("login", etLogin.getText().toString());
        values.put("password",etPassword.getText().toString());

        long newRowId = DB.insert(Users.UsersHistory.TABLE_NAME, null, values);
        finish();
    }

    public boolean validate() {
        if (!isDataOK()) {
            return false;
        } else return true;
    }

    public boolean isDataOK() {
        final String login = etLogin.getText().toString();
        final String password = etPassword.getText().toString();
        if(login.length()<4 || login.length()>24) {
            showToast(getString(R.string.properData));
            return false;
        }
        if(password.length()<8 || password.length()>24) {
            showToast(getString(R.string.properData));
            return false;
        }
        return true;
    }

    public void showToast(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}