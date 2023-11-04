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
 * @author admin
 */
public class Productos {
    private String codigoproducto;
    private String nombreproducto;
    private String descripcion;
    private String magnitud;
    private float precio;
    private double itbms;
    
    public Productos(){   
    }
    
    public Productos(String codigoproducto,String nombreproducto, String descripcion,String magnitud,float precio,double itbms) {
        this.codigoproducto= codigoproducto;
        this.nombreproducto= nombreproducto;
        this.descripcion= descripcion;
        this.magnitud= magnitud;
        this.precio= precio;
        this.itbms= itbms;   
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getCodigoproducto() {
        return codigoproducto;
    }

    public void setCodigoproducto(String codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
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
    
    //metodo insertar
    public void insertProductos(Connection conexion, Productos productos) {
        String query = "INSERT INTO tbl_producto (codigo_producto, producto, descripcion, magnitud, precio, impuesto) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, productos.getCodigoproducto());
            statement.setString(2, productos.getNombreproducto());
            statement.setString(3, productos.getDescripcion());
            statement.setString(4, productos.getMagnitud());
            statement.setFloat(5, productos.getPrecio());
            statement.setDouble(6, productos.getItbms());

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
    public void updateProductoporCodigo(Connection conexion, Productos productos) {
    String query = "UPDATE tbl_producto SET producto = ?, descripcion = ?, magnitud = ?, precio = ?, impuesto = ? WHERE codigo_producto = ?";

    try (PreparedStatement statement = conexion.prepareStatement(query)) {
        statement.setString(1, productos.getNombreproducto());
        statement.setString(2, productos.getDescripcion());
        statement.setString(3, productos.getMagnitud());
        statement.setFloat(4, productos.getPrecio());
        statement.setDouble(5, productos.getItbms());
        statement.setString( 6, productos.getCodigoproducto());

        int filasAfectadas = statement.executeUpdate();
 
    } catch (SQLException e) {
        e.printStackTrace();
        System.out.println("Error: " + e.getMessage());
    }
}
    
   //metodo delete
    public void deleteProductoporCodigo(Connection conexion) {
        String query = "DELETE FROM tbl_producto WHERE codigo_producto = ?";
        
        int opcion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de querer eliminar este producto?", "Confirmación", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION){
          try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, codigoproducto);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Producto con Codigo " + this.codigoproducto + " eliminado exitosamente.");
            } else {
                System.out.println("Producto con Codigo " + this.codigoproducto + " no encontrado o no pudo ser eliminado.");
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
    
    //metodo select
    public void selectProductoporCodigo(Connection conexion) {
        String query = "SELECT * FROM tbl_producto WHERE codigo_producto  = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, this.codigoproducto);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Recupera los datos del cliente
                this.codigoproducto = resultSet.getString(1);
                this.nombreproducto = resultSet.getString(2);
                this.descripcion = resultSet.getString(3);
                this.magnitud = resultSet.getString(4);
                this.precio = resultSet.getFloat(5);
                this.itbms = resultSet.getDouble(6);

            } else {
                JOptionPane.showMessageDialog(null, "El Codigo de Producto"+ this.codigoproducto +" no Existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
