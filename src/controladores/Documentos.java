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
import java.text.DecimalFormat;
/**
 *
 * @author dbpan
 */
public class Documentos {
    private int IDfactura;
    private String Tipodocumento;
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
    private double SumaCantidad;
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

    public Documentos(int IDfactura,String Tipodocumento, int Codigocliente, String Nombre, String RUC, double DescGen, String Direccion, String Telefono1, String Telefono2, String Referencia, double Credito, String Codigoproducto, String Codigoservicio, String Nombreproducto, String Nombreservicio, double Cantidad, String Magnitud, double PrecioProducto, double DescLinea, double Impuestos, double ImporteImpuesto, double Base, double Subtotal1, double MontoPrecio,double SumaCantidad, double SumaDescLinea, double SumaDescGen, double Subtotal2, double SumaImpuesto, double Total, double DIF, String FormaPago1, double MontoPago1, String FormaPago2, double MontoPago2, String FormaPago3, double MontoPago3, String FormaPago4, double MontoPago4) {
        this.IDfactura = IDfactura;
        this.Tipodocumento = Tipodocumento;
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
        this.SumaCantidad = SumaCantidad;
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

    public String getTipodocumento() {
        return Tipodocumento;
    }

    public void setTipodocumento(String Tipodocumento) {
        this.Tipodocumento = Tipodocumento;
    }

    public int getIDfactura() {
        return this.IDfactura;
    }

    public void setIDfactura(int IDfactura) {
        this.IDfactura = IDfactura;
    }

    public int getCodigocliente() {
        return this.Codigocliente;
    }

    public void setCodigocliente(int Codigocliente) {
        this.Codigocliente = Codigocliente;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getRUC() {
        return this.RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public double getDescGen() {     
        return this.DescGen;
    }

    public void setDescGen(double DescGen) {
        this.DescGen = DescGen;
    }

    public String getDireccion() {
        return this.Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono1() {
        return this.Telefono1;
    }

    public void setTelefono1(String Telefono1) {
        this.Telefono1 = Telefono1;
    }

    public String getTelefono2() {
        return this.Telefono2;
    }

    public void setTelefono2(String Telefono2) {
        this.Telefono2 = Telefono2;
    }

    public String getReferencia() {
        return this.Referencia;
    }

    public void setReferencia(String Referencia) {
        this.Referencia = Referencia;
    }

    public double getCredito() {
        return this.Credito;
    }

    public void setCredito(double Credito) {
        this.Credito = Credito;
    }

    public String getCodigoproducto() {
        return this.Codigoproducto;
    }

    public void setCodigoproducto(String Codigoproducto) {
        this.Codigoproducto = Codigoproducto;
    }

    public String getCodigoservicio() {
        return this.Codigoservicio;
    }

    public void setCodigoservicio(String Codigoservicio) {
        this.Codigoservicio = Codigoservicio;
    }

    public String getNombreproducto() {
        return this.Nombreproducto;
    }

    public void setNombreproducto(String Nombreproducto) {
        this.Nombreproducto = Nombreproducto;
    }

    public String getNombreservicio() {
        return this.Nombreservicio;
    }

    public void setNombreservicio(String Nombreservicio) {
        this.Nombreservicio = Nombreservicio;
    }

    public double getCantidad() {
        return this.Cantidad;
    }

    public void setCantidad(double Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getMagnitud() {
        return this.Magnitud;
    }

    public void setMagnitud(String Magnitud) {
        this.Magnitud = Magnitud;
    }

    public double getPrecioProducto() {
        return this.PrecioProducto;
    }

    public void setPrecioProducto(double PrecioProducto) {
        this.PrecioProducto = PrecioProducto;
    }

    public double getDescLinea() {
        return this.DescLinea;
    }

    public void setDescLinea(double DescLinea) {
        this.DescLinea = DescLinea;
    }

    public double getImpuestos() {
        return this.Impuestos;
    }

    public void setImpuestos(double Impuestos) {
        this.Impuestos = Impuestos;
    }

    public double getImporteImpuesto() {
        return this.ImporteImpuesto;
    }

    public void setImporteImpuesto(double ImporteImpuesto) {
        this.ImporteImpuesto = ImporteImpuesto;
    }

    public double getBase() {
        return this.Base;
    }

    public void setBase(double Base) {
        this.Base = Base;
    }

    public double getSubtotal1() {
        return this.Subtotal1;
    }

    public void setSubtotal1(double Subtotal1) {
        this.Subtotal1 = Subtotal1;
    }

    public double getSumaCantidad() {
        return SumaCantidad;
    }

    public void setSumaCantidad(double SumaCantidad) {
        this.SumaCantidad = SumaCantidad;
    }

    public double getMontoPrecio() {
        return this.MontoPrecio;
    }

    public void setMontoPrecio(double MontoPrecio) {
        this.MontoPrecio = MontoPrecio;
    }

    public double getSumaDescLinea() {
        return this.SumaDescLinea;
    }

    public void setSumaDescLinea(double SumaDescLinea) {
        this.SumaDescLinea = SumaDescLinea;
    }

    public double getSumaDescGen() {
        return this.SumaDescGen;
    }

    public void setSumaDescGen(double SumaDescGen) {
        this.SumaDescGen = SumaDescGen;
    }

    public double getSubtotal2() {
        this.Subtotal2= this.MontoPrecio-(this.SumaDescLinea+this.SumaDescGen);
        return this.Subtotal2;
    }

    public void setSubtotal2(double Subtotal2) {
        this.Subtotal2 = Subtotal2;
    }

    public double getSumaImpuesto() {
        return this.SumaImpuesto;
    }

    public void setSumaImpuesto(double SumaImpuesto) {
        this.SumaImpuesto = SumaImpuesto;
    }

    public double getTotal() {
        this.Total= this.Subtotal2+this.SumaImpuesto;
        return this.Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public double getDIF() {
        this.DIF = this.Total - (this.MontoPago1+ this.MontoPago2+ this.MontoPago3+ this.MontoPago4);
        return this.DIF;
    }

    public void setDIF(double DIF) {
        this.DIF = DIF;
    }

    public String getFormaPago1() {
        return this.FormaPago1;
    }

    public void setFormaPago1(String FormaPago1) {
        this.FormaPago1 = FormaPago1;
    }

    public double getMontoPago1() {
        return this.MontoPago1;
    }

    public void setMontoPago1(double MontoPago1) {
        this.MontoPago1 = MontoPago1;
    }

    public String getFormaPago2() {
        return this.FormaPago2;
    }

    public void setFormaPago2(String FormaPago2) {
        this.FormaPago2 = FormaPago2;
    }

    public double getMontoPago2() {
        return this.MontoPago2;
    }

    public void setMontoPago2(double MontoPago2) {
        this.MontoPago2 = MontoPago2;
    }

    public String getFormaPago3() {
        return this.FormaPago3;
    }

    public void setFormaPago3(String FormaPago3) {
        this.FormaPago3 = FormaPago3;
    }

    public double getMontoPago3() {
        return this.MontoPago3;
    }

    public void setMontoPago3(double MontoPago3) {
        this.MontoPago3 = MontoPago3;
    }

    public String getFormaPago4() {
        return this.FormaPago4;
    }

    public void setFormaPago4(String FormaPago4) {
        this.FormaPago4 = FormaPago4;
    }

    public double getMontoPago4() {
        return this.MontoPago4;
    }

    public void setMontoPago4(double MontoPago4) {
        this.MontoPago4 = MontoPago4;
    }

    
    //metodo insert de datos de factura
    public int insertDocumentos(Connection conexion, Documentos documentos) {
        String query = "INSERT INTO tbl_documentos (id,tipo_documento,codigo_cliente,nombre,ruc,descuento_general,direccion,telefono1,telefono2,referencia,credito,codigo_producto,codigo_servicio,nombre_producto,nombre_servicio,cantidad,magnitud,precio_unitario,descuento_linea,itbms,importeimpuesto,base,subtotal1,montoprecio,suma_cantidad,suma_descuentolinea,suma_descuentogeneral,subtotal2,impuesto_total,total,diferencia,forma_pago1,monto_pago1,forma_pago2,monto_pago2,forma_pago3,monto_pago3,,forma_pago4,monto_pago4) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, CURRENT_TIMESTAMP)";

        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, documentos.getIDfactura());
            statement.setString(2, documentos.getTipodocumento());
            statement.setInt(3, documentos.getCodigocliente());
            statement.setString(4, documentos.getNombre());
            statement.setString(5, documentos.getRUC());
            statement.setDouble(6, documentos.getDescGen());
            statement.setString(7, documentos.getDireccion());
            statement.setString(8, documentos.getTelefono1());
            statement.setString(9, documentos.getTelefono2());
            statement.setString(10, documentos.getReferencia());
            statement.setDouble(11, documentos.getCredito());
            statement.setString(12, documentos.getCodigoproducto());
            statement.setString(13, documentos.getCodigoservicio());
            statement.setString(14, documentos.getNombreproducto());
            statement.setString(15, documentos.getNombreservicio());
            statement.setDouble(16, documentos.getCantidad());
            statement.setString(17, documentos.getMagnitud());
            statement.setDouble(18, documentos.getPrecioProducto());
            statement.setDouble(19, documentos.getDescLinea());
            statement.setDouble(20, documentos.getImpuestos());
            statement.setDouble(21, documentos.getImporteImpuesto());
            statement.setDouble(22, documentos.getBase());
            statement.setDouble(23, documentos.getSubtotal1());
            statement.setDouble(24, documentos.getMontoPrecio());
            statement.setDouble(25, documentos.getSumaCantidad());
            statement.setDouble(26, documentos.getSumaDescLinea());
            statement.setDouble(27, documentos.getSumaDescGen());
            statement.setDouble(28, documentos.getSubtotal2());
            statement.setDouble(29, documentos.getSumaImpuesto());
            statement.setDouble(30, documentos.getTotal());
            statement.setDouble(31, documentos.getDIF());
            statement.setString(32, documentos.getFormaPago1());
            statement.setDouble(33, documentos.getMontoPago1());
            statement.setString(34, documentos.getFormaPago2());
            statement.setDouble(35, documentos.getMontoPago2());
            statement.setString(36, documentos.getFormaPago3());
            statement.setDouble(37, documentos.getMontoPago3());
            statement.setString(38, documentos.getFormaPago4());
            statement.setDouble(39, documentos.getMontoPago4());
            

            int filasAfectadas = statement.executeUpdate();

            // Retorna la cantidad de filas afectadas por la inserción.
            return filasAfectadas;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }
    
    //Calculos de la tabla productos
    
    //metodo calculo descuentogeneral
    public double  CalcularDescuentoGen(double PrecioProducto,double DescGen){
        if (DescGen >0){
          this.DescGen = DescGen/100;
        }
        this.DescGen = this.DescGen*PrecioProducto;
        return this.DescGen;
    }
    
    
    //metodo calculo descuentogeneral
    public double  CalcularDescuentoLinea(double PrecioProducto,double DescLinea){
        if (DescLinea >0){
          this.DescLinea = this.DescLinea/100;
        }
        this.DescLinea = this.DescLinea*PrecioProducto;
        return this.DescLinea;
    }
            
    //metodo calculo base
    //Base= la suma de los precios sin impuesto de un mismo Articulo/servicio y con descuento si este tiene 
    public double  CalcularBase (double PrecioProducto,double Cantidad,double DescLinea,double DescGen){
        if (DescLinea >0){
          DescLinea = DescLinea/100;
        }
        if (DescGen >0){
          DescGen = DescGen/100;
        }
        DescGen = DescGen*PrecioProducto;
        DescLinea = DescLinea*PrecioProducto;
        double preciosindesc = PrecioProducto * Cantidad;
        Base = preciosindesc - (DescLinea+DescGen);  
        return Base;
    }
    
    //metodo calculo ibtms
    //itbms = impuesto aplicado a un Articulo/servicio en especifico
    public double  CalcularItbms(double Impuestos){
        Impuestos = Impuestos/100;
        return Impuestos;
    }
    
    //metodo calculo ImporteImpuesto
    //ImporteImpuesto= ibtmsxPrecio de Articulo/servicio
    public double  CalcularImporteImpuesto(double PrecioProducto,double Impuestos,double Cantidad){
        Impuestos = Impuestos/100;
        ImporteImpuesto= PrecioProducto*Impuestos;
        ImporteImpuesto= ImporteImpuesto*Cantidad;
        return ImporteImpuesto;
    }
    
    //metodo calculo subtotal
    //Subtotal1= SubTotal: La suma total de precio de Articulo/servicio con descuento aplicado + ibtms de cada Articulo/servicio 
    public double  CalcularSubtotal(double base,double ImporteImpuesto){
        Subtotal1= Base+ImporteImpuesto;
        return Subtotal1;
    }
    
    //// Métodos para restar valores específicos
    public void restarSumaCantidad(double cantidadRemovida) {
        this.SumaCantidad -= cantidadRemovida;
    }

    public void restarMontoPrecio(double precioRemovido) {
        this.MontoPrecio -= precioRemovido;
    }

    public void restarSumaDescLinea(double descLineaRemovido) {
        this.SumaDescLinea -= descLineaRemovido;
    }

    public void restarSumaDescGen(double descGeneralRemovido) {
        this.SumaDescGen -= descGeneralRemovido;
    }

    public void restarSumaImpuesto(double impuestoRemovido) {
        this.SumaImpuesto -= impuestoRemovido;
    }
    
}
