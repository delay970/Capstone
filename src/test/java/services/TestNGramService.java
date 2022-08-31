package services;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import models.NGram;

public class TestNGramService {

	
	public String testString = "this is a test string this is a test string"; 
	public Map<String, NGram> testmap = new HashMap<String, NGram>();
	
	
	@BeforeAll
	public void setup() {

	}
	
	
	@Test
	public void testCreateNGrams() {
		
	}
}
