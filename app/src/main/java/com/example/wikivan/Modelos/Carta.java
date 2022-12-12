package com.example.wikivan.Modelos;

//Clase que se utiliza para facilitar la transferencia de datos necesarios de las cartas
public class Carta {
    //Variables de cada carta
    private String nom;
    private String ataque;
    private String defensa;
    private String nivel;
    private String raza;
    private String img;

    //Constructor
    public Carta(String nom, String ataque, String defensa, String nivel, String raza, String img) {
        this.nom = nom;
        this.ataque = ataque;
        this.defensa = defensa;
        this.nivel = nivel;
        this.raza = raza;
        this.img = img;
    }

    //Getter y setter
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAtaque() {
        return ataque;
    }

    public void setAtaque(String ataque) {
        this.ataque = ataque;
    }

    public String getDefensa() {
        return defensa;
    }

    public void setDefensa(String defensa) {
        this.defensa = defensa;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }
}
