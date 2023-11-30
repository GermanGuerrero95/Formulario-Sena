/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;

/**
 *
 * @author USER
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatosFormulario extends JFrame {

    // Componentes del formulario
private final JTextField txtNombre;
private final JTextField txtEdad;
private final JButton btnGuardar;

    // Conexión a la base de datos
    private Connection conexion;

    public DatosFormulario(Connection conexion) {
        this.conexion = conexion;

        // Configurar el formulario
        setTitle("Formulario de Datos");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar componentes
        txtNombre = new JTextField(20);
        txtEdad = new JTextField(20);
        btnGuardar = new JButton("Guardar");

        // Configurar el diseño
        setLayout(new GridLayout(3, 2));
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Edad:"));
        add(txtEdad);
        add(new JLabel("")); // Espacio en blanco
        add(btnGuardar);

        // Configurar el evento del botón
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });
    }

    private void guardarDatos() {
        try {
            // Obtener los valores de los campos
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());

            // Preparar la declaración SQL
            String sql = "INSERT INTO datos (nombre, edad) VALUES (?, ?)";
            try (PreparedStatement statement = conexion.prepareStatement(sql)) {
                statement.setString(1, nombre);
                statement.setInt(2, edad);

                // Ejecutar la actualización
                int filasAfectadas = statement.executeUpdate();

                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(this, "Datos guardados correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo guardar los datos");
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        // Crear una instancia de la clase de conexión
        Connection conexion = ConexionBD.obtenerConexion();

        // Verificar que la conexión sea exitosa antes de mostrar el formulario
        if (conexion != null) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    // Crear y mostrar el formulario
                    DatosFormulario formulario = new DatosFormulario(conexion);
                    formulario.setVisible(true);
                }
            });
        }
    }
}
