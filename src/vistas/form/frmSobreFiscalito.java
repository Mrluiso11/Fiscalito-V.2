/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vistas.form;


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
 * @author admin
 */
public class frmSobreFiscalito extends javax.swing.JPanel {

    /**
     * Creates new form frmSobreFiscalito
     */
    public frmSobreFiscalito() {
        initComponents();
            txtaSobre.setEditable(false);
            txtaSobre.setOpaque(false); // Quita el fondo
            txtaSobre.setBorder(null); 
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
        lblLogo = new javax.swing.JLabel();
        btnConoce = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaSobre = new javax.swing.JTextArea();

        bg.setOpaque(false);
        bg.setVerifyInputWhenFocusTarget(false);
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(102, 102, 102));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo_fiscalito_final_mini.png"))); // NOI18N
        bg.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 60, 200, 70));

        btnConoce.setBackground(new java.awt.Color(102, 153, 255));
        btnConoce.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnConoce.setForeground(new java.awt.Color(255, 255, 255));
        btnConoce.setText("Conoce Sobre");
        bg.add(btnConoce, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 420, 170, 50));

        jScrollPane1.setBackground(new java.awt.Color(242, 242, 242));
        jScrollPane1.setBorder(null);

        txtaSobre.setColumns(20);
        txtaSobre.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        txtaSobre.setLineWrap(true);
        txtaSobre.setRows(6);
        txtaSobre.setText("Fiscalito inicia en el 2013 con la misión facilitar el manejo de facturación fiscal por medio de su software. Nuestro proyecto en el 2023 consta del desarrollo y actualización de este programa de facturación fiscal con la Version 2.0, se diseñaron nuevas características y mejoras en su interfaz para que sea más amigable con el usuario al momento de utilizar programa de facturación fiscal.  Esto podría incluir la automatización de tareas repetitivas, la generación de informes fiscales específicos o la mejora de la usabilidad general del software. Adicional, la migración de datos de una base de datos antigua como FoxPro a SQL Lite para su administración correspondiente.");
        txtaSobre.setWrapStyleWord(true);
        jScrollPane1.setViewportView(txtaSobre);

        bg.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 880, 190));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnConoce;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JTextArea txtaSobre;
    // End of variables declaration//GEN-END:variables
}
