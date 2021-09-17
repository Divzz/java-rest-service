package com.example.restservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.example.restservice.models.Quote;

public class QuoteService {
	
	@Autowired
	RestTemplate restTemplate;
	
	public QuoteService() {
		
	}
	
	public String getQuote() {

		try {
			RestTemplate restTemplate = new RestTemplate();
			Quote quote = restTemplate.getForObject("https://quoters.apps.pcfone.io/api/random", Quote.class);
			return quote.getValue().toString();		
		}
		catch(Exception e) {
			return (e.toString());
		}
	}
}
