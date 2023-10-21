package vistas.form;

import Style.CustomTabbedPaneUI;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import Style.Forms;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author dbpan
 */
public class FrmEmpresa extends javax.swing.JPanel {

    private Container bgContainer;

    /**
     * Creates new form FrmEmpresa
     */
    public FrmEmpresa() {
        initComponents();
        bgContainer = this;
        Forms formsPanel = new Forms(bgContainer, jPTitle);
        //BottomStyle(btnActualizar);
        JTabbedPane tabbedPane = new JTabbedPane();
// Agrega pestañas al tabbedPane

// Aplica el estilo personalizado al tabbedPane
        tabbedPane.setUI(new CustomTabbedPaneUI());
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel30 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jTextField33 = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel42 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jPTitle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1096, 849));

        bg.setForeground(new java.awt.Color(102, 102, 102));
        bg.setOpaque(false);
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Nombre de la empresa");
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Nombre comercial de la empresa");
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));
        bg.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 390, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("R.C.U");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jTextField3.setToolTipText("Asa");
        bg.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 170, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("D.V");
        bg.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, -1, -1));
        bg.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 160, -1));
        bg.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 390, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Teléfonos:");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, -1, 20));
        bg.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 180, -1));
        bg.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, 180, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Fax:");
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 100, -1, -1));
        bg.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 150, 160, -1));
        bg.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 120, 160, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setOpaque(false);

        jLabel44.setText("jLabel44");
        jLabel44.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel45.setText("jLabel45");
        jLabel45.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel47.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(102, 102, 102));
        jLabel47.setText("Logo en Factura");

        jLabel48.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(102, 102, 102));
        jLabel48.setText("Foto de la empresa");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel47)
                .addGap(32, 32, 32))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel48)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel47)
                .addGap(37, 37, 37)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel48)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        bg.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 110, 170, 470));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel8.setText("Dirección");
        bg.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(84, 187, 251));
        jSeparator1.setForeground(new java.awt.Color(84, 187, 251));
        jSeparator1.setToolTipText("");
        jSeparator1.setOpaque(true);
        bg.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 390, 3));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("País:");
        bg.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));
        bg.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 180, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Provincia:");
        bg.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 280, -1, -1));
        bg.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 190, -1));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Distrito:");
        bg.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, -1, -1));
        bg.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 390, -1));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Correguimiento:");
        bg.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));
        bg.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 390, -1));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Urbanización:");
        bg.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, -1, -1));
        bg.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 390, -1));

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Calle:");
        bg.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, -1, -1));
        bg.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 390, -1));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Casa #:");
        bg.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, -1, -1));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Piso:");
        bg.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 530, -1, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Local");
        bg.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 530, -1, -1));
        bg.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 550, 130, -1));
        bg.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 110, -1));
        bg.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 550, 110, -1));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Correo Electrónico");
        bg.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 580, -1, -1));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel19.setText("Representante Legal");
        bg.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, -1, -1));

        jSeparator2.setBackground(new java.awt.Color(84, 187, 251));
        jSeparator2.setForeground(new java.awt.Color(84, 187, 251));
        jSeparator2.setOpaque(true);
        bg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, 400, 3));

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Primer Nombre");
        bg.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, -1, -1));
        bg.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 190, -1));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 102, 102));
        jLabel21.setText("Segundo Nombre");
        bg.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 210, -1, -1));
        bg.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, 190, -1));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Apellido Paterno");
        bg.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, -1, -1));

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("Apellido Materno");
        bg.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 260, 110, -1));
        bg.add(jTextField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 190, -1));
        bg.add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 280, 190, -1));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("Cédula:");
        bg.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, -1, -1));
        bg.add(jTextField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 330, 190, -1));

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("D.V:");
        bg.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 310, -1, 20));
        bg.add(jTextField23, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 330, 190, -1));

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setText("Teléfonos:");
        bg.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 360, -1, -1));
        bg.add(jTextField24, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 380, 190, -1));
        bg.add(jTextField25, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 380, 190, -1));

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 102, 102));
        jLabel27.setText("Correo Electrónico");
        bg.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 410, -1, -1));
        bg.add(jTextField26, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 430, 400, -1));
        bg.add(jTextField27, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 600, 390, -1));

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel28.setText("Actividades:");
        bg.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 630, -1, -1));

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel29.setText("Observaciones:");
        bg.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 710, -1, -1));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        bg.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 730, 390, 50));

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel30.setText("Gerente");
        bg.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 460, -1, -1));

        jSeparator3.setBackground(new java.awt.Color(84, 187, 251));
        jSeparator3.setForeground(new java.awt.Color(84, 187, 251));
        jSeparator3.setOpaque(true);
        bg.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 480, 400, 3));

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(102, 102, 102));
        jLabel31.setText("Nombre:");
        bg.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 490, -1, -1));
        bg.add(jTextField28, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, 400, -1));

        jLabel32.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(102, 102, 102));
        jLabel32.setText("Cédula:");
        bg.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 540, -1, -1));
        bg.add(jTextField29, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 560, 180, -1));

        jLabel33.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(102, 102, 102));
        jLabel33.setText("D.V");
        bg.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 540, -1, -1));
        bg.add(jTextField30, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 560, 190, -1));

        jLabel34.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setText("Teléfonos");
        bg.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 580, -1, 20));
        bg.add(jTextField31, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 600, 180, -1));
        bg.add(jTextField32, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 600, 190, -1));

        jLabel35.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 102, 102));
        jLabel35.setText("Correo Electrónico");
        bg.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 630, -1, 20));
        bg.add(jTextField33, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 650, 400, -1));

        jSeparator4.setBackground(new java.awt.Color(84, 187, 251));
        jSeparator4.setForeground(new java.awt.Color(84, 187, 251));
        jSeparator4.setOpaque(true);
        bg.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 700, 400, 3));

        jLabel42.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel42.setText("Otros:");
        bg.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 710, -1, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setOpaque(false);

        btnActualizar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizar.setFocusPainted(false);
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton2.setText("Imprimir");
        jButton2.setFocusPainted(false);

        jButton3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        jButton3.setText("Limpiar");
        jButton3.setFocusPainted(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(btnActualizar)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        btnActualizar.getAccessibleContext().setAccessibleParent(bg);
        jButton2.getAccessibleContext().setAccessibleParent(bg);

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 600, 170, 170));

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        bg.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 650, 390, 50));

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane5.setViewportView(jTextArea5);

        bg.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 730, 390, 50));

        jPTitle.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(125, 125, 125));
        jLabel1.setText("DATOS DE LA EMPRESA");

        javax.swing.GroupLayout jPTitleLayout = new javax.swing.GroupLayout(jPTitle);
        jPTitle.setLayout(jPTitleLayout);
        jPTitleLayout.setHorizontalGroup(
            jPTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(830, Short.MAX_VALUE))
        );
        jPTitleLayout.setVerticalGroup(
            jPTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        bg.add(jPTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 1040, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPTitle;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
