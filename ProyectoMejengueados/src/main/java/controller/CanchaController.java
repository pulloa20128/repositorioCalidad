/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Cancha;
import model.CanchaGestion;

/**
 *
 * @author Libby
 */
@Named(value = "canchaController")
@SessionScoped
public class CanchaController extends Cancha implements Serializable {

    /**
     * Creates a new instance of CanchaController
     */
    public CanchaController() {
    }
    
    public String inserta() {
        if (CanchaGestion.insertar(this)) {
            return "list.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error",
                    "Posible id duplicado");
            FacesContext.getCurrentInstance().addMessage(
                    "editaCanchaForm:identificacion",
                    mensaje);
            return "edita.xhtml";
        }
    }

    public String modifica() {
        if (CanchaGestion.modificar(this)) {
            return "list.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el id no existe");
            FacesContext.getCurrentInstance().addMessage(
                    "editaCanchaForm:identificacion",
                    mensaje);
            return "edita.xhtml";
        }
    }

    public String elimina() {
        if (CanchaGestion.eliminar(this)) {
            return "list.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error",
                    "Posiblemente el id no existe");
            FacesContext.getCurrentInstance().addMessage(
                    "editaCanchaForm:identificacion",
                    mensaje);
            return "edita.xhtml";
        }
    }

    public String edita(String id) {
        Cancha cancha
                = CanchaGestion.getCancha(id);
        if (cancha != null) {
            this.setId(cancha.getId());
            this.setNombre(cancha.getNombre());
            this.setLugar(cancha.getLugar());
            this.setTelefono(cancha.getTelefono());
            
            return "edita.xhtml";
        } else {
            return "list.xhtml";
        }
    }

    
    public List<Cancha> getCanchas(){
        return CanchaGestion.getCanchas();
    }
    
    
    
}
