/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vistas.form;

import Style.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import Style.Forms;
import com.toedter.calendar.JDateChooser;
import conexion.Conexion;
import java.sql.Connection;
import controladores.*;
import documentgeneration.FacturaPDF;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 *
 * @author dbpan
 */
public class frmDocumentos extends javax.swing.JPanel {

    /**
     * Creates new form frmDocumentos
     */
    private int contadorID = 1;
    DecimalFormat formato = new DecimalFormat("#0.00");
    DecimalFormat formatoDecimal = new DecimalFormat("#.##");

    public frmDocumentos() {
        initComponents(); // Inicializa los componentes del formulario.
        // Crea una instancia de Forms y la asocia con este formulario.
        Forms formsPanel = new Forms(this, null);

        // Aplica estilos a la JTable llamada "table" utilizando TableStyler.
        applyTableStyles(TableDocumentos, jScrollPane1);
        applyTableStyles(TableFacturas, jScrollPane2);
        TableFacturas.setShowVerticalLines(true);
        jTabbedPane1.setUI(new CustomTabbedPaneUI());

        Timer timer = new Timer(1000, e -> actualizarFecha());
        timer.start();
        generarID();
        ObtenerNombreCliente();
        ObtenerNombreServicio();
        ObtenerNombreProducto();
        //Descuentos
        txtDescLinea.setText(String.valueOf(0.00));
        txtDescGeneral.setText(String.valueOf(0.00));
        txtDescLinea.setEnabled(false);
        txtReferencia.setEnabled(false);
        double sumaCantidad = 0.0;

    }
    // Método para aplicar estilos adicionales a la tabla contenida en un JScrollPane.

    private void applyTableStyles(JTable table, JScrollPane scrollPane) {
        TableStyler tableStyler = new TableStyler();
        TableStyler.applyStyles(table);  // Aplica estilos a la tabla
        tableStyler.fixTable(scrollPane); // Configura la apariencia del JScrollPane
        CustomTableHeaderRenderer.applyStylesToHeader(table); // Aplica estilos al encabezado de la tabla
    }

