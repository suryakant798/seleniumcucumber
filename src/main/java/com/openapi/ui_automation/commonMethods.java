package com.openapi.ui_automation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;

import com.google.gson.Gson;

import io.cucumber.gherkin.internal.com.eclipsesource.json.JsonObject;
import io.restassured.response.Response;

/**
 * This class has all the reusable methods used for writing the code in step definition classes
 */
public class commonMethods {
	
	public static String updatePayload(String Payload,String key,String Value) {
		
		JSONObject input = new Gson().fromJson(Payload, JSONObject.class) ;
		input.put(key, Value);
		return input.toJSONString();

	}
	
	public static String getUserPayLoadWithCardNumber(String CardNumber) throws Exception {
		String body = BaseTest.getPayloadFromFile("userStatus");
		return updatePayload(updatePayload(body,"pin","6666"),"cardNumber",CardNumber);
	}
	
	public static String getUserPayLoadWithCardNumber(String CardNumber,String Pin) throws Exception {
		JsonObject inputBody = new JsonObject();
		inputBody.add("cardNumber",CardNumber);
		inputBody.add("pin",Pin);
		return (inputBody).toString();
	}
	
	public static void ValidateSatusCode(Response response,int statusCode) {
    	int respStatusCode=response.getStatusCode();
   		System.out.println("Response status code is " +respStatusCode);
   		Assert.assertEquals(respStatusCode,statusCode);
	}
	
	public static void ValidateSatusLine200Ok(Response response) {
		String statusLine=response.getStatusLine();
   		System.out.println("Response status line is " +statusLine);
   		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}


	public static String getNewPayLoad(String key, String value) {
		JsonObject inputBody = new JsonObject();
		inputBody.add(key,value);
		return (inputBody).toString();
	}
	
	public static String getNewPayLoad(String key, List<String> value) {
		JSONObject inputBody = new JSONObject();
		inputBody.put(key,value);
		return (inputBody).toString();
	}
	
	public static String getNewPayLoad(String key, Map <String, Object> value) {
		JSONObject inputBody = new JSONObject();
		inputBody.put(key,value);
		return (inputBody).toString();
	}
}
