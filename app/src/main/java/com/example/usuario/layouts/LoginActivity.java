package com.example.usuario.layouts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Claudio on 01/03/2017.
 */

public class LoginActivity extends AppCompatActivity {

    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        final EditText lo=(EditText)findViewById(R.id.ET_login);
        final EditText pa=(EditText)findViewById(R.id.ET_pass);
        ImageView bu=(ImageView) findViewById(R.id.btLogin);
        final Context context=this.getApplicationContext();
        bu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Logi logi = new Logi(lo.getText().toString(), pa.getText().toString(), context);
                    Alumno a = logi.execute().get();

                    if(a==null){

                    }else {
                        Fragmentos.alumno=a;
                        System.out.println(a.getNombre());
                        Intent intent = new Intent(getApplicationContext(), Cursos.class);
                        startActivity(intent);
                    }
                }catch (Exception e){
                    System.out.println(e.toString());
                }
            }
        });

    }
}
