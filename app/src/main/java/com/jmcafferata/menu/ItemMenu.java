package com.jmcafferata.menu;

import android.content.ClipData;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JM on 31/08/2017.
 */

public class ItemMenu extends Menu implements Parcelable{

    public String nombre;
    public int precio;
    public String comentario;
    public boolean estaListo;
    public int cantidad;
    public String descripcion;

    public ItemMenu (Parcel parcel){
        this.nombre = parcel.readString();
        this.precio = parcel.readInt();
        this.comentario = parcel.readString();
        this.cantidad = parcel.readInt();
        this.descripcion = parcel.readString();
    }

    public ItemMenu(){
        super();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeInt(precio);
        dest.writeString(comentario);
        dest.writeByte((byte) (estaListo ? 1 : 0));
        dest.writeInt(cantidad);
        dest.writeString(descripcion);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemMenu> CREATOR = new Creator<ItemMenu>() {
        @Override
        public ItemMenu createFromParcel(Parcel in) {
            return new ItemMenu(in);
        }

        @Override
        public ItemMenu[] newArray(int size) {
            return new ItemMenu[size];
        }
    };

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isEstaListo() {
        return estaListo;
    }

    public void setEstaListo(boolean estaListo) {
        this.estaListo = estaListo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
