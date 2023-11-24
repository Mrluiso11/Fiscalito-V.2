/**
 * Clase para gestionar clientes en una base de datos.
 * La clase "Clientes" se utiliza para interactuar con los datos de clientes en una base de datos.
 * Proporciona métodos para insertar, actualizar, eliminar y consultar información
 * relacionada con clientes.
 *
 * @author dbpan
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

public class Clientes {

    // Campos de la clase que representan las propiedades de un cliente.
    private String codigo_cliente;
    private String ruc;
    private String nombre;
    private String direccion;
    private String telefono1;
    private String telefono2;
    private String correo;
    private String observaciones;
    private Date Fecha_registro;
    private Date Fecha_actualizacion;

    // Constructor
    public Clientes(String codigo_cliente, String ruc, String nombre, String direccion, String telefono1, String telefono2, String correo, String observaciones, Date Fecha_registro, Date Fecha_actualizacion) {
        this.codigo_cliente = codigo_cliente;
        this.ruc = ruc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.correo = correo;
        this.observaciones = observaciones;
        this.Fecha_registro = Fecha_registro;
        this.Fecha_actualizacion = Fecha_actualizacion;
    }

    public Clientes() {
    }

    // Métodos de acceso y mutadores
    public String getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(String codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        //System.out.println(ruc);
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

    public Date getFecha_registro() {
        return Fecha_registro;
    }

    public void setFecha_registro(Date Fecha_registro) {
        this.Fecha_registro = Fecha_registro;
    }

    public Date getFecha_actualizacion() {
        return Fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date Fecha_actualizacion) {
        this.Fecha_actualizacion = Fecha_actualizacion;
    }

// Método para insertar un nuevo cliente en la base de datos
    public int insertClientes(Connection conexion, Clientes cliente) {

        String query = "INSERT INTO tbl_cliente (ruc, nombre, direccion, telefono1, telefono2, correo, observaciones,fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?,CURRENT_TIMESTAMP)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, cliente.getRuc());
            statement.setString(2, cliente.getNombre());
            statement.setString(3, cliente.getDireccion());
            statement.setString(4, cliente.getTelefono1());
            statement.setString(5, cliente.getTelefono2());
            statement.setString(6, cliente.getCorreo());
            statement.setString(7, cliente.getObservaciones());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Método para obtener una lista de RUC de clientes
    public List<String> getAllRUC(Connection conexion) {
        List<String> rucList = new ArrayList<>();
        String query = "SELECT ruc FROM tbl_cliente";

        try (PreparedStatement statement = conexion.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                ruc = resultSet.getString("ruc");
                rucList.add(ruc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rucList;
    }

    // Método para eliminar un cliente por su RUC
    public int deleteClientePorRuc(Connection conexion) {
        String query = "DELETE FROM tbl_cliente WHERE ruc = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, ruc);

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Método para seleccionar un cliente por su nombre
    public void selectClientePorNombre(Connection conexion) {
        String query = "SELECT * FROM tbl_cliente WHERE nombre = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, nombre);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Recupera los datos del cliente
                codigo_cliente = resultSet.getString("codigo_cliente");
                ruc = resultSet.getString("ruc");
                direccion = resultSet.getString("direccion");
                telefono1 = resultSet.getString("telefono1");
                telefono2 = resultSet.getString("telefono2");
                correo = resultSet.getString("correo");
                observaciones = resultSet.getString("observaciones");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un cliente existente en la base de datos
    public int updateClientePorRuc(Connection conexion, Clientes cliente) {
        String query = "UPDATE tbl_cliente SET nombre = ?, direccion = ?, telefono1 = ?, telefono2 = ?, correo = ?, observaciones = ?, fecha_actualizacion = CURRENT_TIMESTAMP WHERE ruc = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getDireccion());
            statement.setString(3, cliente.getTelefono1());
            statement.setString(4, cliente.getTelefono2());
            statement.setString(5, cliente.getCorreo());
            statement.setString(6, cliente.getObservaciones());
            statement.setString(7, cliente.getRuc());

            int filasAfectadas = statement.executeUpdate();
            return filasAfectadas;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

// Método para obtener una lista de nombres de clientes
    public List<String> getAllNombres(Connection conexion) {
        List<String> nombres = new ArrayList<>();
        String query = "SELECT nombre FROM tbl_cliente";

        try (PreparedStatement statement = conexion.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                nombres.add(nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombres;
    }

// Método para obtener información de un cliente por su nombre
    public void InfoClientePorNombre(Connection conexion) {
        String query = "SELECT codigo_cliente, ruc, telefono1, telefono2, direccion FROM tbl_cliente WHERE nombre = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, nombre);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                codigo_cliente = resultSet.getString("codigo_cliente");
                ruc = resultSet.getString("ruc");
                direccion = resultSet.getString("direccion");
                telefono1 = resultSet.getString("telefono1");
                telefono2 = resultSet.getString("telefono2");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// Método para obtener una lista de clientes en forma de objetos
    public List<Clientes> getAllClientesTable(Connection conexion) {
        List<Clientes> clientes = new ArrayList<>();
        String query = "SELECT * FROM tbl_cliente";

        try (PreparedStatement statement = conexion.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                codigo_cliente = resultSet.getString("codigo_cliente");
                ruc = resultSet.getString("ruc");
                nombre = resultSet.getString("nombre");
                direccion = resultSet.getString("direccion");
                telefono1 = resultSet.getString("telefono1");
                telefono2 = resultSet.getString("telefono2");
                correo = resultSet.getString("correo");
                observaciones = resultSet.getString("observaciones");
                Fecha_registro = resultSet.getDate("fecha_registro");
                Fecha_actualizacion = resultSet.getDate("fecha_actualizacion");

                Clientes cliente = new Clientes();
                cliente.setCodigo_cliente(codigo_cliente);
                cliente.setRuc(ruc);
                cliente.setNombre(nombre);
                cliente.setDireccion(direccion);
                cliente.setTelefono1(telefono1);
                cliente.setTelefono2(telefono2);
                cliente.setCorreo(correo);
                cliente.setObservaciones(observaciones);
                cliente.setFecha_registro(Fecha_registro);
                cliente.setFecha_actualizacion(Fecha_actualizacion);

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }
}
