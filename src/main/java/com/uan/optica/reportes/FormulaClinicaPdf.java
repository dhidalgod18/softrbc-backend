package com.uan.optica.reportes;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import com.uan.optica.entities.RxFinal;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.IOException;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FormulaClinicaPdf {
    private RxFinal finalList;


    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.blue);
        Paragraph p = new Paragraph("Formula Clinica", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        PdfPTable table = new PdfPTable(4); // Dos columnas: Ojo Derecho y Ojo Izquierdo
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);

        // Cabeceras de las columnas
        cabeceraTabla(table);
        String[] ojoder = {"RX final lejos", "Agudeza visual lejos", "RX final cerca", "Agudeza visual cerca", "Add"};
        String[] ojoiz = {"RX final lejos", "Agudeza visual lejos", "RX final cerca", "Agudeza visual cerca", "Add"};

        // Información para Ojo Derecho
        escribirDatosOjo(table, ojoder, ojoiz);


        document.add(table);
        document.close();
    }

    private void cabeceraTabla(PdfPTable pdfTable) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.blue);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);

        cell.setPadding(5);
        font.setColor(Color.white);

        // Cabecera para "Ojo Derecho"
        cell.setPhrase(new Phrase("Ojo Derecho", font));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfTable.addCell(cell);

        // Cabecera para "Ojo Izquierdo"
        cell.setPhrase(new Phrase("Ojo Izquierdo", font));
        cell.setColspan(2);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfTable.addCell(cell);



    }

    private void escribirDatosOjo(PdfPTable pdfTable, String[] ojoder,String[] ojoiz ) {

        int maxFilas = Math.max(ojoder.length, ojoiz.length); // Determina el máximo número de filas
        String[] resder = {finalList.getOd() , finalList.getAvl(), finalList.getOd()+" + "+finalList.getAddicion(), finalList.getAvp(),finalList.getAddicion()};
        String[] resiz = {finalList.getOi() , finalList.getAvl(), finalList.getOi()+ " + "+finalList.getAddicion(), finalList.getAvp(),finalList.getAddicion()};




        for (int i = 0; i < maxFilas; i++) {
            // Ojo derecho
            if (i < ojoder.length) {
                pdfTable.addCell(ojoder[i]);
            } else {
                pdfTable.addCell("");
            }
            pdfTable.addCell(resder[i]);

            // Ojo izquierdo
            if (i < ojoiz.length) {
                pdfTable.addCell(ojoiz[i]);
            } else {
                pdfTable.addCell("");
            }
            pdfTable.addCell(resiz[i]);
        }
        String[] informa = {"Bif", "Uso", "Diagnostico", "Observaciones"};

        PdfPCell cell = new PdfPCell(new Phrase("Información Adicional", FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD, new Color(255, 255, 255))));
        cell.setBackgroundColor(new Color(0, 0, 255)); // Color azul
        cell.setColspan(4); // Combinar las cuatro columnas
        cell.setHorizontalAlignment(Element.ALIGN_CENTER); // Centrar el contenido
        pdfTable.addCell(cell);
        String[] resinfo = {finalList.getBif() , finalList.getUso(), finalList.getDiagnostico(), finalList.getObservaciones()};

        for (int i = 0; i<informa.length; i++){
            if (i < informa.length ){
                pdfTable.addCell(informa[i]);
            } else {
                pdfTable.addCell("");
            }
            pdfTable.addCell(resinfo[i]);
        }

    }
}