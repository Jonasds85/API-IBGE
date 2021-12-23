package com.ibge.ibge.api.controller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import com.google.common.net.HttpHeaders;
import org.springframework.http.MediaType;
import com.ibge.ibge.api.interfaces.IMunicipioController;
import com.ibge.ibge.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MunicipioController implements IMunicipioController {

    @Lazy
    @Autowired
    private MunicipioService municipioService;  

    @Override
    public ResponseEntity<byte[]> DownloadJson(@PathVariable(value = "uf") String uf) {
        String municipiosJson = municipioService.MunicipiosJson(uf);
        byte[] municipiosJsonbytes = municipiosJson.getBytes();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=municipios.json")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(municipiosJsonbytes.length)
                .body(municipiosJsonbytes);
    }
    


    @Override
    public OutputStream DownloadCSV(HttpServletResponse response, @PathVariable(value = "uf") String uf) throws IOException, Exception {
        response.setHeader("Content-Disposition","attachment; filename=municipios.csv");
        OutputStream servletOutputStream = response.getOutputStream();
        municipioService.MunicipiosCSV(uf).writeTo(servletOutputStream);
        servletOutputStream.flush();
        servletOutputStream.close();
        return servletOutputStream;      
    }



    @Override
    public ResponseEntity<Long> GetIdMunicipio(@PathVariable(value = "uf", required = true) String uf,
                                               @PathVariable(value = "nomeMunicipio", required = true) String nomeMunicipio) {
        Long idMunicipio = municipioService.GetIdMunicipioPorNome(uf, nomeMunicipio);
        return ResponseEntity.ok(idMunicipio);
    }


}
