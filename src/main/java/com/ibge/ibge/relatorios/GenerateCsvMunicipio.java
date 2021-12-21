package com.ibge.ibge.relatorios;

import java.io.ByteArrayOutputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ibge.ibge.model.Municipio;
public class GenerateCsvMunicipio {
    private static final Logger logger = LoggerFactory.getLogger(Municipio.class);
    private List<Municipio> municipios;

    public GenerateCsvMunicipio(List<Municipio> municipios){
        this.municipios = municipios;
    }

    public ByteArrayOutputStream GenerateCSV() 
    {   
        try {
            logger.info("gerando arquivo CSV"); 
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            //cabeçario
            result.write("idEstado,siglaEstado,regiaoNome,nomeCidade,nomeMesorregiao,nomeFormatado".getBytes());    
            //corpo
            for (Municipio municipio : municipios) {
                String linha = municipio.toString();
                result.write(linha.getBytes());
            }   
            return result;          
        } catch (Exception e) {
            logger.info("GenerateCSV: " + e.getMessage()); 
            return null;
        }
    }     

}
