package com.example.usuario.layouts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Jose on 26/02/2017.
 */

public class MiAdapterAlumnos extends RecyclerView.Adapter<MiAdapterAlumnos.ViewHolder>{

    private Context context;
    private ArrayList<Alumno> alumnos;

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
                    Bitmap image=imagen.getDrawingCache();
                    Bundle extra=new Bundle();

                    ByteArrayOutputStream stream=new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    Intent intent=new Intent(view.getContext(), Detalles.class);
                    intent.putExtra("nombre", alumnos.get(getAdapterPosition()).getNombre());
                    intent.putExtra("cu", alumnos.get(getAdapterPosition()).getCurso());
                    intent.putExtra("imagen", byteArray);
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nombre.setText(alumnos.get(position).getNombre());
        holder.imagen.setImageBitmap(alumnos.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }


}
