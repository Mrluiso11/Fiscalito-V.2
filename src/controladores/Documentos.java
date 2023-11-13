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
public class Documentos {
    private int IDfactura;
    private int Codigocliente;
    private String Nombre;
    private String RUC;
    private double DescGen;
    private String Direccion;
    private String Telefono1;
    private String Telefono2;
    private String Referencia;
    private double Credito;
    private String Codigoproducto;
    private String Codigoservicio;
    private String Nombreproducto;
    private String Nombreservicio;
    private double Cantidad;
    private String Magnitud;
    private double PrecioProducto;
    private double DescLinea;
    private double Impuestos;
    private double ImporteImpuesto;
    private double Base;
    private double Subtotal1;
    private double MontoPrecio;
    private double SumaDescLinea;
    private double SumaDescGen;
    private double Subtotal2;
    private double SumaImpuesto;
    private double Total;
    private double DIF;
    private String FormaPago1;
    private double MontoPago1;
    private String FormaPago2;
    private double MontoPago2;
    private String FormaPago3;
    private double MontoPago3;
    private String FormaPago4;
    private double MontoPago4;
    
    public Documentos(){
    }

    public Documentos(int IDfactura, int Codigocliente, String Nombre, String RUC, double DescGen, String Direccion, String Telefono1, String Telefono2, String Referencia, double Credito, String Codigoproducto, String Codigoservicio, String Nombreproducto, String Nombreservicio, double Cantidad, String Magnitud, double PrecioProducto, double DescLinea, double Impuestos, double ImporteImpuesto, double Base, double Subtotal1, double MontoPrecio, double SumaDescLinea, double SumaDescGen, double Subtotal2, double SumaImpuesto, double Total, double DIF, String FormaPago1, double MontoPago1, String FormaPago2, double MontoPago2, String FormaPago3, double MontoPago3, String FormaPago4, double MontoPago4) {
        this.IDfactura = IDfactura;
        this.Codigocliente = Codigocliente;
        this.Nombre = Nombre;
        this.RUC = RUC;
        this.DescGen = DescGen;
        this.Direccion = Direccion;
        this.Telefono1 = Telefono1;
        this.Telefono2 = Telefono2;
        this.Referencia = Referencia;
        this.Credito = Credito;
        this.Codigoproducto = Codigoproducto;
        this.Codigoservicio = Codigoservicio;
        this.Nombreproducto = Nombreproducto;
        this.Nombreservicio = Nombreservicio;
        this.Cantidad = Cantidad;
        this.Magnitud = Magnitud;
        this.PrecioProducto = PrecioProducto;
        this.DescLinea = DescLinea;
        this.Impuestos = Impuestos;
        this.ImporteImpuesto = ImporteImpuesto;
        this.Base = Base;
        this.Subtotal1 = Subtotal1;
        this.MontoPrecio = MontoPrecio;
        this.SumaDescLinea = SumaDescLinea;
        this.SumaDescGen = SumaDescGen;
        this.Subtotal2 = Subtotal2;
        this.SumaImpuesto = SumaImpuesto;
        this.Total = Total;
        this.DIF = DIF;
        this.FormaPago1 = FormaPago1;
        this.MontoPago1 = MontoPago1;
        this.FormaPago2 = FormaPago2;
        this.MontoPago2 = MontoPago2;
        this.FormaPago3 = FormaPago3;
        this.MontoPago3 = MontoPago3;
        this.FormaPago4 = FormaPago4;
        this.MontoPago4 = MontoPago4;
    }


    public int getIDfactura() {
        return IDfactura;
    }

    public void setIDfactura(int IDfactura) {
        this.IDfactura = IDfactura;
    }

    public int getCodigocliente() {
        return Codigocliente;
    }

