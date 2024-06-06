package com.example.ubycalls;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        TextView resultTextView = findViewById(R.id.result);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (validateLogin(username, password)) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Login.this, "Invalid login", Toast.LENGTH_SHORT).show();
            }

            if ("correctUsername".equals(username) && "correctPassword".equals(password)) {
                resultTextView.setText("Login Success");
            } else {
                resultTextView.setText("Login Failed");
            }

            resultTextView.setVisibility(View.VISIBLE);
        });
    }

    public boolean validateLogin(String username, String password) {
        // Validación simple. En un caso real, deberías validar con datos de una base de datos.
        return "admin".equals(username) && "password".equals(password);
    }
}
