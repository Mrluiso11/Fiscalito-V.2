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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

/**
 *
 * @author dbpan
 */
public class frmClientes extends javax.swing.JPanel {

    private Container bgContainer;
    String operacion = "";
    private ArrayList<JTextField> camposDeTexto = new ArrayList<>();
    Icon customIcon = new ImageIcon(getClass().getResource("/img/check_icon2.png"));
    private JTextField[] textFieldsToStyle = new JTextField[5];

    /**
     * Creates new form frmClientes
     */
    public frmClientes() {
        initComponents();
        bgContainer = this;
        Forms formsPanel = new Forms(bgContainer, jPTitle);
        textFieldsToStyle[0] = txtNombreCliente;
        textFieldsToStyle[1] = txttelefono1;
        textFieldsToStyle[2] = txtTelefono2;
        textFieldsToStyle[3] = txtEmail;
        textFieldsToStyle[4] = txtRUC;

        inhabilitar();
        // Agregar campos de texto a la lista
        camposDeTexto.add(txtNombreCliente);
        camposDeTexto.add(txttelefono1);
        camposDeTexto.add(txtTelefono2);
        camposDeTexto.add(txtEmail);
        camposDeTexto.add(txtRUC);
        ObtenerNombreClientes();

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
        cbxClientes = new javax.swing.JComboBox<>();

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
        bg.add(txtRUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 180, 280, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("R.U.C. :");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 160, -1, -1));

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
        bg.add(lblNumCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 20, 20));

        btnEliminar.setBackground(new java.awt.Color(102, 153, 255));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        bg.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 710, 120, 40));

        btnNuevo.setBackground(new java.awt.Color(102, 153, 255));
        btnNuevo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNuevo.setForeground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
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
        bg.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 100, 120, 40));

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
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        bg.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 710, 120, 40));

        txtareaDireccion.setColumns(20);
        txtareaDireccion.setRows(5);
        jScrollPane1.setViewportView(txtareaDireccion);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 980, 120));

        txtaObservaciones.setColumns(20);
        txtaObservaciones.setRows(5);
        jScrollPane2.setViewportView(txtaObservaciones);

        bg.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, 980, 130));

        txtNombreCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreClienteActionPerformed(evt);
            }
        });
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

        cbxClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        bg.add(cbxClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 380, -1));

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
    public void limpiarCampos() {
        for (JTextField campo : camposDeTexto) {
            campo.setText("");
        }
        txtareaDireccion.setText("");
        txtaObservaciones.setText("");
    }
    
    
    private void txtTelefono2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefono2ActionPerformed

    }//GEN-LAST:event_txtTelefono2ActionPerformed
    private void ObtenerNombreClientes() {
        Clientes cliente = new Clientes();
        Connection conexion = Conexion.obtenerConexion();

        try {

            List<String> nombres = cliente.getAllNombres(conexion);

            cbxClientes.removeAllItems();

            for (String nombre : nombres) {
                cbxClientes.addItem(nombre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // Se inicia la acción del botón "Buscar"

        // Se obtiene una conexión a la base de datos
        Connection conexion = Conexion.obtenerConexion();

        // Se crea un objeto de la clase "Clientes" para manejar la información del cliente
        Clientes cliente = new Clientes();

        // Verifica si la conexión a la base de datos es exitosa
        if (conexion != null) {
            // Se establece el nombre del cliente seleccionado en un JComboBox
            cliente.setNombre(cbxClientes.getSelectedItem().toString());

            // Se llama al método "selectClientePorNombre" en la clase "Clientes" para buscar el cliente por nombre
            cliente.selectClientePorNombre(conexion);

            // Se cierra la conexión a la base de datos
            Conexion.cerrarConexion(conexion);
        }

        if (cliente.getNombre() != null) {
            // Si se encontró al cliente en la base de datos:

            // Habilita los botones de "Editar" y "Eliminar" y deshabilita el botón "Nuevo"
            btnEditar.setEnabled(true);
            btnEliminar.setEnabled(true);
            btnNuevo.setEnabled(false);
        } else {
            // Si el cliente no se encontró en la base de datos:

            // Deshabilita los botones de "Editar" y "Eliminar"
            btnEditar.setEnabled(false);
            btnEliminar.setEnabled(false);
        }

        // Actualiza los campos de texto y áreas de texto en el formulario con la información del cliente
        lblNumCliente.setText(cliente.getCodigo_cliente());
        txtRUC.setText(cliente.getRuc());
        txtNombreCliente.setText(cliente.getNombre());
        txtareaDireccion.setText(cliente.getDireccion());
        txttelefono1.setText(cliente.getTelefono1());
        txtTelefono2.setText(cliente.getTelefono2());
        txtEmail.setText(cliente.getCorreo());
        txtaObservaciones.setText(cliente.getObservaciones());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txttelefono1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefono1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefono1ActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
        // Se inicia la acción del botón "Guardar"

        // Se obtiene una conexión a la base de datos
        Connection conexion = Conexion.obtenerConexion();

        // Se crea un objeto de la clase "Clientes" para manejar la información del cliente a insertar o actualizar
        Clientes obj_insertClientes = new Clientes();

        // Verifica si el campo está vacío
        if (txtNombreCliente.getText().trim().isEmpty()) {
            // Muestra un mensaje de error si el campo está vacío
            JOptionPane.showMessageDialog(null, "El Nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            txtNombreCliente.requestFocus();
        }
        if (txtareaDireccion.getText().trim().isEmpty()) {
            // Muestra un mensaje de error si el campo está vacío
            JOptionPane.showMessageDialog(null, "La Direccion no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            txtareaDireccion.requestFocus();
        }
        if (txttelefono1.getText().trim().isEmpty()) {
            // Muestra un mensaje de error si el campo está vacío
            JOptionPane.showMessageDialog(null, "El telefono no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            txttelefono1.requestFocus();
        }
        if (txtTelefono2.getText().trim().isEmpty()) {
            // Muestra un mensaje de error si el campo está vacío
            JOptionPane.showMessageDialog(null, "El telefono no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            txtTelefono2.requestFocus();
        }
        if (txtEmail.getText().trim().isEmpty()) {
            // Muestra un mensaje de error si el campo está vacío
            JOptionPane.showMessageDialog(null, "El email no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            txtEmail.requestFocus();
        }
        if (txtaObservaciones.getText().trim().isEmpty()) {
            // Muestra un mensaje de error si el campo está vacío
            JOptionPane.showMessageDialog(null, "El telefono no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            txtaObservaciones.requestFocus();
        }
        if (txtRUC.getText().trim().isEmpty()) {
            // Muestra un mensaje de error si el campo está vacío
            JOptionPane.showMessageDialog(null, "El R.C.U no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            txtRUC.requestFocus();
        } else {

            if (conexion != null) {
                // Se asignan los valores de los campos del formulario al objeto "obj_insertClientes"

                obj_insertClientes.setRuc(txtRUC.getText().trim());
                obj_insertClientes.setNombre(txtNombreCliente.getText().trim());
                obj_insertClientes.setDireccion(txtareaDireccion.getText().trim());
                obj_insertClientes.setTelefono1(txttelefono1.getText().trim());
                obj_insertClientes.setTelefono2(txtTelefono2.getText().trim());
                obj_insertClientes.setCorreo(txtEmail.getText().trim());
                obj_insertClientes.setObservaciones(txtaObservaciones.getText().trim());

                if (operacion.equals("nuevo")) {
                    String cruc = txtRUC.getText().trim();
                    // Obtiene una lista de RUC de clientes existentes en la base de datos.
                    // Obtiene una lista de RUC de clientes existentes en la base de datos.
                    Clientes obj_insertCliente = new Clientes();

                    List<String> rucList = obj_insertCliente.getAllRUC(conexion);

                     // Recorre la lista de RUC de clientes.
                    for (String existingRuc : rucList) {
                        // Comprueba si el RUC de cliente ya existe en la base de datos.
                        if (existingRuc.trim().equalsIgnoreCase(txtRUC.getText().trim())) {
                            // Muestra un mensaje de error y detiene el flujo si el código existe.
                            JOptionPane.showMessageDialog(null, "Este código del R.U.C. del cliente ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                            return; // Sale del método actual y detiene el flujo del programa
                        }
                    }

                    // Si se está realizando una operación de inserción:
                    // Llama al método "insertClientes" en la clase "Clientes" para agregar el cliente a la base de datos
                    int filasAfectadas = obj_insertClientes.insertClientes(conexion, obj_insertClientes);

                    if (filasAfectadas > 0) {
                        // Si se insertaron filas con éxito, muestra un mensaje de éxito
                        JOptionPane.showMessageDialog(null, "Los datos se han guardado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE, customIcon);
                        // Llama a métodos para limpiar y deshabilitar campos y habilitar el botón "Nuevo"
                        inhabilitar();
                        btnNuevo.setEnabled(true);
                        cbxClientes.setEnabled(true);
                        limpiarCampos();
                    } else {
                        // Si no se actualizaron filas, muestra un mensaje de error.
                        JOptionPane.showMessageDialog(null, "Los datos no se han podido guardar", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (operacion.equals("modificar")) {
                    // Si se está realizando una operación de actualización:

                    // Llama al método "updateClientePorRuc" en la clase "Clientes" para actualizar el cliente en la base de datos
                    int filasAfectadas = obj_insertClientes.updateClientePorRuc(conexion, obj_insertClientes);

                    if (filasAfectadas > 0) {
                        // Si se actualizaron filas con éxito, muestra un mensaje de éxito
                        JOptionPane.showMessageDialog(null, "Los datos se han actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE, customIcon);
                        // Llama a métodos para limpiar y deshabilitar campos y habilitar el botón "Nuevo"
                        inhabilitar();
                        btnNuevo.setEnabled(true);
                        cbxClientes.setEnabled(true);
                        limpiarCampos();
                    } else {
                        // Si no se actualizaron filas, muestra un mensaje de error.
                        JOptionPane.showMessageDialog(null, "Los datos no se han podido actualizar", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // Cierra la conexión a la base de datos
                Conexion.cerrarConexion(conexion);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // Se inicia la acción del botón "Eliminar"

        // Se obtiene una conexión a la base de datos
        Connection conexion = Conexion.obtenerConexion();

        // Se crea un objeto de la clase "Clientes" para manejar la información del cliente a eliminar
        Clientes cliente = new Clientes();

        if (conexion != null) {
            // Se obtiene el nombre del cliente del campo de texto
            String nombre = txtNombreCliente.getText();

            // Se muestra un cuadro de diálogo de confirmación para asegurarse de que el usuario quiere eliminar el cliente
            int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro de querer eliminar este cliente?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                // Si el usuario confirma la eliminación:

                // Se establece el RUC del cliente a eliminar en el objeto "cliente"
                cliente.setRuc(txtRUC.getText().trim());

                // Se llama al método "deleteClientePorRuc" en la clase "Clientes" para eliminar el cliente de la base de datos
                int filasAfectadas = cliente.deleteClientePorRuc(conexion);

                // Se cierra la conexión a la base de datos
                Conexion.cerrarConexion(conexion);

                if (filasAfectadas > 0) {
                    // Si se eliminaron filas con éxito, muestra un mensaje de éxito
                    JOptionPane.showMessageDialog(null, "El Cliente: " + nombre + " se ha eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE, customIcon);

                    // Habilita el botón "Nuevo" y limpia los campos
                    btnNuevo.setEnabled(true);
                    limpiarCampos();
                } else {
                    // Si no se eliminaron filas, muestra un mensaje de error
                    JOptionPane.showMessageDialog(null, "El Cliente: " + nombre + " no se ha podido eliminar.", "Error", JOptionPane.ERROR_MESSAGE);

                    // Habilita el botón "Nuevo"
                    btnNuevo.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    public void inhabilitar() {
        // Este método se encarga de deshabilitar los elementos de la interfaz de usuario, establecer colores de fondo y texto, y actualizar el estado de los botones.

        // Se deshabilitan los elementos de la interfaz de usuario
        lblNumCliente.setEnabled(false);
        txtNombreCliente.setEnabled(false);
        txtareaDireccion.setEnabled(false);
        txttelefono1.setEnabled(false);
        txtTelefono2.setEnabled(false);
        txtEmail.setEnabled(false);
        txtaObservaciones.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnBuscar.setEnabled(true);
        txtRUC.setEnabled(false);

        // Se establecen colores de fondo para los campos de texto y áreas de texto
        txtareaDireccion.setBackground(new Color(214, 234, 248));
        txtaObservaciones.setBackground(new Color(214, 234, 248));

        // Se llama al método "colorTexfiel" para actualizar los colores de fondo de los campos de texto
        colorTexfiel();
    }

    public void colorTexfiel() {
        // Este método se encarga de actualizar los colores de fondo y texto de los campos de texto según su estado de habilitación.

        // Se itera a través de los campos de texto especificados en el array "textFieldsToStyle"
        for (JTextField textField : textFieldsToStyle) {
            if (textField != null) { // Verificar que el textField no sea nulo
                if (!textField.isEnabled()) {
                    // Si el campo de texto está deshabilitado, se cambia el color de fondo y texto
                    textField.setBackground(new Color(214, 234, 248));
                } else {
                    // Si el campo de texto está habilitado, se restablecen los colores originales
                    textField.setBackground(Color.WHITE);
                }
            }
        }
    }

    public void habilitar() {
        // Este método se encarga de habilitar los elementos de la interfaz de usuario, establecer colores de fondo y texto, y actualizar el estado de los botones.

        // Se habilitan los elementos de la interfaz de usuario
        lblNumCliente.setEnabled(true);
        txtNombreCliente.setEnabled(true);
        txtareaDireccion.setEnabled(true);
        txttelefono1.setEnabled(true);
        txtTelefono2.setEnabled(true);
        txtEmail.setEnabled(true);
        txtaObservaciones.setEnabled(true);
        btnGuardar.setEnabled(true);
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true);
        txtRUC.setEnabled(true);

        // Se establecen colores de fondo para los campos de texto y áreas de texto
        txtareaDireccion.setBackground(Color.WHITE);
        txtaObservaciones.setBackground(Color.WHITE);

        // Se llama al método "colorTexfiel" para actualizar los colores de fondo de los campos de texto
        colorTexfiel();
    }


    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        operacion = "modificar";
        habilitar();
        btnEditar.setEnabled(false);
        btnBuscar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnNuevo.setEnabled(false);
        lblNumCliente.setText("");
       txtRUC.setEnabled(false);
        txtRUC.setBackground(new Color(214, 234, 248));
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        operacion = "nuevo";
        habilitar();
        btnBuscar.setEnabled(false);
        btnNuevo.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        cbxClientes.setEnabled(false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void txtNombreClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cbxClientes;
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
