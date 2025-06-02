/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package neurosleep;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class VistaTablas extends JFrame {
    private final JTabbedPane tabbedPane;

    public VistaTablas() {
        setTitle("Visualización de Tablas - Base de Datos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        agregarTabla("Estudiantes", new String[]{
                "EstudianteID", "DNI", "Nombre", "Apellido", "FechaNacimiento",
                "Genero", "CorreoElectronico", "Telefono", "Distrito", "FechaRegistro"
        });

        agregarTabla("RegistroSueno", new String[]{
                "SuenoID", "EstudianteID", "Fecha", "HoraInicio",
                "HoraFin", "CalidadSueno", "Despertares", "Observaciones"
        });

        agregarTabla("Profesor", new String[]{
                "ID_Profesor", "Nombre", "Correo", "Departamento"
        });

        agregarTabla("Curso", new String[]{
                "ID_Curso", "Nombre_Curso", "Créditos", "ProfesorID"
        });

        agregarTabla("RendimientoAcademico", new String[]{
                "ID_Rendimiento", "Nota_Final", "Promedio", "Fecha_Evaluación", "EstudianteID", "CursoID"
        });

        agregarTabla("RecomendacionesSueno", new String[]{
                "RecomendacionID", "EstudianteID", "FechaGeneracion", "Contenido"
        });

        agregarTabla("RecomendacionesAcademicas", new String[]{
                "RecomendacionID", "EstudianteID", "FechaGeneracion", "Contenido"
        });

        add(tabbedPane);
        setVisible(true);
    }

    private void agregarTabla(String nombreTabla, String[] columnas) {
        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);

        for (String col : columnas) {
            modelo.addColumn(col);
        }

        String query = "SELECT * FROM " + nombreTabla;

        try (Connection conn = obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Object[] fila = new Object[columnas.length];
                for (int i = 0; i < columnas.length; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la tabla " + nombreTabla + ":\n" + e.getMessage());
        }

        tabbedPane.addTab(nombreTabla, scrollPane);
    }

private Connection obtenerConexion() throws SQLException {
    String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:53350;databaseName=NeuroSleep;encrypt=true;trustServerCertificate=true";
    String usuario = "PHACSI73";
    String contraseña = "descuento2003";
    return DriverManager.getConnection(url, usuario, contraseña);
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaTablas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaTablas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
