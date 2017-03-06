package com.example.usuario.layouts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by usuario on 3/02/17.
 */

public class Cursos extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estudios);


        //---Toolbar---
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        //---Tab---
        TabLayout tab=(TabLayout)findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("ESO"));
        tab.addTab(tab.newTab().setText("BACHILLER"));
        tab.addTab(tab.newTab().setText("CICLOS"));
        tab.setTabGravity(TabLayout.GRAVITY_FILL);

        //---ViewPager---
        final ViewPager viewPager=(ViewPager)findViewById(R.id.pager);

        //---Adapter----
        final PagerAdapter adapter=new PagerAdapter(getSupportFragmentManager());
        adapter.setFragments(this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
