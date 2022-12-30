package com.farida.springboot;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class MyAssertTest {
	List<String> todos = Arrays.asList("AWS", "Azure", "DevOps");
	
	@Test
	void test() {
		boolean test = todos.contains("AWS");
		boolean test2 = todos.contains("GSP");
		//Test apakah ada "AWS" di daftar list
		assertEquals(true, test);
		//Test isi list => 3
		assertEquals(3, todos.size());
		//Test True pada method test 
		assertTrue(test);
		//Test False pada method test2
		assertFalse(test2);
		//Test Persamaan Array
		assertArrayEquals(new int[] {1,2}, new int[] {1,2});
	}

}
