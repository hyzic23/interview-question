package com.example.demo;

import com.example.demo.utils.BackBaseUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	BackBaseUtil utils;

	@Test
	void contextLoads() {
	}


	@Test
	void HashString() throws Exception {
		String url = "https://stash.backbase.com/projects/PO/repos/payment-order-integration-spec/browse/src/main/resources/schemas/definitions.json";
		System.out.println("#############  URL is " + url);

		String shortenUrl = utils.shortenURL(url);
 		System.out.println("#############  shortenUrl URL is " + shortenUrl);

		String originalUrl = utils.expandURL(shortenUrl);
		System.out.println("############# originalUrl URL is " + originalUrl);
	}



}
