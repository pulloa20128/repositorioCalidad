package model;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "canchas")
@SessionScoped
public class Cancha implements Serializable {
    
    private String id;
    private String nombre;
    private String lugar;
    private String telefono;
    

    public Cancha() {
    }

    public Cancha(String id, String nombre, String lugar, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.lugar = lugar;
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
    
}
