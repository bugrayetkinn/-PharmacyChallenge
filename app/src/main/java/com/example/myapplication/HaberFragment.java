package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.myapplication.network.JSONResponse;
import com.example.myapplication.network.RequestInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Code with ❤
 * ╔════════════════════════════╗
 * ║  Created by Buğra Yetkin  ║
 * ╠════════════════════════════╣
 * ║ bugrayetkinn@gmail.com ║
 * ╠════════════════════════════╣
 * ║     19/03/2020 - 14:18     ║
 * ╚════════════════════════════╝
 */
public class HaberFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    ArrayList<HaberModel> modelArrayList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = getLayoutInflater().inflate(R.layout.fragment_haberler, container, false);

        //adapter = new RecyclerAdapter(modelArrayList, container.getContext());
        recyclerView = view.findViewById(R.id.recyclerView);
        modelArrayList = new ArrayList<>();
        adapter = new RecyclerAdapter(modelArrayList, getContext());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        //loadJSON();

        return view;
    }

    public void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.donanimhaber.com").addConverterFactory(GsonConverterFactory.create()).build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponse> call = request.getJson();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {

                JSONResponse jsonResponse = response.body();
                modelArrayList = new ArrayList<>(Arrays.asList(jsonResponse.getHaberModels()));
                adapter = new RecyclerAdapter(modelArrayList, getContext());
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {

            }
        });
    }
}
