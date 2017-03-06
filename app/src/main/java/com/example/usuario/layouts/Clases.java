package com.example.usuario.layouts;

/**
 * Created by Jose on 26/02/2017.
 */

public class Clases {
    private String nombre;
    private int imagen;

    public Clases(){}

    public Clases(String nombre, int imagen){
        this.nombre=nombre;
        this.imagen=imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }
}
