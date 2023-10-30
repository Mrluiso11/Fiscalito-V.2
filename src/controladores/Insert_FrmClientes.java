/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author dbpan
 */
public class Insert_FrmClientes {

    private String ruc;
    private String nombre;
    private String direccion;
    private String telefono1;
    private String telefono2;
    private String correo;
    private String observaciones;

    public Insert_FrmClientes(String ruc, String nombre, String direccion, String telefono1, String telefono2, String correo, String observaciones) {
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.correo = correo;
        this.observaciones = observaciones;
    }

    public Insert_FrmClientes() {
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void insertClientes(Connection conexion, Insert_FrmClientes cliente) {
        String query = "INSERT INTO tbl_cliente (ruc, nombre, direccion, telefono1, telefono2, correo, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, cliente.getRuc());
            statement.setString(2, cliente.getNombre());
            statement.setString(3, cliente.getDireccion());
            statement.setString(4, cliente.getTelefono1());
            statement.setString(5, cliente.getTelefono2());
            statement.setString(6, cliente.getCorreo());
            statement.setString(7, cliente.getObservaciones());

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("No se pudo insertar el cliente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
