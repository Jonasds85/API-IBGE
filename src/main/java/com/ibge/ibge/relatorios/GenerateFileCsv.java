package com.ibge.ibge.relatorios;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ibge.ibge.model.Municipio;

@Service
public class GenerateFileCsv{
    private static final Logger logger = LoggerFactory.getLogger(Municipio.class);
    
    public <T> ByteArrayOutputStream GenerateCSV(List<T> listModel) 
    {   
        try {
            logger.info("gerando arquivo CSV"); 
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            //cabeçario
            String StrCabecario = GetCabecario(listModel);
            result.write(StrCabecario.getBytes());    
            //corpo
            for (T model : listModel) {
                String linha = model.toString();
                result.write(linha.getBytes());
            }   
            return result;          
        } catch (Exception e) {
            logger.info("GenerateCSV: " + e.getMessage()); 
            return null;
        }
    }  
    
    private <T> String GetCabecario(List<T> listModel){
        StringBuilder str = new StringBuilder("");
        Field[] fields = listModel.stream().getClass().getDeclaredFields();
        for (Field field : fields) {
            str.append(field.getName()).append(",");
        }

        String commaseparatedlist = str.toString();
        if (commaseparatedlist.length() > 0)           
            commaseparatedlist = commaseparatedlist.substring(0, commaseparatedlist.length() - 1);

        return commaseparatedlist;
    }

}
