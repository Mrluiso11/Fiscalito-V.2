/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
/**
 *
 * @author admin
 */
public class Servicios {
    private String codigoservicio;
    private String nombreservicio;
    private String descripcion;
    private String tipocobro;
    private float precio;
    private double itbms;
    
    public Servicios(){   
    }
    
    public Servicios(String codigoservicio,String nombreservicio,String descripcion,String tipocobro,float precio,double itbms ){
       this.codigoservicio= codigoservicio;
       this.nombreservicio= nombreservicio;
       this.descripcion= descripcion;
       this.tipocobro= tipocobro;
       this.precio= precio;
       this.itbms= itbms; 
    }

    public String getCodigoservicio() {
        return codigoservicio;
    }

    public void setCodigoservicio(String codigoservicio) {
        this.codigoservicio = codigoservicio;
    }

    public String getNombreservicio() {
        return nombreservicio;
    }

    public void setNombreservicio(String nombreservicio) {
        this.nombreservicio = nombreservicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipocobro() {
        return tipocobro;
    }

    public void setTipocobro(String tipocobro) {
        this.tipocobro = tipocobro;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public double getItbms() {
        return itbms;
    }

    public void setItbms(double itbms) {
        this.itbms = itbms;
    }
    
    //metodos insertar
    public void insertServicio(Connection conexion, Servicios servicios) {
        String query = "INSERT INTO tbl_servicio (codigo_servicio, servicio, descripcion, tipo_cobro, precio, impuesto) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, servicios.getCodigoservicio());
            statement.setString(2, servicios.getNombreservicio());
            statement.setString(3, servicios.getDescripcion());
            statement.setString(4, servicios.getTipocobro());
            statement.setFloat(6, servicios.getPrecio());
            statement.setDouble(7, servicios.getItbms());

            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Datos ingresados exitosamente");
            } else {
                System.out.println("No se pudo insertar el producto");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    //metodo update
    public void updateServicioporCodigo(Connection conexion, Servicios servicios) {
    String query = "UPDATE tbl_servicio SET servicio = ?, descripcion = ?, tipo_cobro = ?, precio = ?, impuesto = ? WHERE codigo_servicio = ?";

    try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, servicios.getNombreservicio());
            statement.setString(2, servicios.getDescripcion());
            statement.setString(3, servicios.getTipocobro());
            statement.setFloat(5, servicios.getPrecio());
            statement.setDouble(6, servicios.getItbms());
            statement.setString(7, servicios.getCodigoservicio());

        int filasAfectadas = statement.executeUpdate();
 
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error: " + e.getMessage());
    }
}
    
    //metodo listado de nombres 
    public List<String> getAllServicios(Connection conexion) {
        List<String> servicios = new ArrayList<>();
        String query = "SELECT servicio FROM tbl_servicio";

        try (PreparedStatement statement = conexion.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                nombreservicio = resultSet.getString("servicio");
                servicios.add(nombreservicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servicios;
    }
    
    
    /*public void InfoProductoPorNombre(Connection conexion) {
    String query = "SELECT ruc, telefono1, telefono2, direccion FROM tbl_cliente WHERE nombre = ?";

    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, nombre); // Establece el nombre en lugar de ruc

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            ruc = resultSet.getString("ruc");
            direccion = resultSet.getString("direccion");
            telefono1 = resultSet.getString("telefono1");
            telefono2 = resultSet.getString("telefono2");

        } 
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }*/
    
    
}
