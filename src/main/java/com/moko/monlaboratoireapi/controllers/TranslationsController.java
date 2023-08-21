package com.moko.monlaboratoireapi.controllers;

import com.moko.monlaboratoireapi.services.TranslationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/v1/translations")
public class TranslationsController {

    private final TranslationsService translationsService;

    @Autowired
    public TranslationsController(TranslationsService translationsService) {
        this.translationsService = translationsService;
    }

    @GetMapping
    public HashMap<String, HashMap<String, HashMap<String, String>>> getTranslations(@RequestParam String languages) throws IOException {
        return translationsService.getTranslations(languages);
    }

    @PostMapping
    public void addTranslations(@RequestParam String languages, @RequestBody HashMap<String, Object> payload) throws Exception {
        translationsService.saveJsonFile(languages, payload);
    }
}
