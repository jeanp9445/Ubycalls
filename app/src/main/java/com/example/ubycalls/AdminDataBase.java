package com.example.ubycalls;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminDataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TimeTrackingDB";
    private static final int DATABASE_VERSION = 4; // Puedes mantener la versión si es la primera vez que creas la tabla

    public AdminDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla TimeRecords
        db.execSQL("CREATE TABLE IF NOT EXISTS TimeRecords (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "date TEXT, " +
                "time_in TEXT, " +
                "time_out TEXT)");

        // Crear la tabla Agentes con las nuevas columnas passwords y contraseñas
        db.execSQL("CREATE TABLE IF NOT EXISTS Agentes (" +
                "id_Agente INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Nombre TEXT, " +
                "Apellido TEXT, " +
                "Celular TEXT, " +
                "Correo TEXT, " +
                "contraseñas TEXT)");

        // Insertar datos de ejemplo
        db.execSQL("INSERT INTO Agentes (Nombre, Apellido, Celular, Correo, contraseñas) " +
                "VALUES ('Nombre1', 'Apellido1', '1234567890', 'iscocontactos@gmail.com', '1111')");
        db.execSQL("INSERT INTO Agentes (Nombre, Apellido, Celular, Correo, contraseñas) " +
                "VALUES ('Nombre2', 'Apellido2', '0987654321', '2222@ubycall.com', '2222')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar tablas existentes y recrearlas
        db.execSQL("DROP TABLE IF EXISTS TimeRecords");
        db.execSQL("DROP TABLE IF EXISTS Agentes");
        onCreate(db);
    }
}
