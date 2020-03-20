package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.Adapter.RecyclerAdapter;
import com.example.myapplication.Model.HaberModel;
import com.example.myapplication.MySingleton;
import com.example.myapplication.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Code with ❤
 * ╔════════════════════════════╗
 * ║  Created by Buğra Yetkin  ║
 * ╠════════════════════════════╣
 * ║ bugrayetkinn@gmail.com ║
 * ╠════════════════════════════╣
 * ║     20/03/2020 - 10:03     ║
 * ╚════════════════════════════╝
 */
public class HaberFragment extends Fragment {

    StringRequest mRequest;

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    ArrayList<HaberModel> modelArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_haber, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        adapter = new RecyclerAdapter(modelArrayList, container.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        String url = "https://www.donanimhaber.com/rss/tum/";
        mRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
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

        MySingleton.getInstance(container.getContext()).addToRequestQueue(mRequest);

        return view;
    }
}
