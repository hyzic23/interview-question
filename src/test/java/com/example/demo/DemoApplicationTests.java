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
		byte[] result = utils.encrypt(url);
		System.out.println("#############  Encrypted URL is " + result);

		String decrypted = utils.decrypt(result);
		System.out.println("############# Decrypted URL is " + decrypted);
	}



}
