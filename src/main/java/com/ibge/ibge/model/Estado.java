package com.ibge.ibge.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ibge.ibge.model.DeserializerJson.JsonEstado;

import lombok.Data;
import lombok.Getter;

@Data
@JsonDeserialize(using = JsonEstado.class)
@Getter
public class Estado {
    private long id;
    private String sigla;
    private String nome;    

    public Estado(){};

    public Estado(long id, String sigla, String nome){
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
    }

    @Override
    public String toString(){
        return this.id +","+
               this.sigla +","+
               this.nome;
    }
}
