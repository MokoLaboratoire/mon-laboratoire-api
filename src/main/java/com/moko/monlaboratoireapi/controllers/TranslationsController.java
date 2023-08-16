package com.moko.monlaboratoireapi.controllers;

import com.moko.monlaboratoireapi.services.TranslationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("api/v1/translations")
public class TranslationsController {

    private final TranslationsService translationsService;

    @Autowired
    public TranslationsController(TranslationsService translationsService) {
        this.translationsService = translationsService;
    }

    @GetMapping
    public HashMap<String, Object> getTranslations() throws IOException {
        return translationsService.getTranslations();
    }

    @PostMapping
    public void addTranslations(@RequestBody HashMap<String, Object> payload) throws Exception {
        translationsService.saveJsonFile(payload);
    }
}
