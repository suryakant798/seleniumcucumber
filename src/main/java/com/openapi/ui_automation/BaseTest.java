package com.openapi.ui_automation;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.remote.http.HttpRequest;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.Status;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.path.json.mapper.factory.JsonbObjectMapperFactory;
import io.restassured.response.Response;

/**
 * This class has all the reusable methods used for writing the code in step definition classes
 */
public class BaseTest {
	private static Logger log = Logger.getLogger(BaseTest.class);
	private static String URI = null;
	private static String baseURL = null;
	private static Response response = null;
	private Map<String, String> headers = new HashMap<String, String>();
	private Map<String, String> params = new HashMap<String, String>();
	public static Map<String, String> dataManager = new HashMap<String, String>();

	public Map<String, String> getHeaders() {
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");
		return headers;
	}

	public void configureHeaders(String authToken) {
		if (authToken.equals("valid")) {
			setHeaders("Authorization", "Bearer " + dataManager.get("AuthToken"));
		} else if (authToken.equals("empty")) {
			setHeaders("Authorization", "Bearer ");
		} else if (authToken.equals("Invalid")) {
			setHeaders("Authorization", "Bearer "
					+ "eyJhbGciOiJSUzI1NiIsImtpZCI6Ilg1ZVhrNHh5b2pORnVtMWtsMll0djhkbE5QNC1jNTdkTzZRapplabbvTmsiLCJ0eXAiOiJKV1QifQ.eyJpZHAiOiJMb2NhbEFjY291bnQiLCJzdWIiOiJiMTgzNjI4YS0xZWJmLTQ4ZmItYjZiYS00N2YzN2NkOWNiMzMiLCJuYW1lIjoiU3VwZXJQYXNzIiwiZXh0ZW5zaW9uX0NhcmROdW1iZXIiOiI5OTM1NDQxNDEwOTk5OSIsInRmcCI6IkIyQ18xX1JPUENfQXV0aCIsImF6cCI6ImM5OWRkYzg2LTMxYjEtNDNhNy1hMjA4LTE4OTM2Mjc3MjFiMiIsInZlciI6IjEuMCIsImlhdCI6MTY4NDI5MjY1OSwiYXVkIjoiYzk5ZGRjODYtMzFiMS00M2E3LWEyMDgtMTg5MzYyNzcyMWIyIiwiZXhwIjoxNjg0Mjk2MjU5LCJpc3MiOiJodHRwczovL3N1bmNvcndtYWIyYy5iMmNsb2dpbi5jb20vN2Y0M2Q1NzItYTNiMy00NmU1LTgzZjQtNDc0NDdjMmJmZTBjL3YyLjAvIiwibmJmIjoxNjg0MjkyNjU5fQ.hNL7jQGEgDtqDbuR4DXlvSSu1wMliimthJkHvlTAoYjm8oCQ9B3JUFZUxhgs3TwsYhoFRYcLjHeghPFUtDcxlJa_2TzZ2y5FIXqrF8uk_N5T_ejHjFt7Gzkj9PAju3BLJmyaxqPYRbyQ5nlgY_NrOxblg4wmx-oAGi4gMxVcB-M5xYiJBEkpeBk4YBYk73_XbllZ1iluxPhOdj1nW6FuK2xJfIP-lxvBE_lYHxsXeEyxrwILbOYISZOL5-bRONIj29bESXRgCD2G4hY3Goj9DW0lmB_bJSahQyC9iQImS-dREYCiFthYhTomPQnR_ul2z8XUGGUSDxKDvkWCkskHsA");
		} else {
			setHeaders("Authorization", "Bearer " + authToken);
		}
	}
	
