package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.material.navigation.NavigationView;

public class HomepageActivity extends AppCompatActivity {

    BubbleNavigationLinearView bubbleNavigation;
    Toolbar toolbar;
    ImageView imgCikis;
    DrawerLayout drawer;
    NavigationView navigation;

    AlertDialog.Builder alertDialog;
    private String RSS_URL = "https://www.donanimhaber.com/rss/tum/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        toolbar = findViewById(R.id.toolbar);
        imgCikis = findViewById(R.id.imgCikis);
        drawer = findViewById(R.id.drawer);
        navigation = findViewById(R.id.navigation);
        bubbleNavigation = findViewById(R.id.equal_navigation_bar);
        toolbar.setTitleMarginStart(200);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        toggle.syncState();

        bubbleNavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(HomepageActivity.this, "Haberler", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(HomepageActivity.this, "Eczane", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(HomepageActivity.this, "Profil", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        imgCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = getLayoutInflater().inflate(R.layout.alert_cikis, null, false);
                final MyAlertCreate alertCreate = new MyAlertCreate();

                alertCreate.createAlert(HomepageActivity.this, view);
                Button buttonEvet = view.findViewById(R.id.buttonEvet);
                Button buttonIptal = view.findViewById(R.id.buttonIptal);

                buttonEvet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(HomepageActivity.this, LoginActivity.class));
                        alertCreate.getDialog().dismiss();
                        finish();
                    }
                });
            }
        });


    }
}
