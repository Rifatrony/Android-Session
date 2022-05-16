package com.example.sessionproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button login;

    EditText numberEt, passwordEt;
    int number;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setTitle("Login");

        numberEt = findViewById(R.id.phoneNumberId);
        passwordEt = findViewById(R.id.passwordId);


    }

    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    private void checkSession() {
        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        int userId = sessionManagement.getSession();

        if (userId != -1){
            moveToMainActivity();
        }
        else {

        }
    }

    public void login(View view) {

        password = passwordEt.getText().toString().trim();
        number = Integer.parseInt(numberEt.getText().toString().trim());

        User user = new User(number,password);

        SessionManagement sessionManagement= new SessionManagement(LoginActivity.this);
        sessionManagement.saveSession(user);

        moveToMainActivity();

    }

    private void moveToMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("password", password);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
}