package com.api.tests;

import org.testng.annotations.Test;

import com.api.main.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeaderAndCookies extends BaseTest {

	@Test
	public void headersAndCookiesTest() {

		Header someHeader = new Header("some_name", "Some_value");
		spec.header(someHeader);

		Cookie someCookie = new Cookie.Builder("some cookie", "some cookie value").build();
		spec.cookie(someCookie);

		Response response = RestAssured.given(spec).cookie("Test cookie name", "Test cookie value")
				.header("Test header name", "Test header Value").log().all().get("/ping");

		// Get headers
		Headers headers = response.getHeaders();
		System.out.println("Headers: " + headers);

		Header serverHeader1 = headers.get("Server");
		System.out.println(serverHeader1.getName() + ": " + serverHeader1.getValue());

		String serverHeader2 = response.getHeader("Server");
		System.out.println("Server: " + serverHeader2);

		// Get cookies
		Cookies cookies = response.getDetailedCookies();
		System.out.println("Cookies: " + cookies);
	}

}
