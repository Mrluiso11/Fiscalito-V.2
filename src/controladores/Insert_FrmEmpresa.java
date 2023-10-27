/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import java.util.Date;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * /**
 *
 * @author dbpan
 */
public class Insert_FrmEmpresa {

    private int id_empresa;
    private String nombre_empresa;
    private String nombre_comercial;
    private String dv;
    private String ruc;
    private String direccion;
    private String pais;
    private String provincia;
    private String distrito;
    private String corregimiento;
    private String urbanizacion;
    private String calle;
    private String locall;
    private String piso;
    private String correo_empresa;
    private String fax;
    private String telefono1;
    private String telefono2;
    private String primer_nombre_representante;
    private String segundo_nombre_representante;
    private String apellido_paterno_representante;
    private String apellido_materno_representante;
    private String cedula_representante;
    private String dv_representante;
    private String telefono1_representante;
    private String telefono2_representante;
    private String correo_representante;
    private String nombre_gerente;
    private String cedula_gerente;
    private String dv_gerente;
    private String telefono1_gerente;
    private String telefono2_gerente;
    private String correo_gerente;
    private byte[] logo_factura;
    private byte[] imagenEmpresa;
    private Date fecha_modificacion;
    private Date fecha_eliminacion;

    public Insert_FrmEmpresa(int id_empresa,
            String nombre_empresa,
            String nombre_comercial,
            String dv,
            String ruc,
            String direccion,
            String pais,
            String provincia,
            String distrito,
            String corregimiento,
            String urbanizacion,
            String calle,
            String locall,
            String piso,
            String correo_empresa,
            String fax,
            String telefono1,
            String telefono2,
            String primer_nombre_representante,
            String segundo_nombre_representante,
            String apellido_paterno_representante,
            String apellido_materno_representante,
            String cedula_representante,
            String dv_representante,
            String telefono1_representante,
            String telefono2_representante,
            String correo_representante,
            String nombre_gerente,
            String cedula_gerente,
            String dv_gerente,
            String telefono1_gerente,
            String telefono2_gerente,
            String correo_gerente,
            byte[] logo_factura,
            byte[] imagenEmpresa,
            Date fecha_modificacion,
            Date fecha_eliminacion
    ) {
        this.id_empresa = id_empresa;
        this.nombre_empresa = nombre_empresa;
        this.nombre_comercial = nombre_comercial;
        this.dv = dv;
        this.ruc = ruc;
        this.direccion = direccion;
        this.pais = pais;
        this.provincia = provincia;
        this.distrito = distrito;
        this.corregimiento = corregimiento;
        this.urbanizacion = urbanizacion;
        this.calle = calle;
        this.locall = locall;
        this.piso = piso;
        this.correo_empresa = correo_empresa;
        this.fax = fax;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.primer_nombre_representante = primer_nombre_representante;
        this.segundo_nombre_representante = segundo_nombre_representante;
        this.apellido_paterno_representante = apellido_paterno_representante;
        this.apellido_materno_representante = apellido_materno_representante;
        this.cedula_representante = cedula_representante;
        this.dv_representante = dv_representante;
        this.telefono1_representante = telefono1_representante;
        this.telefono2_representante = telefono2_representante;
        this.correo_representante = correo_representante;
        this.nombre_gerente = nombre_gerente;
        this.cedula_gerente = cedula_gerente;
        this.dv_gerente = dv_gerente;
        this.telefono1_gerente = telefono1_gerente;
        this.telefono2_gerente = telefono2_gerente;
        this.correo_gerente = correo_gerente;
        this.logo_factura = logo_factura;
        this.imagenEmpresa = imagenEmpresa;
        this.fecha_modificacion = fecha_modificacion;
        this.fecha_eliminacion = fecha_eliminacion;

    }

