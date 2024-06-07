package com.example.ubycalls;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RecuperarCuenta extends AppCompatActivity {

    private EditText etEmail;
    private Button btnRecover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_cuenta);

        etEmail = findViewById(R.id.etEmail);
        btnRecover = findViewById(R.id.btnRecover);
    }

    public void recoverAccount(View view) {
        String email = etEmail.getText().toString();

        // Verificar si el email existe en la base de datos
        if (isEmailRegistered(email)) {
            // Aquí podrías implementar el envío de un correo de recuperación
            // o generar un código de verificación, etc.
            Toast.makeText(this, "Recovery email sent to " + email, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Email not registered", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmailRegistered(String email) {
        AdminDataBase db = new AdminDataBase(this);
        SQLiteDatabase sqldb = db.getReadableDatabase();
        boolean emailExists = false;

        Cursor cursor = sqldb.rawQuery("SELECT COUNT(*) FROM Agentes WHERE Correo = ?", new String[]{email});
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int count = cursor.getInt(0);
                emailExists = (count > 0);
            }
            cursor.close();
        }
        sqldb.close();
        return emailExists;
    }
}
