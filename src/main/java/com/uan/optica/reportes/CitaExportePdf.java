package com.uan.optica.reportes;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import com.uan.optica.entities.Cita;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.IOException;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CitaExportePdf {
    private List<Cita> listacitas;

    public void cabeceraTabla(PdfPTable pdfTable){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.blue);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.white);

        cell.setPhrase(new Phrase("Nombre", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("Telefono", font));
        pdfTable.addCell(cell);

        cell.setPhrase(new Phrase("Hora", font));
        pdfTable.addCell(cell);



    }
    private void escribirDatosTabla(PdfPTable table){
        for (Cita cita: listacitas){
            table.addCell(cita.getNombre());
            table.addCell(String.valueOf(cita.getTelefono()));
            table.addCell(String.valueOf(cita.getHora()));


        }
    }
    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.blue);
        Paragraph p = new Paragraph("Lista de usuarios",font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{3.5f,3.5f,3.5f});
        table.setSpacingBefore(10);
        cabeceraTabla(table);
        escribirDatosTabla(table);

        document.add(table);
        document.close();

    }
}
