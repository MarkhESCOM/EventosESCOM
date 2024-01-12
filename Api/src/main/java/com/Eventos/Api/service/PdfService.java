package com.Eventos.Api.service;

import com.Eventos.Api.model.Evento;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generatePdf(Evento evento) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        document.add(new Paragraph("Reporte de Evento"));
        document.add(new Paragraph("Nombre: " + evento.getNombre()));
        document.add(new Paragraph("Descripción: " + evento.getDescripcionEvento()));
        // Agregar más información si es necesario
        document.close();

        return outputStream.toByteArray();
    }
}