	public void configureHeadersforaccessToken(String accessToken) {
		if (accessToken.equals("valid")) {
			setHeaders("Authorization", "Bearer " + dataManager.get("accessToken"));
		} else if (accessToken.equals("empty")) {
			setHeaders("Authorization", "Bearer ");
		} else if (accessToken.equals("Invalid")) {
			setHeaders("Authorization", "Bearer "
					+ "eyJhbGciOiJSUzI1NiIsImtpZCI6Ilg1ZVhrNHh5b2pORnVtMWtsMll0djhkbE5QNC1jNTdkTzZRapplabbvTmsiLCJ0eXAiOiJKV1QifQ.eyJpZHAiOiJMb2NhbEFjY291bnQiLCJzdWIiOiJiMTgzNjI4YS0xZWJmLTQ4ZmItYjZiYS00N2YzN2NkOWNiMzMiLCJuYW1lIjoiU3VwZXJQYXNzIiwiZXh0ZW5zaW9uX0NhcmROdW1iZXIiOiI5OTM1NDQxNDEwOTk5OSIsInRmcCI6IkIyQ18xX1JPUENfQXV0aCIsImF6cCI6ImM5OWRkYzg2LTMxYjEtNDNhNy1hMjA4LTE4OTM2Mjc3MjFiMiIsInZlciI6IjEuMCIsImlhdCI6MTY4NDI5MjY1OSwiYXVkIjoiYzk5ZGRjODYtMzFiMS00M2E3LWEyMDgtMTg5MzYyNzcyMWIyIiwiZXhwIjoxNjg0Mjk2MjU5LCJpc3MiOiJodHRwczovL3N1bmNvcndtYWIyYy5iMmNsb2dpbi5jb20vN2Y0M2Q1NzItYTNiMy00NmU1LTgzZjQtNDc0NDdjMmJmZTBjL3YyLjAvIiwibmJmIjoxNjg0MjkyNjU5fQ.hNL7jQGEgDtqDbuR4DXlvSSu1wMliimthJkHvlTAoYjm8oCQ9B3JUFZUxhgs3TwsYhoFRYcLjHeghPFUtDcxlJa_2TzZ2y5FIXqrF8uk_N5T_ejHjFt7Gzkj9PAju3BLJmyaxqPYRbyQ5nlgY_NrOxblg4wmx-oAGi4gMxVcB-M5xYiJBEkpeBk4YBYk73_XbllZ1iluxPhOdj1nW6FuK2xJfIP-lxvBE_lYHxsXeEyxrwILbOYISZOL5-bRONIj29bESXRgCD2G4hY3Goj9DW0lmB_bJSahQyC9iQImS-dREYCiFthYhTomPQnR_ul2z8XUGGUSDxKDvkWCkskHsA");
		} else {
			setHeaders("Authorization", "Bearer " + accessToken);
		}
	}
	

	public void setHeaders(String key, String value) {
		headers.put(key, value);
		log.info("headers are : " + headers);
	}

	public Map<String, String> getparams() {
		return this.params;
	}

	public void setParams(String key, String value) {
		params.put(key, value);
		System.out.println(params);
	}

	public void setLocationParams(String latitude, String longitude) {
		if (latitude.length() > 0)
			params.put("latitude", latitude);
		log.info("Latitude param is " + latitude);
		if (longitude.length() > 0)
			params.put("longitude", longitude);
		log.info("Longitude param is " + longitude);
	}

	public static String getPayloadFromFile(String fileName) throws Exception {

		// ReadJson File
		String file = "src/test/resources/input_payload/" + fileName + ".json";
		log.info("Json File path: " + file);
		String json = fileUtils.readFileAsString(file);

		return json;
	}

	protected void setURI(String EndPointUrlName) {
		String env = System.getProperty("runenvironment");
		if (env == null || env.isEmpty())
			env = "dev";
		BaseTest.baseURL = getProperty(env.toLowerCase() + "." + EndPointUrlName);

	}

	protected void updateURI(String EndPointUrlExtention) {
		BaseTest.URI = BaseTest.baseURL.trim() + EndPointUrlExtention.trim();
		log.info("End Point URI :- " + URI);
	}

	protected void appendURI(String EndPointUrlExtention) {
		System.out.println("URI: " + BaseTest.URI);
		BaseTest.URI = BaseTest.URI + EndPointUrlExtention;
		log.info("URI after appending: " + BaseTest.URI);
	}

	public String getURI() {
		return BaseTest.URI;
	}

	public String getBaseURL() {
		return BaseTest.baseURL;
	}

	public static final String getProperty(String property) {
		return fileUtils.getConfigProperty(property);
	}

	public Response getResponse(String Jsonbody) throws Exception {
		RestAssured.baseURI = BaseTest.URI;
		BaseTest.response = RestAssured.given().headers(getHeaders()).body(Jsonbody).get();
		// printing response in the console
		log.info("Payload>>>> " + Jsonbody);
		String responseBody = response.getBody().asPrettyString();
		log.info("Response is>>>> " + responseBody);
		return response;
	}

	public Response getResponse() throws Exception {
		RestAssured.baseURI = BaseTest.URI;
		BaseTest.response = RestAssured.given().headers(getHeaders()).get();
		// printing response in the console
		String responseBody = response.getBody().asPrettyString();
		log.info("Response is>>>> " + responseBody);
		// ReportingUtils.getTestlog().pass("Response generated");
		return response;
	}

	public Response getPostRequestResponse(String Jsonbody) throws Exception {
		RestAssured.baseURI = BaseTest.URI;
		BaseTest.response = RestAssured.given().headers(getHeaders()).body(Jsonbody).post();
		
		log.info("Response getStatusCode >>>> " + response.getStatusCode());
		
		// printing response in the console
		log.info("Payload>>>> " + Jsonbody);
		String responseBody = response.getBody().asPrettyString();
		log.info("Response is>>>> " + responseBody);
		return response;
	}

	public Response getPutRequestResponse(String Jsonbody) throws Exception {
		RestAssured.baseURI = BaseTest.URI;
		BaseTest.response = RestAssured.given().headers(getHeaders()).params(params).body(Jsonbody).put();
		// printing response in the console
		log.info("Payload>>>> " + Jsonbody);
		String responseBody = response.getBody().asPrettyString();
		log.info("Response is>>>> " + responseBody);
		return response;
	}

