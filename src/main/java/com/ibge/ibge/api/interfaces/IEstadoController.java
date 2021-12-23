package com.ibge.ibge.api.interfaces;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

import java.io.IOException;
import java.io.OutputStream;

@RequestMapping(value = "/api/estado")
@Api(value="API REST IBGE")
public interface IEstadoController {
    @CrossOrigin
    @ApiOperation(value = "Retorna arquivo json com lista de estados")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/DownloadJson", method= RequestMethod.GET)
    public abstract ResponseEntity<byte[]> DownloadJson();


    @CrossOrigin
    @ApiOperation(value = "Retorna arquivo CSV com lista de estados")    
    @RequestMapping(value="/DownloadCSV", method= RequestMethod.GET, produces = APPLICATION_OCTET_STREAM_VALUE)
    public abstract OutputStream DownloadCSV(HttpServletResponse response) throws IOException, Exception;
    
}
