package com.example.myapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapter.EczaneAdapter;
import com.example.myapplication.Model.ApiClient;
import com.example.myapplication.Model.CityList;
import com.example.myapplication.Model.DistrictList;
import com.example.myapplication.Model.EczaneGelen;
import com.example.myapplication.Model.EczaneModel;
import com.example.myapplication.Model.RestInterface;
import com.example.myapplication.Model.Result;
import com.example.myapplication.R;
import com.google.gson.Gson;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Code with ❤
 * ╔════════════════════════════╗
 * ║  Created by Buğra Yetkin  ║
 * ╠════════════════════════════╣
 * ║ bugrayetkinn@gmail.com ║
 * ╠════════════════════════════╣
 * ║     20/03/2020 - 09:49     ║
 * ╚════════════════════════════╝
 */
public class EczaneFragment extends Fragment {

    RecyclerView recyclerView;
    SearchableSpinner spinnerCity, spinnerDistrict;
    BufferedReader jsonCityReader, jsonDistrictReader;
    StringBuilder jsonCityBuilder, jsonDistrictBuilder;
    CityList cityList;
    DistrictList districtList;
    Gson gsonCity, gsonDistrict;
    int cityCode;
    List<String> cityDataList = new ArrayList<>();
    List<String> districtDataList = new ArrayList<>();
    ArrayAdapter<String> cityAdapter;
    ArrayAdapter<String> districtAdapter;

    ArrayList<EczaneModel> eczaneModelArrayList = new ArrayList<>();

    EczaneAdapter eczaneAdapter;

    RestInterface restInterface;
    String city, district;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eczane, container, false);

        spinnerCity = view.findViewById(R.id.spinnerCity);
        spinnerDistrict = view.findViewById(R.id.spinnerDistrict);
        recyclerView = view.findViewById(R.id.recylcerViewEcz);

        spinnerDistrict.setTitle("");
        spinnerCity.setTitle("Türkiye");


        cityList = new CityList();
        districtList = new DistrictList();

        try {
            jsonCityReader = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.city)));
            jsonCityBuilder = new StringBuilder();

            for (String line = null; (line = jsonCityReader.readLine()) != null; ) {
                jsonCityBuilder.append(line).append("\n");
            }

            gsonCity = new Gson();
            cityList = gsonCity.fromJson(jsonCityBuilder.toString(), CityList.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < cityList.getCityDetail().size(); i++) {
            cityDataList.add(cityList.getCityDetail().get(i).getName().toUpperCase());
        }

        cityAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, cityDataList);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(cityAdapter);

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                cityCode = position + 1;
                spinnerDistrict.setTitle(cityList.getCityDetail().get(position).getName());
                city = cityDataList.get(position).toUpperCase();

                if (districtDataList.size() > 0) {
                    districtDataList.clear();
                }
                for (int i = 0; i < districtList.getDistrictDetail().size(); i++) {
                    if (districtList.getDistrictDetail().get(i).getIlceSehirkey() == cityCode) {
                        districtDataList.add(districtList.getDistrictDetail().get(i).getIlceTitle());
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                district = districtDataList.get(position).toUpperCase();
                getPharmacyList(city, district);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        try {
            //load File
            jsonDistrictReader = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.district)));
            jsonDistrictBuilder = new StringBuilder();
            for (String line = null; (line = jsonDistrictReader.readLine()) != null; ) {
                jsonDistrictBuilder.append(line).append("\n");
            }

            gsonDistrict = new Gson();
            districtList = gsonDistrict.fromJson(jsonDistrictBuilder.toString(), DistrictList.class);


        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        districtAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, districtDataList);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrict.setAdapter(districtAdapter);


        return view;
    }

    private void getPharmacyList(String il, String ilce) {
        restInterface = ApiClient.getClient().create(RestInterface.class);
        Call<EczaneGelen> call = restInterface.getResult("apikey 3UANksUKuUApFbYezfc1eK:66IvgahJFr8mhATaADR6ko",
                "application/json", il, ilce);
        call.enqueue(new Callback<EczaneGelen>() {
            @Override
            public void onResponse(Call<EczaneGelen> call, Response<EczaneGelen> response) {
                List<Result> resultList = new ArrayList<>();
                resultList = response.body().result;

                if (eczaneModelArrayList != null || resultList.size() == 0) {
                    eczaneModelArrayList.clear();
                }

                for (int i = 0; i < resultList.size(); i++) {
                    EczaneModel eczaneModel = new EczaneModel(resultList.get(i).name, resultList.get(i).address);
                    eczaneModelArrayList.add(eczaneModel);
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setHasFixedSize(true);
                eczaneAdapter = new EczaneAdapter(eczaneModelArrayList, getContext());
                recyclerView.setAdapter(eczaneAdapter);
            }

            @Override
            public void onFailure(Call<EczaneGelen> call, Throwable t) {

            }

        });
    }


}
