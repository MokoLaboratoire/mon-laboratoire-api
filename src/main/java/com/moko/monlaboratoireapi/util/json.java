package com.moko.monlaboratoireapi.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class json {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaulObjectMapper = new ObjectMapper();

        return defaulObjectMapper;
    }

    public static JsonNode parse(String src) throws IOException {
        return objectMapper.readTree(src);
    }
}
