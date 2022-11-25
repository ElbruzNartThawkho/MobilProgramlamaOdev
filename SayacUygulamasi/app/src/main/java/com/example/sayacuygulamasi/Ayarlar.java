package com.example.sayacuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class Ayarlar extends AppCompatActivity {

    public static boolean titresimVeSes;
    public static int sinir;
    private CheckBox titresimVeSesKutusu;
    private Button limitEksi,limitArti;
    private TextView limitText;

    private View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.limitEksilis:
                    limitDegis(-1);
                    break;
                case R.id.limitArtis:
                    limitDegis(1);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);
        titresimVeSes=SharedSingleton.titresimVeSesKontrol();
        sinir=SharedSingleton.limitKontrol();
        limitText =(TextView) findViewById(R.id.limit);
        limitEksi=(Button) findViewById(R.id.limitEksilis);
        limitEksi.setOnClickListener(clickListener);
        limitArti=(Button) findViewById(R.id.limitArtis);
        limitArti.setOnClickListener(clickListener);
        titresimVeSesKutusu=(CheckBox) findViewById(R.id.TitresimVeSes);
        titresimVeSesKutusu.setChecked(titresimVeSes);
        titresimVeSesKutusu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                titresimVeSes=b;
                SharedSingleton.titresimVeSesAyarla(titresimVeSes);
            }
        });
    }

    private void limitDegis(int deger) {
        if(0<=sinir+deger){
            sinir+=deger;
            SharedSingleton.limitAyarla(sinir);
            limitText.setText(sinir+" ");
        }
    }

    public void  onBackPressed(){
        startActivity(new Intent(Ayarlar.this,SayacActivity.class));
    }
}