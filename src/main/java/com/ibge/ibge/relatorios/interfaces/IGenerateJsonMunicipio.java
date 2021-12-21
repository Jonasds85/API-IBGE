package com.ibge.ibge.relatorios.interfaces;

import java.util.List;

import com.ibge.ibge.model.Municipio;

public interface IGenerateJsonMunicipio {
    public abstract String Generate(List<Municipio> municipios);
}
