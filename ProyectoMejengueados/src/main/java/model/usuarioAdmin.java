package model;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named(value = "usuarioAdmin")
@SessionScoped
public class usuarioAdmin implements Serializable {

    private String idAdmin;
    private String pwAdmin;
    private String nombreAdmin;

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getPwAdmin() {
        return pwAdmin;
    }

    public void setPwAdmin(String pwAdmin) {
        this.pwAdmin = pwAdmin;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public usuarioAdmin() {
    }

}
