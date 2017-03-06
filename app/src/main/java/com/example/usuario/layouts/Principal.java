package com.example.usuario.layouts;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class Principal extends AppCompatActivity {
    private TextView orla, qr;
    private MostrarQr mQr=new MostrarQr();
    public static Alumno AlumnoActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        orla = (TextView) findViewById(R.id.btnOrla);
        qr = (TextView) findViewById(R.id.btnQr);
        TextView a = (TextView) findViewById(R.id.textView);
        a.setText(AlumnoActual.getuser());
        orla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cursos.class);
                startActivity(intent);
            }
        });

        qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm=getSupportFragmentManager();
                DialogFragment newFragment = MostrarQr.newInstance(AlumnoActual);
                newFragment.show(fm, "tag");
            }
        });

    }

}
