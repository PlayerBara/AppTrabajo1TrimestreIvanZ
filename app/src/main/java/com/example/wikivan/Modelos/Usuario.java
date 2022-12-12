package com.example.wikivan.Modelos;

public class Usuario {
    //Nombre del usuario
    private String nombre;
    //Contraseña del usuario
    private String password;

    public Usuario(String nombre, String password){
        this.nombre = nombre;
        this.password = password;
    }

    //Getter y setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
