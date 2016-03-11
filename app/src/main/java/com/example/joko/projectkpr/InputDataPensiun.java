package com.example.joko.projectkpr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by joko on 9/20/15.
 */
public class InputDataPensiun extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_pensiun);

        TextView Pensiun = (TextView)findViewById(R.id.PembiayaanPensiun);
        //Button proses = (Button)findViewById(R.id.prosesPensiun);

        Intent i = this.getIntent();

        Button exit = (Button)findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ExitPensiun = new Intent(InputDataPensiun.this, MainActivityKPR.class);
                startActivity(ExitPensiun);
                //setContentView(R.layout.activity_main_activity_kpr);
            }
        });

        /*proses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        final EditText inGajiPensiun = (EditText)findViewById(R.id.inGajiPensiun);
        final Spinner spinnerPensiun = (Spinner)findViewById(R.id.ProdukPensiun);
        //final String SpinnerPensiun = spinnerPensiun.getSelectedItem().toString();
        final EditText jwPensiun = (EditText)findViewById(R.id.inJWPensiun);
        final EditText inYieldPensiun = (EditText)findViewById(R.id.inYieldPensiun);

        inGajiPensiun.addTextChangedListener(new TextWatcher(){
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
                    inGajiPensiun.removeTextChangedListener(this);
                    String replaceable = String.format("[%s,.\\s]", NumberFormat.getInstance().getCurrency().getSymbol());
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
                    inGajiPensiun.setText(formatted);
                    inGajiPensiun.setSelection(formatted.length());
                    inGajiPensiun.addTextChangedListener(this);
                }
            }


        });

        Button btn = (Button)findViewById(R.id.prosesPensiun);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                setContentView(R.layout.tampilpensiun);
                DecimalFormat nilaiTotal = new DecimalFormat("#,###,##0.00");
                TextView tampilgaji = (TextView) findViewById(R.id.TampilanGajiPensiun);
                TextView tampilproduk = (TextView) findViewById(R.id.TampilanProdukPinjaman);
                TextView tampilangsuran = (TextView) findViewById(R.id.TampilanAngsuranPensiun);
                TextView tampilpinjaman = (TextView) findViewById(R.id.TampilanPinjamanPensiun);

                String pensiunInput = String.valueOf(inGajiPensiun.getText().toString());
                String trimPensiun = pensiunInput.trim().replaceAll(",","");
                Double gaji = Double.parseDouble(trimPensiun);
                //tampilgaji.setText(nilaiTotal.format(inGajiPensiun.getText()));
                tampilgaji.setText(nilaiTotal.format(gaji));
                String SpinnerPensiun = spinnerPensiun.getSelectedItem().toString();
                tampilproduk.setText(SpinnerPensiun);

                //int gaji = Integer.parseInt(inGajiPensiun.getText().toString());
                //int nilaiPangkat = Integer.parseInt(inYieldPensiun.getText().toString());
                String Pangkat = String.valueOf(inYieldPensiun.getText().toString());
                Double nilaiPangkat = Double.parseDouble(Pangkat);
                int jangkaWaktu = Integer.parseInt(jwPensiun.getText().toString());
                //tampilangsuran.setText(SpinnerPensiun);


                Double hasilGaji2 = null;
                if ("BARU".equals(SpinnerPensiun)) {
                    double hasilGaji = (double) gaji * 80 / 100;
                    tampilangsuran.setText(nilaiTotal.format(hasilGaji));
                    hasilGaji2 = hasilGaji;
                } else if ("TOP UP".equals(SpinnerPensiun)) {
                    double hasilGaji = (double) gaji * 80 / 100;
                    tampilangsuran.setText(nilaiTotal.format(hasilGaji));
                    hasilGaji2 = hasilGaji;
                } else {
                    double hasilGaji = (double) gaji * 90 / 100;
                    tampilangsuran.setText(nilaiTotal.format(hasilGaji));
                    hasilGaji2 = hasilGaji;
                }


                double cekHitung = (double) nilaiPangkat / 1200;
                //DecimalFormat nilaiTotal = new DecimalFormat("#,###,##0.00");
                double hasilAngsuran = Math.pow(1 + cekHitung, jangkaWaktu);


                double hitungPinjaman = (double) hasilGaji2 * ((1 - (1 / (hasilAngsuran))) / cekHitung);
                //double total = Math.round(hitungPinjaman);
                tampilpinjaman.setText(nilaiTotal.format(hitungPinjaman));

                /*else
                    if (SpinnerPensiun == "TOP UP"){
                        spinnerPensiun.getSelectedItem().toString();
                    } else {
                        spinnerPensiun.getSelectedItem().toString();
                    }*/
                Button back = (Button)findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent backPensiun = new Intent(InputDataPensiun.this, InputDataPensiun.class);
                        startActivity(backPensiun);
                        //setContentView(R.layout.input_pensiun);
                    }
                });

            }
        });

    }

}
