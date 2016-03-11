package com.example.joko.projectkpr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by joko on 12/14/15.
 */
public class syaratKPR extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.persyaratan);

        Intent in = this.getIntent();

        Button keluar = (Button) findViewById(R.id.keluar);
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exit = new Intent(syaratKPR.this, MainActivityKPR.class);
                startActivity(exit);
            }
        });

    }
}
