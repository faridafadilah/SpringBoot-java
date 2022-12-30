package com.farida.springboot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class MyAfterBeforeTest {
	//Hasil:
	//	Before ALL
	//	Before
	//	Test1
	//	Before
	//	Before
	//	Test2
	//	Before
	//	Before
	//	Test3
	//	Before
	//	After All

	
	@BeforeAll
	static void BeforeAll() {
		System.out.println("Before ALL");
	}
	
	@BeforeEach
	void Before() {
		System.out.println("Before");
	}

	@Test
	void test1() {
		System.out.println("Test1");
	}
	
	@Test
	void test2() {
		System.out.println("Test2");
	}
	
	@Test
	void test3() {
		System.out.println("Test3");
	}
	
	@AfterEach
	void After() {
		System.out.println("Before");
	}
	
	@AfterAll
	static void AfterAll() {
		System.out.println("After All");
	}
	
}
