package com.ibge.ibge.model.DeserializerJson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.ibge.ibge.model.Estado;

public class JsonEstado extends StdDeserializer<Estado>{

    protected JsonEstado(Class<?> vc) {
        super(vc);
    }

    public JsonEstado() {
        this(null);
    }

    @Override
    public Estado deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) 
    throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        long id = node.get("id").longValue();
        String sigla = node.get("sigla").textValue();
        String nome = node.get("nome").textValue();
        return new Estado(id, sigla, nome);
    }
    
}
