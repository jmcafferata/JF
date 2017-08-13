package com.jmcafferata.menu;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    public TextView title1;
    public TextView title2;
    public TextView title3;
    public TextView title4;
    public TextView title5;
    public TextView title6;
    public TextView cat1;
    public TextView cat2;
    public TextView cat3;
    public TextView cat4;
    public TextView cat5;
    public TextView descr1;
    public TextView descr2;
    public TextView descr3;
    public TextView descr4;
    public TextView descr5;
    public TextView descr6;

    Typeface cinzel_bold;
    Typeface cinzel_regular;
    Typeface quattrocento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button editar = (Button) findViewById(R.id.editar);
        Button listo = (Button) findViewById(R.id.listo);


        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Editar.class));
            }
        });

        listo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Confirmar.class));
            }
        });

        cinzel_bold = Typeface.createFromAsset(getAssets(), "fonts/Cinzel-Bold.otf");
        cinzel_regular = Typeface.createFromAsset(getAssets(), "fonts/Cinzel-Regular.otf");
        quattrocento = Typeface.createFromAsset(getAssets(), "fonts/Quattrocento-Regular.otf");

        title1 = (TextView) findViewById(R.id.title1);
        title2 = (TextView) findViewById(R.id.title2);
        title3 = (TextView) findViewById(R.id.title3);
        title4 = (TextView) findViewById(R.id.title4);
        title5 = (TextView) findViewById(R.id.title5);
        title6 = (TextView) findViewById(R.id.title6);
        cat1 = (TextView) findViewById(R.id.cat1);
        cat2 = (TextView) findViewById(R.id.cat2);
        cat3 = (TextView) findViewById(R.id.cat3);
        cat4 = (TextView) findViewById(R.id.cat4);
        cat5 = (TextView) findViewById(R.id.cat5);
        descr1 = (TextView) findViewById(R.id.descr1);
        descr2 = (TextView) findViewById(R.id.descr2);
        descr3 = (TextView) findViewById(R.id.descr3);
        descr4 = (TextView) findViewById(R.id.descr4);
        descr5 = (TextView) findViewById(R.id.descr5);
        descr6 = (TextView) findViewById(R.id.descr6);


        title1.setTypeface(cinzel_regular);
        title2.setTypeface(cinzel_regular);
        title3.setTypeface(cinzel_regular);
        title4.setTypeface(cinzel_regular);
        title5.setTypeface(cinzel_regular);
        title6.setTypeface(cinzel_regular);
        cat1.setTypeface(cinzel_bold);
        cat2.setTypeface(cinzel_bold);
        cat3.setTypeface(cinzel_bold);
        cat4.setTypeface(cinzel_bold);
        cat5.setTypeface(cinzel_bold);
        descr1.setTypeface(quattrocento);
        descr2.setTypeface(quattrocento);
        descr3.setTypeface(quattrocento);
        descr4.setTypeface(quattrocento);
        descr5.setTypeface(quattrocento);
        descr6.setTypeface(quattrocento);


    }


}
