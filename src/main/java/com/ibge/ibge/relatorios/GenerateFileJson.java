package com.ibge.ibge.relatorios;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.ibge.ibge.model.Municipio;

@Service
public class GenerateFileJson {
    private static final Logger logger = LoggerFactory.getLogger(Municipio.class);

    public <T> String Generate(List<T> listModel) 
    {      
        logger.info("gerando arquivo JSON"); 
		Gson gson = new Gson();
		String municipiosJson = gson.toJson(listModel);
        return municipiosJson;
    }          
}
