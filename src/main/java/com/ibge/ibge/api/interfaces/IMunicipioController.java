package com.ibge.ibge.api.interfaces;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM_VALUE;

@RequestMapping(value = "/api/municipio")
@Api(value="API REST IBGE")
public interface IMunicipioController {

    @CrossOrigin
    @ApiOperation(value = "Retorna arquivo json com lista de municipios por estado")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/DownloadJson/{uf}", method= RequestMethod.GET)
    public abstract ResponseEntity<byte[]> DownloadJson(@PathVariable(value = "uf") String uf);


    @CrossOrigin
    @ApiOperation(value = "Retorna arquivo CSV com lista de municipios")    
    @RequestMapping(value="/DownloadCSV/{uf}", method= RequestMethod.GET, produces = APPLICATION_OCTET_STREAM_VALUE)
    public abstract void DownloadCSV(HttpServletResponse response, @PathVariable(value = "uf") String uf) throws IOException, Exception;

    
    @CrossOrigin
    @ApiOperation(value = "Retorna arquivo CSV com lista de municipios")    
    @RequestMapping(value="/DownloadCSVbyte/{uf}", method= RequestMethod.GET, produces = APPLICATION_OCTET_STREAM_VALUE)
    public abstract ResponseEntity<byte[]> DownloadCSVbyte(@PathVariable(value = "uf") String uf)throws IOException, Exception;


    @CrossOrigin
    @ApiOperation(value = "busca id do municipio pelo nome e UF")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value="/GetIdMunicipio/{uf}/{nomeMunicipio}", method= RequestMethod.GET)
    public abstract ResponseEntity<Long> GetIdMunicipio(@PathVariable(value = "uf", required = true) String uf, 
                                                        @PathVariable(value = "nomeMunicipio", required = true) String nomeMunicipio);


}