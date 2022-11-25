package com.example.sayacuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class SayacActivity extends AppCompatActivity {

    private TextView sayiYazisi;
    private Button eksi;
    private Button arti;
    private Button ayarlar;
    private MediaPlayer sesMedyasi;
    private Vibrator vibrator;
    private int sayi;
    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.Eksi:
                    sayiEksilt(1);
                    break;
                case R.id.Arti:
                    sayiArtir(1);
                    break;
                case R.id.AyarTusu:
                    ayarlariAc();
                    break;
            }
        }
    };
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_VOLUME_UP){
            sayiArtir(5);
            return true;
        }
        else if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
            sayiEksilt(5);
            return true;
        }
        else{
            return super.onKeyDown(keyCode, event);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sayac);
        sesMedyasi = MediaPlayer.create(getApplicationContext(),R.raw.be);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        sayiYazisi = (TextView) findViewById(R.id.Sayi);
        eksi = (Button) findViewById(R.id.Eksi);
        eksi.setOnClickListener(clickListener);
        arti = (Button) findViewById(R.id.Arti);
        arti.setOnClickListener(clickListener);
        ayarlar = (Button) findViewById(R.id.AyarTusu);
        ayarlar.setOnClickListener(clickListener);
        SharedSingleton.getInstance(getApplicationContext());
        initSayi();


    }

    private void initSayi() {
        sayi=SharedSingleton.sayacKontrol();
        sayiYazisi.setText(sayi+" ");
    }
    private void sayiArtir(int deger) {
        if(Ayarlar.sinir>sayi+deger){
            sayi+=deger;
            SharedSingleton.sayacAyarla(sayi);
            sayiYazisi.setText(sayi+" ");
        }
        else if(Ayarlar.titresimVeSes){
            sesMedyasi.start();
            vibrator.vibrate(500);
        }
    }
    private void sayiEksilt(int deger) {
        if(-Ayarlar.sinir<sayi-deger){
            sayi-=deger;
            SharedSingleton.sayacAyarla(sayi);
            sayiYazisi.setText(sayi+" ");
        }
        else if(Ayarlar.titresimVeSes){
            sesMedyasi.start();
            vibrator.vibrate(500);
        }
    }
    private void ayarlariAc(){
        Intent intent =new Intent(SayacActivity.this,Ayarlar.class);
        startActivity(intent);
        finish();
    }
}