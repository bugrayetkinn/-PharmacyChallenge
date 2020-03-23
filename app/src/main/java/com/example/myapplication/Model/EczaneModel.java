package com.example.myapplication.Model;

/**
 * Code with ❤
 * ╔════════════════════════════╗
 * ║  Created by Buğra Yetkin  ║
 * ╠════════════════════════════╣
 * ║ bugrayetkinn@gmail.com ║
 * ╠════════════════════════════╣
 * ║     23/03/2020 - 14:49     ║
 * ╚════════════════════════════╝
 */
public class EczaneModel {

    private String eczaneAdi;
    private String eczaneAdres;

    public EczaneModel(String eczaneAdi, String eczaneAdres) {
        this.eczaneAdi = eczaneAdi;
        this.eczaneAdres = eczaneAdres;
    }

    public String getEczaneAdi() {
        return eczaneAdi;
    }

    public void setEczaneAdi(String eczaneAdi) {
        this.eczaneAdi = eczaneAdi;
    }

    public String getEczaneAdres() {
        return eczaneAdres;
    }

    public void setEczaneAdres(String eczaneAdres) {
        this.eczaneAdres = eczaneAdres;
    }
}
