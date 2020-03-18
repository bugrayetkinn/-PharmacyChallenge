package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gauravk.bubblenavigation.BubbleNavigationLinearView;
import com.gauravk.bubblenavigation.listener.BubbleNavigationChangeListener;

public class HomepageActivity extends AppCompatActivity {

    BubbleNavigationLinearView bubbleNavigation;
    private String RSS_URL = "https://www.donanimhaber.com/rss/tum/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        bubbleNavigation = findViewById(R.id.equal_navigation_bar);

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

    }
}