    public Insert_FrmEmpresa() {
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getNombre_empresa() {
        return nombre_empresa;
    }

    public void setNombre_empresa(String nombre_empresa) {
        this.nombre_empresa = nombre_empresa;
    }

    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getCorregimiento() {
        return corregimiento;
    }

    public void setCorregimiento(String corregimiento) {
        this.corregimiento = corregimiento;
    }

    public String getUrbanizacion() {
        return urbanizacion;
    }

    public void setUrbanizacion(String urbanizacion) {
        this.urbanizacion = urbanizacion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getLocall() {
        return locall;
    }

    public void setLocall(String locall) {
        this.locall = locall;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getCorreo_empresa() {
        return correo_empresa;
    }

    public void setCorreo_empresa(String correo_empresa) {
        this.correo_empresa = correo_empresa;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public String getPrimer_nombre_representante() {
        return primer_nombre_representante;
    }

    public void setPrimer_nombre_representante(String primer_nombre_representante) {
        this.primer_nombre_representante = primer_nombre_representante;
    }

    public String getSegundo_nombre_representante() {
        return segundo_nombre_representante;
    }

    public void setSegundo_nombre_representante(String segundo_nombre_representante) {
        this.segundo_nombre_representante = segundo_nombre_representante;
    }

    public String getApellido_paterno_representante() {
        return apellido_paterno_representante;
    }

    public void setApellido_paterno_representante(String apellido_paterno_representante) {
        this.apellido_paterno_representante = apellido_paterno_representante;
    }

    public String getApellido_materno_representante() {
        return apellido_materno_representante;
    }

    public void setApellido_materno_representante(String apellido_materno_representante) {
        this.apellido_materno_representante = apellido_materno_representante;
    }

    public String getCedula_representante() {
        return cedula_representante;
    }

    public void setCedula_representante(String cedula_representante) {
        this.cedula_representante = cedula_representante;
    }

    public String getDv_representante() {
        return dv_representante;
    }

    public void setDv_representante(String dv_representante) {
        this.dv_representante = dv_representante;
    }

    public String getTelefono1_representante() {
        return telefono1_representante;
    }

    public void setTelefono1_representante(String telefono1_representante) {
        this.telefono1_representante = telefono1_representante;
    }

    public String getTelefono2_representante() {
        return telefono2_representante;
    }

    public void setTelefono2_representante(String telefono2_representante) {
        this.telefono2_representante = telefono2_representante;
    }

    public String getCorreo_representante() {
        return correo_representante;
    }

    public void setCorreo_representante(String correo_representante) {
        this.correo_representante = correo_representante;
    }

    public String getNombre_gerente() {
        return nombre_gerente;
    }

    public void setNombre_gerente(String nombre_gerente) {
        this.nombre_gerente = nombre_gerente;
    }

    public String getCedula_gerente() {
        return cedula_gerente;
    }

    public void setCedula_gerente(String cedula_gerente) {
        this.cedula_gerente = cedula_gerente;
    }

    public String getDv_gerente() {
        return dv_gerente;
    }

    public void setDv_gerente(String dv_gerente) {
        this.dv_gerente = dv_gerente;
    }

    public String getTelefono1_gerente() {
        return telefono1_gerente;
    }

    public void setTelefono1_gerente(String telefono1_gerente) {
        this.telefono1_gerente = telefono1_gerente;
    }

    public String getTelefono2_gerente() {
        return telefono2_gerente;
    }

    public void setTelefono2_gerente(String telefono2_gerente) {
        this.telefono2_gerente = telefono2_gerente;
    }

    public String getCorreo_gerente() {
        return correo_gerente;
    }

    public void setCorreo_gerente(String correo_gerente) {
        this.correo_gerente = correo_gerente;
    }

    public byte[] getLogo_factura() {
        return logo_factura;
    }

    public void setLogo_factura(byte[] logo_empresa) {
        this.logo_factura = logo_factura;
    }

    public byte[] getImagenEmpresa() {
        return imagenEmpresa;
    }

    public void setImagenEmpresa(byte[] imagenEmpresa) {
        this.imagenEmpresa = imagenEmpresa;
    }

    public Date getFecha_modificacion() {
        return fecha_modificacion;
    }

    public void setFecha_modificacion(Date fecha_modificacion) {
        this.fecha_modificacion = fecha_modificacion;
    }

    public Date getFecha_eliminacion() {
        return fecha_eliminacion;
    }

    public void setFecha_eliminacion(Date fecha_eliminacion) {
        this.fecha_eliminacion = fecha_eliminacion;
    }

    public void insertarEnBaseDeDatos(Connection conexion) {
        System.out.println("Imagen empresa: "+imagenEmpresa);
            System.out.println("Logo empresa: "+logo_factura);
        String query = "INSERT INTO empresa (nombre_empresa, nombre_comercial, dv, ruc, pais, provincia, distrito, "
                + "corregimiento, urbanizacion, calle, locall, piso, correo_empresa, fax, telefono1, telefono2, "
                + "primer_nombre_representante, segundo_nombre_representante, apellido_paterno_representante, "
                + "apellido_materno_representante, cedula_representante, dv_representante, telefono1_representante, "
                + "telefono2_representante, correo_representante, nombre_gerente, cedula_gerente, dv_gerente, "
                + "telefono1_gerente, telefono2_gerente, correo_gerente, logo_factura, imagenEmpresa, fecha_modificacion, "
                + "fecha_eliminacion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, nombre_empresa);
            statement.setString(2, nombre_comercial);
            statement.setString(3, dv);
            statement.setString(4, ruc);
            statement.setString(5, pais);
            statement.setString(6, provincia);
            statement.setString(7, distrito);
            statement.setString(8, corregimiento);
            statement.setString(9, urbanizacion);
            statement.setString(10, calle);
            statement.setString(11, locall);
            statement.setString(12, piso);
            statement.setString(13, correo_empresa);
            statement.setString(14, fax);
            statement.setString(15, telefono1);
            statement.setString(16, telefono2);
            statement.setString(17, primer_nombre_representante);
            statement.setString(18, segundo_nombre_representante);
            statement.setString(19, apellido_paterno_representante);
            statement.setString(20, apellido_materno_representante);
            statement.setString(21, cedula_representante);
            statement.setString(22, dv_representante);
            statement.setString(23, telefono1_representante);
            statement.setString(24, telefono2_representante);
            statement.setString(25, correo_representante);
            statement.setString(26, nombre_gerente);
            statement.setString(27, cedula_gerente);
            statement.setString(28, dv_gerente);
            statement.setString(29, telefono1_gerente);
            statement.setString(30, telefono2_gerente);
            statement.setString(31, correo_gerente);

            statement.setBytes(32, logo_factura);
            statement.setBytes(33, imagenEmpresa);

            // Convierte java.util.Date a java.sql.Date antes de establecerlo en la consulta
            statement.setDate(34, new java.sql.Date(fecha_modificacion.getTime()));
            statement.setDate(35, new java.sql.Date(fecha_eliminacion.getTime()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
