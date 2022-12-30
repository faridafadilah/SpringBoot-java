package com.farida.springboot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyMathTest {
	private MyMath math = new MyMath();
	
	//Test if number={1,2,3} => expected result = 6
	@Test
	void test1() {
		assertEquals(6, math.calculateSum(new int[] {1,2,3}));
	}
	
	//Test if number={} => expected result 0
	@Test
	void test2() {
		assertEquals(0, math.calculateSum(new int[] {}));
	}
}
