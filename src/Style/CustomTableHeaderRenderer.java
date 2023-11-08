package Style;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class CustomTableHeaderRenderer extends DefaultTableCellRenderer {

    public CustomTableHeaderRenderer() {
        setBackground(Color.decode("#9fd9fd")); // Establece el fondo blanco
        setForeground(new Color(0, 0, 0)); // Establece el color de texto
        setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14)); // Personaliza la fuente
        setHorizontalAlignment(SwingConstants.CENTER); // Alinea al centro
    }

    public static void applyStylesToHeader(JTable table) {
        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new CustomTableHeaderRenderer());

        // Mostrar la línea divisoria en el encabezado
        header.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));

        // Modificar el texto del encabezado con un espacio al inicio y al final
        for (int i = 0; i < header.getColumnModel().getColumnCount(); i++) {
            header.getColumnModel().getColumn(i).setHeaderValue(" " + header.getColumnModel().getColumn(i).getHeaderValue() + " ");
        }
    }
}