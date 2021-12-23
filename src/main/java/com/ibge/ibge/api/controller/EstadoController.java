package com.ibge.ibge.api.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import com.google.common.net.HttpHeaders;
import org.springframework.http.MediaType;

import com.ibge.ibge.api.interfaces.IEstadoController;
import com.ibge.ibge.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class EstadoController implements IEstadoController {

    @Lazy
    @Autowired
    private EstadoService estadoService;

    @Override
    public ResponseEntity<byte[]> DownloadJson() {
        String estadoJson = estadoService.EstadosJson();
        byte[] estadosJsonbytes = estadoJson.getBytes();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=estados.json")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(estadosJsonbytes.length)
                .body(estadosJsonbytes);
    }

    @Override
    public OutputStream DownloadCSV(HttpServletResponse response) throws IOException, Exception {
        response.setHeader("Content-Disposition","attachment; filename=estados.csv");
        OutputStream servletOutputStream = response.getOutputStream();
        estadoService.EstadosCSV().writeTo(servletOutputStream);
        servletOutputStream.flush();
        servletOutputStream.close();
        return servletOutputStream;  
    }    
}
