package request;

import client.RestClient;
import io.restassured.response.Response;

public class RequestFactory {

	RestClient restClient;
	
	
	
	public RequestFactory() {
		restClient = new RestClient();
	}

	public Response getAllProducts() {
		return restClient.sendGetRequest("/products");
	}
	
	public Response getProductById(int id) {
		return restClient.sendGetRequest("/products/"+id);
	}
	
	public Response addProducts(Object requestPayload) {
		return restClient.sendPostRequest("/products", requestPayload);
	}
	
	public Response updateProduct(int id, Object requestPayload) {
		return restClient.sendPutRequest("/products/"+id, requestPayload);
	}
	
	public Response deleteProduct(int id) {
		return restClient.sendDeleteRequest("/products/"+id);
	}
}
