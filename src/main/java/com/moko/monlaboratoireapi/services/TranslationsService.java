package com.moko.monlaboratoireapi.services;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Service
public class TranslationsService {

    public HashMap<String, Object> getTranslations() throws IOException {

        Path path = Paths.get("src/main/java/com/moko/monlaboratoireapi/translations/en.json");

        Gson gson = new Gson();

        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            System.out.println(jsonObject);
            HashMap<String, Object> map = gson.fromJson(jsonObject, HashMap.class);
            return map;
        }
    }

    public void saveJsonFile(HashMap<String, Object> payload) throws FileNotFoundException, IOException {
        Path path = Paths.get("src/main/java/com/moko/monlaboratoireapi/translations/en.json");
        final GsonBuilder builder = new GsonBuilder();
        /* final Gson gson = builder.create(); */
        /* String JSONObject = gson.toJson(payload); */
        /* System.out.println(JSONObject); */

        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            JsonElement tree = gson.toJsonTree(payload);
            gson.toJson(tree, writer);
        }

    }
}
