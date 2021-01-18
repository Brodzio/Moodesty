package com.example.moodesty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moodesty.db.DBHelper;
import com.example.moodesty.db.Users;

public class Login extends AppCompatActivity {

    Button btnLoginApp, btnCancelLogin;
    EditText etLogin, etPassword;
    com.example.moodesty.db.DBHelper DBHelper;
    SQLiteDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLoginApp = findViewById(R.id.btnLoginApp);
        btnCancelLogin = findViewById(R.id.btnCancelLogin);
        etLogin = findViewById(R.id.etLoginApp);
        etPassword = findViewById(R.id.etPasswordApp);

        DBHelper = new DBHelper(this);
        DB = DBHelper.getReadableDatabase();

        getSupportActionBar().setTitle(R.string.Log_in);

        btnLoginApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( etLogin.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty())
                {
                    showToast(getString(R.string.enter_data));
                }
                else {
                    if(readData()) {
                        Intent intent = new Intent(Login.this, com.example.moodesty.Menu.class);
                        startActivity(intent);

                    }
                    else {
                        showToast("Enter correct data!");
                    }
                }
            }
        });
        btnCancelLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast(getString(R.string.cancel));
                finish();
            }
        });
    }

    private boolean readData() {
        String[] projection = {
                BaseColumns._ID,
                Users.UsersHistory.COLUMN1,
                Users.UsersHistory.COLUMN2
        };

        String selection = Users.UsersHistory.COLUMN1 + " = ?" + " AND " +Users.UsersHistory.COLUMN2 + " = ?";
        String login = etLogin.getText().toString();
        String password = etPassword.getText().toString();
        String[] selectionArgs = {login, password};

        Cursor cursor = DB.query(
                Users.UsersHistory.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,
                null            // don't filter by row groups
        );

        int cursorCount = cursor.getCount();

        cursor.close();
        DB.close();
        if(cursorCount > 0) {
            return true;
        }

        return false;
    }

    private void showToast(String massage){
        Toast toast = Toast.makeText(this,massage,Toast.LENGTH_SHORT);
        toast.show();
    }
}
