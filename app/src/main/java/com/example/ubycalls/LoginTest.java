package com.example.ubycalls;

public class LoginTest {

    public boolean validateLogin(String username, String password) {
        // Validación simple. En un caso real, deberías validar con datos de una base de datos.
        return "admin".equals(username) && "password".equals(password);
    }
}
