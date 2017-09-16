package com.jmcafferata.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by francodarget on 8/13/17.
 */

public class Agregar extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        final ItemMenu item = bun.getParcelable("item");
        nombreItem.setText(item.getNombre());
        if (item.getCantidad()==0){
            item.setCantidad(1);
        }
        quant.setText(Integer.toString(item.getCantidad()));
        totalNum.setText(""+item.getPrecio());
        if (item.getComentario() != null) {
            aclaraciones.setText(item.getComentario());
            aclaraciones.setTextColor(getResources().getColor(R.color.textGray));
        }

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

                Intent resultIntent = new Intent();
                item.setComentario(aclaraciones.getText().toString());
                resultIntent.putExtra("item", item);
                setResult(RESULT_OK, resultIntent);
                finish();




            }
        });
    }

}



