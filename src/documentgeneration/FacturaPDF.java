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
import java.sql.Connection;
import java.text.SimpleDateFormat;

/**
 *
 * @author dbpan
 */
public class FacturaPDF {

    private static final String FONT_PATH = "/fonts/arial.ttf";
    private static final String BOLD_FONT_PATH = "/fonts/Arial_Bold.ttf";
    private static final String IMAGE_PATH = "/img/icon_fiscalito.png";

    public static void main(String[] args) {
        try {
            PDDocument document = new PDDocument();
            PDRectangle pageSize = PDRectangle.A4;
            PDPage page = new PDPage(pageSize);
            document.addPage(page);
            // Se obtiene una conexión a la base de datos
            Connection conexion = Conexion.obtenerConexion();
            // Se crea un objeto de la clase "Empresa" para manejar la información de la empresa
            Empresa empresa = new Empresa();
            Documentos documentos = new Documentos();
            // Verifica si la conexión a la base de datos es exitosa
            if (conexion != null) {
                empresa.selectEmpresa(conexion);
                documentos.selectDocumentos(conexion, 2);
                Conexion.cerrarConexion(conexion);
            }
            float margin = 20;
            float pageWidth = pageSize.getWidth() - 2 * margin;
            float pageHeight = pageSize.getHeight() - 2 * margin;

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            PDType0Font font = PDType0Font.load(document, FacturaPDF.class.getResourceAsStream(FONT_PATH));
            PDType0Font boldFont = PDType0Font.load(document, FacturaPDF.class.getResourceAsStream(BOLD_FONT_PATH), true);
            addImage(document, page, IMAGE_PATH, 20, 760, 55, 50);

            addText(contentStream, boldFont, empresa.getNombre(), margin + 60, pageHeight + margin - 20);
            addText(contentStream, font, empresa.getNombre_comercial(), margin + 60, pageHeight + margin - 45);
            addText(contentStream, font, "R.U.C.: " + empresa.getRuc(), margin + 60, pageHeight + margin - 60);
            addText(contentStream, font, empresa.getPais() + "," + empresa.getProvincia() + "," + empresa.getDistrito(), margin, pageHeight + margin - 75);
            addText(contentStream, font, empresa.getTelefono1() + " / " + empresa.getTelefono2(), margin, pageHeight + margin - 90);

            addBoxWithText1(document, page);
            addBoxWithText2(document, page);

            addRightAlignedText(contentStream, font, boldFont, pageWidth, pageHeight, margin);

            addTable(document, page, margin - 10, pageHeight - 225);

            addText(contentStream, font, "Refetencia:", margin, pageHeight + margin - 230);
            addText(contentStream, font, "22323", margin + 70, pageHeight + margin - 230);
            addText(contentStream, font, "% Descuento (General): "+String.valueOf(documentos.getDescGen())+"%", margin + 390, pageHeight + margin - 230);
            addText(contentStream, font, "0.00%", margin + 735, pageHeight + margin - 230);

            contentStream.close();

            File file = new File("Factura_" + documentos.getIDfactura() + ".pdf");

            document.save(file);
            document.close();

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

        float fontSize1 = 12;
        float fontSize2 = 9;
        float fontSize3 = 14;
        float fontSize4 = 10;
        float fontSize5 = 12;
        float fontSize6 = 10;
        float fontSize7 = 12;
        Connection conexion = Conexion.obtenerConexion();
        // Se crea un objeto de la clase "Empresa" para manejar la información de la empresa
        Empresa empresa = new Empresa();
        Documentos documentos = new Documentos();
        // Verifica si la conexión a la base de datos es exitosa
        if (conexion != null) {
            empresa.selectEmpresa(conexion);
            documentos.selectDocumentos(conexion, 2);
            Conexion.cerrarConexion(conexion);
        }

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");

        String fechaFormateada = formatoFecha.format(documentos.getFecha_registro());
        String horaFormateada = formatoHora.format(documentos.getFecha_registro());
        float textWidth1 = boldFont.getStringWidth("DOCUMENTO NO FISCAL") / 1000 * fontSize1;
        float textWidth2 = regularFont.getStringWidth("Reporte a Efectos de Documentación") / 1000 * fontSize2;
        float textWidth3 = regularFont.getStringWidth("Factura " + documentos.getIDfactura()) / 1000 * fontSize3;
        float textWidth4 = regularFont.getStringWidth("Cerrado") / 1000 * fontSize4;
        float textWidth5 = regularFont.getStringWidth(fechaFormateada) / 1000 * fontSize5;
        float textWidth6 = regularFont.getStringWidth("F/H Imp. Fiscal") / 1000 * fontSize6;
        float textWidth7 = regularFont.getStringWidth("Credito") / 1000 * fontSize7;

        // Usar la fuente negrita solo para "DOCUMENTO NO FISCAL"
        contentStream.setFont(boldFont, fontSize1);
        contentStream.newLineAtOffset(pageWidth - margin - textWidth1, pageHeight + margin - 20);
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
        contentStream.showText("Fecha: " + fechaFormateada + " Hora: " + horaFormateada);

        contentStream.setFont(regularFont, fontSize6);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("F/H Imp. Fiscal");

        contentStream.setFont(regularFont, fontSize7);
        contentStream.newLineAtOffset(0, -15);
        contentStream.showText("Credito");

        contentStream.endText();
    }

    private static void addTable(PDDocument document, PDPage page, float margin, float yStart) throws IOException {
        // Calcula el ancho disponible para la tabla
        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;

        // Establece la posición vertical inicial de la tabla
        float yPosition = yStart;

        // Define la altura de cada fila de la tabla
        float tableHeight = 15;

        // Especifica los márgenes superior e inferior de cada celda de la tabla
        float marginY = -2;

        // Define los anchos de las columnas de la tabla
        float[] columnWidths = {50, 100, 50, 50, 70, 50, 50, 40, 40, 50};

        // Establece un margen adicional para la tabla
        float tableMargin = -10;

        // Ajusta la posición vertical para evitar solapamiento con el encabezado
        float headerHeight = 8; // Ajusta según el tamaño de fuente del encabezado
        yPosition -= headerHeight;

        // Inicializa el objeto PDPageContentStream para escribir en la página
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
            tableContentStream.newLineAtOffset(headerXPosition + 4, yPositionEncabezado - fontSize);
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
            tableContentStream.showText("%Desc");
            tableContentStream.newLineAtOffset(columnWidths[6], 0);
            tableContentStream.showText("Impor.");
            tableContentStream.newLineAtOffset(columnWidths[7], 0);
            tableContentStream.showText("DescG.");
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
        }
    }

