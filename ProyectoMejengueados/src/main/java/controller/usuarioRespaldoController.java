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
import model.usuarioRespaldo;

@Named(value = "usuarioRespaldoController")
@SessionScoped
public class usuarioRespaldoController implements Serializable {
    
    @Inject
    private usuarioRespaldo usuarioRespaldo;

    public usuarioRespaldoController() {
    }
    
    public String validaRespaldo() {
        try {
            String consulta = "select*from usuarioRespaldo where idRespaldo=?";
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(consulta);
            sentencia.setString(1, usuarioRespaldo.getIdRespaldo());
            ResultSet rs = sentencia.executeQuery();
            if (rs != null && rs.next() && rs.getString(2).equals(usuarioRespaldo.getPwRespaldo())) {
                usuarioRespaldo.setNombreRespaldo(rs.getString(3));
                return "principalRespaldo.xhtml";
            }
        } catch (SQLException ex) {
            Logger.getLogger(usuarioAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "indexRespaldo.xhtml";
    }
    
}
