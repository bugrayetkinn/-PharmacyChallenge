package com.example.myapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EczaneGelen {

    @SerializedName("result")
    @Expose
    public List<Result> result = null;

}
