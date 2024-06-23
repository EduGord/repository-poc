package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest
@Profile("prod")
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}

}
