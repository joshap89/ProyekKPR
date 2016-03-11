package com.example.joko.projectkpr;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivityKPR extends Activity {
    Spinner sp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main_activity_kpr);

        Button go = (Button) findViewById(R.id.kal);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iKal = new Intent(MainActivityKPR.this, InputDataKPR.class);
                startActivity(iKal);
            }
        });

        Button pen = (Button) findViewById(R.id.pensiun);
        pen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivityKPR.this, InputDataPensiun.class);
                startActivity(i);
            }
        });

        Button simulasi = (Button) findViewById(R.id.PinjamanKPR);
        simulasi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent in = new Intent(MainActivityKPR.this, InputDataSimulasiPinjaman.class);
                startActivity(in);
            }
        });
        Button syarat = (Button) findViewById(R.id.syarat);
        syarat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent in = new Intent(MainActivityKPR.this, syaratKPR.class);
                startActivity(in);
            }
        });
        Button baghas = (Button) findViewById(R.id.ilustrasiBaghas);
        baghas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent in = new Intent(MainActivityKPR.this, InputIllustrasiFund.class);
                startActivity(in);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_kpr, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
