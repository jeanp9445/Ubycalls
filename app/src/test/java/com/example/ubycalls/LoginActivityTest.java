package com.example.ubycalls;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LoginActivityTest {

    private LoginTest loginActivity;

    @Before

    public void setUp() {
        // Crear una instancia de LoginActivity para las pruebas
        loginActivity = new LoginTest();
    }

    @Test
    public void testValidateLogin_CorrectCredentials() {
        // Prueba con credenciales correctas
        assertTrue(loginActivity.validateLogin("admin", "password"));
    }

    @Test
    public void testValidateLogin_WrongUsername() {
        // Prueba con un nombre de usuario incorrecto
        assertFalse(loginActivity.validateLogin("wrongUsername", "password"));
    }

    @Test
    public void testValidateLogin_WrongPassword() {
        // Prueba con una contraseña incorrecta
        assertFalse(loginActivity.validateLogin("admin", "wrongPassword"));
    }

    @Test
    public void testValidateLogin_WrongCredentials() {
        // Prueba con credenciales incorrectas
        assertFalse(loginActivity.validateLogin("wrongUsername", "wrongPassword"));
    }
}
