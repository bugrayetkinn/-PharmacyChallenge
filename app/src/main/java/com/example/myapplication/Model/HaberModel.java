package com.example.myapplication.Model;

/**
 * Code with ❤
 * ╔════════════════════════════╗
 * ║  Created by Buğra Yetkin  ║
 * ╠════════════════════════════╣
 * ║ bugrayetkinn@gmail.com ║
 * ╠════════════════════════════╣
 * ║     19/03/2020 - 13:56     ║
 * ╚════════════════════════════╝
 */
public class HaberModel {


    private String baslik;
    private String imgUrl;
    private String tarih;

    public HaberModel(String baslik, String imgUrl, String tarih) {
        this.baslik = baslik;
        this.imgUrl = imgUrl;
        this.tarih = tarih;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }


    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }


    public String getImgLink() {
        return imgUrl;
    }

    public void setImgLink(String imgLink) {
        this.imgUrl = imgLink;
    }
}
