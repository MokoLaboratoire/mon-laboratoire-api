package com.moko.monlaboratoireapi.services;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.stereotype.Service;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class TranslationsService {

    private HashMap<String, Object> getTranslationsObject(String language) throws IOException {
        Path path = Paths.get("src/main/java/com/moko/monlaboratoireapi/translations/" + language + ".json");
        Gson gson = new Gson();
        try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            HashMap<String, Object> map = gson.fromJson(jsonObject, HashMap.class);
            return map;
        }
    }
    
    private List<String> getKeysByCategory(Map<String, String> translationsByCategory) {
        List<String> keysByCategory = new ArrayList<>();
        for ( String key : translationsByCategory.keySet() ) {
            if (!keysByCategory.contains(key)) {
                keysByCategory.add(key);
            }
        }
        return keysByCategory;
    }

    private HashMap<String, List<String>> getKeysByCategories(List<String> categories, HashMap<String, List<String>> keysByCategories, HashMap<String, HashMap<String, String>> translations) {
        for (String category : categories) {
            keysByCategories.put(category, getKeysByCategory((Map<String, String>) translations.get(category)));
        }
        return keysByCategories;
    }

    private HashMap<String, HashMap<String, String>> addKeysByCategory(HashMap<String, HashMap<String, String>> translationsByLanguage, String category) {
        HashMap<String, HashMap<String, String>> keysByCatogory = new HashMap<>();
        Map<String, String> translationsByCategoryMapped = (Map<String, String>) translationsByLanguage.get(category);
        HashMap<String, String> languageAndTranslation = new HashMap<>();
        for (Map.Entry<String, String> entry : translationsByCategoryMapped.entrySet()) {
            keysByCatogory.put(entry.getKey(), new HashMap<String, String>());
        }
        return keysByCatogory;
    }

    public HashMap<String, HashMap<String, HashMap<String, String>>> getTranslations(String languages) throws IOException {
        List<String> languagesList = Arrays.asList(languages.split("\\s*,\\s*"));
        HashMap<String, HashMap<String, HashMap<String, String>>> sortedTranslations = new HashMap<>();
        List<String> categories = new ArrayList<>();
        HashMap<String, List<String>> keysByCategories = new HashMap<>();
        Gson gson = new Gson();
        for (String language : languagesList) {
            Path path = Paths.get("src/main/java/com/moko/monlaboratoireapi/translations/" + language + ".json");
            HashMap<String, HashMap<String, String>> translationsByLanguage = new HashMap<>();
            try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                translationsByLanguage = gson.fromJson(jsonObject, HashMap.class);
                for ( String category : translationsByLanguage.keySet() ) {
                    if (!categories.contains(category)) {
                        categories.add(category);
                    }
                }
                keysByCategories = getKeysByCategories(categories, keysByCategories, translationsByLanguage);
            }
            for (String category : categories) {
                sortedTranslations.put(category, addKeysByCategory(translationsByLanguage, category));
            }
        }
        for (String language : languagesList) {
            Path path = Paths.get("src/main/java/com/moko/monlaboratoireapi/translations/" + language + ".json");
            HashMap<String, HashMap<String, String>> translationsByLanguage = new HashMap<>();
            try (Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                translationsByLanguage = gson.fromJson(jsonObject, HashMap.class);
            }
            Map<String, HashMap<String, String>> translationsByLanguageMapped = (Map<String, HashMap<String, String>>) translationsByLanguage;
            for (String category : categories) {
                Map<String, String> translationsByCategoryMapped = (Map<String, String>) translationsByLanguageMapped.get(category);
                for ( String key : keysByCategories.get(category)) {
                    sortedTranslations.get(category).get(key).put(language, translationsByCategoryMapped.get(key));
                }
            }
        }
        return sortedTranslations;
    }

    public void saveJsonFile(String languages, HashMap<String, Object> payload) throws FileNotFoundException, IOException {
        List<String> languagesList = Arrays.asList(languages.split("\\s*,\\s*"));
        final GsonBuilder builder = new GsonBuilder();
        Gson gson = new Gson();
        for (String language : languagesList) {
            String json = gson.toJson(payload);
            Map<String, Map<String, Map<String, String>>> payloadMapped = gson.fromJson(json, new TypeToken<Map<String, Map<String, Map<String, String>>>>() {}.getType());
            System.out.println("payloadMapped " + payloadMapped);
            HashMap<String, HashMap<String, String>> translationsByLanguage = new HashMap<>();

            for (Map.Entry<String, Map<String, Map<String, String>>> category : payloadMapped.entrySet()) {
                HashMap<String, String> keyAndTranslation = new HashMap<>();
                for (Map.Entry<String, Map<String, String>> key : category.getValue().entrySet()) {
                    for (Map.Entry<String, String> languageAndTranslations : key.getValue().entrySet()) {
                        if(languageAndTranslations.getKey().equals(language)) keyAndTranslation.put(key.getKey(), languageAndTranslations.getValue());
                    }
                }
                translationsByLanguage.put(category.getKey(), keyAndTranslation);
                System.out.println("translationsByLanguage " + translationsByLanguage);
            }
            Path path = Paths.get("src/main/java/com/moko/monlaboratoireapi/translations/" + language + ".json");
        }
    }
}
