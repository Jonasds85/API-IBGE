package com.ibge.ibge.externo;

import java.util.ArrayList;
import java.util.List;
import com.ibge.ibge.model.Municipio;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FallBackIBGEMunicipio implements IIBGEMunicipio {
     @Override
     public List<Municipio> MunicipiosPorUF(String uf) {        
         log.error("Fallback IGBE Municipios MunicipiosPorUF = " + uf);
         return new ArrayList<Municipio>(); 
     }

    @Override
    public List<Municipio> Municipios() {
        log.error("Fallback IGBE Municipios");
        return new ArrayList<Municipio>(); 
   }
}