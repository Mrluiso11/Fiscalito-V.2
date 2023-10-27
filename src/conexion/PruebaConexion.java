/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Connection;

/**
 *
 * @author dbpan
 */
public class PruebaConexion {

    public static void main(String[] args) {
        Connection conexxion = null;
        conexxion = Conexion.obtenerConexion();

        if (conexxion != null) {
            System.out.println("Conexión exitosa a la base de datos SQLite.");
        }
    }
}
