package com.ibge.ibge.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.ibge.ibge.externo.FallBackIBGEMunicipio;
import com.ibge.ibge.externo.IIBGEMunicipio;
import com.ibge.ibge.model.Municipio;
import com.ibge.ibge.relatorios.GenerateCsvMunicipio;
import com.ibge.ibge.relatorios.GenerateJsonMunicipio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "municipio")
@Service
public class MunicipioService {

    @Lazy
    @Autowired()
    private IIBGEMunicipio ibgeMunicipio;    

    @Autowired()
    private FallBackIBGEMunicipio fallBackIBGEMunicipio;
    
    @Autowired()
    private CircuitBreakerFactory circuitBreakerFactory;
    
    @Cacheable(unless = "#result == null or #result.size() == 0")
    public List<Municipio> GetMunicipiosIBGE(String uf) {
        return GetMunicipios(uf);
    }    
    
    public String MunicipiosJson(String uf){
        List<Municipio> municipios = GetMunicipios(uf);
        GenerateJsonMunicipio json = new GenerateJsonMunicipio(municipios);
        String JsonResult = json.Generate();
        return JsonResult;
    }

    public ByteArrayOutputStream MunicipiosCSV(String uf) {        
        List<Municipio> municipios = GetMunicipios(uf);
        GenerateCsvMunicipio CsvMunicipio = new GenerateCsvMunicipio(municipios);        
        return CsvMunicipio.GenerateCSV();
    }

    @Cacheable(unless = "#result == null")
    public Long GetIdMunicipioPorNome(String uf, String nomeMunicipio) {   
        List<Municipio> municipios = GetMunicipios(uf);
        Municipio municipio = municipios.stream()
            .filter(m -> m.getNomeCidade().toUpperCase().equals(nomeMunicipio.toUpperCase()))
            .findAny()
            .orElse(null);

        if (municipio == null)
            return null;

        return municipio.getId();
    }

    private List<Municipio> GetMunicipios(String uf){
        CircuitBreaker cb = circuitBreakerFactory.create("nameservicebreaker");
        if (uf.isEmpty())
            return cb.run(() -> this.ibgeMunicipio.Municipios(), throwable -> fallBackIBGEMunicipio.Municipios());        

        return cb.run(() -> this.ibgeMunicipio.Municipios(), throwable -> fallBackIBGEMunicipio.MunicipiosPorUF(uf));         
    }
}
