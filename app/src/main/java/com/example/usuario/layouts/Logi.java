package com.example.usuario.layouts;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;


import org.apache.commons.net.util.Base64;

import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Claudio on 01/03/2017.
 */

public class Logi extends AsyncTask<String, Void, Alumno> {
        private String lo;
        private String pa;
        private Context ActivityActual;
    Logi(String lo,String pn, Context activity){
        this.lo=lo;
        this.ActivityActual=activity;
        this.pa=pn;
    }

    @Override
    protected Alumno doInBackground(String... params) {
        try {
            String io="";

            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            System.out.println("Cantidad de interfaces"+interfaces.size());//Network interfaces devuelve un enumerado con un listado de interfazes del movil
                for (NetworkInterface intf : interfaces) {
                    List<InetAddress> addrs = Collections.list(intf.getInetAddresses()); //Cogemos el siguiente objecto network interfaces y cogemos el inetaddress que no s la ip que usamos
                    for (InetAddress addr : addrs) {
                        if (!addr.isLoopbackAddress()) { //metodo comprueba si la ip es localhost
                            String sAddr = addr.getHostAddress();
                            //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                            boolean isIPv4 = sAddr.indexOf(':')<0;

                            if (true) {
                                if (isIPv4)
                                    io=addr.getHostAddress();
                                    break;
                            }
                        }
                    }
                }

            System.out.println("ipfefeeefefef "+io);
            if(io.equals("10.10.4.150")){

            }else{
                io="www.iesmurgi.org:3306";
            }
            System.out.println(io);
            Connection con;//Creamos en objeto conexion
            Class.forName("com.mysql.jdbc.Driver");//cargamos la clase con los drivers mysql previamente tenemos que tener cargar los driver en la libreria
            con = DriverManager.getConnection("jdbc:mysql://"+io+"/base20173", "ubase20173", "pbase20173");

            Statement coger = con.createStatement();

            String sql="select * from USUARIOS where user='"+lo+"'";

            ResultSet a= coger.executeQuery(sql);
            Alumno alumno=null;
            System.out.println("a");
            if(!a.next()){
                System.out.println("no hay nada");

                Handler handler =  new Handler(ActivityActual.getMainLooper());
                handler.post( new Runnable(){
                    public void run(){
                        Toast.makeText(ActivityActual, "Error en el login",Toast.LENGTH_LONG).show();
                    }
                });

            }else {
                String uno=a.getString(1);
                String dos=a.getString(2);
                String tres=a.getString(3);
                if(dos.equalsIgnoreCase(pa)){
                    System.out.println("a");
                    alumno=new Alumno("", uno, tres);
                }else{
                    Handler handler =  new Handler(ActivityActual.getMainLooper());
                    handler.post( new Runnable(){
                        public void run(){
                            Toast.makeText(ActivityActual, "Error en el login",Toast.LENGTH_LONG).show();
                        }
                    });
                }


            }

            return alumno;

        } catch (Exception e) {
            System.out.println("a"+e.toString());

               }

        return null;
    }





}
