package com.api.main;

import org.json.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseTest {

	protected Response createBooking() {
		// Create JSON body
		JSONObject body = new JSONObject();
		body.put("firstname", "Dmitry");
		body.put("lastname", "Shyshkin");
		body.put("totalprice", 150);
		body.put("depositpaid", false);

		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2020-03-25");
		bookingdates.put("checkout", "2020-03-27");
		body.put("bookingdates", bookingdates);
		body.put("additionalneeds", "Baby crib");

		// Get response
		Response response = RestAssured.given().contentType(ContentType.JSON).body(body.toString())
				.post("https://restful-booker.herokuapp.com/booking");
		response.print();
		return response;
	}

	protected Response UpdateBooking(int bookingid) {
		// Create JSON body
		JSONObject body = new JSONObject();
		body.put("firstname", "Olga");
		body.put("lastname", "Shyshkin");
		body.put("totalprice", 125);
		body.put("depositpaid", true);

		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2020-03-25");
		bookingdates.put("checkout", "2020-03-27");
		body.put("bookingdates", bookingdates);
		body.put("additionalneeds", "Baby crib");

		// Update booking
		Response responseUpdate = RestAssured.given().auth().preemptive().basic("admin", "password123")
				.contentType(ContentType.JSON).body(body.toString())
				.put("https://restful-booker.herokuapp.com/booking/" + bookingid);
		responseUpdate.print();
		return responseUpdate;
	}

	protected Response PartialUpdate(int bookingid) {
		// Create JSON body
		JSONObject body = new JSONObject();
		body.put("firstname", "Olga");

		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2020-04-25");
		bookingdates.put("checkout", "2020-04-27");

		body.put("bookingdates", bookingdates);

		// PartialUpdate booking
		Response responseUpdate = RestAssured.given().auth().preemptive().basic("admin", "password123")
				.contentType(ContentType.JSON).body(body.toString())
				.patch("https://restful-booker.herokuapp.com/booking/" + bookingid);
		responseUpdate.print();
		return responseUpdate;

	}

	protected Response DeleteBooking(int bookingid) {

		// Delete booking
		Response responseDelete = RestAssured.given().auth().preemptive().basic("admin", "password123")
				.delete("https://restful-booker.herokuapp.com/booking/" + bookingid);
		responseDelete.print();
        return responseDelete;
	}

}
