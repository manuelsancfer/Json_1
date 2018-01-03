package com.example.manuelsanchezferna.json;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by manuel.sanchez.ferna on 01/12/2017.
 */

public class User {

    private String user;
    private String gustos_musicales;
    private boolean publico;
    private String email;
    private String dato;
    private Bitmap foto_perfil;
    private int f1;
    private int f2;
    private int f3;
    private int f4;


    @Override
    public String toString() {
        return "User{" +
                "user='" + user + '\'' +
                ", gustos_musicales='" + gustos_musicales + '\'' +
                ", publico=" + publico +
                ", email='" + email + '\'' +
                ", foto_perfil='" + foto_perfil + '\'' +
                ", dato='" + dato + '\'' +
                ", f1=" + f1 +
                ", f2=" + f2 +
                ", f3=" + f3 +
                ", f4=" + f4 +
                '}';
    }



    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGustos_musicales() {
        return gustos_musicales;
    }

    public void setGustos_musicales(String gustos_musicales) {
        this.gustos_musicales = gustos_musicales;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDato() {return dato;}

    public void setDato(String dato) {
        this.dato = dato;
        try{
            byte[] byteCode = Base64.decode(dato,Base64.DEFAULT);
            this.foto_perfil = BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Bitmap getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(Bitmap foto_perfil) {this.foto_perfil = foto_perfil;}

    public int getF1() {
        return f1;
    }

    public void setF1(int f1) {
        this.f1 = f1;
    }

    public int getF2() {
        return f2;
    }

    public void setF2(int f2) {
        this.f2 = f2;
    }

    public int getF3() {
        return f3;
    }

    public void setF3(int f3) {
        this.f3 = f3;
    }

    public int getF4() {
        return f4;
    }

    public void setF4(int f4) {
        this.f4 = f4;
    }







}
