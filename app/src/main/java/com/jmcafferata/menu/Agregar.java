package com.jmcafferata.menu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by francodarget on 8/13/17.
 */

public class Agregar extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.agregar);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        // UI

        final TextView nombreItem = (TextView)findViewById(R.id.nombreItem);
        final TextView totalNum = (TextView)findViewById(R.id.totalNum);
        final Button less = (Button)findViewById(R.id.less);
        final Button more = (Button)findViewById(R.id.more);
        final TextView quant = (TextView) findViewById(R.id.quant);
        final TextView aclaraciones = (TextView) findViewById(R.id.aclaraciones);
        aclaraciones.setTextColor(getResources().getColor(R.color.textGray));
        final TextView cancelar = (TextView) findViewById(R.id.cancelar);
        final TextView confirmar = (TextView) findViewById(R.id.confirmar);

        // Importar el pedido

        Bundle bun = getIntent().getExtras();

        // Toma nombre, aclaraciones, cantidad y precio del producto

        final int total;
        final Articulo item = bun.getParcelable("item");
        nombreItem.setText(item.getNombre());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        item.setCantidad(prefs.getInt(item.getNombre()+"cantidad", 1));
        System.out.println("LO GUARDADO: "+ prefs.getInt(item.getNombre()+"cantidad", 1));
        System.out.println("LO GUARDADO: "+prefs.getString(item.getNombre()+"aclaracion",""));

        quant.setText(Integer.toString(item.getCantidad()));
        totalNum.setText(""+item.getPrecio()*item.getCantidad());
        item.setComentario(prefs.getString(item.getNombre()+"aclaracion",""));
        aclaraciones.setText(item.getComentario());
        aclaraciones.setTextColor(getResources().getColor(R.color.textGray));
        aclaraciones.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void afterTextChanged(Editable mEdit)
            {
                item.comentario = mEdit.toString();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });


        // Botones de cantidad

        less.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getCantidad()>1){
                    item.setCantidad(item.getCantidad()-1);
                    quant.setText(Integer.toString(item.getCantidad()));
                    totalNum.setText(String.valueOf(item.getCantidad()*item.getPrecio()));
                }
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item.getCantidad()<10){
                    item.setCantidad(item.getCantidad()+1);
                    quant.setText(Integer.toString(item.getCantidad()));
                    totalNum.setText(String.valueOf(item.getCantidad()*item.getPrecio()));
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Guardar cantidad y aclaraciones
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(item.getNombre()+"aclaracion", item.getComentario());
                editor.putInt(item.getNombre()+"cantidad", item.getCantidad());
                editor.commit();

                Intent resultIntent = new Intent();
                item.setComentario(aclaraciones.getText().toString());
                resultIntent.putExtra("item", item);
                setResult(RESULT_OK, resultIntent);
                finish();




            }
        });
    }

}



