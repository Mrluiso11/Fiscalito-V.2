/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Style;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class CustomTabbedPaneUI extends BasicTabbedPaneUI {

    private Color selectedColor = new Color(0, 158, 249); // Color cuando está seleccionada (#099EF9)
    private Color deselectedColor = new Color(134, 207, 252); // Color cuando no está seleccionada (#86cffc)

    @Override
    protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        Color color = isSelected ? selectedColor : deselectedColor;
        g.setColor(color);
        g.fillRoundRect(x, y, w, h, 10, 10); // Ajusta el radio para hacer las pestañas redondeadas
    }

    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        // Puedes personalizar el borde de las pestañas si lo deseas.
        // En este ejemplo, no estamos dibujando un borde, por lo que no se muestra.
    }

    @Override
    protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
        if (isSelected) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.BLACK);
        }
        g.setFont(font);
        g.drawString(title, textRect.x, textRect.y + metrics.getAscent());
    }
}