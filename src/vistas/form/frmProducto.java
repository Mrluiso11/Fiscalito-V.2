/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vistas.form;

import Style.Forms;
import conexion.Conexion;
import java.awt.Container;
import java.sql.Connection;
import controladores.*;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


/**
 *
 * @author dbpan
 */
public class frmProducto extends javax.swing.JPanel {

    private Container bgContainer;
    String operacion = "";
    private ArrayList<JTextField> camposDeTexto = new ArrayList<>();

    /**
     * Creates new form frmArticulos
     */
    public frmProducto() {
        initComponents();
       bgContainer = this;
        Forms formsPanel = new Forms(bgContainer,jPTitle);
        inhabilitar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jPTitle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cbxImpuesto = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaDescripcion = new javax.swing.JTextArea();
        txtNombreProducto = new javax.swing.JTextField();
        cbxMagnitud = new javax.swing.JComboBox<>();

        setOpaque(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(false);
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPTitle.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(125, 125, 125));
        jLabel1.setText("DATOS DE PRODUCTO");

        javax.swing.GroupLayout jPTitleLayout = new javax.swing.GroupLayout(jPTitle);
        jPTitle.setLayout(jPTitleLayout);
        jPTitleLayout.setHorizontalGroup(
            jPTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(841, Short.MAX_VALUE))
        );
        jPTitleLayout.setVerticalGroup(
            jPTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        bg.add(jPTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 1040, 50));
        bg.add(txtCodigoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 280, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Impuesto (%) :");
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Código del Producto :");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Descripción :");
        bg.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Magnitud de modelo : ");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, -1, -1));
        bg.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, 220, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Precio :");
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, -1, -1));

        cbxImpuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "7", "10", "15" }));
        bg.add(cbxImpuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 530, 90, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Producto :");
        bg.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 110, -1, -1));

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        bg.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 660, 120, 40));

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        bg.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 660, 120, 40));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        bg.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 660, 120, 40));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        bg.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 160, 120, 40));

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        bg.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 660, 120, 40));

        txtaDescripcion.setColumns(20);
        txtaDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtaDescripcion);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 970, 140));
        bg.add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 130, 450, -1));

        cbxMagnitud.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Unidades", "Piezas", "Docenas", "cm", "m", "m cuadrados", "m Cúbicos", "Pulgadas", "Yardas", "Yardas líneales", "Pies ", "Pies Cúbicos", "Litros", "Galones", "Pintas", "Onzas", "Kg", "Lb", "Páginas", "Resmas", "Toneladas" }));
        bg.add(cbxMagnitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 410, 110, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 1096, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
       operacion = "nuevo";
        habilitar();
        btnBuscar.setEnabled(false);
        btnNuevo.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       operacion = "modificar";
        habilitar();
        btnEditar.setEnabled(false);
        btnBuscar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnNuevo.setEnabled(false);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Connection conexion = Conexion.obtenerConexion();
        Productos obj_insertProductos = new Productos();
        if (txtCodigoProducto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El Codigo de Producto no puede estar vacio", "Error", JOptionPane.ERROR_MESSAGE);
            txtCodigoProducto.requestFocus();
        } else {
            if (conexion != null) {

                obj_insertProductos.setCodigoproducto(txtCodigoProducto.getText().trim());
                obj_insertProductos.setNombreproducto(txtNombreProducto.getText().trim());
                obj_insertProductos.setDescripcion(txtaDescripcion.getText().trim());
                obj_insertProductos.setMagnitud(cbxMagnitud.getSelectedItem().toString());
                obj_insertProductos.setPrecio(Float.parseFloat(txtPrecio.getText().trim()));
                obj_insertProductos.setItbms(Double.parseDouble(cbxImpuesto.getSelectedItem().toString()));
                
                if (operacion.equals("nuevo")) {
                    obj_insertProductos.insertProductos(conexion, obj_insertProductos); // Pasar el objeto obj_insertProductos
                    inhabilitar();
                    limpiarCampos();

                    // Notificar al usuario
                    JOptionPane.showMessageDialog(null, "Los datos se han guardado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else if (operacion.equals("modificar")) {
                    obj_insertProductos.updateProductoporCodigo(conexion, obj_insertProductos);
                    inhabilitar();
                    limpiarCampos();

                    // Notificar al usuario
                    JOptionPane.showMessageDialog(null, "Los datos se han actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }

                Conexion.cerrarConexion(conexion);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Connection conexion = Conexion.obtenerConexion();
        Productos productos = new Productos(); // Crear un objeto de la clase Clientes
        if (conexion != null) {
            productos.setCodigoproducto(txtCodigoProducto.getText().trim());
            productos.selectProductoporCodigo(conexion); // Llama al método en la clase Clientes
            Conexion.cerrarConexion(conexion);
        }

        if (productos.getNombreproducto() != null) {
            // Cliente encontrado, habilita los botones
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnNuevo.setEnabled(false);
        } else {
            // Cliente no encontrado, deshabilita los botones
            btnEditar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }

        // Actualiza los campos de texto y áreas de texto en el formulario aquí
        txtNombreProducto.setText(productos.getNombreproducto());
        txtCodigoProducto.setText(productos.getCodigoproducto());
        txtaDescripcion.setText(productos.getDescripcion());
        txtPrecio.setText(String.valueOf(productos.getPrecio()));
        cbxImpuesto.setSelectedItem(productos.getItbms());
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        Connection conexion = Conexion.obtenerConexion();
        // Crear un objeto de la clase producto
        Productos productos = new Productos();
        if (conexion != null) {
            //cliente.setRuc(txtRUC.getText().trim());
            productos.setCodigoproducto(txtCodigoProducto.getText().trim());
            productos.deleteProductoporCodigo(conexion);
            Conexion.cerrarConexion(conexion);
            limpiarCampos();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed
                                                                                                                                                                                                        
    private void cbxMagnitudActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    
    public void inhabilitar() {
        txtNombreProducto.setEnabled(false);
        txtCodigoProducto.setEnabled(true);
        txtaDescripcion.setEnabled(false);
        txtPrecio.setEnabled(false);
        cbxMagnitud.setEnabled(false);
        cbxImpuesto.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(true);
        
    }

    public void habilitar() {
        txtNombreProducto.setEnabled(true);
        txtCodigoProducto.setEnabled(true);
        txtaDescripcion.setEnabled(true);
        txtPrecio.setEnabled(true);
        cbxMagnitud.setEnabled(true);
        cbxImpuesto.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
    }
    
    public void limpiarCampos() {
        for (JTextField campo : camposDeTexto) {
            campo.setText("");
        }
        txtNombreProducto.setText("");
        txtCodigoProducto.setText("");
        txtaDescripcion.setText("");
        txtPrecio.setText("");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cbxImpuesto;
    private javax.swing.JComboBox<String> cbxMagnitud;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCodigoProducto;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextArea txtaDescripcion;
    // End of variables declaration//GEN-END:variables
}
