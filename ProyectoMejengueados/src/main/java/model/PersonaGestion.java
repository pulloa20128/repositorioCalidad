package model;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

public class PersonaGestion {
    
    private String cedula;
    private String nombre;     
    private String Apellido1;
    private String Apellido2;
    private Date fechaNacimiento;
    private String correo;     
    private String celular;
    
    
    private static final String SQL_INSERT_PERSONA = "insert into persona values (?,?,?,?,?,?,?)";
    private static final String SQL_SELECT_PERSONA = "select * from persona where cedula=?";
    private static final String SQL_UPDATE_PERSONA = "update persona set nombre=?, apellido1=?, apellido2=?, fechaNacimiento=?, correo=?, celular=? where cedula=?";
    private static final String SQL_DELETE_PERSONA = "delete from persona where id=?";
    private static final String SQL_SELECT_PERSONAS = "select * from persona";

    public static String generarJson(){
        Persona persona=new Persona();
        String tiraJson ="";String fechaNacimiento;
        try{
            PreparedStatement sentencia = Conexion.getConexion()
                    .prepareStatement(SQL_SELECT_PERSONA);
            ResultSet rs= sentencia.executeQuery();
            while(rs.next()){
                persona.setCedula(rs.getString(1));
                persona.setNombre(rs.getString(2));
                persona.setApellido1(rs.getString(3));
                persona.setApellido2(rs.getString(4));
                persona.setFechaNacimiento(rs.getDate(5));
                persona.setCorreo(rs.getString(6));
                persona.setCedula(rs.getString(7));
                
                StringWriter tira = new StringWriter();
                JsonWriter jsonWriter =Json.createWriter(tira);
                //jsonWriter.writeObject(ObjectJson);
                if(tiraJson==null){
                    tiraJson=tira.toString()+"\n";
                }
                else
                    tiraJson=tiraJson+ tira.toString()+"\n";
                
            }
            
        }
        catch(SQLException ex){
            Logger.getLogger(PersonaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }
    
    public static boolean insertar(Persona persona) {
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_INSERT_PERSONA);
            consulta.setString(1, persona.getCedula());
            consulta.setString(2, persona.getNombre());
            consulta.setString(3, persona.getApellido1());
            consulta.setString(4, persona.getApellido2());
            consulta.setObject(5, persona.getFechaNacimiento());
            consulta.setString(6, persona.getCorreo());
            consulta.setString(7, persona.getCelular());
            
            
            return (consulta.executeUpdate() == 1);
        } catch (SQLException ex) {
        }
        return false;
    }

    public static boolean modificar(Persona persona) {
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_UPDATE_PERSONA);
            java.sql.Date dia1=new java.sql.Date(persona.getFechaNacimiento().getTime());
            consulta.setString(1, persona.getNombre());
            consulta.setString(2, persona.getApellido1());
            consulta.setString(3, persona.getApellido2());
            consulta.setDate(4, dia1);
            consulta.setString(5, persona.getCorreo());
            consulta.setString(6, persona.getCelular());
            consulta.setString(7, persona.getCedula());
            return (consulta.executeUpdate()>0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static boolean eliminar(String cedula) {
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_DELETE_PERSONA);
            consulta.setString(1, cedula);
            return (consulta.executeUpdate() == 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static Persona getPersona(String cedula) {
        Persona persona= null;
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_PERSONA);
            consulta.setString(1, cedula);
            ResultSet datos = consulta.executeQuery();
            if (datos != null && datos.next()) {
                persona = new Persona(
                        datos.getString(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4),
                        datos.getDate(5),
                        datos.getString(6),
                        datos.getString(7)
                                
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return persona;
    }

    public static ArrayList<Persona> getPersona() {
        ArrayList<Persona> lista = new ArrayList<>();
        try {
            PreparedStatement consulta = Conexion.getConexion().prepareStatement(SQL_SELECT_PERSONA);
            ResultSet datos = consulta.executeQuery();
            if (datos != null && datos.next()) {
                do {
                    lista.add(new Persona(
                            datos.getString(1),
                            datos.getString(2),
                            datos.getString(3),
                            datos.getString(4),
                            datos.getDate(5),                            
                            datos.getString(6),
                            datos.getString(7)
                            
                    ));
                } while (datos.next());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
        
    }
    
     private static final String SQL_INGRESO_YEAR_GENDER
            = "select ID,NOMBRE,CANTIDAD from edades";

    public static ArrayList<Edades> getIngresoYearGender() {
        ArrayList<Edades >lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion()
                    .prepareStatement(SQL_INGRESO_YEAR_GENDER);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                lista.add(new Edades(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getDouble(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
         private static final String SQL_INGRESO_Reservas
            = "select ID,NOMBRE,CANTIDAD from reservas";
    
    
        public static ArrayList<Reservas> getReservas() {
        ArrayList<Reservas >lista = new ArrayList<>();

        try {
            PreparedStatement consulta = Conexion.getConexion()
                    .prepareStatement(SQL_INGRESO_Reservas);
            ResultSet datos = consulta.executeQuery();
            while (datos != null && datos.next()) {
                lista.add(new Reservas(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getDouble(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersonaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
