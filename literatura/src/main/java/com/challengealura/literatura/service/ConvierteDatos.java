package com.challengealura.literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {

    private ObjectMapper objectMapper = new ObjectMapper();

    //Ya con el json obtenido del api, pasamos el json y la clase que mapea estos datos
    //Para luego retornar esos datos en el tipo de clase que le pasemos
    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        try {
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
