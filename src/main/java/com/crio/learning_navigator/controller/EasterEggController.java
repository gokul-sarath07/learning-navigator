package com.crio.learning_navigator.controller;

import com.crio.learning_navigator.service.EasterEggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.crio.learning_navigator.config.PathConstants.API_BASE_PATH;
import static com.crio.learning_navigator.config.PathConstants.HIDDEN_NUMBER_FACT;

@RestController
@RequestMapping(API_BASE_PATH)
public class EasterEggController {

    @Autowired
    private EasterEggService easterEggService;

    @GetMapping(HIDDEN_NUMBER_FACT)
    public ResponseEntity<String> easterEgg(@PathVariable("number") int number) {
        String response = easterEggService.getNumberFact(number);

        return ResponseEntity.ok(response);
    }
}
