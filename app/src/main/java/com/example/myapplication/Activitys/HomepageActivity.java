package com.example.myapplication.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.Adapter.RecyclerAdapter;
import com.example.myapplication.Model.HaberModel;
import com.example.myapplication.MyAlertCreate;
import com.example.myapplication.MySingleton;
import com.example.myapplication.R;
import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;
import com.google.android.material.navigation.NavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity {

    RequestQueue mQueue;

    BubbleNavigationLinearView bubbleNavigation;
    Toolbar toolbar;
    ImageView imgCikis;
    DrawerLayout drawer;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    ArrayList<HaberModel> modelArrayList;
    NavigationView navigation;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progressBar);
        imgCikis = findViewById(R.id.imgCikis);
        recyclerView = findViewById(R.id.recyclerView);
        drawer = findViewById(R.id.drawer);
        navigation = findViewById(R.id.navigation);
        bubbleNavigation = findViewById(R.id.equal_navigation_bar);
        modelArrayList = new ArrayList<>();
        toolbar.setTitleMarginStart(200);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, 0, 0);
        toggle.syncState();

        adapter = new RecyclerAdapter(modelArrayList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


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

                buttonIptal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertCreate.getDialog().dismiss();
                    }
                });
            }
        });

        mQueue = MySingleton.getInstance(this).getRequestQueue();

        String url = "https://www.donanimhaber.com/rss/tum/";
        StringRequest mRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Document doc = Jsoup.parse(response);
                Elements itemElements = doc.select("item");

                for (int i = 0; i < itemElements.size(); i++) {

                    Element item = itemElements.get(i);
                    String title = item.child(0).text();
                    String imgLink = item.child(5).attr("url");
                    String tarih = item.child(4).text();
                    String haberLink = item.child(3).text();

                    HaberModel model = new HaberModel(title, imgLink, tarih, haberLink);
                    modelArrayList.add(model);
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(this).addToRequestQueue(mRequest);
    }
}
