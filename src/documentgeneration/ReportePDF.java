/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package documentgeneration;

import conexion.Conexion;
import controladores.Documentos;
import controladores.Empresa;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.commons.io.IOUtils;
import java.awt.Color;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;

/**
 *
 * @author dbpan
 */
public class ReportePDF {

    private static final String FONT_PATH = "/fonts/arial.ttf";
    private static final String BOLD_FONT_PATH = "/fonts/Arial_Bold.ttf";
    private static final String IMAGE_PATH = "/img/maleta.jpg";

    private static Date fecha1;
    private static Date fecha2;
    private static String Tdocumento;
    private static String TFacturado;

    public ReportePDF(Date fecha1, Date fecha2, String Tdocumento, String TFacturado) {
        this.fecha1 = fecha1;
        this.fecha2 = fecha2;
        this.Tdocumento = Tdocumento;
        this.TFacturado = TFacturado;
    }

    public static void main(String[] args) {
        try {
            PDDocument Reportes = new PDDocument();
            // Cambia la orientación a horizontal
            PDRectangle pageSize = new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth());
            PDPage page = new PDPage(pageSize);
            Reportes.addPage(page);
            Connection conexion = Conexion.obtenerConexion();
            Empresa empresa = new Empresa();

            empresa.selectEmpresa(conexion);
            Conexion.cerrarConexion(conexion);

            byte[] logoFacturaBytes = empresa.getLogo_empresa();
            ImageIcon fotoFactura = new ImageIcon(logoFacturaBytes);

            float margin = 10;
            float pageWidth = pageSize.getWidth() - 2 * margin;
            float pageHeight = pageSize.getHeight() - 2 * margin;

            PDPageContentStream contentStream = new PDPageContentStream(Reportes, page);
            PDType0Font font = PDType0Font.load(Reportes, ReportePDF.class.getResourceAsStream(FONT_PATH));
            PDType0Font boldFont = PDType0Font.load(Reportes, ReportePDF.class.getResourceAsStream(BOLD_FONT_PATH), true);

            // Ajusta la posición de la imagen y otros elementos
            addImage(Reportes, page, fotoFactura, 20, 510, 73, 65);
            addText(contentStream, boldFont, empresa.getNombre(), margin + 85, pageHeight + margin - 20);
            addText(contentStream, font, empresa.getNombre_comercial(), margin + 85, pageHeight + margin - 45);
            addText(contentStream, font, "R.U.C.: " + empresa.getRuc(), margin + 85, pageHeight + margin - 60);
            addText(contentStream, font, empresa.getPais() + "," + empresa.getProvincia() + "," + empresa.getDistrito(), margin + 85, pageHeight + margin - 75);

            addRightAlignedText(contentStream, font, boldFont, pageWidth, pageHeight, margin);
            // Ajusta la posición de la tabla
            addTable(Reportes, page, margin - 10, pageHeight - 70);
            contentStream.close();

            // Después de agregar la tabla, agrega las líneas de texto en la parte derecha
            // Guarda el documento y abre el archivo
            File file = new File("document.pdf");
            Reportes.save(file);
            Reportes.close();
            Desktop.getDesktop().open(file);
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

    private static void addRightAlignedText(PDPageContentStream contentStream, PDType0Font regularFont, PDType0Font boldFont, float pageWidth, float pageHeight, float margin) throws IOException {
        contentStream.beginText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaFormateada1 = dateFormat.format(fecha1);
        String fechaFormateada2 = dateFormat.format(fecha2);
        float fontSize1 = 12;
        float fontSize2 = 12;
        float fontSize3 = 12;
        float fontSize4 = 12;

        float textWidth1 = boldFont.getStringWidth("DOCUMENTO NO FISCAL") / 1000 * fontSize1;
        float textWidth2 = regularFont.getStringWidth("Reporte a Efectos de Documentación") / 1000 * fontSize2;
        float textWidth3 = regularFont.getStringWidth("Factura 1") / 1000 * fontSize3;
        float textWidth4 = regularFont.getStringWidth("Cerrado") / 1000 * fontSize4;

        // Usar la fuente negrita solo para "DOCUMENTO NO FISCAL"
        contentStream.setFont(regularFont, fontSize1);
        contentStream.newLineAtOffset(pageWidth - margin - 70 - textWidth1, pageHeight + margin - 20);
        contentStream.showText("Reporte de Documentos Impresos");

        // Restaurar la fuente regular para el resto del texto
        contentStream.setFont(regularFont, fontSize2);
        contentStream.newLineAtOffset(0, -18);
        contentStream.showText("Del: " + fechaFormateada1 + "  Al: " + fechaFormateada2);

        contentStream.setFont(regularFont, fontSize3);
        contentStream.newLineAtOffset(0, -18);
        contentStream.showText("Todos lo tipos de documentos");

        contentStream.setFont(regularFont, fontSize4);
        contentStream.newLineAtOffset(0, -18);
        contentStream.showText("Total Facturado: " + TFacturado);

        contentStream.endText();
    }

    private static float addTable(PDDocument document, PDPage page, float margin, float yStart) throws IOException {
        String td = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Connection conexion = Conexion.obtenerConexion();
        Documentos documentos = new Documentos();
        java.util.List<Documentos> documentosList = new ArrayList<>();

        if (conexion != null) {
            documentosList = documentos.selectDocumentos(conexion);
            Conexion.cerrarConexion(conexion);
        }

        // Calcula el ancho disponible para la tabla
        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;

        // Establece la posición vertical inicial de la tabla
        float yPosition = yStart;

        // Define la altura de cada fila de la tabla
        float tableHeight = 15;

        // Especifica los márgenes superior e inferior de cada celda de la tabla
        float marginY = -2;

        // Define los anchos de las columnas de la tabla
        float[] columnWidths = {100, 50, 75, 85, 100, 85, 60, 102, 103};

        // Establece un margen adicional para la tabla
        float tableMargin = -8;

        // Ajusta la posición vertical para evitar solapamiento con el encabezado
        float headerHeight = 8; // Ajusta según el tamaño de fuente del encabezado
        yPosition -= headerHeight;
        // Incluye la variable para almacenar la posición final de la tabla
        float tableEndY = yPosition;
        // Inicializa el objeto PDPageContentStream para escribir en la página
        try (PDPageContentStream tableContentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {

            // Configura la posición horizontal actual al inicio de la tabla, teniendo en cuenta el margen adicional
            float nextX = margin + tableMargin;

            // Carga la fuente para el texto de la tabla
            PDType0Font font = PDType0Font.load(document, ReportePDF.class.getResourceAsStream("/fonts/arial.ttf"));

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
            float rectWidth = tableWidth - 81;
            float rectHeight = headerHeight;
            tableContentStream.addRect(headerXPosition + 22, yPositionEncabezado - 4 - fontSize, rectWidth - 1, rectHeight + 5);
            tableContentStream.fill();

            // Configura la posición X después del fondo de color
            headerXPosition += 22;

            // Configura el color del texto a negro
            tableContentStream.setNonStrokingColor(0, 0, 0); // Negro

            // Agrega texto para cada columna del encabezado de la tabla
            tableContentStream.beginText();
            tableContentStream.newLineAtOffset(headerXPosition + 4, yPositionEncabezado - fontSize);
            tableContentStream.showText("Fecha de Facturación");
            tableContentStream.newLineAtOffset(columnWidths[0], 0);
            tableContentStream.showText("ID Factura");
            tableContentStream.newLineAtOffset(columnWidths[1], 0);
            tableContentStream.showText("T.documento");
            tableContentStream.newLineAtOffset(columnWidths[2], 0);
            tableContentStream.showText("Estatus");
            tableContentStream.newLineAtOffset(columnWidths[3], 0);
            tableContentStream.showText("Nombre Cliente");
            tableContentStream.newLineAtOffset(columnWidths[4], 0);
            tableContentStream.showText("Subtotal");
            tableContentStream.newLineAtOffset(columnWidths[5], 0);
            tableContentStream.showText("Impuestos");
            tableContentStream.newLineAtOffset(columnWidths[6], 0);
            tableContentStream.showText("Total");
            tableContentStream.newLineAtOffset(columnWidths[7], 0);
            tableContentStream.showText("DIF");
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
            // Agrega los datos de la base de datos a la tabla
            float textYPosition = yPositionEncabezado - fontSize - marginY;
            int numeroProducto = 1; // Variable de contador para la numeración

            for (Documentos documento : documentosList) {
                Date fechaFactura = documento.getFecha_registro();
                String fechaFormateada = dateFormat.format(fechaFactura);

                if (fechaFactura != null
                        && Tdocumento != null
                        && ((fecha1 == null || fechaFactura.after(fecha1) || fechaFactura.equals(fecha1))
                        && (fecha2 == null || fechaFactura.before(fecha2) || fechaFactura.equals(fecha2)))
                        && (Tdocumento.equals("Todos") || documento.getTipodocumento().equals(Tdocumento))) {
                     if ("Si".equals(documento.getCredito())) {
                         td = "Credito";}else{
                      td = "Caselado";
                     }
                    textYPosition -= tableHeight;  // Mueve la posición vertical hacia arriba para la próxima fila
                    tableContentStream.beginText();
                    tableContentStream.setFont(font, fontSize);
                    tableContentStream.newLineAtOffset(headerXPosition + 4, textYPosition);
                    // Ajusta según las propiedades de tu objeto Documentos
                    tableContentStream.showText(fechaFormateada);
                    tableContentStream.newLineAtOffset(columnWidths[0], 0);
                    tableContentStream.showText(String.valueOf(documento.getIDfactura()));
                    tableContentStream.newLineAtOffset(columnWidths[1], 0);
                    tableContentStream.showText(documento.getTipodocumento());
                    tableContentStream.newLineAtOffset(columnWidths[2], 0);
                    tableContentStream.showText(td);
                    tableContentStream.newLineAtOffset(columnWidths[3], 0);
                    tableContentStream.showText(documento.getNombre());
                    tableContentStream.newLineAtOffset(columnWidths[4], 0);
                    tableContentStream.showText(String.valueOf(documento.getSubtotal2()));
                    tableContentStream.newLineAtOffset(columnWidths[5], 0);
                    tableContentStream.showText(String.valueOf(documento.getImpuestos()));
                    tableContentStream.newLineAtOffset(columnWidths[6], 0);
                    tableContentStream.showText(String.valueOf(documento.getTotal()));
                    tableContentStream.newLineAtOffset(columnWidths[7], 0);
                    tableContentStream.showText(String.valueOf(documento.getDIF()));
                    tableContentStream.newLineAtOffset(columnWidths[8], 0);

                    tableContentStream.endText();
                    numeroProducto++; // In
                } else if (fechaFactura != null
                        && ((fecha1 == null || fechaFactura.after(fecha1) || fechaFactura.equals(fecha1))
                        && (fecha2 == null || fechaFactura.before(fecha2) || fechaFactura.equals(fecha2)))) {
                    if ("Si".equals(documento.getCredito())) {
                         td = "Credito";
                        textYPosition -= tableHeight;  // Mueve la posición vertical hacia arriba para la próxima fila
                        tableContentStream.beginText();
                        tableContentStream.setFont(font, fontSize);
                        tableContentStream.newLineAtOffset(headerXPosition + 4, textYPosition);
                        // Ajusta según las propiedades de tu objeto Documentos
                        tableContentStream.showText(fechaFormateada);
                        tableContentStream.newLineAtOffset(columnWidths[0], 0);
                        tableContentStream.showText(String.valueOf(documento.getIDfactura()));
                        tableContentStream.newLineAtOffset(columnWidths[1], 0);
                        tableContentStream.showText(documento.getTipodocumento());
                        tableContentStream.newLineAtOffset(columnWidths[2], 0);
                        tableContentStream.showText(td );
                        tableContentStream.newLineAtOffset(columnWidths[3], 0);
                        tableContentStream.showText(documento.getNombre());
                        tableContentStream.newLineAtOffset(columnWidths[4], 0);
                        tableContentStream.showText(String.valueOf(documento.getSubtotal2()));
                        tableContentStream.newLineAtOffset(columnWidths[5], 0);
                        tableContentStream.showText(String.valueOf(documento.getImpuestos()));
                        tableContentStream.newLineAtOffset(columnWidths[6], 0);
                        tableContentStream.showText(String.valueOf(documento.getTotal()));
                        tableContentStream.newLineAtOffset(columnWidths[7], 0);
                        tableContentStream.showText(String.valueOf(documento.getDIF()));
                        tableContentStream.newLineAtOffset(columnWidths[8], 0);

                        tableContentStream.endText();
                        numeroProducto++; // In
                    }
                }

            }
            tableEndY = textYPosition;
        }
        return tableEndY;
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
