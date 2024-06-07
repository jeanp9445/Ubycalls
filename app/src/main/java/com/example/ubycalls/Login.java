package com.example.ubycalls;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity{

    private EditText etUsername, etPassword;
    private Button btnLogin;

    private TextView rc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        rc = findViewById(R.id.txtRC);

        TextView resultTextView = findViewById(R.id.result);

    }

    public void Logeo(View view){

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if (validateLogin(username, password)) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(Login.this, "Invalid login", Toast.LENGTH_SHORT).show();
        }
/*
        if ("correctUsername".equals(username) && "correctPassword".equals(password)) {
            resultTextView.setText("Login Success");
        } else {
            resultTextView.setText("Login Failed");
        }

        resultTextView.setVisibility(View.VISIBLE);*/
    }

    public boolean validateLogin(String username, String password) {
        // Instanciar la base de datos
        AdminDataBase db = new AdminDataBase(this);
        SQLiteDatabase sqldb = db.getReadableDatabase();

        // Preparar la consulta SQL para verificar el nombre de usuario y la contraseña
        String query = "SELECT COUNT(*) FROM Agentes WHERE Correo = ? AND contraseñas = ?";

        // Variables para almacenar los resultados de la consulta
        String[] selectionArgs = { username, password };
        boolean loginValid = false;

        // Ejecutar la consulta
        Cursor cursor = sqldb.rawQuery(query, selectionArgs);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int count = cursor.getInt(0);
                loginValid = (count > 0); // Si se encuentra al menos una coincidencia, el login es válido
            }
            cursor.close();
        }

        // Cerrar la base de datos
        sqldb.close();

        // Retornar el resultado de la validación
        return loginValid;
    }

    public void RecuperarCuenta(View view) {
        Intent intent = new Intent(Login.this, RecuperarCuenta.class);
        startActivity(intent);
    }
}
