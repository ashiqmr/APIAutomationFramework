package client;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;

public class RestClient {
	
	public Response sendGetRequest(String uri) {
		return given().when().get(uri);	
	}

	public Response sendPostRequest(String uri, Object requestPayload) {
		return given().when().contentType(ContentType.JSON).when().body(requestPayload).post(uri);
	}
	
	public Response sendPutRequest(String uri, Object requestPayload) {
		return given().when().contentType(ContentType.JSON).when().body(requestPayload).put(uri);
	}
	
	public Response sendPatchRequest(String uri, Object requestPayload) {
		return given().when().contentType(ContentType.JSON).when().body(requestPayload).patch(uri);
	}
	
	public Response sendDeleteRequest(String uri) {
		return given().when().delete(uri);
	}
}
