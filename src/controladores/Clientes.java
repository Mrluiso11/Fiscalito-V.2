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
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author dbpan
 */
public class Clientes {

    private String ruc;
    private String nombre;
    private String direccion;
    private String telefono1;
    private String telefono2;
    private String correo;
    private String observaciones;
    private Date Fecha_registro;
    private Date Fecha_actualizacion;

    public Clientes(String ruc, String nombre, String direccion, String telefono1, String telefono2, String correo, String observaciones, Date Fecha_registro, Date Fecha_actualizacion) {
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

    //Metodo para insertar
    public void insertClientes(Connection conexion, Clientes cliente) {
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
            if (filasAfectadas > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("No se pudo insertar el cliente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Metodo para eliminar
    public void deleteClientePorRuc(Connection conexion) {
        String query = "DELETE FROM tbl_cliente WHERE ruc = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, ruc);

            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Cliente con RUC " + ruc + " eliminado exitosamente.");
            } else {
                System.out.println("Cliente con RUC " + ruc + " no encontrado o no pudo ser eliminado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Metodo select
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
                JOptionPane.showMessageDialog(null, "El R.C.U " + ruc + " no Existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //metodo update
    public void updateClientePorRuc(Connection conexion, Clientes cliente) {
        String query = "UPDATE tbl_cliente SET nombre = ?, direccion = ?, telefono1 = ?, telefono2 = ?, correo = ?, observaciones = ?,fecha_actualizacion = CURRENT_TIMESTAMP  WHERE ruc = ?";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getDireccion());
            statement.setString(3, cliente.getTelefono1());
            statement.setString(4, cliente.getTelefono2());
            statement.setString(5, cliente.getCorreo());
            statement.setString(6, cliente.getObservaciones());
            statement.setString(7, cliente.getRuc());

            int filasAfectadas = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//Lista de nombres

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

    public void InfoClientePorNombre(Connection conexion) {
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
    }

    public List<Clientes> getAllClientesTable(Connection conexion) {
        List<Clientes> Clientes = new ArrayList<>();
        String query = "SELECT * FROM tbl_cliente";

        try (PreparedStatement statement = conexion.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
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
                cliente.setRuc(ruc);
                cliente.setNombre(nombre);
                cliente.setDireccion(direccion);
                cliente.setTelefono1(telefono1);
                cliente.setTelefono2(telefono2);
                cliente.setCorreo(correo);
                cliente.setFecha_registro(Fecha_registro);
                cliente.setFecha_actualizacion(Fecha_actualizacion );

                Clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Clientes;
    }

}
