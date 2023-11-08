package Style;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import vistas.swing.scroll.ScrollBar;
import vistas.system.SystemColor;

public class TableStyler extends JTable {

    public static void applyStyles(JTable table) {
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int row, int col) {
                Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, row, col);
                com.setBackground(Color.WHITE);
                setBorder(noFocusBorder);

                if (selected) {
                    com.setForeground(Color.WHITE);
                    com.setBackground(new Color(41, 128, 185));
                } else {
                    com.setForeground(new Color(102, 102, 102));
                    com.setBackground(Color.WHITE);
                }

                // Centrar el contenido en la celda
                setHorizontalAlignment(JLabel.CENTER);

                return com;
            }
        };

        // Aplicar el renderizador personalizado a cada columna
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        // Agregar un manejador de clics en la tabla para deseleccionar al hacer doble clic
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Doble clic
                    table.clearSelection();
                }
            }
        });

        // Otras personalizaciones de estilo
        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setRowHeight(40);
        table.getTableHeader().setReorderingAllowed(false);
        table.setShowGrid(true);

        // Aplicar estilos al encabezado
        CustomTableHeaderRenderer.applyStylesToHeader(table);
    }

    public void fixTable(JScrollPane scroll) {
        scroll.setBorder(null);
        scroll.setVerticalScrollBar(new ScrollBar());
        scroll.getVerticalScrollBar().setBackground(Color.WHITE);
        scroll.getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    }
}