package com.mcnc.example.clientapp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

public class MyTest {
	
	@Test
	public void doTest() throws UnsupportedEncodingException {
		String url = URLEncoder.encode("http://localhost:8080/client-app", "UTF-8");
		System.out.println(url);
	}
}
