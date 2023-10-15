/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Style;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;

public class CustomTableHeaderRenderer extends DefaultTableCellRenderer {
    public CustomTableHeaderRenderer() {
        setBackground(Color.decode("#9fd9fd")); // Establece el fondo blanco
        setForeground(new Color(0, 0, 0)); // Establece el color de texto
        setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14)); // Personaliza la fuente
       
    }
      public static void applyStylesToHeader(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new CustomTableHeaderRenderer());

        // Mostrar la línea divisoria en el encabezado
        header.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
    }
}