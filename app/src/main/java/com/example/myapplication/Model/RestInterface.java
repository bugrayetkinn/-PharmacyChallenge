package com.example.myapplication.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Code with ❤
 * ╔════════════════════════════╗
 * ║  Created by Buğra Yetkin  ║
 * ╠════════════════════════════╣
 * ║ bugrayetkinn@gmail.com ║
 * ╠════════════════════════════╣
 * ║     20/03/2020 - 23:48     ║
 * ╚════════════════════════════╝
 */
public interface RestInterface {

    @GET("dutyPharmacy")
    Call<EczaneGelen> getResult(@Header("authorization") String string, @Header("content-type") String contentType,

                                @Query("il") String city, @Query("ilce") String district);
}