    public void setCodigocliente(int Codigocliente) {
        this.Codigocliente = Codigocliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public double getDescGen() {
        return DescGen;
    }

    public void setDescGen(double DescGen) {
        this.DescGen = DescGen;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono1() {
        return Telefono1;
    }

    public void setTelefono1(String Telefono1) {
        this.Telefono1 = Telefono1;
    }

    public String getTelefono2() {
        return Telefono2;
    }

    public void setTelefono2(String Telefono2) {
        this.Telefono2 = Telefono2;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String Referencia) {
        this.Referencia = Referencia;
    }

    public double getCredito() {
        return Credito;
    }

    public void setCredito(double Credito) {
        this.Credito = Credito;
    }

    public String getCodigoproducto() {
        return Codigoproducto;
    }

    public void setCodigoproducto(String Codigoproducto) {
        this.Codigoproducto = Codigoproducto;
    }

    public String getCodigoservicio() {
        return Codigoservicio;
    }

    public void setCodigoservicio(String Codigoservicio) {
        this.Codigoservicio = Codigoservicio;
    }

    public String getNombreproducto() {
        return Nombreproducto;
    }

    public void setNombreproducto(String Nombreproducto) {
        this.Nombreproducto = Nombreproducto;
    }

    public String getNombreservicio() {
        return Nombreservicio;
    }

    public void setNombreservicio(String Nombreservicio) {
        this.Nombreservicio = Nombreservicio;
    }

    public double getCantidad() {
        return Cantidad;
    }

    public void setCantidad(double Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getMagnitud() {
        return Magnitud;
    }

    public void setMagnitud(String Magnitud) {
        this.Magnitud = Magnitud;
    }

    public double getPrecioProducto() {
        return PrecioProducto;
    }

    public void setPrecioProducto(double PrecioProducto) {
        this.PrecioProducto = PrecioProducto;
    }

    public double getDescLinea() {
        return DescLinea;
    }

    public void setDescLinea(double DescLinea) {
        this.DescLinea = DescLinea;
    }

    public double getImpuestos() {
        return Impuestos;
    }

    public void setImpuestos(double Impuestos) {
        this.Impuestos = Impuestos;
    }

    public double getImporteImpuesto() {
        return ImporteImpuesto;
    }

    public void setImporteImpuesto(double ImporteImpuesto) {
        this.ImporteImpuesto = ImporteImpuesto;
    }

    public double getBase() {
        return Base;
    }

    public void setBase(double Base) {
        this.Base = Base;
    }

    public double getSubtotal1() {
        return Subtotal1;
    }

    public void setSubtotal1(double Subtotal1) {
        this.Subtotal1 = Subtotal1;
    }

    public double getMontoPrecio() {
        return MontoPrecio;
    }

    public void setMontoPrecio(double MontoPrecio) {
        this.MontoPrecio = MontoPrecio;
    }

    public double getSumaDescLinea() {
        return SumaDescLinea;
    }

    public void setSumaDescLinea(double SumaDescLinea) {
        this.SumaDescLinea = SumaDescLinea;
    }

    public double getSumaDescGen() {
        return SumaDescGen;
    }

    public void setSumaDescGen(double SumaDescGen) {
        this.SumaDescGen = SumaDescGen;
    }

    public double getSubtotal2() {
        return Subtotal2;
    }

    public void setSubtotal2(double Subtotal2) {
        this.Subtotal2 = Subtotal2;
    }

    public double getSumaImpuesto() {
        return SumaImpuesto;
    }

    public void setSumaImpuesto(double SumaImpuesto) {
        this.SumaImpuesto = SumaImpuesto;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public double getDIF() {
        return DIF;
    }

    public void setDIF(double DIF) {
        this.DIF = DIF;
    }

    public String getFormaPago1() {
        return FormaPago1;
    }

    public void setFormaPago1(String FormaPago1) {
        this.FormaPago1 = FormaPago1;
    }

    public double getMontoPago1() {
        return MontoPago1;
    }

    public void setMontoPago1(double MontoPago1) {
        this.MontoPago1 = MontoPago1;
    }

    public String getFormaPago2() {
        return FormaPago2;
    }

    public void setFormaPago2(String FormaPago2) {
        this.FormaPago2 = FormaPago2;
    }

    public double getMontoPago2() {
        return MontoPago2;
    }

    public void setMontoPago2(double MontoPago2) {
        this.MontoPago2 = MontoPago2;
    }

    public String getFormaPago3() {
        return FormaPago3;
    }

    public void setFormaPago3(String FormaPago3) {
        this.FormaPago3 = FormaPago3;
    }

    public double getMontoPago3() {
        return MontoPago3;
    }

    public void setMontoPago3(double MontoPago3) {
        this.MontoPago3 = MontoPago3;
    }

    public String getFormaPago4() {
        return FormaPago4;
    }

    public void setFormaPago4(String FormaPago4) {
        this.FormaPago4 = FormaPago4;
    }

    public double getMontoPago4() {
        return MontoPago4;
    }

    public void setMontoPago4(double MontoPago4) {
        this.MontoPago4 = MontoPago4;
    }

    
    //metodo insert de datos de factura
    public int insertDocumentos(Connection conexion, Documentos documentos) {
        String query = "INSERT INTO tbl_documentos (id,codigo_cliente,nombre,ruc,descuento_general,direccion,telefono1,telefono2,referencia,credito,codigo_producto,codigo_servicio,nombre_producto,nombre_servicio,cantidad,magnitud,precio_unitario,descuento_linea,itbms,importeimpuesto,base,subtotal1,montoprecio,suma_descuentolinea,suma_descuentogeneral,subtotal2,impuesto_total,total,diferencia,forma_pago1,monto_pago1,forma_pago2,monto_pago2,forma_pago3,monto_pago3,,forma_pago4,monto_pago4) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, CURRENT_TIMESTAMP)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, documentos.getIDfactura());
            statement.setInt(2, documentos.getCodigocliente());
            statement.setString(3, documentos.getNombre());
            statement.setString(4, documentos.getRUC());
            statement.setDouble(5, documentos.getDescGen());
            statement.setString(6, documentos.getDireccion());
            statement.setString(7, documentos.getTelefono1());
            statement.setString(8, documentos.getTelefono2());
            statement.setString(9, documentos.getReferencia());
            statement.setDouble(10, documentos.getCredito());
            statement.setString(11, documentos.getCodigoproducto());
            statement.setString(12, documentos.getCodigoservicio());
            statement.setString(13, documentos.getNombreproducto());
            statement.setString(14, documentos.getNombreservicio());
            statement.setDouble(15, documentos.getCantidad());
            statement.setString(16, documentos.getMagnitud());
            statement.setDouble(17, documentos.getPrecioProducto());
            statement.setDouble(18, documentos.getDescLinea());
            statement.setDouble(19, documentos.getImpuestos());
            statement.setDouble(20, documentos.getImporteImpuesto());
            statement.setDouble(21, documentos.getBase());
            statement.setDouble(22, documentos.getSubtotal1());
            statement.setDouble(23, documentos.getMontoPrecio());
            statement.setDouble(24, documentos.getSumaDescLinea());
            statement.setDouble(25, documentos.getSumaDescGen());
            statement.setDouble(26, documentos.getSubtotal2());
            statement.setDouble(27, documentos.getSumaImpuesto());
            statement.setDouble(28, documentos.getTotal());
            statement.setDouble(29, documentos.getDIF());
            statement.setString(30, documentos.getFormaPago1());
            statement.setDouble(31, documentos.getMontoPago1());
            statement.setString(32, documentos.getFormaPago2());
            statement.setDouble(33, documentos.getMontoPago2());
            statement.setString(34, documentos.getFormaPago3());
            statement.setDouble(35, documentos.getMontoPago3());
            statement.setString(36, documentos.getFormaPago4());
            statement.setDouble(37, documentos.getMontoPago4());
            

            int filasAfectadas = statement.executeUpdate();

            // Retorna la cantidad de filas afectadas por la inserción.
            return filasAfectadas;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }
    
    
    
    
}
