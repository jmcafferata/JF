package com.jmcafferata.menu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by francodarget on 8/13/17.
 */

public class Editar extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar);

        // Crear SharedPreferences
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor editor = prefs.edit();

        // Para hacer chiquita la ventana
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.9),(int)(height*.6)); // Este es el tamaño de la ventana

        // UI Total, Cancelar, Aceptar
        final TextView total = (TextView) findViewById(R.id.total);
        final TextView cancelar = (TextView) findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final TextView aceptar = (TextView) findViewById(R.id.aceptar);


        // Vamos a traer el pedido
        Bundle bun = getIntent().getExtras();
        final Pedido pedido = bun.getParcelable("pedido");
        System.out.println("PEDIDO tiene" + pedido.items.size() + "ITEMS");


        // Vamos a poner el TOTAL de abajo
        int totalPedido = 0;
        for (Articulo it : pedido.items){
            totalPedido = totalPedido + it.getCantidad()*it.getPrecio();
            System.out.println("El precio total por ahora es: " + totalPedido);
        }
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
                itemView=li.inflate(R.layout.editar_item, null ); // editar_item es el xml que se va a inflar
                TextView articulo = (TextView) itemView.findViewById(R.id.articulo);
                final TextView totalNum = (TextView) itemView.findViewById(R.id.totalNum);
                Button less = (Button) itemView.findViewById(R.id.less);
                Button more = (Button) itemView.findViewById(R.id.more);
                final TextView quant = (TextView) itemView.findViewById(R.id.quant);
                TextView remove = (TextView) itemView.findViewById(R.id.remove);
                EditText aclaraciones = (EditText) itemView.findViewById(R.id.aclaraciones);
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
                aclaraciones.addTextChangedListener(new TextWatcher() // Para que con cada letra que se escribe se actualice Aclaraciones
                {
                    @Override
                    public void afterTextChanged(Editable mEdit)
                    {
                        it.comentario = mEdit.toString();
                        editor.putString(it.getNombre()+"aclaracion", it.comentario); // Se va guardando a SP
                    }

                    public void beforeTextChanged(CharSequence s, int start, int count, int after){}

                    public void onTextChanged(CharSequence s, int start, int before, int count){}
                });

                less.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {   // Boton de menos
                        if(it.getCantidad()>1){
                            it.setCantidad(it.getCantidad()-1);
                            quant.setText(Integer.toString(it.getCantidad()));
                            totalNum.setText(String.valueOf(it.getCantidad()*it.getPrecio()));
                            int totalPedidoNew = 0;
                            for (Articulo it : pedido.items){
                                totalPedidoNew = totalPedidoNew + it.getCantidad()*it.getPrecio();
                                System.out.println("El precio total por ahora es: " + totalPedidoNew);
                            }
                            total.setText(""+totalPedidoNew);
                            editor.putInt(it.getNombre()+"cantidad", it.getCantidad()); // Se va actualizando SP
                        }
                    }
                });

                more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {   // Boton de más
                        if(it.getCantidad()<10){
                            it.setCantidad(it.getCantidad()+1);
                            quant.setText(Integer.toString(it.getCantidad()));
                            totalNum.setText(String.valueOf(it.getCantidad()*it.getPrecio()));
                            int totalPedidoNew = 0;
                            for (Articulo it : pedido.items){
                                totalPedidoNew = totalPedidoNew + it.getCantidad()*it.getPrecio();
                                System.out.println("El precio total por ahora es: " + totalPedidoNew);
                            }
                            total.setText(""+totalPedidoNew);
                            editor.putInt(it.getNombre()+"cantidad", it.getCantidad()); // Se va actualizando SP
                        }
                    }
                });

                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pedido.items.remove(it); // Se saca el item de Pedido.Items
                        itemView.setVisibility(View.GONE);
                        it.setCantidad(0);
                        int totalPedidoNew = 0; // Para actualizar el total abajo
                        for (Articulo it : pedido.items){
                            totalPedidoNew = totalPedidoNew + it.getCantidad()*it.getPrecio();
                            System.out.println("El precio total por ahora es: " + totalPedidoNew);
                        }
                        total.setText(""+totalPedidoNew);
                        if (pedido.items.isEmpty()){ // Si no queda ningún artículo, cerrar la ventana
                            Intent inte = new Intent();
                            inte.putExtra("pedido", pedido);
                            setResult(RESULT_OK, inte);
                            finish();
                        }

                        // Eliminar los SP para que no aparezcan en agregar
                        SharedPreferences.Editor editorRemove = prefs.edit();
                        editorRemove.putString(it.getNombre()+"aclaracion", "");
                        editorRemove.putInt(it.getNombre()+"cantidad", 1);
                        editorRemove.commit();
                    }
                });
                parentView.addView(itemView);
            }
        }

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.commit(); // Este es el editor de more, less y aclaraciones
                Intent inte = new Intent();
                inte.putExtra("pedido", pedido);
                setResult(RESULT_OK, inte);
                finish();
            }
        });

    }

}



