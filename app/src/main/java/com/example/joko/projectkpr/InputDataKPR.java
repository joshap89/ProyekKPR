package com.example.joko.projectkpr;

/**
 * Created by joko on 9/19/15.
 */
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class InputDataKPR extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_data);

        TextView Kalkulator = (TextView)findViewById(R.id.kalkulator);
        Button proses = (Button)findViewById(R.id.ProsesKal);

        Intent inKal = this.getIntent();

        Button home = (Button)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent HomeInput = new Intent(InputDataKPR.this, MainActivityKPR.class);
                startActivity(HomeInput);
                //setContentView(R.layout.activity_main_activity_kpr);
            }
        });

       // final EditText inPinjaman1 = (EditText)findViewById(R.id.inPinjaman);
        final EditText inPinjaman = (EditText)findViewById(R.id.inPinjaman);
        inPinjaman.addTextChangedListener(new TextWatcher(){
            private String current = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){

            }

            @Override
            public void afterTextChanged(Editable s){
                if(!s.toString().equals(current)){
                    inPinjaman.removeTextChangedListener(this);
                    String replaceable = String.format("[%s,.\\s]",NumberFormat.getInstance().getCurrency().getSymbol());
                    String cleanString = s.toString().replaceAll(replaceable,"");

                    double parsed;
                    try{
                        parsed=Double.parseDouble(cleanString);
                    }catch (NumberFormatException e){
                        parsed = 000;
                    }
                    NumberFormat formatter = NumberFormat.getInstance();
                    formatter.setMaximumFractionDigits(0);
                    String formatted = formatter.format((parsed));

                    current = formatted;
                    inPinjaman.setText(formatted);
                    inPinjaman.setSelection(formatted.length());
                    inPinjaman.addTextChangedListener(this);
                }
            }


        });


                final EditText inYield = (EditText) findViewById(R.id.inYield);
                final EditText jw = (EditText) findViewById(R.id.inJW);


                Button btn = (Button) findViewById(R.id.ProsesKal);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //int pinjaman = Integer.parseInt(inPinjaman1.getText().toString().replace("", ","));
                        setContentView(R.layout.tampilkalkulator);
                        DecimalFormat nilaiTotal = new DecimalFormat("#,###,##0.00");
                        TextView tampilPinjaman = (TextView) findViewById(R.id.TampilanPinjaman);
                        TextView tampilYield = (TextView) findViewById(R.id.TampilanYield);
                        TextView tampilJW = (TextView) findViewById(R.id.TampilanJW);
                        TextView tampilAngsuran = (TextView) findViewById(R.id.TampilanAngsuran);

                        String pinjamanInput = String.valueOf(inPinjaman.getText().toString());
                        String trimPinjaman = pinjamanInput.trim().replaceAll(",","");
                        Double pinjaman = Double.parseDouble(trimPinjaman);
                        //tampilPinjaman.setText(inPinjaman.getText().toString());
                        tampilPinjaman.setText(nilaiTotal.format(pinjaman));
                        tampilYield.setText(inYield.getText().toString());
                        tampilJW.setText(jw.getText().toString());

                        String Pangkat = String.valueOf(inYield.getText().toString());
                        Double nilaiPangkat = Double.parseDouble(Pangkat);

                        int jangkaWaktu = Integer.parseInt(jw.getText().toString());

                        double cekHitung = (double) nilaiPangkat / 1200;
                        double hasilAngsuran = Math.pow(1 + cekHitung, jangkaWaktu);

                        double hitungPinjaman = (double) pinjaman * (cekHitung / (1 - (1 / ((hasilAngsuran)))));

                        tampilAngsuran.setText(nilaiTotal.format(hitungPinjaman));

                        Button back = (Button) findViewById(R.id.back);
                        back.setOnClickListener(new View.OnClickListener() {
                            @Override
                    public void onClick(View v) {
                        Intent HomeInput = new Intent(InputDataKPR.this, InputDataKPR.class);
                        startActivity(HomeInput);
                        //setContentView(R.layout.input_data);
                    }
                });

            }

        });



    }




}
