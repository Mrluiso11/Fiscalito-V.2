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
            statement.setFloat(5, servicios.getPrecio());
            statement.setDouble(6, servicios.getItbms());

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
    
    //metodo delete
    public void deleteProductoporCodigo(Connection conexion) {
        String query = "DELETE FROM tbl_servicio WHERE codigo_servicio = ?";
        
        int opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de querer eliminar este producto?", "Confirmación", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION){
          try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, codigoservicio);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Producto con Codigo " + this.codigoservicio + " eliminado exitosamente.");
            } else {
                System.out.println("Producto con Codigo " + this.codigoservicio + " no encontrado o no pudo ser eliminado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }  
        }
        if (opcion == JOptionPane.NO_OPTION){
            JOptionPane.showMessageDialog(null, "No se eliminó el producto");
        }

    }
    
    public void selectProductoporCodigo(Connection conexion) {
        String query = "SELECT * FROM tbl_servicio WHERE codigo_servicio  = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, this.codigoservicio);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Recupera los datos del cliente
                this.codigoservicio = resultSet.getString("codigo_servicio");
                this.nombreservicio = resultSet.getString("servicio");
                this.descripcion = resultSet.getString("descripcion");
                this.tipocobro = resultSet.getString("tipo_cobro");
                this.precio = resultSet.getFloat("precio");
                this.itbms = resultSet.getDouble("Impuesto");

            } else {
                JOptionPane.showMessageDialog(null, "El Codigo de Servicio "+ this.codigoservicio +" no Existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
                this.nombreservicio = resultSet.getString("servicio");
                servicios.add(nombreservicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servicios;
    }
    
    
    public void InfoServicioPorNombre(Connection conexion) {
    String query = "SELECT codigo_servicio, descripcion, tipo_cobro, precio, impuesto FROM tbl_producto WHERE servicio = ?";
    

    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, nombreservicio); // Establece el nombre de la variable en lugar de ruc

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            this.codigoservicio = resultSet.getString("codigo_producto");
            this.descripcion= resultSet.getString("descripcion");
            this.tipocobro = resultSet.getString("tipo_cobro");
            this.precio = resultSet.getFloat("precio");
            this.itbms = resultSet.getDouble("impuesto");
        } 
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
    
}
