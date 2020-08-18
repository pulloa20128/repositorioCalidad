package model;



import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    private static Conexion laConexion;
    private static final String DBURL = "jdbc:derby://localhost:1527/appmejengueados;user=root;password=root";
    private static Connection conn = null;

    public Conexion() {
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver").getDeclaredConstructor().newInstance();
            conn= DriverManager.getConnection(DBURL);
            
        }catch (ClassNotFoundException|SQLException|InstantiationException|
                IllegalAccessException|NoSuchMethodException|
                InvocationTargetException ex){
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    public static synchronized Connection getConexion(){
        if(laConexion==null){
            laConexion= new Conexion();
        }
        return conn;
    }
    
}
