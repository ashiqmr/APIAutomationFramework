package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class ProductTest extends BaseTest{
	
	
	
	@Test
	public void verifyGetRequest() {
		extentReports.createTestCase("Verify Get Product");
		Response valResp = requestFactory.getAllProducts();
		extentReports.addLog(Status.INFO, valResp.asPrettyString());
		
		valResp.then().statusCode(200);
		
	}
	
	@Test
	public void verifyAddProduct() {
		extentReports.createTestCase("Verify Add Product");
		String requestPayload = "{\n"
				+ "  \"name\": \"Samsung S22\",\n"
				+ "  \"type\": \"Mobile\",\n"
				+ "  \"price\": 1000,\n"
				+ "  \"shipping\": 10,\n"
				+ "  \"upc\": \"string\",\n"
				+ "  \"description\": \"string\",\n"
				+ "  \"manufacturer\": \"string\",\n"
				+ "  \"model\": \"string\",\n"
				+ "  \"url\": \"string\",\n"
				+ "  \"image\": \"string\"\n"
				+ "}";
		ValidatableResponse valResp = requestFactory.addProducts(requestPayload).then();
		extentReports.addLog(Status.INFO, valResp.log().all().toString());
		valResp.statusCode(201);
	}
	
	@Test
	public void verifyAddProductMap() {
		extentReports.createTestCase("Verify Add Map Product");
		Map<String, Object> requestPayload = new HashMap<String, Object>();
		requestPayload.put("name", "Samsung S24");
		requestPayload.put("type", "Mobile");
		requestPayload.put("price", 1100);
		requestPayload.put("shipping", 11);
		requestPayload.put("upc", "Mobile");
		requestPayload.put("description", "Mobile");
		requestPayload.put("manufacturer", "Mobile");
		requestPayload.put("model", "Mobile");
		requestPayload.put("url", "Mobile");
		requestPayload.put("image", "Mobile");
		
		ValidatableResponse valResp = requestFactory.addProducts(requestPayload).then();
		extentReports.addLog(Status.INFO, valResp.log().all().toString());
		valResp.statusCode(201);
		
		
	}
	
	@Test
	public void verifyEndToEndCallFlow() {
		extentReports.createTestCase("Verify End to End");
		//Send Request
		Map<String, Object> requestPayload = new HashMap<String, Object>();
		requestPayload.put("name", "Samsung S24");
		requestPayload.put("type", "Mobile");
		requestPayload.put("price", 1100);
		requestPayload.put("shipping", 11);
		requestPayload.put("upc", "Mobile");
		requestPayload.put("description", "Mobile");
		requestPayload.put("manufacturer", "Mobile");
		requestPayload.put("model", "Mobile");
		requestPayload.put("url", "Mobile");
		requestPayload.put("image", "Mobile");
		
		Response rep = requestFactory.addProducts(requestPayload);
		rep.then().log().all().statusCode(201);
		
		//Get Product ID
		JsonPath jsonPathEval = rep.jsonPath();
		int id = jsonPathEval.get("id");
		System.out.println("The new ID is "+id);
		
		//Get product with id
		requestFactory.getProductById(id).then().log().all().statusCode(200);
		
		//Update the product
		requestPayload.put("price", 2000);
		requestFactory.updateProduct(id, requestPayload).then().log().all().statusCode(200);
		
		//Delete Product
		requestFactory.deleteProduct(id).then().log().all().statusCode(200);
		
		//Verify item is deleted
		requestFactory.getProductById(id).then().log().all().statusCode(404);
		
	}

}