    private void generarID() {

        // Establecer conexión a la base de datos
        Connection conexion = Conexion.obtenerConexion();

        if (conexion != null) {
            try {
                // Consulta SQL para obtener el valor máximo actual de la columna ID
                String consulta = "SELECT MAX(ID) FROM tbl_documentos";
                PreparedStatement ps = conexion.prepareStatement(consulta);
                ResultSet rs = ps.executeQuery();

                // Obtener el valor máximo actual
                int maxID = 0;
                if (rs.next()) {
                    maxID = rs.getInt(1);
                }

                // Incrementar el valor máximo en uno para obtener el nuevo ID
                int nuevoID = maxID + 1;

                // Cerrar recursos
                rs.close();
                ps.close();

                // Formatear el nuevo ID con ceros a la izquierda si es necesario
                String idSecuencial = String.format("%04d", nuevoID);

                // Actualizar la etiqueta con el nuevo ID
                lblIDfactura.setText(idSecuencial);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            {
                Conexion.cerrarConexion(conexion);
            }
        }
    }

    private void LimpiarCampos() {
        txtRUC.setText(" ");
        txtDescGeneral.setText("0.00");
        txtDireccion.setText(" ");
        txtTelefono1.setText(" ");
        txtTelefono2.setText(" ");
        txtReferencia.setText(" ");
        txtCodigoproducto.setText(" ");
        txtCantidad.setText("0.00");
        txtPrecio.setText("0.00");
        txtDescLinea.setText("0.00");
        txtMontopago1.setText("0.00");
        txtMontopago2.setText("0.00");
        txtMontopago3.setText("0.00");
        txtMontopago4.setText("0.00");
        DefaultTableModel model = (DefaultTableModel) TableDocumentos.getModel();
        model.setRowCount(0);
        lblCantidad.setText(" ");
        lblMonto.setText(" ");
        lblDescLinea.setText(" ");
        lblDescGen.setText(" ");
        lblSubtotal.setText(" ");
        lblImpuesto.setText(" ");
        lblTotal.setText(" ");
        lblDIF.setText(" ");
    }

    private void actualizarFecha() {
        // Obtener la fecha actual del sistema
        Date fechaActual = new Date();

        // Formatear la fecha en un formato específico (por ejemplo, "dd/MM/yyyy HH:mm:ss")
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");

        lblFechaImpresion.setText(formatoFecha.format(fechaActual));

    }

    private void TipoDocumento() {
        Documentos documentos = new Documentos();
        documentos.setTipodocumento(jcbTipoDocumento.getSelectedItem().toString());
    }

    private void cargarTable() {
        String DecLinea;
        if (!txtDescLinea.isEnabled()) {
            DecLinea = String.valueOf(0.00);
        }
        String Decgen;
        if (!txtDescGeneral.isEnabled()) {
            Decgen = String.valueOf(0.00);
        }
        String Confirmservicio;
        if (chkServicio.isSelected()) {
            Confirmservicio = "Si";
        } else {
            Confirmservicio = "No";
        }

        Documentos documentos = new Documentos();

        //Calculos de tabla
        // Enviar los valores necesarios a clase (ajusta según tus necesidades)
        documentos.setPrecioProducto(Double.parseDouble(txtPrecio.getText()));
        documentos.setCantidad(Double.parseDouble(txtCantidad.getText()));
        documentos.setDescLinea(Double.parseDouble(txtDescLinea.getText()));
        documentos.setDescGen(Double.parseDouble(txtDescGeneral.getText()));
        documentos.setImpuestos(Double.parseDouble(cbxImpuesto.getSelectedItem().toString()));

        // Llamar a los métodos de cálculo en la clase Documentos
        documentos.CalcularDescuentoGen(documentos.getPrecioProducto(), documentos.getDescGen());
        documentos.CalcularDescuentoLinea(documentos.getPrecioProducto(), documentos.getDescLinea());
        documentos.CalcularBase(documentos.getPrecioProducto(), documentos.getCantidad(), documentos.getDescLinea(), documentos.getDescGen());
        documentos.CalcularItbms(documentos.getImpuestos());
        documentos.CalcularImporteImpuesto(documentos.getPrecioProducto(), documentos.getImpuestos(), documentos.getCantidad());
        documentos.CalcularSubtotal(documentos.getBase(), documentos.getImporteImpuesto());

        // Obtener resultados de los métodos get
        double precioProducto1 = documentos.getPrecioProducto();
        double cantidad = Double.parseDouble(txtCantidad.getText());
        double DescGen1 = documentos.getDescGen();
        double DescLinea1 = documentos.getDescLinea();
        double Impuestos1 = documentos.getImpuestos();
        double base = documentos.getBase();
        double importeImpuesto = documentos.getImporteImpuesto();
        double subtotal = documentos.getSubtotal1();

        double precioProductoFormateado = Double.parseDouble(formatoDecimal.format(precioProducto1));
        double descLineaFormateado = Double.parseDouble(formatoDecimal.format(DescLinea1));
        double descGenFormateado = Double.parseDouble(formatoDecimal.format(DescGen1));
        double baseFormateado = Double.parseDouble(formatoDecimal.format(base));
        double impuestosFormateado = Double.parseDouble(formatoDecimal.format(Impuestos1));
        double importeImpuestoFormateado = Double.parseDouble(formatoDecimal.format(importeImpuesto));
        double subtotalFormateado = Double.parseDouble(formatoDecimal.format(subtotal));

        Connection conexion = Conexion.obtenerConexion();
        Articulos producto = new Articulos(); // Crear un objeto de la clase Clientes
        if (conexion != null) {
            producto.setNombreproducto(cbxArticuloServicio.getSelectedItem().toString());
            producto.InfoProductoPorNombre(conexion); // Llama al método en la clase Clientes
            Conexion.cerrarConexion(conexion);
        }
        DefaultTableModel model = (DefaultTableModel) TableDocumentos.getModel();
        model.addRow(new Object[]{txtCodigoproducto.getText(), cbxArticuloServicio.getSelectedItem().toString(), Confirmservicio, producto.getDescripcion(), cbxMagnitudes.getSelectedItem().toString(), cantidad, precioProductoFormateado, descLineaFormateado, descGenFormateado, baseFormateado, impuestosFormateado, importeImpuestoFormateado, subtotalFormateado});
        double sumaCantidad = 0.0;

        //Calculos de barra inferior
        //Suma de Cantidad de productos
        // Obtener el índice de la columna "Cantidad"
        int cantidadColumnIndex = model.findColumn("Cantidad");

        // Verificar si la columna "Cantidad" existe
        if (cantidadColumnIndex != -1) {
            // Suma de Cantidad de productos
            for (int i = 0; i < model.getRowCount(); i++) {
                // Obtener el valor de la columna "Cantidad" en la fila actual
                Object cantidadObject = model.getValueAt(i, cantidadColumnIndex);

                if (cantidadObject != null) {
                    String cantidadString = cantidadObject.toString();

                    try {
                        double cantidadEnFila = Double.parseDouble(cantidadString);
                        sumaCantidad += cantidadEnFila;

                    } catch (NumberFormatException e) {
                        // Manejar la excepción si no se puede convertir a Double
                        JOptionPane.showMessageDialog(null, "Error en la fila " + i + ": " + "No se pudo convertir a número el valor " + cantidadString, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "La columna 'Cantidad' no existe en la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        //Monto = suma precio del producto sin descuento y sin importe
        int precioProductoColumnIndex = model.findColumn("Base");

        // Verificar si la columna "precioProducto1" existe
        if (precioProductoColumnIndex != -1) {
            // Suma de precios de productos
            double sumaPrecio = 0.0;

            for (int i = 0; i < model.getRowCount(); i++) {
                // Obtener el valor de la columna "precioProducto1" en la fila actual
                Object precioProductoObject = model.getValueAt(i, precioProductoColumnIndex);

                if (precioProductoObject != null) {
                    String precioProductoString = precioProductoObject.toString();

                    try {
                        double precioProducto = Double.parseDouble(precioProductoString);
                        sumaPrecio += precioProducto;

                    } catch (NumberFormatException e) {
                        // Manejar la excepción si no se puede convertir a Double
                        JOptionPane.showMessageDialog(null, "Error en la fila " + i + ": " + "No se pudo convertir a número el precio " + precioProductoString, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            // Enviar la suma a la clase Documentos mediante el método setMontoPrecio
            documentos.setMontoPrecio(sumaPrecio);

            // Actualizar el texto del label con la suma de precios
            lblMonto.setText(String.valueOf(sumaPrecio));
        } else {
            JOptionPane.showMessageDialog(null, "La columna 'precioProducto1' no existe en la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        //descuento linea total= suma de los descuento de linea de los productos
        // Obtener el índice de la columna "Desc. Linea"
        int descLineaColumnIndex = model.findColumn("Desc. Linea");

        // Verificar si la columna "Desc. Linea" existe
        if (descLineaColumnIndex != -1) {
            // Suma de Desc. Linea
            double sumaDescLinea = 0.0;

            for (int i = 0; i < model.getRowCount(); i++) {
                // Obtener el valor de la columna "Desc. Linea" en la fila actual
                Object descLineaObject = model.getValueAt(i, descLineaColumnIndex);

                if (descLineaObject != null) {
                    String descLineaString = descLineaObject.toString();

                    try {
                        double descLinea = Double.parseDouble(descLineaString);
                        sumaDescLinea += descLinea;

                    } catch (NumberFormatException e) {
                        // Manejar la excepción si no se puede convertir a Double
                        JOptionPane.showMessageDialog(null, "Error en la fila " + i + ": " + "No se pudo convertir a número el valor " + descLineaString, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            // Enviar la suma a la clase Documentos mediante el método setSumaDescLinea
            documentos.setSumaDescLinea(sumaDescLinea);

        } else {
            JOptionPane.showMessageDialog(null, "La columna 'Desc. Linea' no existe en la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        //descuento general total= suma de los descuento general de los productos
        // Obtener el índice de la columna "Desc. General"
        int descGeneralColumnIndex = model.findColumn("Desc. General");

        // Verificar si la columna "Desc. Linea" existe
        if (descGeneralColumnIndex != -1) {
            // Suma de Desc. Linea
            double sumadescGeneral = 0.0;

            for (int i = 0; i < model.getRowCount(); i++) {
                // Obtener el valor de la columna "Desc. Linea" en la fila actual
                Object descGeneralObject = model.getValueAt(i, descGeneralColumnIndex);

                if (descGeneralObject != null) {
                    String descGeneralString = descGeneralObject.toString();

                    try {
                        double descGeneral = Double.parseDouble(descGeneralString);
                        sumadescGeneral += descGeneral;

                    } catch (NumberFormatException e) {
                        // Manejar la excepción si no se puede convertir a Double
                        JOptionPane.showMessageDialog(null, "Error en la fila " + i + ": " + "No se pudo convertir a número el valor " + descGeneralString, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            // Enviar la suma a la clase Documentos mediante el método setSumaDescGen
            documentos.setSumaDescGen(sumadescGeneral);

        } else {
            JOptionPane.showMessageDialog(null, "La columna 'Desc. General' no existe en la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        //Calculo monto precio total
        documentos.CalcularMonto(documentos.getMontoPrecio(), documentos.getSumaDescGen(), documentos.getSumaDescLinea());

        //Impuestos= Suma de los ImporteImpuesto de cada producto agregado a la lista
        int impuestototalColumnIndex = model.findColumn("Importe I.T.B.M.S");

        // Verificar si la columna "Impuesto" existe
        if (impuestototalColumnIndex != -1) {
            // Suma de precios de productos
            double sumaImpuesto = 0.0;

            for (int i = 0; i < model.getRowCount(); i++) {
                // Obtener el valor de la columna "precioProducto1" en la fila actual
                Object impuestototalObject = model.getValueAt(i, impuestototalColumnIndex);

                if (impuestototalObject != null) {
                    String impuestototalString = impuestototalObject.toString();

                    try {
                        double impuestototal = Double.parseDouble(impuestototalString);
                        sumaImpuesto += impuestototal;

                    } catch (NumberFormatException e) {
                        // Manejar la excepción si no se puede convertir a Double
                        JOptionPane.showMessageDialog(null, "Error en la fila " + i + ": " + "No se pudo convertir a número  " + impuestototalString, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            // Enviar la suma a la clase Documentos mediante el método setSumaImpuesto
            documentos.setSumaImpuesto(sumaImpuesto);
        } else {
            JOptionPane.showMessageDialog(null, "La columna 'Impuesto' no existe en la tabla.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        //Asignar resultados a label barra inferior
        lblCantidad.setText(String.format("%.2f", sumaCantidad));
        lblMonto.setText(String.format("%.2f", documentos.getMontoPrecio()));
        lblDescLinea.setText(String.format("%.2f", documentos.getSumaDescLinea()));
        lblDescGen.setText(String.format("%.2f", documentos.getSumaDescGen()));
        lblSubtotal.setText(String.format("%.2f", documentos.getSubtotal2()));
        lblImpuesto.setText(String.format("%.2f", documentos.getSumaImpuesto()));
        lblTotal.setText(String.format("%.2f", documentos.getTotal()));
        Conexion.cerrarConexion(conexion);
    }

//
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton7 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        bg2 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        cboxFormapago1 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        txtMontopago1 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        cboxFormapago2 = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        txtMontopago2 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        cboxFormapago3 = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        txtMontopago3 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        cboxFormapago4 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        txtMontopago4 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        btnImprimir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono2 = new javax.swing.JTextField();
        txtDescGeneral = new javax.swing.JTextField();
        txtTelefono1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        chkCredito = new javax.swing.JCheckBox();
        jcbNombre = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lblNumeroCliente = new javax.swing.JLabel();
        txtRUC = new javax.swing.JTextField();
        chkDescGen = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableDocumentos = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        LblCantidad = new javax.swing.JLabel();
        lblMonto_precio = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblCantidad = new javax.swing.JLabel();
        lblMonto = new javax.swing.JLabel();
        lblDescLinea = new javax.swing.JLabel();
        lblDescGen = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblImpuesto = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblDIF = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        cbxArticuloServicio = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        chkDescLinea = new javax.swing.JRadioButton();
        txtDescLinea = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        cbxMagnitudes = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbxImpuesto = new javax.swing.JComboBox<>();
        chkServicio = new javax.swing.JCheckBox();
        btnRemover = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        txtCodigoproducto = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnBuscar2 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jcbTipoDocumento = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        lblIDfactura = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lblFechaImpresion = new javax.swing.JLabel();
        bg1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jctipoDocumentos = new javax.swing.JComboBox<>();
        jLabel40 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        btnAplicar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableFacturas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jdFecha2 = new com.toedter.calendar.JDateChooser();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jdFecha1 = new com.toedter.calendar.JDateChooser();

        jButton7.setText("jButton7");

        setOpaque(false);

        jTabbedPane1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(1100, 881));
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        bg2.setBackground(new java.awt.Color(245, 245, 245));
        bg2.setPreferredSize(new java.awt.Dimension(1100, 850));
        bg2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(160, 840));

        jLabel30.setBackground(new java.awt.Color(204, 204, 204));
        jLabel30.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Pagos");
        jLabel30.setOpaque(true);

        jLabel31.setText("Forma de pago [1]");

        cboxFormapago1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Efectivo", "Clave", "Visa", "MasterCard", "Amex", "Transferencia Bancaria", "Cheque", "T. Débito 1", "T. Credito 1", "T. Credito 2", "Cheque Banco1", "Cheque Banco 2", "Nota de Credito", "Otros" }));

        jLabel32.setText("Monto del pago [1]");

        jLabel33.setText("Forma de pago [2]");

        cboxFormapago2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Efectivo", "Clave", "Visa", "MasterCard", "Amex", "Transferencia Bancaria", "Cheque", "T. Débito 1", "T. Credito 1", "T. Credito 2", "Cheque Banco1", "Cheque Banco 2", "Nota de Credito", "Otros" }));

        jLabel34.setText("Monto del pago [2]");

        txtMontopago2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontopago2ActionPerformed(evt);
            }
        });

        jLabel35.setText("Forma de pago [3]");

        cboxFormapago3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Efectivo", "Clave", "Visa", "MasterCard", "Amex", "Transferencia Bancaria", "Cheque", "T. Débito 1", "T. Credito 1", "T. Credito 2", "Cheque Banco1", "Cheque Banco 2", "Nota de Credito", "Otros" }));

        jLabel36.setText("Monto del pago[3]");

        jLabel37.setText("Forma de pago [4]");

        cboxFormapago4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ninguno", "Efectivo", "Clave", "Visa", "MasterCard", "Amex", "Transferencia Bancaria", "Cheque", "T. Débito 1", "T. Credito 1", "T. Credito 2", "Cheque Banco1", "Cheque Banco 2", "Nota de Credito", "Otros" }));

        jLabel38.setText("Monto del pago");

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel33))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel34))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMontopago2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMontopago1)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel37)
                                    .addComponent(txtMontopago3, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel38)
                                    .addComponent(txtMontopago4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxFormapago4, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                            .addComponent(cboxFormapago3, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                            .addComponent(cboxFormapago2, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                            .addComponent(cboxFormapago1, 0, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboxFormapago1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMontopago1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboxFormapago2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMontopago2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel35)
                .addGap(4, 4, 4)
                .addComponent(cboxFormapago3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMontopago3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboxFormapago4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMontopago4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        bg2.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 0, 160, 840));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);

        jLabel9.setBackground(new java.awt.Color(204, 204, 204));
        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Datos del Cliente");
        jLabel9.setOpaque(true);

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jLabel10.setText("Cust# :");

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jLabel12.setText("Nombre:");

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jLabel13.setText("Dirección / Teléfonos:");

        txtDescGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescGeneralActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jLabel15.setText("Referencia :");

        chkCredito.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        chkCredito.setText("Crédito");
        chkCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCreditoActionPerformed(evt);
            }
        });

        jcbNombre.setEditable(true);
        jcbNombre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jLabel11.setText("R.U.C:");

        lblNumeroCliente.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        lblNumeroCliente.setText("####");

        chkDescGen.setText("% Desc General :");
        chkDescGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDescGenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNumeroCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(119, 119, 119))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jcbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                        .addComponent(btnBuscar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(119, 119, 119)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCredito)
                    .addComponent(txtDescGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtReferencia)
                    .addComponent(chkDescGen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(53, 53, 53))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(lblNumeroCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(chkDescGen))
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDescGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar)
                        .addComponent(txtRUC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTelefono2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkCredito)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bg2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 79, 942, 180));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.setOpaque(false);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jScrollPane1ComponentAdded(evt);
            }
        });

        TableDocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Articulo/ Servicio", "¿Servicio?", "Descripción", "Magnitud", "Cantidad", "Precio", "Desc. Linea", "Desc. General", "Base", "I.T.B.M.S", "Importe I.T.B.M.S", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableDocumentos.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(TableDocumentos);
        TableDocumentos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
        );

        bg2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 942, 360));

        jPanel5.setBackground(new java.awt.Color(245, 245, 245));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setOpaque(false);

        LblCantidad.setText("Cantidad:");

        lblMonto_precio.setText("Monto:");

        jLabel24.setText("Desc. Línea");

        jLabel25.setText("Desc. General:");

        jLabel26.setText("SubTotal:");

        jLabel27.setText("Impuestos:");

        jLabel28.setText("Total:");

        jLabel29.setText("DIF:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblCantidad)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblCantidad)))
                .addGap(65, 65, 65)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMonto_precio)
                    .addComponent(lblMonto))
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblDescLinea)))
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescGen)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblSubtotal)))
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblImpuesto)))
                .addGap(55, 55, 55)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(67, 67, 67)
                        .addComponent(jLabel29))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblTotal)
                        .addGap(44, 44, 44)
                        .addComponent(lblDIF)))
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LblCantidad)
                    .addComponent(lblMonto_precio)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCantidad)
                        .addComponent(lblMonto)
                        .addComponent(lblDescLinea)
                        .addComponent(lblSubtotal)
                        .addComponent(lblImpuesto)
                        .addComponent(lblTotal)
                        .addComponent(lblDIF))
                    .addComponent(lblDescGen))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        bg2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 730, 942, 60));

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setOpaque(false);

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jLabel17.setText("Articulo/ Servicio :");

        cbxArticuloServicio.setEditable(true);
        cbxArticuloServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxArticuloServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxArticuloServicioActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jLabel19.setText("Cantidad:");

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jLabel20.setText("Precio Unitario :");

        chkDescLinea.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        chkDescLinea.setText("% Desc. Línea:");
        chkDescLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDescLineaActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jLabel21.setText("I.T.B.M.S. (%) :");

        cbxMagnitudes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unidades", "Piezas", "Docenas", "cm", "m", "m cuadrados", "m Cúbicos", "Pulgadas", "Yardas", "Yardas líneales", "Pies ", "Pies Cúbicos", "Litros", "Galones", "Pintas", "Onzas", "Kg", "Lb", "Páginas", "Resmas", "Toneladas", "Por Hora", "Por Día", "Por Semana ", "Por Mes", "Por Año", "Por Contrato" }));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jLabel5.setText("Magnitud :");

        cbxImpuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "7", "10", "15" }));

        chkServicio.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        chkServicio.setText("Servicio");
        chkServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkServicioActionPerformed(evt);
            }
        });

        btnRemover.setText("-");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnAgregar.setText("+");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        jLabel18.setText("Código Producto:");

        btnBuscar2.setText("Buscar");
        btnBuscar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCodigoproducto, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cbxMagnitudes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxArticuloServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chkServicio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                        .addGap(18, 24, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDescLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkDescLinea))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(cbxImpuesto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnBuscar2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(chkDescLinea))
                .addGap(53, 53, 53))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbxArticuloServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(chkServicio)
                                    .addComponent(btnBuscar2))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCodigoproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDescLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxMagnitudes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        bg2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 252, 942, 120));

        jPanel9.setOpaque(false);

        jcbTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Factura", "Devolución" }));

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(204, 0, 0));
        jLabel39.setText("Factura ");

        lblIDfactura.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblIDfactura.setText("####");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel45.setText("Fecha/ Hora Impresión :");

        lblFechaImpresion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFechaImpresion.setText("HHHHHH");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIDfactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jcbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(jLabel45)
                .addGap(18, 18, 18)
                .addComponent(lblFechaImpresion)
                .addContainerGap(160, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(lblFechaImpresion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(lblIDfactura))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        bg2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jTabbedPane1.addTab("Registro de Factura", bg2);

        bg1.setBackground(new java.awt.Color(245, 245, 245));
        bg1.setPreferredSize(new java.awt.Dimension(1156, 850));

        jPanel7.setOpaque(false);

        jctipoDocumentos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Factura", "Devolución" }));
        jctipoDocumentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jctipoDocumentosActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 204, 204));
        jLabel40.setText("Documento Generado");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jctipoDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addContainerGap(373, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jctipoDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel1.setOpaque(false);

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel46.setText("Período Facturado");

        btnAplicar.setText("Aplicar");
        btnAplicar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplicarActionPerformed(evt);
            }
        });

        TableFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID Factura", "Numero de cliente", "Nombre de Cliente", "Fecha de Facturación", "Monto Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TableFacturas);

        jButton1.setText("Visualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/visualizar_icon.png"))); // NOI18N

        jdFecha2.setDateFormatString("yyyy-MM-dd");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 204, 204));
        jLabel41.setText("Al");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 204, 204));
        jLabel42.setText("Del");

        jdFecha1.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addGap(935, 935, 935))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel42)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jdFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)
                                .addComponent(btnAplicar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(btnAplicar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(jdFecha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bg1Layout = new javax.swing.GroupLayout(bg1);
        bg1.setLayout(bg1Layout);
        bg1Layout.setHorizontalGroup(
            bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        bg1Layout.setVerticalGroup(
            bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Historial de Factura", null, bg1, "");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents
    private void ObtenerNombreCliente() {
        // Instanciar la clase Clientes
        Clientes cliente = new Clientes();
        Connection conexion = Conexion.obtenerConexion();

        try {
            // Establecer la conexión a la base de datos aquí

            List<String> nombres = cliente.getAllNombres(conexion);

            // Limpiar el JComboBox antes de agregar los nuevos elementos
            jcbNombre.removeAllItems();

            for (String nombre : nombres) {
                jcbNombre.addItem(nombre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión a la base de datos aquí
        }
    }

    private void ObtenerNombreProducto() {
        // Instanciar la clase Clientes
        Articulos producto = new Articulos();
        Connection conexion = Conexion.obtenerConexion();

        try {
            // Establecer la conexión a la base de datos aquí

            List<String> Productos = producto.getAllProductos(conexion);

            // Limpiar el JComboBox antes de agregar los nuevos elementos
            cbxArticuloServicio.removeAllItems();

            for (String nombreproducto : Productos) {
                cbxArticuloServicio.addItem(nombreproducto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión a la base de datos aquí
            Conexion.cerrarConexion(conexion);
        }

    }

    private void ObtenerNombreServicio() {
        // Instanciar la clase Clientes
        Servicios servicio = new Servicios();
        Connection conexion = Conexion.obtenerConexion();

        try {
            // Establecer la conexión a la base de datos aquí

            List<String> Servicios = servicio.getAllServicios(conexion);

            // Limpiar el JComboBox antes de agregar los nuevos elementos
            cbxArticuloServicio.removeAllItems();

            for (String nombreservicio : Servicios) {
                cbxArticuloServicio.addItem(nombreservicio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar la conexión a la base de datos aquí
            Conexion.cerrarConexion(conexion);
        }
    }

    private void cargarTableFactura() {

        Connection conexion = Conexion.obtenerConexion();
        DefaultTableModel modelo = (DefaultTableModel) TableFacturas.getModel();
        // Limpiar cualquier contenido que pueda haber en la tabla actualmente
        modelo.setRowCount(0);
        Documentos obj_documentos = new Documentos();
        // Obtener la lista de productos desde la base de datos
        List<Documentos> facturas = obj_documentos.selectDocumentos(conexion);
        // Llenar la tabla con los datos
        for (Documentos factura : facturas) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaFormateada = dateFormat.format(factura.getFecha_registro());

            modelo.addRow(new Object[]{
                factura.getIDfactura(),
                factura.getCodigocliente(),
                factura.getNombre(),
                fechaFormateada, // Mostrar la fecha formateada en la tabla
                factura.getTotal(),});
        }
    }


    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged

    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void chkCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCreditoActionPerformed
        Documentos documentos = new Documentos();
        String Confirmcredito;
        if (chkCredito.isSelected()) {
            txtReferencia.setEnabled(true);
            Confirmcredito = "Si";
        } else {
            txtReferencia.setEnabled(false);
            Confirmcredito = "No";
        }
        documentos.setCredito(Confirmcredito);
    }//GEN-LAST:event_chkCreditoActionPerformed

    private void txtMontopago2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontopago2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMontopago2ActionPerformed

    private void insertBaseDatosE() {
        // Se obtiene una conexión a la base de datos
        Connection conexion = Conexion.obtenerConexion();

        if (conexion != null) {
            try {
                // Verifica que lblIDfactura.getText() sea un valor numérico antes de intentar convertirlo
                int idFactura;
                try {
                    idFactura = Integer.parseInt(lblIDfactura.getText());
                } catch (NumberFormatException e) {
                    System.out.println("Error: lblIDfactura no es un número válido.");
                    return;  // Sale del método si no se puede convertir a un número
                }

                // Itera sobre las filas de la tabla
                DefaultTableModel model = (DefaultTableModel) TableDocumentos.getModel();

                for (int i = 0; i < model.getRowCount(); i++) {
                    // Crea una nueva instancia de Documentos para cada fila
                    Documentos documentos = new Documentos();
                    documentos.setIDfactura(idFactura);
                    // Configura los atributos del objeto Documentos con los valores de la fila actual
                    documentos.setCodigoproducto(model.getValueAt(i, 0).toString());
                    documentos.setNombreproducto(model.getValueAt(i, 1).toString());
                    documentos.setConfirmservicio(model.getValueAt(i, 2).toString());
                    documentos.setDescripcion(model.getValueAt(i, 3).toString());
                    documentos.setMagnitud(model.getValueAt(i, 4).toString());
                    documentos.setCantidad(Double.parseDouble(model.getValueAt(i, 5).toString())); // Ajusta según la estructura de tu tabla
                    documentos.setPrecioProducto(Double.parseDouble(model.getValueAt(i, 6).toString())); // Ajusta según la estructura de tu tabla
                    documentos.setDescLinea(Double.parseDouble(model.getValueAt(i, 7).toString())); // Ajusta según la estructura de tu tabla
                    documentos.setDescGen(Double.parseDouble(model.getValueAt(i, 8).toString())); // Ajusta según la estructura de tu tabla
                    documentos.setBase(Double.parseDouble(model.getValueAt(i, 9).toString()));
                    documentos.setImpuestos(Double.parseDouble(model.getValueAt(i, 10).toString()));
                    documentos.setImporteImpuesto(Double.parseDouble(model.getValueAt(i, 11).toString())); // Ajusta según la estructura de tu tabla
                    documentos.setSubtotal1(Double.parseDouble(model.getValueAt(i, 12).toString()));

                    // Llama al método insertElementos para insertar en la base de datos
                    int filasAfectadas = documentos.insertElementos(conexion, documentos);
                }
            } catch (Exception e) {
                // Maneja otras excepciones de manera más específica si es posible
                e.printStackTrace();
                System.out.println("error " + e);
            } finally {
                // Cierra la conexión a la base de datos en el bloque finally para asegurar que se cierre
                Conexion.cerrarConexion(conexion);
            }
        }
    }

    private void insertBaseDatosD() {
        // Se obtiene una conexión a la base de datos
        Connection conexion = Conexion.obtenerConexion();

        if (conexion != null) {
            try {
                Documentos obj_insert = new Documentos();

                // Verifica que los elementos seleccionados no sean nulos
                if (jcbTipoDocumento.getSelectedItem() != null) {
                    obj_insert.setTipodocumento(jcbTipoDocumento.getSelectedItem().toString());
                }

                // Verifica que lblIDfactura.getText() sea un valor numérico antes de intentar convertirlo
                try {
                    obj_insert.setIDfactura(Integer.parseInt(lblIDfactura.getText()));
                } catch (NumberFormatException e) {
                    System.out.println("Error: lblIDfactura no es un número válido.");
                    return;  // Sale del método si no se puede convertir a un número
                }

                obj_insert.setCodigocliente(Integer.parseInt(lblNumeroCliente.getText()));

                // Verifica que el elemento seleccionado no sea nulo
                if (jcbNombre.getSelectedItem() != null) {
                    obj_insert.setNombre(jcbNombre.getSelectedItem().toString());
                }

                Documentos documentos = new Documentos();
                String Confirmcredito;
                if (chkCredito.isSelected()) {
                    txtReferencia.setEnabled(true);
                    Confirmcredito = "Si";
                } else {
                    txtReferencia.setEnabled(false);
                    Confirmcredito = "no";
                }
                documentos.setCredito(Confirmcredito);

                //InsertDocumentos
                obj_insert.setRUC(txtRUC.getText().trim());
                obj_insert.setDescGen(Double.parseDouble(txtDescGeneral.getText().trim()));
                obj_insert.setDireccion(txtDireccion.getText().trim());
                obj_insert.setTelefono1(txtTelefono1.getText().trim());
                obj_insert.setTelefono2(txtTelefono2.getText().trim());
                obj_insert.setCredito(Confirmcredito);
                obj_insert.setReferencia(txtReferencia.getText().trim());
                obj_insert.setMontoPrecio(Double.parseDouble(lblMonto.getText()));
                obj_insert.setSumaCantidad(Double.parseDouble(lblCantidad.getText()));
                obj_insert.setSumaDescLinea(Double.parseDouble(lblDescLinea.getText()));
                obj_insert.setSumaDescGen(Double.parseDouble(lblDescGen.getText()));
                obj_insert.setSubtotal2(Double.parseDouble(lblSubtotal.getText()));
                obj_insert.setSumaImpuesto(Double.parseDouble(lblImpuesto.getText()));
                obj_insert.setTotal(Double.parseDouble(lblTotal.getText()));
                obj_insert.setFormaPago1(cboxFormapago1.getSelectedItem().toString());
                obj_insert.setMontoPago1(Double.parseDouble(txtMontopago1.getText()));
                obj_insert.setFormaPago2(cboxFormapago2.getSelectedItem().toString());
                obj_insert.setMontoPago2(Double.parseDouble(txtMontopago2.getText()));
                obj_insert.setFormaPago3(cboxFormapago3.getSelectedItem().toString());
                obj_insert.setMontoPago3(Double.parseDouble(txtMontopago3.getText()));
                obj_insert.setFormaPago4(cboxFormapago4.getSelectedItem().toString());
                obj_insert.setMontoPago4(Double.parseDouble(txtMontopago4.getText()));
                obj_insert.setDIF(Double.parseDouble(lblDIF.getText()));

                String pattern = "dd/MM/yyyy HH:mm:ss";
                SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
                obj_insert.setFecha_registro(dateFormat.parse(lblFechaImpresion.getText()));

                // Realiza la inserción en la base de datos
                obj_insert.insertDocumentos(conexion, obj_insert);
                /*} catch (NumberFormatException nfe) {
                System.out.println("Error al convertir a número: " + nfe.getMessage());*/
            } catch (Exception e) {
                // Maneja otras excepciones de manera más específica si es posible
                e.printStackTrace();
                System.out.println("error " + e);
            } finally {
                // Cierra la conexión a la base de datos en el bloque finally para asegurar que se cierre
                Conexion.cerrarConexion(conexion);
            }
        }
    }

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        Documentos documentos = new Documentos();
        TipoDocumento();
        lblDIF.setText(String.format("%.2f", documentos.getDIF()));
        insertBaseDatosD();
        insertBaseDatosE();
        LimpiarCampos();
        generarID();

        //SE CREA LA FACTURA
        /*FacturaPDF obj = new FacturaPDF();
        obj.main(new String[]{},lblIDfactura.getText());*/
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void jScrollPane1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jScrollPane1ComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1ComponentAdded

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        Connection conexion = Conexion.obtenerConexion();
        Clientes cliente = new Clientes(); // Crear un objeto de la clase Clientes
        if (conexion != null) {
            cliente.setNombre(jcbNombre.getSelectedItem().toString());
            cliente.InfoClientePorNombre(conexion); // Llama al método en la clase Clientes
            Conexion.cerrarConexion(conexion);
        }
        lblNumeroCliente.setText(cliente.getCodigo_cliente());
        txtRUC.setText(cliente.getRuc());
        txtDireccion.setText(cliente.getDireccion());
        txtTelefono1.setText(cliente.getTelefono1());
        txtTelefono2.setText(cliente.getTelefono2());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void chkServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkServicioActionPerformed
        Documentos documentos = new Documentos();
        String Confirmservicio;
        if (chkServicio.isSelected()) {
            Confirmservicio = "Si";
            ObtenerNombreServicio();
        } else {
            ObtenerNombreProducto();
            Confirmservicio = "No";
        }
        documentos.setConfirmservicio(Confirmservicio);
    }//GEN-LAST:event_chkServicioActionPerformed

    /*Info Tabla articulos agregados
    N°= codigo de producto
    Articulo/servicio= nombre de Articulo/servicio
    ¿Servicio?= confirmacion sobre servicio si o no
    Descripcion= descripcion de Articulo/servicio
    Cantidad = cantidad de Articulo/servicio
    Precio= precio de Articulo/servicio por unidad
    Base= la suma de los precios sin impuesto de un mismo Articulo/servicio y con descuento si este tiene 
    itbms = impuesto aplicado a un Articulo/servicio en especifico
    ImporteImpuesto= 0.07xPrecio de Articulo/servicio
    SubTotal: La suma total de precio de Articulo/servicio con descuento aplicado + ibtms de cada Articulo/servicio 
     */

    private void cbxArticuloServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxArticuloServicioActionPerformed

    }//GEN-LAST:event_cbxArticuloServicioActionPerformed

    private void btnBuscar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar2ActionPerformed
        Connection conexion = Conexion.obtenerConexion();
        // Crear un objeto de la clase Clientes
        Articulos articulo = new Articulos();
        Servicios servicio = new Servicios();
        if (chkServicio.isSelected()) {
            if (conexion != null) {
                servicio.setNombreservicio(cbxArticuloServicio.getSelectedItem().toString());
                servicio.InfoServicioPorNombre(conexion);
                txtCodigoproducto.setText(servicio.getCodigoservicio());
                txtPrecio.setText(String.valueOf(servicio.getPrecio()));
            }
        } else {
            if (conexion != null) {
                articulo.setNombreproducto(cbxArticuloServicio.getSelectedItem().toString());
                articulo.InfoProductoPorNombre(conexion); // Llama al método en la clase Clientes
                txtCodigoproducto.setText(articulo.getCodigoproducto());
                txtPrecio.setText(String.valueOf(articulo.getPrecio()));
            }
        }
        Conexion.cerrarConexion(conexion);

    }//GEN-LAST:event_btnBuscar2ActionPerformed

    private void jctipoDocumentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jctipoDocumentosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jctipoDocumentosActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        cargarTable();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void chkDescLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDescLineaActionPerformed
        if (chkDescLinea.isSelected()) {
            txtDescLinea.setEnabled(true);
        } else {
            txtDescLinea.setEnabled(false);
            txtDescLinea.setText(String.valueOf(0.00));
        }
    }//GEN-LAST:event_chkDescLineaActionPerformed

    private void txtDescGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescGeneralActionPerformed

    }//GEN-LAST:event_txtDescGeneralActionPerformed

    private void chkDescGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDescGenActionPerformed
        if (chkDescGen.isSelected()) {
            txtDescGeneral.setEnabled(true);
        } else {
            txtDescGeneral.setEnabled(false);
            txtDescGeneral.setText(String.valueOf(0.00));
        }
    }//GEN-LAST:event_chkDescGenActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        Documentos documentos = new Documentos();
        DefaultTableModel model = (DefaultTableModel) TableDocumentos.getModel();

        // Obtiene la fila seleccionada
        int selectedRow = TableDocumentos.getSelectedRow();

        // Verifica si hay una fila seleccionada antes de intentar removerla
        if (selectedRow != -1) {
            // Obtén los valores de la fila antes de removerla
            double cantidadRemovida = (double) model.getValueAt(selectedRow, model.findColumn("Cantidad"));
            double precioRemovido = (double) model.getValueAt(selectedRow, model.findColumn("Precio"));
            double preciomontoRemovido = precioRemovido * cantidadRemovida;
            double descLineaRemovido = (double) model.getValueAt(selectedRow, model.findColumn("Desc. Linea"));
            double descGeneralRemovido = (double) model.getValueAt(selectedRow, model.findColumn("Desc. General"));
            double impuestoRemovido = (double) model.getValueAt(selectedRow, model.findColumn("Importe I.T.B.M.S"));
            double subtotalRemovido = (double) model.getValueAt(selectedRow, model.findColumn("Base"));
            double totalRemovido = (double) model.getValueAt(selectedRow, model.findColumn("Subtotal"));

            // Remueve la fila seleccionada del modelo de la tabla
            model.removeRow(selectedRow);

            // Resta los valores removidos de los totales
            documentos.restarSumaCantidad(Double.parseDouble(lblCantidad.getText()), cantidadRemovida);
            documentos.restarMontoPrecio(Double.parseDouble(lblMonto.getText()), preciomontoRemovido);
            documentos.restarSumaDescLinea(Double.parseDouble(lblDescLinea.getText()), descLineaRemovido);
            documentos.restarSumaDescGen(Double.parseDouble(lblDescGen.getText()), descGeneralRemovido);
            documentos.restarSumaImpuesto(Double.parseDouble(lblImpuesto.getText()), impuestoRemovido);
            documentos.restarSubtotal(Double.parseDouble(lblSubtotal.getText()), subtotalRemovido);
            documentos.restarTotal(Double.parseDouble(lblTotal.getText()), totalRemovido);

            // Actualiza los textos de los labels con los nuevos totales
            lblCantidad.setText(String.format("%.2f", documentos.getSumaCantidad()));
            lblMonto.setText(String.format("%.2f", documentos.getMontoPrecio()));
            lblDescLinea.setText(String.format("%.2f", documentos.getSumaDescLinea()));
            lblDescGen.setText(String.format("%.2f", documentos.getSumaDescGen()));
            lblSubtotal.setText(String.format("%.2f", documentos.getSubtotal2()));
            lblImpuesto.setText(String.format("%.2f", documentos.getSumaImpuesto()));
            lblTotal.setText(String.format("%.2f", documentos.getTotal()));
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para remover.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnRemoverActionPerformed


    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        LimpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int filaSeleccionada = TableFacturas.getSelectedRow();
        if (filaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) TableFacturas.getModel();
            Object valorCelda = modelo.getValueAt(filaSeleccionada, 0);
            if (valorCelda != null) {
                String ruc = valorCelda.toString();
                
                // Pasa el valor de ruc al constructor de frmHistorialDoc
                frmHistorialDoc obj = new frmHistorialDoc(ruc);
                obj.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ninguna Factura", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicarActionPerformed
        cargarTableFiltro();
    }//GEN-LAST:event_btnAplicarActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        cargarTableFactura();
    }//GEN-LAST:event_jTabbedPane1MouseClicked
    private void cargarTableFiltro() {
        Connection conexion = Conexion.obtenerConexion();
        DefaultTableModel modelo = (DefaultTableModel) TableFacturas.getModel();
        // Limpiar cualquier contenido que pueda haber en la tabla actualmente
        modelo.setRowCount(0);
        Documentos obj_documentos = new Documentos();
        // Obtener la lista de productos desde la base de datos
        List<Documentos> facturas = obj_documentos.selectDocumentos(conexion);

        // Obtener las fechas seleccionadas de los JDateChooser
        Date fechaSeleccionada1 = jdFecha1.getDate();
        Date fechaSeleccionada2 = jdFecha2.getDate();

        // Verificar si se ha seleccionado alguna fecha
        if (fechaSeleccionada1 == null && fechaSeleccionada2 == null) {
            // Mostrar un mensaje indicando que no se ha seleccionado ninguna fecha
            JOptionPane.showMessageDialog(this, "No ha seleccionado ninguna fecha", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;  // Salir del método ya que no hay fechas seleccionadas
        }

        // Obtener el tipo de documento seleccionado
        String tipoDocumentoSeleccionado = jctipoDocumentos.getSelectedItem().toString();

        // Llenar la tabla con los datos filtrados por el rango de fechas y tipo de documento
        for (Documentos factura : facturas) {
            Date fechaFactura = factura.getFecha_registro();
            String tipoDocumentoFactura = factura.getTipodocumento();

            // Verificar si la fecha de la factura está dentro del rango seleccionado
            // y si el tipo de documento coincide con la selección del JComboBox
            if (fechaFactura != null
                    && ((fechaSeleccionada1 == null || fechaFactura.after(fechaSeleccionada1) || fechaFactura.equals(fechaSeleccionada1))
                    && (fechaSeleccionada2 == null || fechaFactura.before(fechaSeleccionada2) || fechaFactura.equals(fechaSeleccionada2)))
                    && (tipoDocumentoSeleccionado.equals("Todos") || tipoDocumentoFactura.equals(tipoDocumentoSeleccionado))) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String fechaFormateada = dateFormat.format(fechaFactura);

                modelo.addRow(new Object[]{
                    factura.getIDfactura(),
                    factura.getCodigocliente(),
                    factura.getNombre(),
                    fechaFormateada, // Mostrar la fecha formateada en la tabla
                    factura.getTotal(),});
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblCantidad;
    private javax.swing.JTable TableDocumentos;
    private javax.swing.JTable TableFacturas;
    private javax.swing.JPanel bg1;
    private javax.swing.JPanel bg2;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAplicar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar2;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JComboBox<String> cboxFormapago1;
    private javax.swing.JComboBox<String> cboxFormapago2;
    private javax.swing.JComboBox<String> cboxFormapago3;
    private javax.swing.JComboBox<String> cboxFormapago4;
    private javax.swing.JComboBox<String> cbxArticuloServicio;
    private javax.swing.JComboBox<String> cbxImpuesto;
    private javax.swing.JComboBox<String> cbxMagnitudes;
    private javax.swing.JCheckBox chkCredito;
    private javax.swing.JRadioButton chkDescGen;
    private javax.swing.JRadioButton chkDescLinea;
    private javax.swing.JCheckBox chkServicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> jcbNombre;
    private javax.swing.JComboBox<String> jcbTipoDocumento;
    private javax.swing.JComboBox<String> jctipoDocumentos;
    private com.toedter.calendar.JDateChooser jdFecha1;
    private com.toedter.calendar.JDateChooser jdFecha2;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblDIF;
    private javax.swing.JLabel lblDescGen;
    private javax.swing.JLabel lblDescLinea;
    private javax.swing.JLabel lblFechaImpresion;
    private javax.swing.JLabel lblIDfactura;
    private javax.swing.JLabel lblImpuesto;
    private javax.swing.JLabel lblMonto;
    private javax.swing.JLabel lblMonto_precio;
    private javax.swing.JLabel lblNumeroCliente;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigoproducto;
    private javax.swing.JTextField txtDescGeneral;
    private javax.swing.JTextField txtDescLinea;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtMontopago1;
    private javax.swing.JTextField txtMontopago2;
    private javax.swing.JTextField txtMontopago3;
    private javax.swing.JTextField txtMontopago4;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtRUC;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtTelefono1;
    private javax.swing.JTextField txtTelefono2;
    // End of variables declaration//GEN-END:variables

}
