package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.main.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteBookingTests extends BaseTest {

	@Test
	public void deleteBookingTest() {
		// Create booking
		Response responseCreate = createBooking();
		
		// Get bookingId of new booking
		int bookingid = responseCreate.jsonPath().getInt("bookingid");

		//Delete booking
		Response responseDelete = DeleteBooking(bookingid);
		
		// Verifications
		// Verify response 201
		Assert.assertEquals(responseDelete.getStatusCode(), 201, "Status code should be 201, but it's not.");
	
		Response responseGet = RestAssured.get("https://restful-booker.herokuapp.com/booking/" + bookingid);
		responseGet.print();
		
		Assert.assertEquals(responseGet.getBody().asString(), "Not Found", "Body should be 'Not Found', but it's not.");
		
	}
}