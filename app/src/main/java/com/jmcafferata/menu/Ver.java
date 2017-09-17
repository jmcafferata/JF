package com.jmcafferata.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Ver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver);

        // Crear SharedPreferences
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = prefs.edit();

        // Para hacer chiquita la ventana
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.9),(int)(height*.7)); // Este es el tamaño de la ventana

        // UI Total, Cancelar, Aceptar
        final TextView total = (TextView) findViewById(R.id.total);
        final TextView cancelar = (TextView) findViewById(R.id.cancelar);
        final TextView aceptar = (TextView) findViewById(R.id.aceptar);


        // Vamos a traer el pedido
        Bundle bun = getIntent().getExtras();
        final Pedido pedido = bun.getParcelable("pedido");
        System.out.println(pedido.toString());


        // Vamos a poner el TOTAL de abajo
        int totalPedido = 0;
        for (Articulo it : pedido.items){
            totalPedido = totalPedido + it.getCantidad()*it.getPrecio();
        }
        pedido.total = totalPedido;
        total.setText(""+totalPedido);

        // Vamos a inflar cada item en editar
        int color = 2; // Para alternar los colores de fondo
        if (pedido.items != null) {
            for (final Articulo it : pedido.items) {

                // UI
                LinearLayout parentView; // El parentView es el ScrollView en editar.xml
                parentView = (LinearLayout) findViewById(R.id.parentView);
                LayoutInflater li=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View itemView; // Cada item
                itemView=li.inflate(R.layout.ver_item, null ); // editar_item es el xml que se va a inflar
                TextView articulo = (TextView) itemView.findViewById(R.id.articulo);
                final TextView totalNum = (TextView) itemView.findViewById(R.id.totalNum);
                final TextView quant = (TextView) itemView.findViewById(R.id.quant);
                TextView aclaraciones = (TextView) itemView.findViewById(R.id.aclaraciones);
                if (color % 2 == 0){
                    itemView.setBackgroundColor(getResources().getColor(R.color.background1));
                } else {
                    itemView.setBackgroundColor(getResources().getColor(R.color.background2));
                }
                color++;

                // Vamos a programar el item_view
                articulo.setText(it.getNombre());
                totalNum.setText(String.valueOf(it.getCantidad()*it.getPrecio()));
                quant.setText(""+it.getCantidad());
                if (it.getComentario() != null) {
                    aclaraciones.setText(it.getComentario());
                    aclaraciones.setTextColor(getResources().getColor(R.color.textGray));
                }
                parentView.addView(itemView);
            }
        }

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
