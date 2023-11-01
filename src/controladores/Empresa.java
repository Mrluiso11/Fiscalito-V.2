package controladores;

import java.util.Date;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Empresa {

    private String nombre;
    private String nombre_comercial;
    private String dv;
    private String ruc;
    private String pais;
    private String provincia;
    private String distrito;
    private String corregimiento;
    private String urbanizacion;
    private String calle;
    private String casa;
    private String local;
    private String piso;
    private String correo_empresa;
    private String actividades; // Nueva variable para actividades
    private String observaciones; // Nueva variable para observaciones
    private String telefono1;
    private String telefono2;
    private String fax1;
    private String fax2;
    private String primer_nombre_representante;
    private String segundo_nombre_representante;
    private String apellido_paterno;
    private String apellido_materno;
    private String cedula;
    private String dv_representante;
    private String telefono1_representante;
    private String telefono2_representante;
    private String correo_representante;
    private String nombre_gerente;
    private String cedula_gerente;
    private String dv_gerente;
    private String telefono_gerente1;
    private String telefono_gerente2;
    private String correo_gerente;
    private String otros;
    private byte[] logo_factura;
    private byte[] logo_empresa;
    private Date fecha_actualizacion;

    public Empresa(
            String nombre,
            String nombre_comercial,
            String dv,
            String ruc,
            String pais,
            String provincia,
            String distrito,
            String corregimiento,
            String urbanizacion,
            String calle,
            String casa,
            String local,
            String piso,
            String correo_empresa,
            String actividades,
            String observaciones,
            String telefono1,
            String telefono2,
            String fax1,
            String fax2,
            String primer_nombre_representante,
            String segundo_nombre_representante,
            String apellido_paterno,
            String apellido_materno,
            String cedula,
            String dv_representante,
            String telefono1_representante,
            String telefono2_representante,
            String correo_representante,
            String nombre_gerente,
            String cedula_gerente,
            String dv_gerente,
            String telefono_gerente1,
            String telefono_gerente2,
            String correo_gerente,
            String otros,
            byte[] logo_factura,
            byte[] logo_empresa,
            Date fecha_actualizacion) {

        this.nombre = nombre;
        this.nombre_comercial = nombre_comercial;
        this.dv = dv;
        this.ruc = ruc;
        this.pais = pais;
        this.provincia = provincia;
        this.distrito = distrito;
        this.corregimiento = corregimiento;
        this.urbanizacion = urbanizacion;
        this.calle = calle;
        this.casa = casa;
        this.local = local;
        this.piso = piso;
        this.correo_empresa = correo_empresa;
        this.actividades = actividades; // Asignar el valor de actividades
        this.observaciones = observaciones; // Asignar el valor de observaciones
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.fax1 = fax1;
        this.fax2 = fax2;
        this.primer_nombre_representante = primer_nombre_representante;
        this.segundo_nombre_representante = segundo_nombre_representante;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.cedula = cedula;
        this.dv_representante = dv_representante;
        this.telefono1_representante = telefono1_representante;
        this.telefono2_representante = telefono2_representante;
        this.correo_representante = correo_representante;
        this.nombre_gerente = nombre_gerente;
        this.cedula_gerente = cedula_gerente;
        this.dv_gerente = dv_gerente;
        this.telefono_gerente1 = telefono_gerente1;
        this.telefono_gerente2 = telefono_gerente2;
        this.correo_gerente = correo_gerente;
        this.otros = otros;
        this.logo_factura = logo_factura;
        this.logo_empresa = logo_empresa;
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public Empresa() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getCasa() {
        return casa;
    }

    public void setCasa(String casa) {
        this.casa = casa;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
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

    public String getActividades() {
        return actividades;
    }

    public void setActividades(String actividades) {
        this.actividades = actividades;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public String getFax1() {
        return fax1;
    }

    public void setFax1(String fax1) {
        this.fax1 = fax1;
    }

    public String getFax2() {
        return fax2;
    }

    public void setFax2(String fax2) {
        this.fax2 = fax2;
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

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getTelefono_gerente1() {
        return telefono_gerente1;
    }

    public void setTelefono_gerente1(String telefono_gerente1) {
        this.telefono_gerente1 = telefono_gerente1;
    }

    public String getTelefono_gerente2() {
        return telefono_gerente2;
    }

    public void setTelefono_gerente2(String telefono_gerente2) {
        this.telefono_gerente2 = telefono_gerente2;
    }

    public String getCorreo_gerente() {
        return correo_gerente;
    }

    public void setCorreo_gerente(String correo_gerente) {
        this.correo_gerente = correo_gerente;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    public byte[] getLogo_factura() {
        return logo_factura;
    }

    public void setLogo_factura(byte[] logo_factura) {
        this.logo_factura = logo_factura;
    }

    public byte[] getLogo_empresa() {
        return logo_empresa;
    }

    public void setLogo_empresa(byte[] logo_empresa) {
        this.logo_empresa = logo_empresa;
    }

    public Date getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(Date fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }

    public boolean insertEmpresa(Connection connection) {
        boolean insercionExitosa = false;
        System.out.println("Logo factura "+logo_factura);
        System.out.println("Logo empresa "+logo_empresa);    
             
        String query = "INSERT INTO tbl_empresa (ruc, nombre, nombre_comercial, dv, pais, provincia, distrito, "
                + "corregimiento, urbanizacion, calle, casa, piso, local, correo_empresa, actividades, observaciones, telefono1, telefono2, fax1, fax2, "
                + "primer_nombre_representante, segundo_nombre_representante, apellido_paterno, "
                + "apellido_materno, cedula, dv_representante, telefono1_representante, "
                + "telefono2_representante, correo_representante, nombre_gerente, cedula_gerente, dv_gerente, "
                + "telefono_gerente1, telefono_gerente2, correo_gerente, otros, logo_factura, logo_empresa) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ruc);
            statement.setString(2, nombre);
            statement.setString(3, nombre_comercial);
            statement.setString(4, dv);
            statement.setString(5, pais);
            statement.setString(6, provincia);
            statement.setString(7, distrito);
            statement.setString(8, corregimiento);
            statement.setString(9, urbanizacion);
            statement.setString(10, calle);
            statement.setString(11, casa);
            statement.setString(12, piso);
            statement.setString(13, local);
            statement.setString(14, correo_empresa);
            statement.setString(15, actividades);
            statement.setString(16, observaciones);
            statement.setString(17, telefono1);
            statement.setString(18, telefono2);
            statement.setString(19, fax1);
            statement.setString(20, fax2);
            statement.setString(21, primer_nombre_representante);
            statement.setString(22, segundo_nombre_representante);
            statement.setString(23, apellido_paterno);
            statement.setString(24, apellido_materno);
            statement.setString(25, cedula);
            statement.setString(26, dv_representante);
            statement.setString(27, telefono1_representante);
            statement.setString(28, telefono2_representante);
            statement.setString(29, correo_representante);
            statement.setString(30, nombre_gerente);
            statement.setString(31, cedula_gerente);
            statement.setString(32, dv_gerente);
            statement.setString(33, telefono_gerente1);
            statement.setString(34, telefono_gerente2);
            statement.setString(35, correo_gerente);
            statement.setString(36, otros);
            statement.setBytes(37, logo_factura);
            statement.setBytes(38, logo_empresa);
            // ... (Establece los valores de los parámetros restantes)

            // Ejecuta la inserción
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                insercionExitosa = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return insercionExitosa;
    }

}
