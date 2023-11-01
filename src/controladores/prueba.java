/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author dbpan
 */
public class prueba {

    private String ruc;
    private byte[] logo_factura;
    private byte[] logo_empresa;

    public prueba() {
    }

    public prueba(String ruc, byte[] logo_factura, byte[] logo_empresa) {
        this.ruc = ruc;
        this.logo_factura = logo_factura;
        this.logo_empresa = logo_empresa;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public byte[] getLogo_factura() {
        return logo_factura;
    }

    public void setLogo_factura(byte[] logo_factura) {
        this.logo_factura = logo_factura;
    }

    public byte[] getLogo_empresa() {
        return logo_empresa;
    }

    public void setLogo_empresa(byte[] logo_empresa) {
        this.logo_empresa = logo_empresa;
    }

    public void LogosByRuc(Connection conexion, String ruc) {
        String query = "SELECT logo_factura, logo_empresa FROM tbl_empresa WHERE ruc = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, ruc);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Recupera los datos del cliente
                logo_factura = resultSet.getBytes("logo_factura");
               logo_empresa = resultSet.getBytes("logo_empresa");

                // Haz lo que necesites con los valores, como mostrarlos o utilizarlos en tu aplicación
            } else {
                JOptionPane.showMessageDialog(null, "El R.C.U " + ruc + " no Existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
