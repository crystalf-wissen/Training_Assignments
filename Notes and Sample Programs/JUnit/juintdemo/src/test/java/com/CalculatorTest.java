package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@DisplayName("Testing Math Operations");
@TestInstance(Lifecycle.PER_CLASS);
class CalculatorTest {
	static Calculator c1;
	
	@BeforeAll
	static void createCalculator() {
		c1 = new Calculator();
	}
	
	
	@AfterAll
	static void removeCalculator() {
		c1 = new Calculator();
		System.out.println("-----FINISHED-----");
	}
	
	@BeforeEach
	void abc() {
		System.out.print("Before each test case.....");
	}
	
	@AfterEach
	void xyz() {
		System.out.print("After each test case.....");
	}
	
	
	@Test
	void test() {
		assertTrue(true);
	}
	
	@Test
	void testAdd() {
		assertEquals(30,c1.add(10,20), () -> "Sum is not right");
		assertEquals(-10,c1.add(-20,10), () -> "Sum is not right");
	}

	@Test
	void testDiv() {
		Calculator c1 = new Calculator();
		assertEquals(2,c1.div(10,5));
		assertThrows(ArithmeticException.class, () -> c1.div(10, 0));
	}
	
	@Test
	@DisplayName(value = "Testing Multiply Operation")
	void testMul() {
		System.out.print("From testMulStart()");
		assertAll(
		()->assertEquals(50, c1.mul(10,5)),
		()->assertEquals(100, c1.mul(10,10)),
		()->assertEquals(60,c1.mul(10,6)),
		()->assertEquals(90, c1.mul(10,9)),
		()->assertEquals(30, c1.mul(10,3))
		);
		System.out.print("From testMulStart()");
		
	}
	
	@Test
	void testMod() {
		assertAll(
		()->assertEquals(0, c1.mod(10,5)),
		()->assertEquals(0, c1.mod(20,5))
		);
	}

}
