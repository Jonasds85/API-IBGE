package com.ibge.ibge.relatorios;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.ibge.ibge.model.Municipio;

public class GenerateJsonMunicipio {
    private static final Logger logger = LoggerFactory.getLogger(Municipio.class);
    private List<Municipio> municipios;

    public GenerateJsonMunicipio(List<Municipio> municipios){
        this.municipios = municipios;
    }

    public String Generate() 
    {      
        logger.info("gerando arquivo JSON"); 
		Gson gson = new Gson();
		String municipiosJson = gson.toJson(municipios);
        return municipiosJson;
    }          
}
