package model;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "usuarioRespaldo")
@SessionScoped
public class usuarioRespaldo implements Serializable {

    private String idRespaldo;
    private String pwRespaldo;
    private String nombreRespaldo;

    public String getIdRespaldo() {
        return idRespaldo;
    }

    public void setIdRespaldo(String idRespaldo) {
        this.idRespaldo = idRespaldo;
    }

    public String getPwRespaldo() {
        return pwRespaldo;
    }

    public void setPwRespaldo(String pwRespaldo) {
        this.pwRespaldo = pwRespaldo;
    }

    public String getNombreRespaldo() {
        return nombreRespaldo;
    }

    public void setNombreRespaldo(String nombreRespaldo) {
        this.nombreRespaldo = nombreRespaldo;
    }

    public usuarioRespaldo() {
    }

}
