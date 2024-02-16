package br.com.fabex.api;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(
		classes = {Application.class}
)
@Order(1)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
class ApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;

	@Test
	void startupTest() {
		assertNotNull(applicationContext);
	}

	@Test
	void contextLoads() {
	}

}
