package com.example.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

@Controller
public class DemoController {

	// Create the low-level client
	RestClient restClient = RestClient.builder(
		new HttpHost("localhost", 9200)).build();

	@Get("/search")
	public String getSomeInfo() throws IOException {
		String multiSearchQuery = "\n{}\n{\"query\": {\"term\": {\"InvoiceDate\": \"1/18/2011 9:44\"}}}\n{\"index\": \"favorite_candy\"}\n{\"query\": {\"match_all\":{}}}\n";

		Request request = new Request("GET", "aggregation_data/_msearch");
		request.setJsonEntity(multiSearchQuery);
		Response response = restClient.performRequest(request);
		return EntityUtils.toString(response.getEntity());
	}
}
