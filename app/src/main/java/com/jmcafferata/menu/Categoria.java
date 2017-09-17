package com.jmcafferata.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JM on 05/09/2017.
 */

public class Categoria {
    public String nombre;
    public ArrayList<Articulo> items = new ArrayList<>();



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Articulo> getItems() {
        return items;
    }

    public void setItems(ArrayList<Articulo> items) {
        this.items = items;
    }
}
