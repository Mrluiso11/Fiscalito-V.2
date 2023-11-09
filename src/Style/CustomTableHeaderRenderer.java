package Style;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

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
            TableColumn column = header.getColumnModel().getColumn(i);
            column.setHeaderValue(" " + column.getHeaderValue() + " ");

            // Ajustar automáticamente el ancho de la columna según el contenido
            int headerWidth;
            TableCellRenderer headerRenderer = column.getHeaderRenderer();
            if (headerRenderer != null) {
                headerWidth = (int) headerRenderer.getTableCellRendererComponent(table, column.getHeaderValue(), false, false, 0, i).getPreferredSize().getWidth();
            } else {
                headerWidth = (int) column.getHeaderValue().toString().length() * 10; // Ajusta según la longitud del texto
            }
            int maxWidth = getMaxColumnWidth(table, i);
            column.setPreferredWidth(Math.max(headerWidth, maxWidth));
        }
    }

    private static int getMaxColumnWidth(JTable table, int columnIndex) {
        int maxWidth = 0;
        for (int row = 0; row < table.getRowCount(); row++) {
            TableCellRenderer cellRenderer = table.getCellRenderer(row, columnIndex);
            Object value = table.getValueAt(row, columnIndex);
            Component cellRendererComponent = cellRenderer.getTableCellRendererComponent(table, value, false, false, row, columnIndex);
            maxWidth = Math.max(maxWidth, cellRendererComponent.getPreferredSize().width);
        }
        return maxWidth;
    }
}
