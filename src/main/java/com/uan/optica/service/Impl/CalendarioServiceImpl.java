package com.uan.optica.service.Impl;

import com.uan.optica.entities.Calendario;
import com.uan.optica.entities.Cita;
import com.uan.optica.repository.CalendarioRepository;
import com.uan.optica.service.CalendarioService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarioServiceImpl implements CalendarioService {
    @Autowired
    private CalendarioRepository calendarioRepository;
    @Override
    public boolean crearCalendario(Calendario calendario) {
        Calendario calendarioGuardado = calendarioRepository.save(calendario);
        if (calendarioGuardado == null) {
            throw new RuntimeException("No se pudo guardar el calendario");
        }
        return true;
    }

    @Override
    public boolean modificarDatosCalendario(int idcalendario, String nuevadiasatencion, int nuevaduracion) {
        try {
            Optional<Calendario> optionalCalendario = calendarioRepository.findById(idcalendario);
            if (optionalCalendario.isPresent()) {
                Calendario calendario = optionalCalendario.get();
                calendario.setDiasatencion(nuevadiasatencion);
                calendario.setDuracioncita(nuevaduracion);
                calendarioRepository.save(calendario);
                return true;
            } else {
                return false; // El usuario no fue encontrado
            }
        } catch (NumberFormatException e) {
            // Manejo de la excepción si el formato del teléfono es inválido
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            // Manejo de otras excepciones
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int duracioncita(String dia) {
        Integer duracionCita = calendarioRepository.obtenerDuracionCitaPorDia(dia);
        System.out.println(duracionCita+"Duracioncitaaaaaaaaaa");
        if (duracionCita != null) {
            return duracionCita;
        } else {
            throw new RuntimeException("No se encontró la duración de la cita para el día proporcionado");
        }
    }

    @Override
    public List<Cita> obtenercitas(String fecha) {
        return calendarioRepository.obtenerCitasPorFecha(fecha);

    }

    @Override
    public void generarPdfCitasPorFecha(String fecha) {
        try {
            List<Cita> citas = obtenercitas(fecha);

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("citas_" + fecha + ".pdf"));
            document.open();

            for (Cita cita : citas) {
                document.add(new Paragraph("Nombre: " + cita.getNombre()));
                document.add(new Paragraph("Teléfono: " + cita.getTelefono()));
                document.add(new Paragraph("Hora: " + cita.getHora()));
                document.add(new Paragraph("\n"));
            }

            document.close();
            System.out.println("PDF generado correctamente.");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
