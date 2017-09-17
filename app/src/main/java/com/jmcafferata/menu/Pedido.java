package com.jmcafferata.menu;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Franco on 13/08/2017.
 */

public class Pedido implements Parcelable{
    public int total;
    public int mesa;
    public ArrayList<Articulo> items;
    public String horaLlegada;
    public String horaPedido;
    public String horaPartida;

    public Pedido(Parcel parcel){

        this.total = parcel.readInt();
        this.mesa = parcel.readInt();
        items = new ArrayList<>();
        parcel.readTypedList(items, Articulo.CREATOR);
    }


    public static final Creator<Pedido> CREATOR = new Creator<Pedido>() {
        @Override
        public Pedido createFromParcel(Parcel in) {
            return new Pedido(in);
        }

        @Override
        public Pedido[] newArray(int size) {
            return new Pedido[size];
        }
    };

    public Pedido(){
        super();
        items = new ArrayList<>();
    }

    public void agregar(Articulo item){
        item.setCantidad(item.getCantidad()+1);
        actualizarPrecio();
    }

    public void restar(Articulo item){
        if(item.getCantidad()>1){
            item.setCantidad(item.getCantidad()-1);} else {
            item.setCantidad(1);
        }
        actualizarPrecio();
    }

    public void remover(Articulo item){
        items.remove(item);
        actualizarPrecio();
    }

    public void actualizarPrecio(){
        setTotal(0);
        for (Articulo i:items) {

            setTotal(getTotal()+(i.getPrecio()*i.getCantidad()));
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(total);
        dest.writeInt(mesa);
        dest.writeTypedList(items);
        dest.writeString(horaLlegada);
        dest.writeString(horaPedido);
        dest.writeString(horaPartida);
    }

    @Override
    public String toString() {
        String itemsString = "";
        for (Articulo item : items) {
            itemsString = itemsString + item.toString() + "\n";
        }
        return "********Pedido: ********\n" +
                "Total: " + total +
                "\nMesa: " + mesa +
                "\n Items:\n" + itemsString +
                "Hora del pedido: " + horaPedido;

    }
}
