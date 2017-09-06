package com.jmcafferata.menu;

import android.content.ClipData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JM on 01/09/2017.
 */


public class Menu {

    public ArrayList<Categoria> categorias = new ArrayList<>();

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }
}

