package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import model.Conexion;
import model.usuarioAdmin;

@Named(value = "usuarioAdminController")
@SessionScoped
public class usuarioAdminController implements Serializable {
    
    @Inject
    private usuarioAdmin usuarioAdmin;
    

    public usuarioAdminController() {
    }
    
    public String validaAdmin() {
        try {
            String consulta = "select*from usuarioAdmin where idAdmin=?";
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(consulta);
            sentencia.setString(1, usuarioAdmin.getIdAdmin());
            ResultSet rs = sentencia.executeQuery();
            if (rs != null && rs.next() && rs.getString(2).equals(usuarioAdmin.getPwAdmin())) {
                usuarioAdmin.setNombreAdmin(rs.getString(3));
                return "principalAdmin.xhtml";
            }
        } catch (SQLException ex) {
            Logger.getLogger(usuarioAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "indexAdmin.xhtml";
    }
    
}
