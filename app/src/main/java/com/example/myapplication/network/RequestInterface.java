package com.example.myapplication.network;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Code with ❤
 * ╔════════════════════════════╗
 * ║  Created by Buğra Yetkin  ║
 * ╠════════════════════════════╣
 * ║ bugrayetkinn@gmail.com ║
 * ╠════════════════════════════╣
 * ║     19/03/2020 - 16:26     ║
 * ╚════════════════════════════╝
 */
public interface RequestInterface {
    @GET("rss/tum")
    Call<JSONResponse> getJson();
}
