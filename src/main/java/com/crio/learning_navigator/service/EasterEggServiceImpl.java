package com.crio.learning_navigator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EasterEggServiceImpl implements EasterEggService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String getNumberFact(int number) {
        String uri = "http://numbersapi.com/" + number;

        return restTemplate.getForObject(uri, String.class);
    }
}
