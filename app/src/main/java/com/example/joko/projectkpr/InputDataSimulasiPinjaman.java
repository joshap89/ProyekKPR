package com.example.joko.projectkpr;


/**
 * Created by joko on 9/20/15.
 */
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
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class InputDataSimulasiPinjaman extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_kpr);

        TextView simulasi = (TextView)findViewById(R.id.simulasi);
        //Button prosesSimulasi = (Button)findViewById(R.id.prosesSimulasi);

        Intent in = this.getIntent();
        Button back = (Button)findViewById(R.id.exit);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ExitSimulasi = new Intent(InputDataSimulasiPinjaman.this, MainActivityKPR.class);
                startActivity(ExitSimulasi);
                //setContentView(R.layout.activity_main_activity_kpr);
            }
        });

        /*prosesSimulasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/

        final EditText inTenor = (EditText)findViewById(R.id.inTenor);
        final EditText inYieldKPR = (EditText)findViewById(R.id.inYield);
        final EditText gajiTetap = (EditText)findViewById(R.id.inGT);
        final EditText gajiTidakTetap = (EditText)findViewById(R.id.inGTT);
        final EditText hutang = (EditText)findViewById(R.id.inHutang);

        gajiTetap.addTextChangedListener(new TextWatcher(){
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
                    gajiTetap.removeTextChangedListener(this);
                    String replaceable = String.format("[%s,.\\s]", NumberFormat.getInstance().getCurrency().getSymbol());
                    String cleanString = s.toString().replaceAll(replaceable,"");

                    double parsed;
                    try{
                        parsed=Double.parseDouble(cleanString);
                    }catch (NumberFormatException e){
                        parsed = 0.00;
                    }
                    NumberFormat formatter = NumberFormat.getInstance();
                    formatter.setMaximumFractionDigits(0);
                    String formatted = formatter.format((parsed));

                    current = formatted;
                    gajiTetap.setText(formatted);
                    gajiTetap.setSelection(formatted.length());
                    gajiTetap.addTextChangedListener(this);
                }
            }
        });

        hutang.addTextChangedListener(new TextWatcher() {
            private String current = "";

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(current)) {
                    hutang.removeTextChangedListener(this);
                    String replaceable = String.format("[%s,.\\s]", NumberFormat.getInstance().getCurrency().getSymbol());
                    String cleanString = s.toString().replaceAll(replaceable, "");

                    double parsed;
                    try {
                        parsed = Double.parseDouble(cleanString);
                    } catch (NumberFormatException e) {
                        parsed = 0.00;
                    }
                    NumberFormat formatter = NumberFormat.getInstance();
                    formatter.setMaximumFractionDigits(0);
                    String formatted = formatter.format((parsed));

                    current = formatted;
                    hutang.setText(formatted);
                    hutang.setSelection(formatted.length());
                    hutang.addTextChangedListener(this);
                }
            }
        });

        gajiTidakTetap.addTextChangedListener(new TextWatcher() {
            private String current = "";

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(current)) {
                    gajiTidakTetap.removeTextChangedListener(this);
                    String replaceable = String.format("[%s,.\\s]", NumberFormat.getInstance().getCurrency().getSymbol());
                    String cleanString = s.toString().replaceAll(replaceable, "");

                    double parsed;
                    try {
                        parsed = Double.parseDouble(cleanString);
                    } catch (NumberFormatException e) {
                        parsed = 0.00;
                    }
                    NumberFormat formatter = NumberFormat.getInstance();
                    formatter.setMaximumFractionDigits(0);
                    String formatted = formatter.format((parsed));

                    current = formatted;
                    gajiTidakTetap.setText(formatted);
                    gajiTidakTetap.setSelection(formatted.length());
                    gajiTidakTetap.addTextChangedListener(this);
                }
            }
        });

        Button btn = (Button)findViewById(R.id.prosesSimulasi);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setContentView(R.layout.tampilsimulasikpr);
                TextView tampilJangkaWaktu = (TextView) findViewById(R.id.jangkaWaktuSimulasi);
                TextView tampilYieldSimulasi = (TextView) findViewById(R.id.yieldSimulasi);
                TextView tampilGajiTetap = (TextView) findViewById(R.id.tampilGajiTetap);
                TextView tampilGajiTidakTetap = (TextView) findViewById(R.id.tampilGajiTidakTetap);
                TextView tampilTotalGaji = (TextView) findViewById(R.id.tampilTotalGaji);
                TextView tampilAngsuranBerjalan = (TextView) findViewById(R.id.tampilAngsuranBerjalan);
                TextView tampilCashRatio = (TextView) findViewById(R.id.tampilCashRatio);
                TextView tampilGajiDiakui = (TextView) findViewById(R.id.tampilGajiDiakui);
                TextView tampilPinjamanSimulasi = (TextView) findViewById(R.id.tampilPinjamanSimulasi);


                DecimalFormat nilaiTotal = new DecimalFormat("#,###,##0.00");
                tampilJangkaWaktu.setText(inTenor.getText().toString());
                tampilYieldSimulasi.setText(inYieldKPR.getText().toString());
                //tampilGajiTetap.setText(gajiTetap.getText().toString());
                String gajiTetapInput = String.valueOf(gajiTetap.getText().toString());
                String trimGajiTetap = gajiTetapInput.trim().replaceAll(",", "");
                Double GT = Double.parseDouble(trimGajiTetap);
                tampilGajiTetap.setText(nilaiTotal.format(GT));

                String GTTInput = String.valueOf(gajiTidakTetap.getText().toString());
                String trimGTT = GTTInput.trim().replaceAll(",", "");
                int GajiTT = Integer.parseInt(trimGTT);
                double TotalGTT = (double) GajiTT * 50 / 100;
                tampilGajiTidakTetap.setText(nilaiTotal.format(TotalGTT));

                //int GT = Integer.parseInt(gajiTetap.getText().toString());
                double TotalGaji = (double) GT + TotalGTT;
                tampilTotalGaji.setText(nilaiTotal.format(TotalGaji));

                //tampilAngsuranBerjalan.setText(hutang.getText().toString());
                String hutangInput = String.valueOf(hutang.getText().toString());
                String trimHutang = hutangInput.trim().replaceAll(",", "");
                int TotalHutang = Integer.parseInt(trimHutang);
                tampilAngsuranBerjalan.setText(nilaiTotal.format(TotalHutang));
                Double CashRatio2 = null;
                if(TotalGaji <= 5000000){
                    double CashRatio = (double) TotalGaji * 40 / 100;
                    tampilCashRatio.setText(nilaiTotal.format(CashRatio));
                    CashRatio2 = CashRatio;
                } else if (TotalGaji <= 10000000){
                    double CashRatio = (double) TotalGaji * 45 / 100;
                    tampilCashRatio.setText(nilaiTotal.format(CashRatio));
                    CashRatio2 = CashRatio;
                } else if (TotalGaji <= 20000000){
                    double CashRatio = (double) TotalGaji * 50 / 100;
                    tampilCashRatio.setText(nilaiTotal.format(CashRatio));
                    CashRatio2 = CashRatio;
                } else if (TotalGaji > 20000000){
                    double CashRatio = (double) TotalGaji * 50 /100;
                    tampilCashRatio.setText(nilaiTotal.format(CashRatio));
                    CashRatio2 = CashRatio;
                }
                if (CashRatio2 <= TotalHutang) {
                    AlertDialog alertDialog = new AlertDialog.Builder(InputDataSimulasiPinjaman.this).create();
                    alertDialog.setTitle("Alert Dialog");
                    alertDialog.setMessage("Bpk/Ibu Calon Nasabah, Kami belum dapat memberikan pinjaman");
                    alertDialog.setButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent alert = new Intent(InputDataSimulasiPinjaman.this, InputDataSimulasiPinjaman.class);
                                    startActivity(alert);
                                }
                            });
                    alertDialog.show();
                }

                double THP = (double) CashRatio2 - TotalHutang;
                tampilGajiDiakui.setText(nilaiTotal.format(THP));

                //int pangkat = Integer.parseInt(inYieldKPR.getText().toString());
                String nilaiPangkat = String.valueOf(inYieldKPR.getText().toString());
                Double pangkat = Double.parseDouble(nilaiPangkat);
                int jw = Integer.parseInt(inTenor.getText().toString());

                double cekhitung = (double) pangkat / 1200;
                double hasilAngsuran = Math.pow(1 + cekhitung, jw);
                double hitungPinjaman = (double) THP * ((1- (1 / (hasilAngsuran))) / cekhitung);
                tampilPinjamanSimulasi.setText(nilaiTotal.format(hitungPinjaman));

                Button back = (Button)findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent BackSimulasi = new Intent(InputDataSimulasiPinjaman.this, InputDataSimulasiPinjaman.class);
                        startActivity(BackSimulasi);
                        //setContentView(R.layout.input_kpr);
                    }
                });
                 }
        });
    }
}
