package com.example.usuario.layouts;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Jose on 26/02/2017.
 */

public class Alumno {
    private String nombre, user, curso, ruta;
    private Bitmap imagen, qr=null;



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

    public Alumno(String string, String clase, String ruta, int n) {
        this.nombre=string;
        this.curso=clase;
        this.ruta=ruta;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
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

    public void setQr(Bitmap codigo){qr=codigo; }

    public Bitmap getQr(){
        return qr;
    }

    public void setImagen(Bitmap imagen){this.imagen=imagen;}

}
