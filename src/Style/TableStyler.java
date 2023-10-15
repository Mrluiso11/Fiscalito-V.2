package Style;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import vistas.swing.scroll.ScrollBar;
import vistas.system.SystemColor;

public class TableStyler extends JTable {

    public static void applyStyles(JTable table) {
        // Aplicar el renderizador personalizado a cada columna
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
                Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
                com.setBackground(Color.WHITE); // Establecer fondo blanco por defecto
                setBorder(noFocusBorder);
                // Cambiar el color del texto cuando está seleccionado
                if (selected) {
                    com.setForeground(SystemColor.MAIN_COLOR_1); // Cambiar el color del texto
                } else {
                    com.setForeground(new Color(102, 102, 102));
                }
                return com;
            }
        };

        // Aplicar el renderizador personalizado a cada columna
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
       
        // Otras personalizaciones de estilo
        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(230, 230, 230));
        table.setRowHeight(40);
        table.getTableHeader().setReorderingAllowed(false);
        table.setShowGrid(true);
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
