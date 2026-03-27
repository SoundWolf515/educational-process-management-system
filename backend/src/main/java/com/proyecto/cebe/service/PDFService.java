package com.proyecto.cebe.service;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

@Service
public class PDFService {
    
    @Autowired
    private TemplateEngine templateEngine;

    public byte[] generarPdf(String template, Context context){

        String html = templateEngine.process(template, context);

        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){

            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, null);
            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();
        } catch(Exception e){
            throw new RuntimeException("Error generando PDF", e);
        }
    }

}
