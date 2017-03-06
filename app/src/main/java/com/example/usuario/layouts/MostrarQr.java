package com.example.usuario.layouts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

/**
 * Created by Jose on 23/02/2017.
 */

public class MostrarQr extends DialogFragment {
    private static Alumno usuari;
    private static Context contextt;
    public static MostrarQr newInstance(Alumno alumno, Context context) {
        MostrarQr frag = new MostrarQr();
        usuari=alumno;
        contextt=context;
        Bundle args = new Bundle();
        args.putString("title", alumno.getuser());
        frag.setArguments(args);
        return frag;
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.qr, container, false);
        ImageView im=(ImageView) v.findViewById(R.id.im_qr);
        System.out.println(usuari);
        Bitmap a;
        try {
            if(usuari.getQr()==null) {
                System.out.println("El usuario no tiene qr");
                usuari.setQr(StringtoBitmap(usuari.getuser()));
                a=usuari.getQr();
            }else{
                a=usuari.getQr();
            }
            im.setImageBitmap(a);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }


    private Bitmap StringtoBitmap(String frase) throws WriterException{
        BitMatrix bitMatrix=null;
        try{
            bitMatrix=new MultiFormatWriter().encode(frase, BarcodeFormat.DATA_MATRIX.QR_CODE, 400, 400, null);
        }catch (Exception e){
        }
        int qrWidth=bitMatrix.getWidth();
        int qrHeight=bitMatrix.getHeight();
        int[] pixels=new int[qrWidth*qrHeight];
        for(int y=0; y<qrHeight; y++){
            int offset = y*qrWidth;
            for(int x=0; x<qrWidth; x++){
                pixels[offset + x] = bitMatrix.get(x, y)? getResources().getColor(R.color.colorPrimaryDark):getResources().getColor(R.color.blanco);
            }
        }
        Bitmap bitmap=Bitmap.createBitmap(qrWidth, qrHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 400, 0, 0, qrWidth, qrHeight);
        return bitmap;
    }
}