    private static void addBoxWithText1(PDDocument document, PDPage page) throws IOException {
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
            documentos.selectDocumentos(conexion, 2);
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
        float textOffsetY = rectY + rectHeight - 15;

        boxContentStream.setFont(PDType0Font.load(document, FacturaPDF.class.getResourceAsStream("/fonts/arial.ttf")), 8);
        boxContentStream.newLineAtOffset(textOffsetX, textOffsetY);
        boxContentStream.showText("Documento emitido a favor de");

        boxContentStream.setFont(PDType0Font.load(document, FacturaPDF.class.getResourceAsStream("/fonts/arial.ttf")), 12);
        boxContentStream.newLineAtOffset(0, -25);
        boxContentStream.showText(documentos.getNombre());

        boxContentStream.newLineAtOffset(0, -18);
        boxContentStream.showText(documentos.getRUC());

        boxContentStream.newLineAtOffset(0, -18);
        boxContentStream.showText(documentos.getTelefono1()+"/"+documentos.getTelefono1());

        boxContentStream.endText();

        boxContentStream.close();
    }

    private static void addBoxWithText2(PDDocument document, PDPage page) throws IOException {
        PDPageContentStream boxContentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

        float margin = 20;

        float rectX = margin;
        float rectY = page.getMediaBox().getHeight() - margin - 510;
        float rectWidth = 560;
        float rectHeight = 120;
        Empresa empresa = new Empresa();
        Documentos documentos = new Documentos();
        Connection conexion = Conexion.obtenerConexion();
        if (conexion != null) {
            empresa.selectEmpresa(conexion);
            documentos.selectDocumentos(conexion, 2);
            Conexion.cerrarConexion(conexion);
        }

        boxContentStream.setNonStrokingColor(Color.WHITE);
        boxContentStream.setStrokingColor(Color.BLACK);
        boxContentStream.setLineWidth(1.0f);

        boxContentStream.addRect(rectX, rectY, rectWidth, rectHeight);
        boxContentStream.fillAndStroke();

        boxContentStream.beginText();
        PDType0Font font = PDType0Font.load(document, FacturaPDF.class.getResourceAsStream("/fonts/arial.ttf"));
        boxContentStream.setFont(font, 12);
        boxContentStream.setNonStrokingColor(Color.BLACK);

        float textOffsetX = rectX + 10;
        float textOffsetY = rectY + rectHeight - 15;

        // Texto 1
        float fontSize1 = 12;
        boxContentStream.setFont(font, fontSize1);
        float offsetY1 = 0;
        boxContentStream.newLineAtOffset(textOffsetX + 250, textOffsetY - 10 - offsetY1);
        boxContentStream.showText("Total de Descuento en Linea:");

        // Texto 2
        float fontSize2 = 12;
        boxContentStream.setFont(font, fontSize2);
        float offsetY2 = 15;  // Ajusta la distancia vertical entre las líneas
        boxContentStream.newLineAtOffset(textOffsetX - 30, textOffsetY - 422 - offsetY2);
        boxContentStream.showText("Total de Descuento general:");

        // Texto 3
        float fontSize3 = 12;
        boxContentStream.setFont(font, fontSize3);
        float offsetY3 = 30;  // Ajusta la distancia vertical entre las líneas
        boxContentStream.newLineAtOffset(textOffsetX - 28, textOffsetY - 409 - offsetY3);
        boxContentStream.showText("Subtotal:");

        // Texto 4
        float fontSize4 = 12;
        boxContentStream.setFont(font, fontSize4);
        float offsetY4 = 45;  // Ajusta la distancia vertical entre las líneas
        boxContentStream.newLineAtOffset(textOffsetX - 29, textOffsetY - 395 - offsetY4);
        boxContentStream.showText("Impuestos:");

        // Texto 5
        float fontSize5 = 12;
        boxContentStream.setFont(font, fontSize5);
        float offsetY5 = 60;  // Ajusta la distancia vertical entre las líneas
        boxContentStream.newLineAtOffset(textOffsetX - 30, textOffsetY - 380 - offsetY5);
        boxContentStream.showText("TOTAL:");

        // Nuevas líneas de texto al lado de las anteriore
        boxContentStream.newLineAtOffset(textOffsetX + 150, textOffsetY - 330); // Ajusta la posición horizontal según sea necesario
        boxContentStream.showText(String.valueOf(documentos.getSumaDescLinea()));
        boxContentStream.newLineAtOffset(0, -20);
        boxContentStream.showText(String.valueOf(documentos.getSumaDescGen()));
        boxContentStream.newLineAtOffset(0, -20);
        boxContentStream.showText(String.valueOf(documentos.getSubtotal2()));
        boxContentStream.newLineAtOffset(0, -22);
        boxContentStream.showText(String.valueOf(documentos.getSumaImpuesto()));
        boxContentStream.newLineAtOffset(0, -25);
        boxContentStream.showText(String.valueOf(documentos.getTotal()));

        boxContentStream.endText();
        boxContentStream.close();

    }

    private static void addImage(PDDocument document, PDPage page, String imagePath, float x, float y, float width, float height) throws IOException {
        try (InputStream in = FacturaPDF.class.getResourceAsStream(imagePath)) {
            byte[] imageBytes = IOUtils.toByteArray(in);
            PDImageXObject image = PDImageXObject.createFromByteArray(document, imageBytes, "image");
            PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true);
            contentStream.drawImage(image, x, y, width, height);
            contentStream.close();
        }
    }
}