	public Response getPostRequestResponse() throws Exception {
		RestAssured.baseURI = BaseTest.URI;
		BaseTest.response = RestAssured.given().headers(getHeaders()).post();
		// printing response in the console
		String responseBody = response.getBody().asPrettyString();
		log.info("Response is>>>> " + responseBody);
		return response;
	}

	public Response getHeadRequestResponse() throws Exception {
		RestAssured.baseURI = BaseTest.URI;
		BaseTest.response = RestAssured.given().headers(getHeaders()).head();
		// printing response in the console
		String responseBody = response.getBody().asPrettyString();
		log.info("Response is>>>> " + responseBody);
		return response;
	}

	public Response getDeleteResponseWithParams() throws Exception {
		RestAssured.baseURI = BaseTest.URI;
		BaseTest.response = RestAssured.given().queryParams(getparams()).headers(getHeaders()).when().delete();
		// printing response in the console
		String responseBody = response.getBody().asPrettyString();
		log.info("Response is>>>> " + responseBody);
		return response;
	}

	public Response getRequestResponseWithParameteres() throws Exception {
		RestAssured.baseURI = BaseTest.URI;
		BaseTest.response = RestAssured.given().queryParams(getparams()).headers(getHeaders()).when().get();
		// printing response in the console
		String responseBody = response.getBody().asPrettyString();
		log.info("Response is>>>> " + responseBody);
		return response;
	}

	public Response gePostResponseWithParameteres(String jsonBody) throws Exception {
		RestAssured.baseURI = BaseTest.URI;
		BaseTest.response = RestAssured.given().queryParams(getparams()).headers(getHeaders()).body(jsonBody).when()
				.post();
		// printing response in the console
		String responseBody = response.getBody().asPrettyString();
		log.info("Response is>>>> " + responseBody);
		return response;
	}

	public Response getRequestResponseWithParameteres(String jsonBody) throws Exception {
		RestAssured.baseURI = BaseTest.URI;
		BaseTest.response = RestAssured.given().queryParams(getparams()).headers(getHeaders()).body(jsonBody).when()
				.get();
		// printing response in the console
		String responseBody = response.getBody().asPrettyString();
		log.info("Response is>>>> " + responseBody);
		return response;
	}

	public String getResponseAsString(String Jsonbody) throws Exception {
		Response response = getResponse(Jsonbody);
		return response.getBody().asPrettyString();
	}

	public void validateSatusCode(int statusCode) {
		int respStatusCode = response.getStatusCode();
		log.info("Response status code is " + respStatusCode);
		Assert.assertEquals(respStatusCode, statusCode);

	}

	public boolean isSatusCode(int statusCode) {
		int respStatusCode = response.getStatusCode();
//		log.info("Response status code value is " + respStatusCode);
		return respStatusCode == statusCode;
	}

	public boolean isSatusCodeSucess() {
		int respStatusCode = response.getStatusCode();
//		log.info("Response status code value is " + respStatusCode);
		if (respStatusCode >= 200 && respStatusCode < 299)
			return true;
		else
			return false;
	}

	public void validateSatusCodeNotfound(int statusCode) {
		int respStatusCode = response.getStatusCode();
		log.info("Response status code is " + respStatusCode);
		Assert.assertEquals(respStatusCode, statusCode);
	}

	public void validateSatusLine200Ok() {
		String statusLine = response.getStatusLine();
		log.info("Response status line is " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	public void validateJSONValue(String key, String ExpectedValue) {
		String ActualValue = response.jsonPath().get(key).toString();
		log.info("vaule of " + key + " is " + ActualValue);
		Assert.assertEquals(ActualValue, ExpectedValue);
	}

	public void validateJSONValueContains(String key, String ExpectedValue) {
		String ActualValue = response.jsonPath().get(key).toString();
		log.info("vaule of " + key + " is " + ActualValue);
		Assert.assertTrue(ActualValue.contains(ExpectedValue), ActualValue + " doesnot contain " + ExpectedValue);
	}

	public String validateJSONValueNotNull(String key) {
		String ActualValue = response.jsonPath().get(key).toString();
		log.info("vaule of " + key + " is " + ActualValue);
		Assert.assertNotNull(ActualValue, "respective value for " + key + " is null");
		return ActualValue;
	}

	public void validateJSONValueNull(String key) {
		Object ActualValue = response.jsonPath().get(key);
		log.info("vaule of " + key + " is " + ActualValue);
		Assert.assertNull(ActualValue, "respective value for " + key + " is not null");

	}
	
	//SKT
	public void getsatusCode() {
		RestAssured.baseURI = BaseTest.URI;
		BaseTest.response = RestAssured.given().headers(getHeaders()).post();
		// printing response in the console
		String responseBody = response.getBody().asPrettyString();
		System.out.println("SKT responseBody" + responseBody);
		
		
		int respStatusCode = response.getStatusCode();
//		log.info("Response status code value is " + respStatusCode);
		System.out.println("SKT response" + respStatusCode);
		

	}

}
