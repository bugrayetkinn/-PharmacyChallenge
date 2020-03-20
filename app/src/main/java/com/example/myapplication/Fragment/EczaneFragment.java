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

import com.example.myapplication.Model.CityList;
import com.example.myapplication.Model.DistrictList;
import com.example.myapplication.R;
import com.google.gson.Gson;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_eczane, container, false);

        spinnerCity = view.findViewById(R.id.spinnerCity);
        spinnerDistrict = view.findViewById(R.id.spinnerDistrict);

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
}
