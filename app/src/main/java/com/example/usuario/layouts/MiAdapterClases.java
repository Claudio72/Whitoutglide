package com.example.usuario.layouts;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jose on 28/02/2017.
 */

public class MiAdapterClases extends RecyclerView.Adapter<MiAdapterClases.ViewHolder>{
    private ArrayList<Clases> clases;
    private Context context;

    public MiAdapterClases(Context context, ArrayList<Clases> clases){
        this.context=context;
        this.clases=clases;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView nombre;
        public ImageView imagen;

        public ViewHolder(View v){
            super(v);

            nombre=(TextView)v.findViewById(R.id.nombreC);
            imagen=(ImageView)v.findViewById(R.id.imagenC);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isNetworkAvailable(context)) {
                        Intent intent = new Intent(view.getContext(), LlenadoInternet.class);
                        intent.putExtra("Curso", "" + clases.get(getAdapterPosition()).getNombre());
                        view.getContext().startActivity(intent);
                    }else{
                        Toast.makeText(context, "No hay inter net", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    @Override
    public MiAdapterClases.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v;
        v = LayoutInflater.from(context).inflate(R.layout.plantillaclase, parent, false);
        MiAdapterClases.ViewHolder vh=new MiAdapterClases.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MiAdapterClases.ViewHolder holder, int position) {
        holder.nombre.setText(clases.get(position).getNombre());
        holder.imagen.setImageResource(clases.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        return clases.size();
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}
