package com.ibge.ibge.model.DeserializerJson;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.ibge.ibge.model.Municipio;

public class JsonMunicipio extends StdDeserializer<Municipio> {
    protected JsonMunicipio(Class<?> vc) {
        super(vc);
    }

    public JsonMunicipio() {
        this(null);
    }

    @Override
    public Municipio deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) 
    throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        long idCidade = node.get("id").longValue();
        String siglaEstado = node.get("microrregiao").get("mesorregiao").get("UF").get("sigla").textValue();
        String nomeMesorregiao = node.get("microrregiao").get("mesorregiao").get("nome").textValue();
        long idEstado = node.get("microrregiao").get("mesorregiao").get("UF").get("id").longValue();
        String regiaoNome = node.get("microrregiao").get("mesorregiao").get("UF").get("regiao").get("nome").textValue();
        String nomeCidade = node.get("nome").textValue();
        String nomeFormatado = nomeCidade.concat("/").concat(siglaEstado);

        return new Municipio(idCidade, idEstado, siglaEstado, regiaoNome, nomeCidade, nomeMesorregiao, nomeFormatado);
    }    
}
