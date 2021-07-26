package com.example.agribot_cf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Crops extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView;
    CardView apple_card, corn_card, tomato_card, potato_card, strawberry_card, grape_card;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_crops);


        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        textView=findViewById(R.id.crop_title);
        toolbar=findViewById(R.id.toolbar);


        apple_card=findViewById(R.id.apple_card);
        corn_card=findViewById(R.id.corn_card);
        tomato_card=findViewById(R.id.tomato_card);
        potato_card=findViewById(R.id.potato_card);
        strawberry_card=findViewById(R.id.strawberry_card);
        grape_card=findViewById(R.id.grape_card);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);


        apple_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Crops.this, Apple.class);
                startActivity(intent);
            }
        });

        corn_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Crops.this, Corn.class);
                startActivity(intent);
            }
        });

        grape_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Crops.this, Grape.class);
                startActivity(intent);
            }
        });

        tomato_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Crops.this, Tomato.class);
                startActivity(intent);
            }
        });

        potato_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Crops.this, Potato.class);
                startActivity(intent);
            }
        });

        strawberry_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Crops.this, Strawberry.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){

            case R.id.nav_home:
                break;

                case R.id.nav_controller:
                Intent intent= new Intent(Crops.this, BotController.class );
                startActivity(intent);
                break;

            case R.id.nav_abtus:
                Intent intent1= new Intent(Crops.this, AbtUs.class );
                startActivity(intent1);
                break;

            case R.id.nav_faq:
                Intent intent2= new Intent(Crops.this, faq.class );
                startActivity(intent2);
                break;

            case R.id.nav_profile:
                Intent intent3= new Intent(Crops.this, Profile.class );
                startActivity(intent3);
                break;

            case R.id.nav_logout:
                Intent intent5= new Intent(Crops.this, Login.class );
                startActivity(intent5);
                break;
        }
        return true;
    }
}