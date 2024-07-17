package vistas.form;

import conexion.*;
import controladores.Empresa;
import java.awt.Image;
import java.sql.Connection;
import javax.swing.ImageIcon;
import java.sql.SQLException;

public class frm_Dashboard extends javax.swing.JPanel {

    public frm_Dashboard() {
        initComponents();
        init();
    }

    private void init() {
        mostrarDatosEmpresa();
    }

    public void mostrarDatosEmpresa() {
        // Se obtiene una conexión a la base de datos
        Connection conexion = Conexion.obtenerConexion();

        // Se crea un objeto de la clase "Empresa" para manejar la información de la empresa
        Empresa empresa = new Empresa();

        // Verifica si la conexión a la base de datos es exitosa
        if (conexion != null) {
            // Selecciona la información de la empresa desde la base de datos
            empresa.selectEmpresa(conexion);

            // Variable para almacenar si la tabla tiene datos
            boolean tieneDatos = false;

            // Verifica si la tabla tiene datos
            try {
                tieneDatos = empresa.tablaTieneDatos(conexion);
            } catch (SQLException e) {
                e.printStackTrace(); // Manejo adecuado de la excepción
            }
            // Se cierra la conexión a la base de datos
            Conexion.cerrarConexion(conexion);

            // Verificar si la empresa tiene datos
            if (tieneDatos) {
                
                lblNombreEmpresa.setText(empresa.getNombre());
                lblNombreComercialEmpresa.setText(empresa.getNombre_comercial());
                lblRUC.setText(empresa.getRuc());
                lblDireccion.setText(empresa.getPais() + "," + empresa.getProvincia() + "," + empresa.getDistrito());
                lblTelefono.setText(empresa.getTelefono1() + " / " + empresa.getTelefono2());
                lblDV.setText(empresa.getDv());
                lblFax.setText(empresa.getFax1() + " / " + empresa.getFax2());
                lblActividades.setText(empresa.getActividades());

                // Manejo de las imágenes de la empresa
                byte[] logoEmpresaBytes = empresa.getLogo_empresa();
                if (logoEmpresaBytes != null) {
                    try {
                        // Convertir el array de bytes a una ImageIcon
                        ImageIcon fotoEmpresa = new ImageIcon(logoEmpresaBytes);

                        // Escalar la imagen al tamaño deseado
                        int widthEmpresa = 154;  // Tamaño deseado para el ancho
                        int heightEmpresa = 119; // Tamaño deseado para el alto
                        Image fotoEscalada = fotoEmpresa.getImage().getScaledInstance(widthEmpresa, heightEmpresa, Image.SCALE_SMOOTH);

                        // Crear un nuevo ImageIcon con la imagen escalada
                        ImageIcon fotoEscaladaIcon = new ImageIcon(fotoEscalada);

                        // Establecer el nuevo ImageIcon en el JLabel
                        imgFotoEmpresa.setIcon(fotoEscaladaIcon);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } 
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        lblNombreEmpresa = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblRUC = new javax.swing.JLabel();
        lblNombreComercialEmpresa = new javax.swing.JLabel();
        lblDV = new javax.swing.JLabel();
        lblFax = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblActividades = new javax.swing.JLabel();
        imgFotoEmpresa = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1096, 934));

        bg.setOpaque(false);
        bg.setVerifyInputWhenFocusTarget(false);
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNombreEmpresa.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        lblNombreEmpresa.setForeground(new java.awt.Color(102, 102, 102));
        lblNombreEmpresa.setText("lblNombreEmpresa");
        bg.add(lblNombreEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 610, 60));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel2.setText("Datos de la Empresa");
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(84, 187, 251));
        jSeparator1.setForeground(new java.awt.Color(84, 187, 251));
        jSeparator1.setOpaque(true);
        bg.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 540, 800, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Nombre de la Razón Social:");
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, -1, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("R.U.C. :");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Dirección:");
        bg.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Telefono:");
        bg.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 450, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("D.V:");
        bg.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 370, -1, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Fax:");
        bg.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 450, -1, -1));

        jSeparator2.setBackground(new java.awt.Color(84, 187, 251));
        jSeparator2.setForeground(new java.awt.Color(84, 187, 251));
        jSeparator2.setOpaque(true);
        bg.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 800, -1));

        lblRUC.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        lblRUC.setText("RUC");
        bg.add(lblRUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 80, -1));

        lblNombreComercialEmpresa.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        lblNombreComercialEmpresa.setText("NombreComercialEmpresa");
        bg.add(lblNombreComercialEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 290, -1, -1));

        lblDV.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        lblDV.setText("DV");
        bg.add(lblDV, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 390, -1, -1));

        lblFax.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        lblFax.setText("Fax");
        bg.add(lblFax, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 470, -1, -1));

        lblDireccion.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        lblDireccion.setText("Direccion");
        bg.add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 410, -1, -1));

        lblTelefono.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        lblTelefono.setText("Telefono");
        bg.add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 470, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(84, 187, 251)));
        jPanel1.setOpaque(false);

        lblActividades.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        lblActividades.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblActividades.setText("Actividades");
        lblActividades.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblActividades, javax.swing.GroupLayout.PREFERRED_SIZE, 837, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblActividades, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addContainerGap())
        );

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 560, 860, 110));

        imgFotoEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        bg.add(imgFotoEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 40, 170, 160));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 846, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JLabel imgFotoEmpresa;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblActividades;
    private javax.swing.JLabel lblDV;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblFax;
    private javax.swing.JLabel lblNombreComercialEmpresa;
    private javax.swing.JLabel lblNombreEmpresa;
    private javax.swing.JLabel lblRUC;
    private javax.swing.JLabel lblTelefono;
    // End of variables declaration//GEN-END:variables
}
