package com.ibge.ibge.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ibge.ibge.model.DeserializerJson.JsonMunicipio;

import lombok.Data;
import lombok.Getter;

@Data
@JsonDeserialize(using = JsonMunicipio.class)
@Getter
public class Municipio {
    private long id;
    private long idEstado;
    private String siglaEstado;
    private String regiaoNome;
    private String nomeCidade;
    private String nomeMesorregiao;
    private String nomeFormatado;

    public Municipio(){};

    public  Municipio(long id, long idEstado, String  siglaEstado, String regiaoNome, String nomeCidade, String nomeMesorregiao, String nomeFormatado) {
        this.id = id;
        this.idEstado = idEstado;
        this.siglaEstado = siglaEstado;
        this.regiaoNome = regiaoNome;
        this.nomeCidade = nomeCidade;
        this.nomeMesorregiao = nomeMesorregiao;
        this.nomeFormatado = nomeFormatado;
    }    

    @Override
    public String toString(){
        return this.id +","+
               this.idEstado +","+
               this.siglaEstado +","+
               this.regiaoNome +","+
               this.nomeCidade +","+
               this.nomeMesorregiao +","+
               this.nomeFormatado;
    }
}
