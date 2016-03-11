package com.example.joko.projectkpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Joko Saputra on 1/11/16.
 */
public class InputIllustrasiFund extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_fund);

        TextView simulasi = (TextView)findViewById(R.id.ilustrasiFund);
        Intent in = this.getIntent();
        Button back = (Button)findViewById(R.id.exitIlustrasi);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ExitIlustrasi = new Intent(InputIllustrasiFund.this, MainActivityKPR.class);
                startActivity(ExitIlustrasi);
            }
        });

        final EditText inHPM = (EditText)findViewById(R.id.inHPM);
        final EditText inNominal = (EditText)findViewById(R.id.inNominal);
        final EditText inNisbah = (EditText)findViewById(R.id.inNisbah);
        final EditText inJH = (EditText)findViewById(R.id.inJH);
        final EditText inJumlahPerhitungan = (EditText)findViewById(R.id.inJumlahPerhitungan);

        inNominal.addTextChangedListener(new TextWatcher() {
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
                    inNominal.removeTextChangedListener(this);
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
                    inNominal.setText(formatted);
                    inNominal.setSelection(formatted.length());
                    inNominal.addTextChangedListener(this);
                }
            }
        });

        Button btn = (Button)findViewById(R.id.prosesIlustrasi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                setContentView(R.layout.tampililustrasibaghas);
                DecimalFormat nilaiTotal = new DecimalFormat("#,###,##0.00");
                TextView tampilJumlahHPM        = (TextView) findViewById(R.id.jumlahHPM);
                TextView tampilNominal          = (TextView) findViewById(R.id.nominal);
                TextView tampilBagHas           = (TextView) findViewById(R.id.baghas);
                TextView tampilJumlahHari       = (TextView) findViewById(R.id.jumlahHari);
                TextView tampilJumlahHitungHari = (TextView) findViewById(R.id.jumlahHitungHari);
                TextView tampilBagHasBruto      = (TextView) findViewById(R.id.BagHasBruto);
                TextView tampilPajakBagHas      = (TextView) findViewById(R.id.PajakBagHas);
                TextView tampilBagHasNet        = (TextView) findViewById(R.id.BagHasNet);
                TextView tampilER               = (TextView) findViewById(R.id.EquivalentRate);

                tampilJumlahHPM.setText(inHPM.getText().toString());
                tampilNominal.setText(inNominal.getText().toString());
                tampilBagHas.setText(inNisbah.getText().toString());
                tampilJumlahHari.setText(inJH.getText().toString());
                tampilJumlahHitungHari.setText(inJumlahPerhitungan.getText().toString());

                String inHpm = String.valueOf(inHPM.getText().toString());
                Double HPM = Double.parseDouble(inHpm);
                String inNOminal = String.valueOf(inNominal.getText().toString());
                String trimNominal = inNOminal.trim().replaceAll(",", "");
                Double Nominal = Double.parseDouble(trimNominal);
                String inNIsbah = String.valueOf(inNisbah.getText().toString());
                Double Nisbah = Double.parseDouble(inNIsbah);
                String inJh = String.valueOf(inJH.getText().toString());
                Double JH = Double.parseDouble(inJh);
                String inJP = String.valueOf(inJumlahPerhitungan.getText().toString());
                Double JumlahPerhitungan = Double.parseDouble(inJP);

                double BagHasBruto = (double) Nominal / 1000 * HPM * Nisbah / 100 * JH / JumlahPerhitungan;

                tampilBagHasBruto.setText(nilaiTotal.format(BagHasBruto));

                double PajakBagHas = (double) BagHasBruto * 20 / 100;

                tampilPajakBagHas.setText(nilaiTotal.format(PajakBagHas));

                double BagHasNet = (double) BagHasBruto - PajakBagHas;

                tampilBagHasNet.setText(nilaiTotal.format(BagHasNet));

                double ER = (double) Nisbah / 100;
                double ER1 = (double) HPM * ER;
                double ER2 = (double) ER1 / 1000;
                double ER3 = (double) ER2 / JumlahPerhitungan;
                double ERNet = (double) ER3 * 365 * 10000 / 100;


                tampilER.setText(String.format("%.2f", ERNet));

                Button back = (Button)findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent BackSimulasi = new Intent(InputIllustrasiFund.this, InputIllustrasiFund.class);
                        startActivity(BackSimulasi);
                        //setContentView(R.layout.input_kpr);
                    }
                });
        }
        });
    }
}
