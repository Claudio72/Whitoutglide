package com.example.usuario.layouts;

import android.graphics.Bitmap;

/**
 * Created by Jose on 26/02/2017.
 */

public class Alumno {
    private String nombre, user, curso;
    private Bitmap imagen;

    Alumno(String nombre, Bitmap imagen, String cu){
        this.nombre=nombre;
        this.imagen=imagen;
        this.curso=cu;
    }

    Alumno(String nombre, String user, String cu){
        this.nombre=nombre;
        this.user=user;
        this.curso=cu;
    }

    public String getNombre(){
        return nombre;
    }

    public String getuser() {
        return user;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public String getCurso(){ return curso; }

}
