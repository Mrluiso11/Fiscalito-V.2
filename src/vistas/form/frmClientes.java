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

/**
 *
 * @author dbpan
 */
public class frmClientes extends javax.swing.JPanel {

    private Container bgContainer;

    /**
     * Creates new form frmClientes
     */
    public frmClientes() {
        initComponents();
        bgContainer = this;
        Forms formsPanel = new Forms(bgContainer, jPTitle);

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
        txtRUC = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lblNumCliente = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtTelefono2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtareaDireccion = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaObservaciones = new javax.swing.JTextArea();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txttelefono1 = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(false);
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPTitle.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(125, 125, 125));
        jLabel1.setText("DATOS DE CLIENTE");

        javax.swing.GroupLayout jPTitleLayout = new javax.swing.GroupLayout(jPTitle);
        jPTitle.setLayout(jPTitleLayout);
        jPTitleLayout.setHorizontalGroup(
            jPTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPTitleLayout.setVerticalGroup(
            jPTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        bg.add(jPTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 1040, 50));
        bg.add(txtRUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 130, 280, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("R.U.C. :");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, -1, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Observaciones: ");
        bg.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Teléfonos: ");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));
        bg.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, 340, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Correo Eletrónico:");
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, -1, -1));

        lblNumCliente.setBackground(new java.awt.Color(255, 255, 255));
        lblNumCliente.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        lblNumCliente.setForeground(new java.awt.Color(102, 102, 102));
        lblNumCliente.setText("#####");
        bg.add(lblNumCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        btnEliminar.setBackground(new java.awt.Color(102, 153, 255));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        bg.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 710, 120, 40));

        btnNuevo.setBackground(new java.awt.Color(102, 153, 255));
        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("Nuevo");
        bg.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 710, 120, 40));

        btnGuardar.setBackground(new java.awt.Color(102, 153, 255));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        bg.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 710, 120, 40));

        btnBuscar.setBackground(new java.awt.Color(102, 153, 255));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        bg.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 160, 120, 40));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Nombre : ");
        bg.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        txtTelefono2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefono2ActionPerformed(evt);
            }
        });
        bg.add(txtTelefono2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 410, 110, -1));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Dirección :");
        bg.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        btnEditar.setBackground(new java.awt.Color(102, 153, 255));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Editar");
        bg.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 710, 120, 40));

        txtareaDireccion.setColumns(20);
        txtareaDireccion.setRows(5);
        jScrollPane1.setViewportView(txtareaDireccion);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 980, 120));

        txtaObservaciones.setColumns(20);
        txtaObservaciones.setRows(5);
        jScrollPane2.setViewportView(txtaObservaciones);

        bg.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, 980, 130));
        bg.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 450, -1));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Cust# : ");
        bg.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        txttelefono1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefono1ActionPerformed(evt);
            }
        });
        bg.add(txttelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 110, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 1096, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTelefono2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefono2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefono2ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txttelefono1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefono1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefono1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Connection conexion = Conexion.obtenerConexion();
        if (conexion != null) {
            Insert_FrmClientes obj_insertClientes = new Insert_FrmClientes();
            obj_insertClientes.setRuc(txtRUC.getText().trim());
            obj_insertClientes.setNombre(txtNombreCliente.getText().trim());
            obj_insertClientes.setDireccion(txtareaDireccion.getText().trim());
            obj_insertClientes.setTelefono1(txttelefono1.getText().trim());
            obj_insertClientes.setTelefono2(txtTelefono2.getText().trim());
            obj_insertClientes.setCorreo(txtEmail.getText().trim());
            obj_insertClientes.setObservaciones(txtaObservaciones.getText().trim());
            obj_insertClientes.insertClientes(conexion, obj_insertClientes); // Pasar el objeto obj_insertClientes
            Conexion.cerrarConexion(conexion);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNumCliente;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtRUC;
    private javax.swing.JTextField txtTelefono2;
    private javax.swing.JTextArea txtaObservaciones;
    private javax.swing.JTextArea txtareaDireccion;
    private javax.swing.JTextField txttelefono1;
    // End of variables declaration//GEN-END:variables
}
