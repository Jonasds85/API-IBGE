package com.ibge.ibge.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.ibge.ibge.externo.FallBackIBGEEstado;
import com.ibge.ibge.externo.IIBGEEstado;
import com.ibge.ibge.model.Estado;
import com.ibge.ibge.relatorios.GenerateFileCsv;
import com.ibge.ibge.relatorios.GenerateFileJson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "estado")
@Service
public class EstadoService {
    
    @Lazy
    @Autowired
    private IIBGEEstado ibgeEstado;

    @Autowired()
    private FallBackIBGEEstado fallBackIBGEEstado;
    
    @Autowired()
    private CircuitBreakerFactory circuitBreakerFactory;

    @Autowired()
    private GenerateFileJson GenerateJson;

    @Autowired
    private GenerateFileCsv GenerateCSV;
    
    public String EstadosJson(){     
        List<Estado> estados = GetEstados();           
        return GenerateJson.Generate(estados);
    }

    public ByteArrayOutputStream EstadosCSV(){    
        return GenerateCSV.GenerateCSV(GetEstados());
    }

    @Cacheable(unless = "#result == null or #result.size() == 0")
    private List<Estado> GetEstados(){
        CircuitBreaker cb = circuitBreakerFactory.create("nameservicebreakerEstado"); 
           
        return cb.run(() -> this.ibgeEstado.Estados(), 
                throwable -> fallBackIBGEEstado.Estados());                
    }    
}
