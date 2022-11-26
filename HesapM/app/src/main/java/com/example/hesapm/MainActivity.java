package com.example.hesapm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText yeniSayi;
    private TextView operatorGosterge,sonuc;
    private String bekleyenOperator;

    private Double sayi1=null,sayi2=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yeniSayi =(EditText) findViewById(R.id.editTextNumber);
        operatorGosterge = (TextView) findViewById(R.id.islemGosterge);
        sonuc = (TextView) findViewById(R.id.sonucGosterge);

        Button button0=(Button) findViewById(R.id.button0);
        Button button1=(Button) findViewById(R.id.button1);
        Button button2=(Button) findViewById(R.id.button2);
        Button button3=(Button) findViewById(R.id.button3);
        Button button4=(Button) findViewById(R.id.button4);
        Button button5=(Button) findViewById(R.id.button5);
        Button button6=(Button) findViewById(R.id.button6);
        Button button7=(Button) findViewById(R.id.button7);
        Button button8=(Button) findViewById(R.id.button8);
        Button button9=(Button) findViewById(R.id.button9);
        Button divideBtn=(Button) findViewById(R.id.divide);
        Button mulBtn=(Button) findViewById(R.id.multiply);
        Button sumBtn=(Button) findViewById(R.id.sum);
        Button subBtn=(Button) findViewById(R.id.sub);
        Button equalBtn=(Button) findViewById(R.id.equal);

        View.OnClickListener listener =new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                yeniSayi.append(button.getText().toString());
            }
        };

        View.OnClickListener operatorListener =new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button=(Button) view;
                String op = button.getText().toString();
                String value=yeniSayi.getText().toString();
                try {
                    Double dValue=Double.valueOf(value);
                    islemleriGerceklestirmek(dValue,op);
                }catch (NumberFormatException e){
                    yeniSayi.setText("");
                }
                bekleyenOperator=op;
                operatorGosterge.setText(bekleyenOperator);
            }
        };
        button7.setOnClickListener(listener);button8.setOnClickListener(listener);button9.setOnClickListener(listener);
        button4.setOnClickListener(listener);button5.setOnClickListener(listener);button6.setOnClickListener(listener);
        button1.setOnClickListener(listener);button2.setOnClickListener(listener);button3.setOnClickListener(listener);
        button0.setOnClickListener(listener);

        divideBtn.setOnClickListener(operatorListener);mulBtn.setOnClickListener(operatorListener);
        sumBtn.setOnClickListener(operatorListener);subBtn.setOnClickListener(operatorListener);
        equalBtn.setOnClickListener(operatorListener);
    }

    private void islemleriGerceklestirmek(Double value,String op){
        if(null == sayi1){
            sayi1=value;
        }else{
            sayi2=value;
            if(bekleyenOperator=="="){
                bekleyenOperator=op;
            }
            switch (bekleyenOperator){
                case "=":
                    sayi1=sayi2;
                    break;
                case "/":
                    if(sayi2==0){
                        sayi1=0.0d;
                    }else{
                        sayi1/=sayi2;
                    }
                    break;
                case "+":
                    sayi1+=sayi2;
                    break;
                case "*":
                    sayi1*=sayi2;
                    break;
                case "-":
                    sayi1-=sayi2;
                    break;
            }
        }
        sonuc.setText(sayi1.toString());
        yeniSayi.setText("");
        operatorGosterge.setText(op);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("BEKLEYENOPERATOR",bekleyenOperator);
        if(sayi1!=null){
            outState.putDouble("SAYI1",sayi1);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        bekleyenOperator= savedInstanceState.getString("BEKLEYENOPERATOR");
        sayi1=savedInstanceState.getDouble("SAY1");
        sonuc.setText(String.valueOf(sayi1));
        operatorGosterge.setText(bekleyenOperator);
    }
}