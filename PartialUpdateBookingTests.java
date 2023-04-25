package com.api.tests;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.api.main.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PartialUpdateBookingTests extends BaseTest {

	@Test
	public void partialUpdateBookingTest() {
		// Create booking
		Response responseCreate = createBooking();

		// Get bookingId of new booking
		int bookingid = responseCreate.jsonPath().getInt("bookingid");

		//Update booking 
		Response responseUpdate = PartialUpdate(bookingid);
		
		// Verifications
		// Verify response 200
		Assert.assertEquals(responseUpdate.getStatusCode(), 200, "Status code should be 200, but it's not.");

		// Verify All fields
		SoftAssert softAssert = new SoftAssert();
		String actualFirstName = responseUpdate.jsonPath().getString("firstname");
		softAssert.assertEquals(actualFirstName, "Olga", "firstname in response is not expected");

		String actualLastName = responseUpdate.jsonPath().getString("lastname");
		softAssert.assertEquals(actualLastName, "Shyshkin", "lastname in response is not expected");

		int price = responseUpdate.jsonPath().getInt("totalprice");
		softAssert.assertEquals(price, 150, "totalprice in response is not expected");

		boolean depositpaid = responseUpdate.jsonPath().getBoolean("depositpaid");
		softAssert.assertFalse(depositpaid, "depositpaid should be false, but it's not");

		String actualCheckin = responseUpdate.jsonPath().getString("bookingdates.checkin");
		softAssert.assertEquals(actualCheckin, "2020-04-25", "checkin in response is not expected");

		String actualCheckout = responseUpdate.jsonPath().getString("bookingdates.checkout");
		softAssert.assertEquals(actualCheckout, "2020-04-27", "checkout in response is not expected");

		String actualAdditionalneeds = responseUpdate.jsonPath().getString("additionalneeds");
		softAssert.assertEquals(actualAdditionalneeds, "Baby crib", "additionalneeds in response is not expected");

		softAssert.assertAll();
	}
}