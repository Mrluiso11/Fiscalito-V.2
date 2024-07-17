/*
 * En este archivo se encuentra la definición de la clase "Servicios" que se utiliza
 * para interactuar con los datos de servicios en la base de datos.
 * Proporciona métodos para insertar, actualizar, eliminar y consultar información
 * relacionada con servicios.
 */
package controladores;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * La clase "Servicios" representa los servicios y proporciona métodos para
 * interactuar con ellos.
 *
 * @author admin
 */
public class Servicios {

    DecimalFormat formato = new DecimalFormat("#0.00");
    // Atributos que representan las propiedades de un servicio.
    private String codigoservicio;
    private String nombreservicio;
    private String descripcion;
    private String tipocobro;
    private float precio;
    private double itbms;
    private Date fecha_registro;
    private Date fecha_actualizacion;

    /**
     * Constructor vacío de la clase "Servicios".
     */
    public Servicios() {
    }

    /**
     * Constructor con parámetros de la clase "Servicios" que inicializa todas
     * las propiedades.
     *
     * @param codigoservicio Código del servicio.
     * @param nombreservicio Nombre del servicio.
     * @param descripcion Descripción del servicio.
     * @param tipocobro Tipo de cobro del servicio.
     * @param precio Precio del servicio.
     * @param itbms Impuesto del servicio.
     * @param fecha_registro Fecha de registro del servicio.
     * @param fecha_actualizacion Fecha de actualización del servicio.
     */
    public Servicios(String codigoservicio, String nombreservicio, String descripcion, String tipocobro, float precio, double itbms, Date fecha_registro, Date fecha_actualizacion) {
        this.codigoservicio = codigoservicio;
        this.nombreservicio = nombreservicio;
        this.descripcion = descripcion;
        this.tipocobro = tipocobro;
        this.precio = precio;
        this.itbms = itbms;
        this.fecha_registro = fecha_registro;
        this.fecha_actualizacion = fecha_actualizacion;
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
        formato.format(this.precio);
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

    // Los siguientes métodos son operaciones relacionadas con la base de datos.
    // Cada método realiza una operación específica, como insertar, actualizar, eliminar o consultar servicios.
    // Método para insertar un nuevo servicio en la base de datos.
    public int insertServicio(Connection conexion, Servicios servicios) {
        String query = "INSERT INTO tbl_servicio (codigo_servicio, servicio, descripcion, tipo_cobro, precio, impuesto, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, servicios.getCodigoservicio());
            statement.setString(2, servicios.getNombreservicio());
            statement.setString(3, servicios.getDescripcion());
            statement.setString(4, servicios.getTipocobro());
            statement.setFloat(5, servicios.getPrecio());
            statement.setDouble(6, servicios.getItbms());

            int filasAfectadas = statement.executeUpdate();

            // Retorna la cantidad de filas afectadas por la inserción.
            return filasAfectadas;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }

    // Método para obtener una lista de los códigos de servicios desde la base de datos.
    public List<String> getAllCServicios(Connection conexion) {
        List<String> codigosServicios = new ArrayList<>();
        String query = "SELECT codigo_servicio FROM tbl_servicio";

        try (PreparedStatement statement = conexion.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                codigoservicio = resultSet.getString("codigo_servicio");
                codigosServicios.add(codigoservicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return codigosServicios;
    }

    // Método para actualizar un servicio existente en la base de datos.
    public int updateServicioporCodigo(Connection conexion, Servicios servicios) {
        String query = "UPDATE tbl_servicio SET servicio = ?, descripcion = ?, tipo_cobro = ?, precio = ?, impuesto = ?, fecha_actualizacion = CURRENT_TIMESTAMP WHERE codigo_servicio = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, servicios.getNombreservicio());
            statement.setString(2, servicios.getDescripcion());
            statement.setString(3, servicios.getTipocobro());
            statement.setFloat(4, servicios.getPrecio());
            statement.setDouble(5, servicios.getItbms());
            statement.setString(6, servicios.getCodigoservicio());

            int filasAfectadas = statement.executeUpdate();

            // Retorna la cantidad de filas afectadas por la actualización.
            return filasAfectadas;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }

    // Método para eliminar un servicio por su código.
    public int deleteServicioporCodigo(Connection conexion) {
        String query = "DELETE FROM tbl_servicio WHERE codigo_servicio = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, codigoservicio);

            int filasAfectadas = statement.executeUpdate();

            // Retorna la cantidad de filas afectadas por la eliminación.
            return filasAfectadas;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }

    // Método para seleccionar un servicio por su código y cargar sus datos.
    public void selectProductoporCodigo(Connection conexion) {
        String query = "SELECT * FROM tbl_servicio WHERE codigo_servicio = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, this.codigoservicio);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Recupera los datos del servicio.
                this.codigoservicio = resultSet.getString("codigo_servicio");
                this.nombreservicio = resultSet.getString("servicio");
                this.descripcion = resultSet.getString("descripcion");
                this.tipocobro = resultSet.getString("tipo_cobro");
                this.precio = resultSet.getFloat("precio");
                this.itbms = resultSet.getDouble("impuesto");
            } else {
                JOptionPane.showMessageDialog(null, "El Codigo de Servicio " + this.codigoservicio + " no Existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Método para obtener una lista de nombres de servicios desde la base de datos.
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

    // Método para cargar la información de un servicio por su nombre.
    public void InfoServicioPorNombre(Connection conexion) {
        String query = "SELECT codigo_servicio, descripcion, tipo_cobro, precio, impuesto FROM tbl_servicio WHERE servicio = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, nombreservicio);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                codigoservicio = resultSet.getString("codigo_servicio");
                descripcion = resultSet.getString("descripcion");
                tipocobro = resultSet.getString("tipo_cobro");
                precio = resultSet.getFloat("precio");
                itbms = resultSet.getDouble("impuesto");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener una lista de todos los servicios en la base de datos.
    public List<Servicios> getAllProductosTable(Connection conexion) {
        List<Servicios> servicios = new ArrayList<>();
        String query = "SELECT * FROM tbl_servicio";

        try (PreparedStatement statement = conexion.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String codigoservicio = resultSet.getString("codigo_servicio");
                String nombreservicio = resultSet.getString("servicio");
                String descripcion = resultSet.getString("descripcion");
                String tipocobro = resultSet.getString("tipo_cobro");
                float precio = resultSet.getFloat("precio");
                double itbms = resultSet.getDouble("impuesto");
                Date fechaRegistro = resultSet.getDate("fecha_registro");
                Date fechaActualizacion = resultSet.getDate("fecha_actualizacion");

                Servicios servicio = new Servicios();
                servicio.setCodigoservicio(codigoservicio);
                servicio.setNombreservicio(nombreservicio);
                servicio.setDescripcion(descripcion);
                servicio.setTipocobro(tipocobro);
                servicio.setPrecio(precio);
                servicio.setItbms(itbms);
                servicio.setFecha_registro(fechaRegistro);
                servicio.setFecha_actualizacion(fechaActualizacion);

                servicios.add(servicio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return servicios;
    }
    // Método para eliminar todos los registros de la tabla

    public int deleteAllServicios(Connection conexion) {
        String query = "DELETE FROM tbl_servicio";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
        return 0;
    }

}
