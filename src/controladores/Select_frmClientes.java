/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
/**
 *
 * @author dbpan
 */
public class Select_frmClientes {
   private String ruc;
    private String nombre;
    private String direccion;
    private String telefono1;
    private String telefono2;
    private String correo;
    private String observaciones;

    public Select_frmClientes(String ruc, String nombre, String direccion, String telefono1, String telefono2, String correo, String observaciones) {
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.correo = correo;
        this.observaciones = observaciones;
    }

    public Select_frmClientes() {
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

 
   public void selectClientePorRuc(Connection conexion) {
        String query = "SELECT * FROM tbl_cliente WHERE ruc = ?";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, ruc);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                // Recupera los datos del cliente
                nombre = resultSet.getString("nombre");
                direccion = resultSet.getString("direccion");
                telefono1 = resultSet.getString("telefono1");
                telefono2 = resultSet.getString("telefono2");
                correo = resultSet.getString("correo");
                observaciones = resultSet.getString("observaciones");
                
            } else {
                System.out.println("Cliente con RUC " + ruc + " no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
