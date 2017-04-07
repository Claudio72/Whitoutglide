package com.example.usuario.layouts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
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
import java.util.concurrent.ExecutionException;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by Jose on 26/02/2017.
 */

public class  MiAdapterAlumnos extends RecyclerView.Adapter<MiAdapterAlumnos.ViewHolder>{

    private Context context;
    private ArrayList<Alumno> alumnos;
    private Bitmap bitmap;
    public MiAdapterAlumnos(Context context, ArrayList<Alumno> alumnos){
        this.context=context;
        this.alumnos=alumnos;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombre;
        public ImageView imagen;

        public ViewHolder(View v){
            super(v);
            nombre=(TextView)v.findViewById(R.id.nombreA);
            imagen=(ImageView)v.findViewById(R.id.imagenA);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imagen.buildDrawingCache();
                    Bitmap image=null;
                    Foto foto=new Foto(alumnos.get(getAdapterPosition()).getRuta());
                    try {
                         image=foto.execute().get();
                    }catch (Exception e){

                    }

                    Bundle extra=new Bundle();
                    alumnos.get(getAdapterPosition()).setImagen(image);
                    Detalles.alumno=alumnos.get(getAdapterPosition());
                    Intent intent=new Intent(view.getContext(), Detalles.class);
                    intent.putExtras(extra);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    view.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public MiAdapterAlumnos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v;
        v = LayoutInflater.from(context).inflate(R.layout.plantillalumno, parent, false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.nombre.setText(alumnos.get(position).getNombre());

        try {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    try {
                          bitmap =Glide.with(context).load(alumnos.get(position).getRuta()).asBitmap().into(-1,-1).get();
                    } catch (final ExecutionException e) {
                        Log.e(TAG, e.getMessage());
                    } catch (final InterruptedException e) {
                        Log.e(TAG, e.getMessage());
                    }
                    return null;

                }
                @Override
                protected void onPostExecute(Void dummy) {
                    if (null != bitmap) {
                        // The full bitmap should be available here
                        holder.imagen.setImageBitmap(bitmap);
                        Log.d(TAG, "Image loaded");
                    };
                }
            }.execute();


        }catch(Exception e){
                System.out.println(e.toString());
            }

        }


    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public class Foto extends AsyncTask<String, Void , Bitmap> {

        private String ruta;

        public Foto(String ruta){
            this.ruta=ruta;
        }


        @Override
        protected Bitmap doInBackground(String... params) {


            try {
                    URL url=new URL(ruta);
                    URLConnection uc = url.openConnection();
                    InputStream in = uc.getInputStream();
                    Bitmap fot= BitmapFactory.decodeStream(in);
                return fot;

            }catch (Exception e) {
                System.out.println("a"+e.toString());
                e.printStackTrace();
            }
            return null;
        }

    }
}
