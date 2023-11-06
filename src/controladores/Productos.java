/**
 * Clase para gestionar productos en una base de datos.
 * La clase "Productos" se utiliza para interactuar con los datos de productos en una base de datos.
 * Proporciona métodos para insertar, actualizar, eliminar y consultar información
 * relacionada con productos.
 *
 * @author admin
 */
package controladores;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class Productos {

    // Campos de la clase que representan las propiedades de un producto.
    private String codigoproducto;
    private String nombreproducto;
    private String descripcion;
    private String magnitud;
    private float precio;
    private double itbms;
    private Date fecha_registro;
    private Date fecha_actualizacion;

    // Constructores
    public Productos() {
    }

    public Productos(String codigoproducto, String nombreproducto, String descripcion, String magnitud, float precio, double itbms, Date fecha_registro, Date fecha_actualizacion) {
        this.codigoproducto = codigoproducto;
        this.nombreproducto = nombreproducto;
        this.descripcion = descripcion;
        this.magnitud = magnitud;
        this.precio = precio;
        this.itbms = itbms;
        this.fecha_registro = fecha_registro;
        this.fecha_actualizacion = fecha_actualizacion;
    }

    // Métodos de acceso y mutadores
    public String getCodigoproducto() {
        return codigoproducto;
    }

    public void setCodigoproducto(String codigoproducto) {
        this.codigoproducto = codigoproducto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
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

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    // Método para insertar un nuevo producto en la base de datos
    public int insertProductos(Connection conexion, Productos productos) {
        String query = "INSERT INTO tbl_producto (codigo_producto, producto, descripcion, magnitud, precio, impuesto, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, productos.getCodigoproducto());
            statement.setString(2, productos.getNombreproducto());
            statement.setString(3, productos.getDescripcion());
            statement.setString(4, productos.getMagnitud());
            statement.setFloat(5, productos.getPrecio());
            statement.setDouble(6, productos.getItbms());

            int filasAfectadas = statement.executeUpdate();

            return filasAfectadas;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }

    // Método para actualizar un producto existente en la base de datos
    public int actualizarProducto(Connection conexion, Productos productos) {
        String query = "UPDATE tbl_producto SET producto = ?, descripcion = ?, magnitud = ?, precio = ?, impuesto = ?, fecha_actualizacion = CURRENT_TIMESTAMP WHERE codigo_producto = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, productos.getNombreproducto());
            statement.setString(2, productos.getDescripcion());
            statement.setString(3, productos.getMagnitud());
            statement.setFloat(4, productos.getPrecio());
            statement.setDouble(5, productos.getItbms());
            statement.setString(6, productos.getCodigoproducto());

            int filasAfectadas = statement.executeUpdate();

            return filasAfectadas;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }

    // Método para eliminar un producto por su código
    public int deleteProductoporCodigo(Connection conexion) {
        String query = "DELETE FROM tbl_producto WHERE codigo_producto = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, codigoproducto);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }

    // Método para seleccionar un producto por su código
    public void selectProductoporCodigo(Connection conexion) {
        String query = "SELECT * FROM tbl_producto WHERE codigo_producto  = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, this.codigoproducto);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Recupera los datos del producto
                codigoproducto = resultSet.getString(1);
                nombreproducto = resultSet.getString(2);
                descripcion = resultSet.getString(3);
                magnitud = resultSet.getString(4);
                precio = resultSet.getFloat(5);
                itbms = resultSet.getDouble(6);
            } else {
                JOptionPane.showMessageDialog(null, "El Codigo de Producto " + this.codigoproducto + " no Existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Método para obtener una lista de nombres de productos
    public List<String> getAllProductos(Connection conexion) {
        List<String> productos = new ArrayList<>();
        String query = "SELECT producto FROM tbl_producto";

        try (PreparedStatement statement = conexion.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                this.nombreproducto = resultSet.getString("producto");
                productos.add(nombreproducto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // Método para obtener información de un producto por su nombre
    public void InfoProductoPorNombre(Connection conexion) {
        String query = "SELECT codigo_producto, descripcion, magnitud, precio, impuesto FROM tbl_producto WHERE producto = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, nombreproducto);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                codigoproducto = resultSet.getString("codigo_producto");
                descripcion = resultSet.getString("descripcion");
                magnitud = resultSet.getString("magnitud");
                precio = resultSet.getFloat("precio");
                itbms = resultSet.getDouble("impuesto");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener una lista de productos en forma de objetos
    public List<Productos> getAllProductosTable(Connection conexion) {
        List<Productos> productos = new ArrayList<>();
        String query = "SELECT * FROM tbl_producto";

        try (PreparedStatement statement = conexion.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String codigoProducto = resultSet.getString("codigo_producto");
                String nombreProducto = resultSet.getString("producto");
                String descripcion = resultSet.getString("descripcion");
                String magnitud = resultSet.getString("magnitud");
                float precio = resultSet.getFloat("precio");
                double itbms = resultSet.getDouble("impuesto");
                Date fechaRegistro = resultSet.getDate("fecha_registro");
                Date fechaActualizacion = resultSet.getDate("fecha_actualizacion");

                Productos producto = new Productos();
                producto.setCodigoproducto(codigoProducto);
                producto.setNombreproducto(nombreProducto);
                producto.setDescripcion(descripcion);
                producto.setMagnitud(magnitud);
                producto.setPrecio(precio);
                producto.setItbms(itbms);
                producto.setFecha_registro(fechaRegistro);
                producto.setFecha_actualizacion(fechaActualizacion);

                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }
}
