package model;

import java.io.Serializable;

public class Usuario implements Serializable {
    public Usuario(String nombre, String apellido, String correo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }
    public Usuario() {
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    private String nombre, apellido, correo;

    //Para hacer el que el objeto sea leíble, es necesario que tenga su UID
    private final static long serialVersionUID = 123123123L;
}
