package Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.Border;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.text.JTextComponent;

public class Forms extends javax.swing.JPanel {

    private Container bg;
    private JPanel jPTitle;

    // Constructor que recibe un contenedor principal y un panel de título
    public Forms(Container container, JPanel jPTitle) {
        this.bg = container;
        this.jPTitle = jPTitle;
        aplicarEstiloEnFormulario();
        aplicarEstiloARoundedBorder(jPTitle);
        aplicarEstilosAComponentesUI();

    }

    // Aplica un borde redondeado al panel de título
    private void aplicarEstiloARoundedBorder(JPanel panel) {
        if (jPTitle != null) {
            Border roundedBorder = new RoundBorder(8, Color.decode("#54bbfb")); // Personaliza el radio y el color aquí
            panel.setBorder(roundedBorder);
        }
    }

    // Aplica estilos a todos los botones contenidos en el contenedor principal
    private void aplicarEstilosAComponentesUI() {
        SwingUtilities.invokeLater(() -> {
            Component[] components = getAllComponents(bg);
            for (Component component : components) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    aplicarEstilosAlBoton(button);
                } else if (component instanceof JComboBox) {
                    JComboBox<?> comboBox = (JComboBox<?>) component;
                    aplicarEstiloAComboBox(comboBox);
                }
            }
        });
    }

    private void aplicarEstiloAComboBox(JComboBox<?> comboBox) {
        comboBox.setBackground(Color.WHITE);
        comboBox.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
        // Configura el renderizador personalizado
        comboBox.setRenderer(new CustomComboBoxRenderer());
        // Aplica un borde redondeado similar al de JTextField

    }

    // Aplica estilos a un botón específico, incluyendo color de fondo, texto y bordes
    private void aplicarEstilosAlBoton(JButton button) {
        button.setBackground(Color.decode("#54bbfb")); // Fondo
        button.setForeground(Color.WHITE); // Texto
        button.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14)); // Fuente
        // Desactivar el enfoque visual
        button.setFocusPainted(false);
        // Cambiar el color de fondo al hacer hover
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(Color.decode("#099EF9"));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(Color.decode("#54bbfb"));
            }
        });
    }

    private void aplicarEstiloATextFields(Container container) {
    Component[] components = container.getComponents();

    Border defaultBorder = new RoundBorder(4, Color.decode("#F2F2F2")); // Borde por defecto
    Border focusBorder = new RoundBorder(4, Color.decode("#54bbfb")); // Borde cuando se selecciona

    for (Component component : components) {
        if (component instanceof JTextComponent) {
            final JTextComponent textComponent = (JTextComponent) component;
            textComponent.setBackground(Color.WHITE); // Establece el fondo a blanco
            textComponent.setForeground(Color.LIGHT_GRAY);
            textComponent.setFont(new Font("Arial", Font.BOLD, 12));
            textComponent.setBorder(defaultBorder); // Aplica el mismo borde redondeado

            // Agregar un FocusListener para cambiar el borde cuando se selecciona
            textComponent.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    textComponent.setBorder(focusBorder);
                }

                @Override
                public void focusLost(FocusEvent e) {
                    textComponent.setBorder(defaultBorder);
                }
            });
        }

        if (component instanceof Container) {
            aplicarEstiloATextFields((Container) component);
        }
    }
}


    // Aplica estilos a todos los campos de texto en el contenedor principal
    private void aplicarEstiloEnFormulario() {
        aplicarEstiloATextFields(bg);
    }

    // Clase interna para definir un borde redondeado personalizado
    class RoundBorder implements Border {

        private int radius;
        private Color color;

        public RoundBorder(int radius, Color color) {
            this.radius = radius;
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            RoundRectangle2D roundRect = new RoundRectangle2D.Float(x, y, width - 1, height - 1, radius, radius);
            g2d.setColor(color);
            g2d.draw(roundRect);
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius, this.radius, this.radius, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }

    // Método que obtiene todos los componentes contenidos en un contenedor (recursivamente)
    public static Component[] getAllComponents(final Container container) {
        Component[] components = container.getComponents();
        ArrayList<Component> componentList = new ArrayList<>();
        for (Component component : components) {
            componentList.add(component);
            if (component instanceof Container) {
                componentList.addAll(Arrays.asList(getAllComponents((Container) component)));
            }
        }
        return componentList.toArray(new Component[0]);
    }

}
