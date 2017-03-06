package com.example.usuario.layouts;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Jose on 16/02/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private Fragmentos fragEso;
    private Fragmentos fragBach;
    private Fragmentos fragCiclos;
    private ArrayList<Clases> eso = new ArrayList<>();
    private ArrayList<Clases> bach = new ArrayList<>();
    private ArrayList<Clases> cf = new ArrayList<>();

    public PagerAdapter(FragmentManager fm){
        super(fm);
        llenarArray();
    }

    public void setFragments(Context c){

        fragEso = new Fragmentos();
        fragBach = new Fragmentos();
        fragCiclos = new Fragmentos();

        fragEso.setClases(eso);
        fragBach.setClases(bach);
        fragCiclos.setClases(cf);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag;
        switch (position){
            case 0:
                frag=fragEso;
                return frag;

            case 1:
                frag=fragBach;
                return frag;

            case 2:
                frag=fragCiclos;
                return frag;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    private void llenarArray() {
        if(eso.isEmpty()){
            eso.add(new Clases("ESOA", R.drawable.book));
            eso.add(new Clases("ESOB", R.drawable.book));
            eso.add(new Clases("ESOC", R.drawable.book));
        }

        if(bach.isEmpty()){
            bach.add(new Clases("Bachiller A", R.drawable.bachillerato));
            bach.add(new Clases("Bachiller B", R.drawable.bachillerato));
            bach.add(new Clases("Bachiller C", R.drawable.bachillerato));
        }

        if(cf.isEmpty()){
            cf.add(new Clases("DAM", R.drawable.informatica));
            cf.add(new Clases("EVA", R.drawable.informatica));
            cf.add(new Clases("GAD", R.drawable.informatica));
            cf.add(new Clases("SMR", R.drawable.informatica));
            cf.add(new Clases("SEA", R.drawable.informatica));
            cf.add(new Clases("IEA", R.drawable.informatica));
            cf.add(new Clases("FPB", R.drawable.informatica));
            cf.add(new Clases("FIN", R.drawable.informatica));
        }
    }
}
