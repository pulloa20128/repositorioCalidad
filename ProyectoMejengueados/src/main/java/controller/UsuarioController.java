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
import model.Usuario;
import model.Conexion;

@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    @Inject
    private Usuario usuario;

    public UsuarioController() {
    }

    public String valida() {
        try {
            String consulta = "select*from usuario where id=?";
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(consulta);
            sentencia.setString(1, usuario.getId());
            ResultSet rs = sentencia.executeQuery();
            if (rs != null && rs.next() && rs.getString(2).equals(usuario.getPw())) {
                usuario.setNombre(rs.getString(3));
                return "principal.xhtml";
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index.xhtml";
    }
}
