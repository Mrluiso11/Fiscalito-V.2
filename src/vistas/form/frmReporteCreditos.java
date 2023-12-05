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
import documentgeneration.ReportePDF;
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
 * @author admin
 */
public class frmReporteCreditos extends javax.swing.JPanel {

    /**
     * Creates new form frmReporteCreditos
     */
    public frmReporteCreditos() {
        initComponents();
        cargarTableFactura();
        Forms formsPanel = new Forms(this, null);
        applyTableStyles(TableFacturas, jScrollPane2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        btnAplicar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableFacturas = new javax.swing.JTable();
        jdFecha2 = new com.toedter.calendar.JDateChooser();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jdFecha1 = new com.toedter.calendar.JDateChooser();
        lblTotalfacturado = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        btnImprimir = new javax.swing.JButton();

        bg1.setBackground(new java.awt.Color(245, 245, 245));
        bg1.setPreferredSize(new java.awt.Dimension(1156, 850));

        jPanel7.setOpaque(false);

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 204, 204));
        jLabel40.setText("Documentos Generados");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(324, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
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
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Fecha de Facturación", "ID Factura", "Tipo de Documento", "Status", "Nombre de Cliente", "SubTotal", "Impuestos", "Total", "DIF"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableFacturas.setEnabled(false);
        jScrollPane2.setViewportView(TableFacturas);

        jdFecha2.setDateFormatString("dd-MM-yyyy");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 204, 204));
        jLabel41.setText("Al");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 204, 204));
        jLabel42.setText("Del");

        jdFecha1.setDateFormatString("dd-MM-yyyy");

        lblTotalfacturado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalfacturado.setText("TotalFacturado");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel47.setText("Total Facturado :");

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1017, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel47)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblTotalfacturado, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jdFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(btnAplicar)))
                .addContainerGap(549, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAplicar, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42)
                    .addComponent(jdFecha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdFecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(lblTotalfacturado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bg1Layout = new javax.swing.GroupLayout(bg1);
        bg1.setLayout(bg1Layout);
        bg1Layout.setHorizontalGroup(
            bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg1Layout.createSequentialGroup()
                .addGroup(bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bg1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bg1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        bg1Layout.setVerticalGroup(
            bg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bg1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
private void applyTableStyles(JTable table, JScrollPane scrollPane) {
        TableStyler tableStyler = new TableStyler();
        TableStyler.applyStyles(table);  // Aplica estilos a la tabla
        tableStyler.fixTable(scrollPane); // Configura la apariencia del JScrollPane
        CustomTableHeaderRenderer.applyStylesToHeader(table); // Aplica estilos al encabezado de la tabla
    }
    private void btnAplicarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAplicarActionPerformed
        vargarTableFiltro();
    }//GEN-LAST:event_btnAplicarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
  Date fechaSeleccionada1 = jdFecha1.getDate();
        Date fechaSeleccionada2 = jdFecha2.getDate();
        String Tfacturado = lblTotalfacturado.getText();
        // Verifica si ambas fechas están seleccionadas
        if (fechaSeleccionada1 == null || fechaSeleccionada2 == null) {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona ambas fechas antes de imprimir.", "Fechas no seleccionadas", JOptionPane.WARNING_MESSAGE);
            return;  // Sale del método si las fechas no están seleccionadas
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String fechaFormateada1 = dateFormat.format(fechaSeleccionada1);
        String fechaFormateada2 = dateFormat.format(fechaSeleccionada2);

        // Crear una instancia de la clase ReportePDF
        ReportePDF reporte = new ReportePDF(fechaSeleccionada1, fechaSeleccionada2, null, Tfacturado,"Credito");

        // Llamar al método main de esa instancia
        reporte.main(null);
    }//GEN-LAST:event_btnImprimirActionPerformed
    private void cargarTableFactura() {
        Connection conexion = Conexion.obtenerConexion();
        DefaultTableModel modelo = (DefaultTableModel) TableFacturas.getModel();

        // Limpiar cualquier contenido que pueda haber en la tabla actualmente
        modelo.setRowCount(0);

        Documentos obj_documentos = new Documentos();

        // Obtener la lista de productos desde la base de datos
        List<Documentos> facturas = obj_documentos.selectDocumentos(conexion);

        // Llenar la tabla con los datos filtrados por "Sí" en factura.getCredito()
        for (Documentos factura : facturas) {
            // Verificar si factura.getCredito() es "Sí"
            // Verificar si factura.getCredito() es "Sí"
            if ("Si".equals(factura.getCredito())) {
                String td = "Credito";
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String fechaFormateada = dateFormat.format(factura.getFecha_registro());

                modelo.addRow(new Object[]{
                    fechaFormateada,
                    factura.getIDfactura(),
                    factura.getTipodocumento(),
                    td,
                    factura.getNombre(),
                    factura.getSubtotal2(),
                    factura.getImpuestos(),
                    factura.getTotal(),
                    factura.getDIF()
                });
            }
        }
    }

    private void vargarTableFiltro() {
        Connection conexion = Conexion.obtenerConexion();
        DefaultTableModel modelo = (DefaultTableModel) TableFacturas.getModel();
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
        // Variable para almacenar la suma total
        float sumaTotal = 0.0f;

        // Llenar la tabla con los datos filtrados por el rango de fechas y tipo de documento
        for (Documentos factura : facturas) {
            Date fechaFactura = factura.getFecha_registro();
            String tipoDocumentoFactura = factura.getTipodocumento();
            if ("Si".equals(factura.getCredito())) {
            // Verificar si la fecha de la factura está dentro del rango seleccionado
            // y si el tipo de documento coincide con la selección del JComboBox
            if (fechaFactura != null
                    && ((fechaSeleccionada1 == null || fechaFactura.after(fechaSeleccionada1) || fechaFactura.equals(fechaSeleccionada1))
                    && (fechaSeleccionada2 == null || fechaFactura.before(fechaSeleccionada2) || fechaFactura.equals(fechaSeleccionada2)))) {

                // Formatear la fecha
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String fechaFormateada = dateFormat.format(fechaFactura);
                String td = "Credito";
                // Agregar fila a la tabla
                modelo.addRow(new Object[]{
                    fechaFormateada,
                    factura.getIDfactura(),
                    factura.getTipodocumento(),
                    td,
                    factura.getNombre(),
                    factura.getSubtotal2(),
                    factura.getImpuestos(),
                    factura.getTotal(),
                    factura.getDIF()});

                // Sumar al total
                sumaTotal += factura.getTotal();
            }
            }
        }

        // Establecer el valor total en lblTotalFacturado
        lblTotalfacturado.setText(String.valueOf(sumaTotal));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableFacturas;
    private javax.swing.JPanel bg1;
    private javax.swing.JButton btnAplicar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdFecha1;
    private com.toedter.calendar.JDateChooser jdFecha2;
    private javax.swing.JLabel lblTotalfacturado;
    // End of variables declaration//GEN-END:variables
}
