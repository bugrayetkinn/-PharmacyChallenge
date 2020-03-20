package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Fragment.EczaneFragment;
import com.example.myapplication.Fragment.HaberFragment;
import com.example.myapplication.MyAlertCreate;
import com.example.myapplication.R;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.material.navigation.NavigationView;

public class HomepageActivity extends AppCompatActivity {


    BubbleNavigationLinearView bubbleNavigation;
    FrameLayout frameLayout;
    Toolbar toolbar;
    ImageView imgCikis;
    DrawerLayout drawer;
    NavigationView navigation;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.frameLayout);
        progressBar = findViewById(R.id.progressBar);
        imgCikis = findViewById(R.id.imgCikis);
        drawer = findViewById(R.id.drawer);
        navigation = findViewById(R.id.navigation);
        bubbleNavigation = findViewById(R.id.equal_navigation_bar);
        toolbar.setTitleMarginStart(200);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        toggle.syncState();

        View view = getLayoutInflater().inflate(R.layout.navigation_header, null, false);
        navigation.addHeaderView(view);
        navigation.setItemIconSize(100);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.menuHaber:
                        changeFragment(R.id.frameLayout, new HaberFragment());
                        break;
                    case R.id.menuEczane:
                        changeFragment(R.id.frameLayout, new EczaneFragment());
                        break;
                    case R.id.menuProfil:

                        break;
                    case R.id.menuCikis:
                        myAlertDialog();
                }
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                }
                return true;
            }
        });


        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new HaberFragment()).commit();

        CountDownTimer timer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFinish() {
                progressBar.setVisibility(View.INVISIBLE);
            }
        }.start();


        bubbleNavigation.setNavigationChangeListener(new BubbleNavigationChangeListener() {
            @Override
            public void onNavigationChanged(View view, int position) {
                switch (position) {
                    case 0:
                        CountDownTimer timer = new CountDownTimer(1000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                progressBar.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onFinish() {
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        }.start();
                        changeFragment(R.id.frameLayout, new HaberFragment());
                        break;
                    case 1:
                        changeFragment(R.id.frameLayout, new EczaneFragment());
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

                myAlertDialog();
            }
        });

    }

    public void changeFragment(int layout, Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(layout, fragment).commit();
    }

    public void myAlertDialog() {

        final MyAlertCreate alertCreate = new MyAlertCreate();

        View view = getLayoutInflater().inflate(R.layout.alert_cikis, null, false);
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

        buttonIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertCreate.getDialog().dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {

        final MyAlertCreate alertCreate = new MyAlertCreate();
        View view = getLayoutInflater().inflate(R.layout.alert_cikis, null, false);

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
        buttonIptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertCreate.getDialog().dismiss();
            }
        });

    }
}
