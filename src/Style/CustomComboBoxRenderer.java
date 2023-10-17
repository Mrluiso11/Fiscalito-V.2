/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Style;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author dbpan
 */
public class CustomComboBoxRenderer extends DefaultListCellRenderer {
    private final Icon arrowIcon = UIManager.getIcon("ComboBox.arrowIcon");

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        // Cambia el color de fondo cuando se selecciona o se hace hover
        if (isSelected || (index >= 0 && list.isSelectedIndex(index))) {
            label.setBackground(Color.decode("#54bbfb"));
        } else {
            label.setBackground(Color.WHITE);
        }

        // Agrega el icono de la flecha hacia abajo
        if (index == -1) {
            label.setIcon(arrowIcon);
        }

        return label;
    }
}