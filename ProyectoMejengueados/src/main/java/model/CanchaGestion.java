package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CanchaGestion {
    private static final String SQL_SELECT_CANCHAS = "select * from cancha";
    
    private static final String SQL_INSERT_CANCHA
            = "insert into cancha values (?,?,?,?)";
    private static final String SQL_SELECT_CANCHA
            = "select * from cancha where id=?";
    private static final String SQL_UPDATE_CANCHA
            = "update cancha set nombre=?, lugar=?,"
            + "telefono=? where id=?";
    private static final String SQL_DELETE_CANCHA
            = "delete from cancha where id=?";
    
    
      public static boolean insertar(Cancha cancha) {
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_INSERT_CANCHA);
            sentencia.setString(1, cancha.getId());
            sentencia.setString(2, cancha.getNombre());
            sentencia.setString(3, cancha.getLugar());
            sentencia.setString(4, cancha.getTelefono());
            
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CanchaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
      
      
       public static boolean modificar(Cancha cancha) {
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_UPDATE_CANCHA);
            sentencia.setString(1, cancha.getNombre());
            sentencia.setString(2, cancha.getLugar());
            sentencia.setString(3, cancha.getTelefono());
            sentencia.setString(4, cancha.getId());
            
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CanchaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
       
       public static boolean eliminar(Cancha cancha) {
        try {
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_DELETE_CANCHA);
            sentencia.setString(1, cancha.getId());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CanchaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
       
       
       public static Cancha getCancha(String id) {
        Cancha cancha = null;
        try {
            PreparedStatement consulta = Conexion.getConexion()
                    .prepareStatement(SQL_SELECT_CANCHA);
            consulta.setString(1, id);
            ResultSet datos = consulta.executeQuery();
            if (datos != null && datos.next()) {
                cancha = new Cancha(
                        datos.getString(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4)
                        
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CanchaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cancha;
    }
    
    public static ArrayList<Cancha> getCanchas(){
        ArrayList<Cancha> lista = new ArrayList<>();
        try{
            PreparedStatement consulta = Conexion.getConexion()
                    .prepareStatement(SQL_SELECT_CANCHAS);
            ResultSet datos = consulta.executeQuery();
            while(datos!=null && datos.next()){
                lista.add(new Cancha(
                           datos.getString(1),
                           datos.getString(2),
                           datos.getString(3),
                           datos.getString(4)));  
            }
        }catch(SQLException ex){
            Logger.getLogger(CanchaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
}
