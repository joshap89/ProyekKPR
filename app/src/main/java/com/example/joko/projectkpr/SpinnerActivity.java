package com.example.joko.projectkpr;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.lang.reflect.Array;

/**
 * Created by joko on 9/19/15.
 */
public class SpinnerActivity extends Activity  {

    private String[] isi_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_pensiun);

        //dapatkan data arraystring dari resource
        isi_spinner = this.getResources().getStringArray(R.array.isi_spinner);

        //buat object spinner
        Spinner spinner = (Spinner) findViewById(R.id.ProdukPensiun);

        //buat arrayadapter dengan isi_spinner di dalamnya, dan style simple_spinner_dropdown_item
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.isi_spinner,android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, isi_spinner);

        //set spinner adapter
        spinner.setAdapter(adapter);

        //berikan action pada saat spinner terpilih
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                //tampilkan isi spinner dari array string yg terpilih berdasarkan position
                Toast.makeText(SpinnerActivity.this, isi_spinner[position],
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }



}
