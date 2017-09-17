package com.jmcafferata.menu;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JM on 31/08/2017.
 */

public class Articulo extends Menu implements Parcelable{

    public String nombre;
    public int precio;
    public String comentario;
    public boolean estaListo;
    public int cantidad;
    public String descripcion;



    public Articulo(Parcel parcel){
        this.nombre = parcel.readString();
        this.precio = parcel.readInt();
        this.comentario = parcel.readString();
        this.estaListo = parcel.readByte() !=0;
        this.cantidad = parcel.readInt();
        this.descripcion = parcel.readString();
    }

    public Articulo(){
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

    public static final Creator<Articulo> CREATOR = new Creator<Articulo>() {
        @Override
        public Articulo createFromParcel(Parcel in) {
            return new Articulo(in);
        }

        @Override
        public Articulo[] newArray(int size) {
            return new Articulo[size];
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Articulo articulo = (Articulo) o;

        return nombre != null ? nombre.equals(articulo.nombre) : articulo.nombre == null;

    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }

    @Override
    public String toString() {
        return  "Artículo: " + nombre +
                "\nPrecio: " + precio +
                "\nComentario: " + comentario +
                "\nCantidad: " + cantidad;
    }
}
