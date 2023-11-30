/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/form";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "german950901";

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            // Cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            
            System.out.println("¡Conexión exitosa!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }

    public static void cerrarConexion(Connection conexion) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Prueba de conexión
        Connection conexion = obtenerConexion();
        // Realiza otras operaciones con la conexión si es necesario
        
        // Cierra la conexión después de usarla
        cerrarConexion(conexion);
    }
}
