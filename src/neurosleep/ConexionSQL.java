/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package neurosleep;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQL {
    public static Connection conectar() throws SQLException {
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:53350;databaseName=NeuroSleep;encrypt=true;trustServerCertificate=true";
        String usuario = "PHACSI73";
        String contraseña = "descuento2003";

        return DriverManager.getConnection(url, usuario, contraseña);
    }

    public static void main(String[] args) {
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        Connection conn = conectar();
        if (conn != null) {
            System.out.println("Conexion exitosa");
            conn.close();
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
