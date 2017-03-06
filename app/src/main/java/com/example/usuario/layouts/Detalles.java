package com.example.usuario.layouts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jose on 28/02/2017.
 */

public class Detalles extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalles);

        Bundle bundle=getIntent().getExtras();
        Intent intent=getIntent();

        ImageView image=(ImageView)findViewById(R.id.imagenD);
        TextView nombre=(TextView)findViewById(R.id.nombreD);
        TextView curso=(TextView)findViewById(R.id.cursoD);

        Bitmap bmp= intent.getParcelableExtra("imagen");
        image.setImageBitmap(bmp);
        curso.setText(intent.getStringExtra("cu"));
        nombre.setText(bundle.getString("nombre"));
    }
}
