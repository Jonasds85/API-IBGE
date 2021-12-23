package com.ibge.ibge.externo;

import java.util.List;

import com.ibge.ibge.model.Estado;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "IIBGEEstado", 
             url = "https://servicodados.ibge.gov.br", 
             configuration = FeignClintConfig.class )
public interface IIBGEEstado {
    
    @GetMapping(value = "/api/v1/localidades/estados")
    public abstract List<Estado> Estados();
}
