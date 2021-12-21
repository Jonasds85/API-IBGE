package com.ibge.ibge.externo;

import java.util.List;
import com.ibge.ibge.model.Municipio;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "IIBGEMunicipio", 
             url = "https://servicodados.ibge.gov.br",
             configuration = FeignClintConfig.class
             //fallback = FallBackIBGEMunicipio.class
             )
             
public interface IIBGEMunicipio {
    @GetMapping(value = "/api/v1/localidades/estados/{UF}/municipios")
    public abstract List<Municipio> MunicipiosPorUF(@PathVariable(value = "UF", required = true) String uf);

    @GetMapping(value = "/api/v1/localidades/municipios")
    public abstract List<Municipio> Municipios();
}
