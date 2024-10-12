package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Producto implements Serializable {  //Necesita implementar interfaz serializable para poder guardarse/escribirse/leerse

    private static final Long serialVersionUID = 12345L;
    
    // variables - private
    private int id;
    private String title;
    private double price;
    private int stock;

    public void mostrarDatos() {
        System.out.println("serialVersionUID = " + serialVersionUID);
        System.out.println("id = " + id);
        System.out.println("title = " + title);
        System.out.println("price = " + price);
        System.out.println("stock = " + stock);
    }

    //Añadiendo libreria lombok esto no es necesario
    // constructores
    /*
    public Producto(int id, String title, double price, int stock){
        this.id = id;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    //métodos obligatorios
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }*/
}


