package model;

import java.io.Serializable;
import java.util.Date;

public class Persona implements Serializable{
    
    private String cedula;
    private String nombre;     
    private String Apellido1;
    private String Apellido2;
    private Date fechaNacimiento;
    private String correo;     
    private String celular;
    

    public Persona() {
    }

    public Persona(String cedula, String nombre, String Apellido1, String Apellido2, Date fechaNacimiento, String correo, String celular) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.Apellido1 = Apellido1;
        this.Apellido2 = Apellido2;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.celular = celular;
        
    }
    



    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return Apellido1;
    }

    public void setApellido1(String Apellido1) {
        this.Apellido1 = Apellido1;
    }

    public String getApellido2() {
        return Apellido2;
    }

    public void setApellido2(String Apellido2) {
        this.Apellido2 = Apellido2;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "Persona{" + "cedula=" + cedula + ", nombre=" + nombre + ", Apellido1=" + Apellido1 + ", Apellido2=" + Apellido2 + ", fechaNacimiento=" + fechaNacimiento + ", correo=" + correo + ", celular=" + celular + '}';
    }

    

    
    
    
    
    
}
