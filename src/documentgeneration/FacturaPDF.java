/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package documentgeneration;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.commons.io.IOUtils;
import java.awt.Color;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import controladores.*;
import conexion.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 *
 * @author dbpan
 */
public class FacturaPDF {

    private static final String FONT_PATH = "/fonts/arial.ttf";
    private static final String BOLD_FONT_PATH = "/fonts/Arial_Bold.ttf";
    //private static final String IMAGE_PATH = "/img/icon_fiscalito.png";

    public static void main(String[] args, String id) {
        try {
            PDDocument document = new PDDocument();
            PDRectangle pageSize = PDRectangle.A4;
            PDPage page = new PDPage(pageSize);
            document.addPage(page);
            int ide = Integer.parseInt(id) - 1;

            Connection conexion = Conexion.obtenerConexion();
            Empresa empresa = new Empresa();
            Documentos documentos = new Documentos();

            empresa.selectEmpresa(conexion);
            documentos.selectDocumentos(conexion, ide);
            Conexion.cerrarConexion(conexion);

            byte[] logoFacturaBytes = empresa.getLogo_factura();

            float margin = 20;
            float pageWidth = pageSize.getWidth() - 2 * margin;
            float pageHeight = pageSize.getHeight() - 2 * margin;

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            PDType0Font font = PDType0Font.load(document, FacturaPDF.class.getResourceAsStream(FONT_PATH));
            PDType0Font boldFont = PDType0Font.load(document, FacturaPDF.class.getResourceAsStream(BOLD_FONT_PATH), true);
            // Utiliza directamente el objeto ImageIcon en el método addImage
            if (logoFacturaBytes != null) {
                ImageIcon fotoFactura = new ImageIcon(logoFacturaBytes);
                addImage(document, page, fotoFactura, 20, 760, 55, 50);
            }
            addText(contentStream, boldFont, empresa.getNombre(), margin + 60, pageHeight + margin - 20);
            addText(contentStream, font, empresa.getNombre_comercial(), margin + 60, pageHeight + margin - 45);
            addText(contentStream, font, "R.U.C.: " + empresa.getRuc(), margin + 60, pageHeight + margin - 60);
            addText(contentStream, font, empresa.getPais() + "," + empresa.getProvincia() + "," + empresa.getDistrito(), margin, pageHeight + margin - 75);
            addText(contentStream, font, empresa.getTelefono1() + " / " + empresa.getTelefono2(), margin, pageHeight + margin - 90);

            addRightAlignedText(contentStream, font, boldFont, pageWidth, pageHeight, margin, ide);

            float a = addTable(document, page, margin - 10, pageHeight - 225, ide);

            addBoxWithText1(document, page, ide);
            float boxX = 20; // Ajusta según sea necesario
            float boxY = 850 - a; // Ajusta según sea necesario
            addBoxWithText2(document, page, ide, boxX, boxY);

            addText(contentStream, font, "Referencia:", margin, pageHeight + margin - 230);
            addText(contentStream, font, String.valueOf(documentos.getReferencia()) + "     /   Credito: " + String.valueOf(documentos.getCredito()), margin + 70, pageHeight + margin - 230);
            addText(contentStream, font, "% Descuento (General): " + String.valueOf(documentos.getDescGen()) + "%", margin + 390, pageHeight + margin - 230);
            addText(contentStream, font, "0.00%", margin + 735, pageHeight + margin - 230);
            contentStream.close();

            //File file = new File("Factura_" + documentos.getIDfactura() + ".pdf");
            // Crear un diálogo de selección de archivo
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar Factura PDF");
// Establecer un nombre predeterminado
            fileChooser.setSelectedFile(new File("Factura_" + documentos.getIDfactura() + ".pdf"));
            // Mostrar el diálogo y obtener la opción del usuario
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                // Obtener la ubicación seleccionada por el usuario
                File fileToSave = fileChooser.getSelectedFile();

                // Guardar el documento en la ubicación seleccionada
                document.save(fileToSave);
                document.close();

                Desktop.getDesktop().open(fileToSave);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addText(PDPageContentStream contentStream, PDType0Font font, String text, float x, float y) throws IOException {
        contentStream.beginText();

        contentStream.setFont(font, 12);
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);
        contentStream.endText();
    }

    private static void addRightAlignedText(PDPageContentStream contentStream, PDType0Font regularFont, PDType0Font boldFont, float pageWidth, float pageHeight, float margin, int id) throws IOException {
        contentStream.beginText();

        float fontSize1 = 12;
        float fontSize2 = 9;
        float fontSize3 = 14;
        float fontSize4 = 10;
        float fontSize5 = 12;
        float fontSize6 = 12;
        float fontSize7 = 12;
        Connection conexion = Conexion.obtenerConexion();
        // Se crea un objeto de la clase "Empresa" para manejar la información de la empresa
        Empresa empresa = new Empresa();
        Documentos documentos = new Documentos();
        // Verifica si la conexión a la base de datos es exitosa
        if (conexion != null) {
            empresa.selectEmpresa(conexion);
            documentos.selectDocumentos(conexion, id);
            Conexion.cerrarConexion(conexion);
        }

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a");

        String fechaFormateada = formatoFecha.format(documentos.getFecha_registro());
        String horaFormateada = formatoHora.format(documentos.getFecha_registro());
        float textWidth1 = boldFont.getStringWidth("DOCUMENTO NO FISCAL") / 1000 * fontSize1;
        float textWidth2 = regularFont.getStringWidth("Reporte a Efectos de Documentación") / 1000 * fontSize2;
        float textWidth3 = regularFont.getStringWidth("Factura " + documentos.getIDfactura()) / 1000 * fontSize3;
        float textWidth4 = regularFont.getStringWidth("Cerrado") / 1000 * fontSize4;
        float textWidth5 = regularFont.getStringWidth(fechaFormateada) / 1000 * fontSize5;
        float textWidth6 = regularFont.getStringWidth(fechaFormateada) / 1000 * fontSize6;
        float textWidth7 = regularFont.getStringWidth("Credito") / 1000 * fontSize7;

        // Usar la fuente negrita solo para "DOCUMENTO NO FISCAL"
        contentStream.setFont(boldFont, fontSize1);
        contentStream.newLineAtOffset(pageWidth - margin + 35 - textWidth1, pageHeight + margin - 20);
        contentStream.showText("DOCUMENTO NO FISCAL");

        // Restaurar la fuente regular para el resto del texto
        contentStream.setFont(regularFont, fontSize2);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Reporte a Efectos de Documentación");

        contentStream.setFont(regularFont, fontSize3);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Factura " + documentos.getIDfactura());

        contentStream.setFont(regularFont, fontSize4);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Cerrado");

        contentStream.setFont(regularFont, fontSize5);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Fecha: " + fechaFormateada);

        contentStream.setFont(regularFont, fontSize6);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Hora: " + horaFormateada);

        contentStream.setFont(regularFont, fontSize7);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText(documentos.getTipodocumento());

        contentStream.endText();
    }

    private static float addTable(PDDocument document, PDPage page, float margin, float yStart, int id) throws IOException {
        Connection conexion = Conexion.obtenerConexion();
        Documentos documentos = new Documentos();
        List<Documentos> documentosList = new ArrayList<>();

        if (conexion != null) {
            documentosList = documentos.selectDocumentosPorID(conexion, id);
            Conexion.cerrarConexion(conexion);
        }

        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
        float yPosition = yStart;
        float tableHeight = 15;
        float marginY = -2;
        float[] columnWidths = {50, 100, 50, 50, 70, 50, 50, 40, 40, 50};
        float tableMargin = -10;
        float headerHeight = 8;
        yPosition -= headerHeight;

        // Incluye la variable para almacenar la posición final de la tabla
        float tableEndY = yPosition;

        try (PDPageContentStream tableContentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {

            // Configura la posición horizontal actual al inicio de la tabla, teniendo en cuenta el margen adicional
            float nextX = margin + tableMargin;

            // Carga la fuente para el texto de la tabla
            PDType0Font font = PDType0Font.load(document, FacturaPDF.class.getResourceAsStream("/fonts/arial.ttf"));

            // Establece el tamaño de la fuente para el texto de la tabla
            int fontSize = 8;

            // Configura la fuente y el tamaño del texto para el contenido de la tabla
            tableContentStream.setFont(font, fontSize);

            // Almacena la posición vertical actual para el encabezado de la tabla
            float yPositionEncabezado = yPosition;

            // Ajusta la posición vertical restando la altura de la fila de la tabla
            yPosition -= tableHeight;

            // Inicia la sección de texto para el encabezado de la tabla
            tableContentStream.beginText();

            // Configura la posición inicial del texto para el encabezado de la tabla
            float headerXPosition = margin + tableMargin + 10; // Ajusta la posición X inicial del encabezado

            // Configura el fondo de color para el encabezado
            float[] headerColor = new float[]{211 / 255f, 211 / 255f, 211 / 255f};
            tableContentStream.setNonStrokingColor(headerColor[0], headerColor[1], headerColor[2]);

            // Finaliza la sección de texto antes de agregar el rectángulo
            tableContentStream.endText();

            // Agrega el rectángulo con el fondo de color
            float rectWidth = tableWidth - 24;
            float rectHeight = headerHeight;
            tableContentStream.addRect(headerXPosition + 22, yPositionEncabezado - 4 - fontSize, rectWidth - 1, rectHeight + 5);
            tableContentStream.fill();

            // Configura la posición X después del fondo de color
            headerXPosition += 22;

            // Configura el color del texto a negro
            tableContentStream.setNonStrokingColor(0, 0, 0); // Negro

            // Agrega texto para cada columna del encabezado de la tabla
            tableContentStream.beginText();
            tableContentStream.newLineAtOffset(headerXPosition + 10, yPositionEncabezado - fontSize);
            tableContentStream.showText("Codigo");
            tableContentStream.newLineAtOffset(columnWidths[0], 0);
            tableContentStream.showText("Articulo");
            tableContentStream.newLineAtOffset(columnWidths[1], 0);
            tableContentStream.showText("Cant");
            tableContentStream.newLineAtOffset(columnWidths[2], 0);
            tableContentStream.showText("Serv");
            tableContentStream.newLineAtOffset(columnWidths[3], 0);
            tableContentStream.showText("Unidad");
            tableContentStream.newLineAtOffset(columnWidths[4], 0);
            tableContentStream.showText("Precio");
            tableContentStream.newLineAtOffset(columnWidths[5], 0);
            tableContentStream.showText("%DescLi");
            tableContentStream.newLineAtOffset(columnWidths[6], 0);
            tableContentStream.showText("%Desc");
            tableContentStream.newLineAtOffset(columnWidths[7], 0);
            tableContentStream.showText("Import.");
            tableContentStream.newLineAtOffset(columnWidths[8], 0);
            tableContentStream.showText("Subtotal");

            tableContentStream.endText();

            // Establece el ancho de la línea para el contorno de la tabla
            tableContentStream.setLineWidth(1f);

            // Establece el color para el contorno de la tabla
            tableContentStream.setStrokingColor(Color.WHITE);

            // Dibuja las líneas horizontales y verticales de la tabla
            tableContentStream.moveTo(headerXPosition, yPositionEncabezado - marginY);
            tableContentStream.lineTo(headerXPosition + tableWidth - 60, yPositionEncabezado - marginY);
            tableContentStream.stroke();

            tableContentStream.moveTo(headerXPosition, yPosition - marginY);
            tableContentStream.lineTo(headerXPosition + tableWidth - 58, yPosition - marginY);
            tableContentStream.stroke();

            tableContentStream.moveTo(headerXPosition, yPositionEncabezado - marginY);
            tableContentStream.lineTo(headerXPosition, yPosition - marginY);
            tableContentStream.stroke();

            float currentX = headerXPosition;
            for (float width : columnWidths) {
                currentX += width;
                tableContentStream.moveTo(currentX, yPositionEncabezado - marginY);
                tableContentStream.lineTo(currentX, yPosition - marginY - 1);
                tableContentStream.stroke();
            }
            DecimalFormat df = new DecimalFormat("#.####");
            DecimalFormat df1 = new DecimalFormat("#.##");

            //boxContentStream.showText("Total de Descuento en Linea:     " + String.valueOf(Double.parseDouble(df.format( documentos.getSumaDescLinea()))));
            // Agrega los datos de la base de datos a la tabla
            float textYPosition = yPositionEncabezado + -fontSize - marginY;
            int numeroProducto = 1; // Variable de contador para la numeración

            for (Documentos documento : documentosList) {
                textYPosition -= tableHeight;  // Mueve la posición vertical hacia arriba para la próxima fila
                tableContentStream.beginText();
                tableContentStream.setFont(font, fontSize);
                tableContentStream.newLineAtOffset(headerXPosition + 4, textYPosition);

                // Añade la numeración a la izquierda
                tableContentStream.showText(String.valueOf(numeroProducto));
                tableContentStream.newLineAtOffset(15, 0);  // Ajusta según el espacio deseado entre el número y el texto

                // Ajusta según las propiedades de tu objeto Documentos
                tableContentStream.showText(documento.getCodigoproducto());
                tableContentStream.newLineAtOffset(columnWidths[0], 0);
                tableContentStream.showText(documento.getNombreproducto());
                tableContentStream.newLineAtOffset(columnWidths[1], 0);
                tableContentStream.showText(String.valueOf(documento.getCantidad()));
                tableContentStream.newLineAtOffset(columnWidths[2], 0);
                tableContentStream.showText(String.valueOf(documento.getConfirmservicio()));
                tableContentStream.newLineAtOffset(columnWidths[3], 0);
                tableContentStream.showText(String.valueOf(documento.getMagnitud()));
                tableContentStream.newLineAtOffset(columnWidths[4], 0);
                tableContentStream.showText(String.valueOf(documento.getPrecioProducto()));
                tableContentStream.newLineAtOffset(columnWidths[5], 0);
                tableContentStream.showText(String.valueOf(Double.parseDouble(df.format(documento.getDescLinea()))));
                tableContentStream.newLineAtOffset(columnWidths[6], 0);
                tableContentStream.showText(String.valueOf(Double.parseDouble(df1.format(documento.getDescGen()))));
                tableContentStream.newLineAtOffset(columnWidths[7], 0);
                tableContentStream.showText(String.valueOf(Double.parseDouble(df1.format(documento.getImporteImpuesto()))));
                tableContentStream.newLineAtOffset(columnWidths[8], 0);
                tableContentStream.showText(String.valueOf(Double.parseDouble(df1.format(documento.getSubtotal1()))));
                tableContentStream.newLineAtOffset(columnWidths[9], 0);
                // ... Continúa agregando las demás columnas

                tableContentStream.endText();
                numeroProducto++; // Incrementa el contador de numeración
            }

            tableEndY = textYPosition;
        }

        // Llama a addBoxWithText2 después de generar la tabla
        // addBoxWithText2(document, page, id/*, tableEndY*/);
        // Devuelve la posición final de la tabla
        return tableEndY;
    }

    private static void addBoxWithText1(PDDocument document, PDPage page, int id) throws IOException {
        PDPageContentStream boxContentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

        float margin = 20;

        float rectX = margin;
        float rectY = page.getMediaBox().getHeight() - margin - 210;
        float rectWidth = 560;
        float rectHeight = 85;
        Empresa empresa = new Empresa();
        Documentos documentos = new Documentos();
        Connection conexion = Conexion.obtenerConexion();
        if (conexion != null) {
            empresa.selectEmpresa(conexion);
            documentos.selectDocumentos(conexion, id);
            Conexion.cerrarConexion(conexion);
        }
        boxContentStream.setNonStrokingColor(Color.WHITE);
        boxContentStream.setStrokingColor(Color.BLACK);
        boxContentStream.setLineWidth(1.0f);

        boxContentStream.addRect(rectX, rectY, rectWidth, rectHeight);
        boxContentStream.fillAndStroke();

        boxContentStream.beginText();
        boxContentStream.setFont(PDType0Font.load(document, FacturaPDF.class.getResourceAsStream("/fonts/arial.ttf")), 12);
        boxContentStream.setNonStrokingColor(Color.BLACK);

        float textOffsetX = rectX + 10;
        float textOffsetY = rectY + rectHeight - 9;

        boxContentStream.setFont(PDType0Font.load(document, FacturaPDF.class.getResourceAsStream("/fonts/arial.ttf")), 8);
        boxContentStream.newLineAtOffset(textOffsetX, textOffsetY);
        boxContentStream.showText("Documento emitido a favor de ");

        boxContentStream.setFont(PDType0Font.load(document, FacturaPDF.class.getResourceAsStream("/fonts/arial.ttf")), 12);
        boxContentStream.newLineAtOffset(0, -20);
        boxContentStream.showText(documentos.getNombre());

        // Asegurar que las siguientes líneas comiencen desde el final de la línea anterior
        boxContentStream.newLineAtOffset(0, -15);

        boxContentStream.showText(documentos.getDireccion());
        boxContentStream.newLineAtOffset(0, -18);
        boxContentStream.showText(documentos.getRUC());
        boxContentStream.newLineAtOffset(0, -18);
        boxContentStream.showText(documentos.getTelefono1() + " / " + documentos.getTelefono1());

        boxContentStream.endText();

        boxContentStream.close();
    }

    private static void addBoxWithText2(PDDocument document, PDPage page, int id, float boxX, float boxY) throws IOException {
        float margin = 20;
        float rectWidth = 560;
        float rectHeight = 100;

        Connection conexion = Conexion.obtenerConexion();
        Empresa empresa = new Empresa();
        Documentos documentos = new Documentos();

        if (conexion != null) {
            empresa.selectEmpresa(conexion);
            documentos.selectDocumentos(conexion, id);
            Conexion.cerrarConexion(conexion);
        }

        try (PDPageContentStream boxContentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
            float rectX = boxX;
            float rectY = page.getMediaBox().getHeight() - boxY - rectHeight;

            boxContentStream.setNonStrokingColor(Color.WHITE);
            boxContentStream.setStrokingColor(Color.BLACK);
            boxContentStream.setLineWidth(1.0f);
            boxContentStream.addRect(rectX, rectY, rectWidth, rectHeight);
            boxContentStream.fillAndStroke();

            boxContentStream.beginText();
            PDType0Font font = PDType0Font.load(document, FacturaPDF.class.getResourceAsStream("/fonts/arial.ttf"));
            boxContentStream.setFont(font, 12);
            boxContentStream.setNonStrokingColor(Color.BLACK);

            float textOffsetX = rectX + 300;
            float textOffsetY = rectY + rectHeight - 15;

            float fontSize = 12;
            float offsetY = 15;  // Ajusta la distancia vertical entre las líneas
            DecimalFormat df = new DecimalFormat("#.##");
            boxContentStream.setFont(font, fontSize);
            boxContentStream.newLineAtOffset(textOffsetX + 10, textOffsetY - 10);
            boxContentStream.showText("Total de Descuento en Linea:     " + String.valueOf(Double.parseDouble(df.format(documentos.getSumaDescLinea()))));
            boxContentStream.newLine();

            boxContentStream.setFont(font, fontSize);
            boxContentStream.newLineAtOffset(0, -offsetY);
            boxContentStream.showText("Total de Descuento general:       " + String.valueOf(Double.parseDouble(df.format(documentos.getSumaDescGen()))));
            boxContentStream.newLine();

            boxContentStream.setFont(font, fontSize);
            boxContentStream.newLineAtOffset(0, -offsetY);
            boxContentStream.showText("Subtotal:                                      " + String.valueOf(Double.parseDouble(df.format(documentos.getSubtotal2()))));
            boxContentStream.newLine();

            boxContentStream.setFont(font, fontSize);
            boxContentStream.newLineAtOffset(0, -offsetY);
            boxContentStream.showText("Impuestos:                                   " + String.valueOf(Double.parseDouble(df.format(documentos.getSumaImpuesto()))));
            boxContentStream.newLine();

            boxContentStream.setFont(font, fontSize);
            boxContentStream.newLineAtOffset(0, -offsetY);
            boxContentStream.showText("TOTAL:                                        " + String.valueOf(Double.parseDouble(df.format(documentos.getTotal()))));

            boxContentStream.endText();
        }
    }

    private static void addImage(PDDocument document, PDPage page, ImageIcon imageIcon, float x, float y, float width, float height) throws IOException {
        try {
            Image image = imageIcon.getImage();
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGr = bufferedImage.createGraphics();
            bGr.drawImage(image, 0, 0, null);
            bGr.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();

            PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, imageBytes, "image");
            PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true);
            contentStream.drawImage(pdImage, x, y, width, height);
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
