package com.example.restservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.restservice.models.Quote;

@Service
public class QuoteService {
	
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
