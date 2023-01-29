package com.example.mobilprogramlamafinalsinavi;

public class MesajModel {
    String mesajAdi;
    String mesajIcerik;


    public MesajModel(String mesajAdi, String mesajIcerik) {
        this.mesajAdi = mesajAdi;
        this.mesajIcerik = mesajIcerik;
    }

    public String getMesajAdi() {
        return mesajAdi;
    }

    public void setMesajAdi(String mesajAdi) {
        this.mesajAdi = mesajAdi;
    }

    public String getMesajIcerik() {
        return mesajIcerik;
    }

    public void setMesajIcerik(String mesajIcerik) {
        this.mesajIcerik = mesajIcerik;
    }
}
