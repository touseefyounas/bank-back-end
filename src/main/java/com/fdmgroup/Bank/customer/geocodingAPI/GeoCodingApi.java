package com.fdmgroup.Bank.customer.geocodingAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeoCodingApi {
	
	private static final String BASE_URL = "https://geocoder.ca/";
	private static final String AUTH_KEY = "776086010181456885173x214750127";
	private static final HttpClient client = HttpClient.newHttpClient();
	private static final WebClient webClient = WebClient.builder()
            .defaultHeader("User-Agent", "Java Application")
            .build();
	
	public static Map<String, String> getLocationWebClient(String postalCode) {
		
		String url = BASE_URL + postalCode + "?json=1&auth=" + AUTH_KEY;
		
		try {
		String response = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();
		
		return unpack(response);
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return Collections.emptyMap();
	}
	
	public static Map<String, String> getLocation(String postalCode) {
		
		String url = BASE_URL + postalCode + "?json=1&auth=" + AUTH_KEY;
		
		HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "Java Application")
                .GET()
                .build();
		
		try {
			HttpResponse<String> response = client.send(request, 
			        HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
			return unpack(response.body());
			
		} catch (IOException | InterruptedException e) {
			
			e.printStackTrace();
		}
		
		return Collections.emptyMap();
		
	}
	
	public static Map<String, String> unpack(String response) {
		
		Map<String, String> addressMap = new HashMap<>();
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			JsonNode root = mapper.readTree(response);
			
			addressMap.put("city", root.at("/standard/city").asText());
			addressMap.put("province", root.at("/standard/prov").asText());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			
		}
		
		return addressMap;
	}
	
}
