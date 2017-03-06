package com.example.usuario.layouts;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * Created by Jose on 26/02/2017.
 */

public class LlenadoInternet extends AppCompatActivity {
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler);
        context=getApplicationContext();

        Bundle bundle=getIntent().getExtras();

        ArrayList<Alumno> alumnos=new ArrayList<>();
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        final Context context=this.getApplicationContext();

        BD b=new BD(bundle.getString("Curso"), context);

        try {
            alumnos = b.execute().get();
        }catch (Exception e){

        }


            recyclerView = (RecyclerView) findViewById(R.id.recycler);

            layoutManager = new GridLayoutManager(context, 3);
            recyclerView.setLayoutManager(layoutManager);

            adapter = new MiAdapterAlumnos(context, alumnos);
            recyclerView.setAdapter(adapter);


    }

    public class BD extends AsyncTask<String, Void , ArrayList<Alumno>> {

        public ArrayList<Alumno> alumnos=new ArrayList<>();
        private String clase;
        ProgressDialog dialog;
        Context context;

        public BD(String clase, Context co){
            this.clase=clase;
            context=co;
        }

        @Override
        protected void onPreExecute() {
            dialog=new ProgressDialog(LlenadoInternet.this);
            dialog.setMessage("Loading...");
            dialog.show();
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Alumno> doInBackground(String... params) {


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

                        System.out.println("ip"+io);
                        if(io.equals("10.10.4.150")){

                        }else{
                            io="www.iesmurgi.org:3306";
                        }
                        System.out.println(io);


                        Connection con;//Creamos en objeto conexion
                        Class.forName("com.mysql.jdbc.Driver");//cargamos la clase con los drivers mysql previamente tenemos que tener cargar los driver en la libreria
                        con = DriverManager.getConnection("jdbc:mysql://www.iesmurgi.org:3306/base20173", "ubase20173", "pbase20173");

                    Statement coger = con.createStatement();
                    System.out.println(clase);
                    if(clase.equals("Bachiller A"))clase="BACHA";
                    if(clase.equals("Bachiller B"))clase="BACHB";
                    if(clase.equals("Bachiller C"))clase="BACHC";

                        System.out.println(clase);
                    String sql="select * from "+clase;

                    ResultSet a= coger.executeQuery(sql);
                    alumnos=new ArrayList<>();
                    //ftp://segundodam@www.iesmurgi.org/public_html/a/PNG_transparency_demonstration_2.png
                    //ftp://segundodam@www.iesmurgi.org/public_html/a/FPB/1.jpg
                    // www.iesmurgi.org/public_html/a/PNG_transparency_demonstration_2.png
                    //www.iesmurgi.org:85/segundodam/a/7938.png.jpg



                    while (a.next()){
                        URL url=new URL(a.getString(4));
                        URLConnection uc = url.openConnection();
                        String userpass = "usegundodam" + ":" + "segundodam2017";
                        String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
                        uc.setRequestProperty ("Authorization", basicAuth);




                        String da=a.getString(5);
                        String[] ti =da.split("-");

                        String anio=ti[0];
                        String mes=ti[1];
                        String dia=ti[2];

                        System.out.println("     "+anio+" "+mes+" "+dia);

                        InputStream in = uc.getInputStream();
                        System.out.println(io);

                        Bitmap fot= BitmapFactory.decodeStream(in);
                        alumnos.add(new Alumno(a.getString(2), fot, clase));
                    }
                    dialog.dismiss();
                    return alumnos;

                } catch (Exception e) {
                    System.out.println("a"+e.toString());
                        e.printStackTrace();
                }




            return null;
        }


    }




}
