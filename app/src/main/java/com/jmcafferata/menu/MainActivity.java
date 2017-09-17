package com.jmcafferata.menu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    // FONTS

    Typeface cinzel_bold;
    Typeface cinzel_regular;
    Typeface quattrocento;

    // UI

    LinearLayout menuItem1;
    Button editar;
    Button listo;
    TextView totalText;

    // RECIBIR PEDIDO DE AGREGAR

    final Pedido pedido = new Pedido();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // UI

        cinzel_bold = Typeface.createFromAsset(getAssets(), "fonts/Cinzel-Bold.otf");
        cinzel_regular = Typeface.createFromAsset(getAssets(), "fonts/Cinzel-Regular.otf");
        quattrocento = Typeface.createFromAsset(getAssets(), "fonts/Quattrocento-Regular.otf");

        menuItem1 = (LinearLayout) findViewById(R.id.menuItem1);

        editar = (Button) findViewById(R.id.editar);
        listo = (Button) findViewById(R.id.listo);
        totalText = (TextView) findViewById(R.id.totalText);

        // MENU

        // ITEMS

        Articulo item1 = new Articulo();
        item1.setNombre(getString(R.string._1Nombre));
        item1.setDescripcion(getString(R.string._1Descripcion));
        item1.setPrecio(this.getResources().getInteger(R.integer._1Precio));
        Articulo item2 = new Articulo();
        item2.setNombre(getString(R.string._2Nombre));
        item2.setDescripcion(getString(R.string._2Descripcion));
        item2.setPrecio(this.getResources().getInteger(R.integer._2Precio));
        Articulo item3 = new Articulo();
        item3.setNombre(getString(R.string._3Nombre));
        item3.setDescripcion(getString(R.string._3Descripcion));
        item3.setPrecio(this.getResources().getInteger(R.integer._3Precio));
        Articulo item4 = new Articulo();
        item4.setNombre(getString(R.string._4Nombre));
        item4.setDescripcion(getString(R.string._4Descripcion));
        item4.setPrecio(this.getResources().getInteger(R.integer._4Precio));
        Articulo item5 = new Articulo();
        item5.setNombre(getString(R.string._5Nombre));
        item5.setDescripcion(getString(R.string._5Descripcion));
        item5.setPrecio(this.getResources().getInteger(R.integer._5Precio));
        Articulo item6 = new Articulo();
        item6.setNombre(getString(R.string._6Nombre));
        item6.setDescripcion(getString(R.string._6Descripcion));
        item6.setPrecio(this.getResources().getInteger(R.integer._6Precio));


        // CATEGORIAS

        Categoria cat1 = new Categoria();
        cat1.setNombre(getString(R.string.Categoria1));
        cat1.items.add(item1);
        cat1.items.add(item2);
        Categoria cat2 = new Categoria();
        cat2.setNombre(getString(R.string.Categoria2));
        cat2.items.add(item3);
        Categoria cat3 = new Categoria();
        cat3.setNombre(getString(R.string.Categoria3));
        cat3.items.add(item4);
        Categoria cat4 = new Categoria();
        cat4.setNombre(getString(R.string.Categoria4));
        cat4.items.add(item5);
        Categoria cat5 = new Categoria();
        cat5.setNombre(getString(R.string.Categoria5));
        cat5.items.add(item6);

        Menu menu = new Menu();
        menu.categorias.add(cat1);
        menu.categorias.add(cat2);
        menu.categorias.add(cat3);
        menu.categorias.add(cat4);
        menu.categorias.add(cat5);

        // PEDIDO

        final Pedido pedido = new Pedido();
        pedido.items = new ArrayList<>();


        LinearLayout parentView;
        parentView = (LinearLayout) findViewById(R.id.parentView);

        // Ingresar mesa
        Intent myIntent = new Intent(MainActivity.this, Mesa.class);
        startActivity(myIntent);

        for (Categoria c : menu.categorias) {
            View categoriaView;
            LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            categoriaView = li.inflate(R.layout.categoria_layout, null);
            TextView categoriaText = (TextView) categoriaView.findViewById(R.id.cat);
            categoriaText.setText(c.getNombre());
            categoriaText.setTypeface(cinzel_bold);
            parentView.addView(categoriaView);
            int color = 2;
            for (final Articulo i : c.items) {

                View menu_item;
                menu_item = li.inflate(R.layout.menu_item_layout, null);
                TextView titulo = (TextView) menu_item.findViewById(R.id.title1);
                titulo.setText(i.getNombre());
                titulo.setTypeface(cinzel_regular);
                TextView desc = (TextView) menu_item.findViewById(R.id.descr1);
                desc.setText(i.getDescripcion());
                desc.setTypeface(quattrocento);
                TextView precio = (TextView) menu_item.findViewById(R.id.precio1);
                precio.setText("" + i.getPrecio());
                if (color % 2 == 0) {
                    menu_item.setBackgroundColor(getResources().getColor(R.color.background1));
                } else {
                    menu_item.setBackgroundColor(getResources().getColor(R.color.background2));
                }
                color++;
                menu_item.setClickable(true);
                // Buttons

                menu_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(MainActivity.this, Agregar.class);
                        Bundle bun = new Bundle();
                        bun.putParcelable("item", i);
                        myIntent.putExtras(bun);
                        startActivityForResult(myIntent, 1);

                    }
                });
                parentView.addView(menu_item);
            }

        }


        // Menu


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Articulo itemNuevo = data.getParcelableExtra("item");
                    if (pedido.items.contains(itemNuevo)) {
                        pedido.items.remove(itemNuevo);

                    }
                    pedido.items.add(itemNuevo);
                    // Crear SharedPreferences
                    final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    final SharedPreferences.Editor editor = prefs.edit();
                    pedido.mesa = prefs.getInt("mesa",0);
                    TextView total = (TextView) findViewById(R.id.total);
                    pedido.total = 0;
                    for (Articulo im : pedido.items) {
                        pedido.total = pedido.total + (im.precio * im.cantidad);
                    }
                    total.setText("" + pedido.getTotal());
                    editar.setTextColor(getResources().getColor(R.color.white));
                    totalText.setTextColor(getResources().getColor(R.color.white));
                    total.setTextColor(getResources().getColor(R.color.white));
                    listo.setTextColor(getResources().getColor(R.color.white));
                    editar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(MainActivity.this, Editar.class);
                            Bundle bun = new Bundle();
                            bun.putParcelable("pedido", pedido);
                            myIntent.putExtras(bun);
                            startActivityForResult(myIntent, 2);
                        }
                    });

                    listo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent myIntent = new Intent(MainActivity.this, Confirmar.class);
                            Bundle bun = new Bundle();
                            bun.putParcelable("pedido", pedido);
                            myIntent.putExtras(bun);
                            startActivityForResult(myIntent, 3);
                        }
                    });
                } break;
            case 2:
                    if (resultCode == RESULT_OK) {
                        Pedido pedidoTraido = data.getParcelableExtra("pedido");
                        pedido.items = pedidoTraido.items;
                        pedido.total = 0;
                        for (Articulo im : pedido.items) {
                            pedido.total = pedido.total + (im.precio * im.cantidad);
                        }
                        TextView total = (TextView) findViewById(R.id.total);
                        total.setText("" + pedido.getTotal());
                        if (pedido.total == 0) {
                            editar.setTextColor(getResources().getColor(R.color.textGray));
                            editar.setClickable(false);
                        }
                    } break;
            case 3 :
                    if (resultCode == RESULT_OK) {
                        Pedido pedidoTraido = data.getParcelableExtra("pedido");
                        pedido.items = pedidoTraido.items;
                        Intent newIntent = new Intent(MainActivity.this, Listo.class);
                        Bundle bun = new Bundle();
                        Calendar now = Calendar.getInstance();
                        pedido.setHoraPedido(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
                        System.out.println(pedido.toString());
                        bun.putParcelable("pedidoFinal", pedido);
                        newIntent.putExtras(bun);
                        startActivity(newIntent);

                    }
                }


        }


    }
