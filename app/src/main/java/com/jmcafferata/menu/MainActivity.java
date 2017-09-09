package com.jmcafferata.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    // FONTS

    Typeface cinzel_bold;
    Typeface cinzel_regular;
    Typeface quattrocento;

    // UI

    TextView cat1;
    TextView title1;
    TextView descr1;
    TextView sign;
    TextView precio1;
    LinearLayout menuItem1;
    Button editar;
    Button listo;

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

        // MENU

        // ITEMS

        ItemMenu item1 = new ItemMenu();
        item1.setNombre(getString(R.string._1Nombre));
        item1.setDescripcion(getString(R.string._1Descripcion));
        item1.setPrecio(this.getResources().getInteger(R.integer._1Precio));
        ItemMenu item2 = new ItemMenu();
        item2.setNombre(getString(R.string._2Nombre));
        item2.setDescripcion(getString(R.string._2Descripcion));
        item2.setPrecio(this.getResources().getInteger(R.integer._2Precio));
        ItemMenu item3 = new ItemMenu();
        item3.setNombre(getString(R.string._3Nombre));
        item3.setDescripcion(getString(R.string._3Descripcion));
        item3.setPrecio(this.getResources().getInteger(R.integer._3Precio));
        ItemMenu item4 = new ItemMenu();
        item4.setNombre(getString(R.string._4Nombre));
        item4.setDescripcion(getString(R.string._4Descripcion));
        item4.setPrecio(this.getResources().getInteger(R.integer._4Precio));
        ItemMenu item5 = new ItemMenu();
        item5.setNombre(getString(R.string._5Nombre));
        item5.setDescripcion(getString(R.string._5Descripcion));
        item5.setPrecio(this.getResources().getInteger(R.integer._5Precio));
        ItemMenu item6 = new ItemMenu();
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


        LinearLayout parentView;
        parentView = (LinearLayout) findViewById(R.id.parentView);

        for(Categoria c : menu.categorias){
            View categoriaView;
            LayoutInflater li=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            categoriaView = li.inflate(R.layout.categoria_layout,null);
            TextView categoriaText = (TextView) categoriaView.findViewById(R.id.cat);
            categoriaText.setText(c.getNombre());
            categoriaText.setTypeface(cinzel_bold);
            parentView.addView(categoriaView);
            int color = 2;
            for(final ItemMenu i : c.items){

                View menu_item;
                menu_item=li.inflate(R.layout.menu_item_layout, null );
                TextView titulo = (TextView) menu_item.findViewById(R.id.title1);
                titulo.setText(i.getNombre());
                titulo.setTypeface(cinzel_regular);
                TextView desc = (TextView) menu_item.findViewById(R.id.descr1);
                desc.setText(i.getDescripcion());
                desc.setTypeface(quattrocento);
                TextView precio = (TextView) menu_item.findViewById(R.id.precio1);
                precio.setText(""+i.getPrecio());
                if (color % 2 == 0){
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
                        myIntent.putExtra("item", i); //Optional parameters
                        myIntent.putExtra("pedido", pedido);
                        MainActivity.this.startActivity(myIntent);
                        System.out.println("PASANDO: "+
                        i.getNombre()
                        +i.getCantidad()
                        + i.getPrecio());
                    }
                });
                parentView.addView(menu_item);
            }

        }








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

        // Menu






    }


}
