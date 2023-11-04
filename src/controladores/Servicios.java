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
public class Servicios {
    private String codigoservicio;
    private String nombreservicio;
    private String descripcion;
    private String tipocobro;
    private String contrato;
    private float precio;
    private double itbms;
    
    public Servicios(){   
    }
    
    public Servicios(String codigoservicio,String nombreservicio,String descripcion,String tipocobro,String contrato,float precio,double itbms ){
       this.codigoservicio= codigoservicio;
       this.nombreservicio= nombreservicio;
       this.descripcion= descripcion;
       this.tipocobro= tipocobro;
       this.contrato= contrato;
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

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
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
        String query = "INSERT INTO tbl_servicio (codigo_servicio, servicio, descripcion, tipo_cobro, contrato, precio, impuesto) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, servicios.getCodigoservicio());
            statement.setString(2, servicios.getNombreservicio());
            statement.setString(3, servicios.getDescripcion());
            statement.setString(4, servicios.getTipocobro());
            statement.setString(5, servicios.getContrato());
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
    
    
    
}
