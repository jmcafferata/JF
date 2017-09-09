package com.jmcafferata.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        final TextView cancelar = (TextView) findViewById(R.id.cancelar);
        final TextView confirmar = (TextView) findViewById(R.id.confirmar);

        // Importar el pedido

        final Pedido pedido = getIntent().getParcelableExtra("pedido");

        // Toma nombre, cantidad y precio del producto

        final int total;
        final ItemMenu item = getIntent().getParcelableExtra("item");
        nombreItem.setText(item.getNombre());
        for(ItemMenu ip : pedido.items){
            if(ip.getNombre().equals(item.getNombre())){
                item.setCantidad(ip.getCantidad());
            }
        }
        item.setCantidad(1);
        total = item.getPrecio() * item.getCantidad();
        totalNum.setText(""+item.getPrecio());
        quant.setText(Integer.toString(item.getCantidad()));

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

                pedido.items.add(item);
                System.out.println("SE HA AGREGADO UN ITEM");
                System.out.println("Item: " + item.getNombre() + "/n Cantidad: " + item.getCantidad());
                for (ItemMenu ip : pedido.items){
                    System.out.println("Item del Pedido: " + ip.getNombre() + "/n Cantidad: " + ip.getCantidad());
                }
                Intent in = new Intent(Agregar.this, MainActivity.class);
                in.putExtra("pedido",pedido);
                Agregar.this.startActivity(in);
                finish();



            }
        });
    }

}



