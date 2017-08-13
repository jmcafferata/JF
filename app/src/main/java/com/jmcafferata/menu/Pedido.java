package com.jmcafferata.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Franco on 13/08/2017.
 */

public class Pedido {
    public int total;
    public int mesa;
    public List<Item> items = new ArrayList<>();
    public String horaLlegada;
    public String horaPedido;
    public String horaPartida;

    public void agregar(Item item){
    items.add(item);
        actualizarPrecio();
    }
    
    public void remover(Item item){
        items.remove(item);
        actualizarPrecio();
    }
    
    public void actualizarPrecio(){
        for (Item i:items) {
            setTotal(getTotal() + i.getPrecio());
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public String getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(String horaPedido) {
        this.horaPedido = horaPedido;
    }

    public String getHoraPartida() {
        return horaPartida;
    }

    public void setHoraPartida(String horaPartida) {
        this.horaPartida = horaPartida;
    }
}
