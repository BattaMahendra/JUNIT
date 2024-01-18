package com.mahi.junit.custom;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/*
 * this is useful for creating instance of the class directly
 * i.e you need instance of class in order to run its methods 
 * so you have to create everything manually
 * so we use @TestInstance so that we dont need to write bp code for instantiation of a class
 * if you use PER_CLASS then you don't need static for @BeforeAll method
 */
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
/*
 * this annotation replaces the class name with the string in junit window
 * so that code becomes more readable
 * we can use this on the method level too
 * in that case it displays passed string instead of method name
 */
@DisplayName("testing class for Calculations.class")
class CalculationsTest {
	
	 Calculations obj;
	 
	 /*
	  * this runs even before the CalculationsTest instance is created.
	  * so without instance how can a method runs
	  * thats why we have to declare this method to be static so that it can 
	  * run even before the instance is created
	  * same applies for the @AfterAll annotated method
	  * 
	  * in @TestInstance if you use PER_CLASS then 
	  * you don't need static for @BeforeAll method because the instance is 
	  * created even before execution of @BeforeAll methods
	  */
	 @BeforeAll
	 static void beforeAllInit() {
		 System.out.println("runs even before the instance of class is created");
	 }
	
	 /*
	  * this method runs before every @Test annotated method 
	  */
	@BeforeEach
	 void  init() {
		obj=new Calculations();
		System.out.println("this runs before every test method");
	}
	
	@AfterEach
	void afterAll() {
		System.out.println(" cleaning up...");
	}

	@Test
	void test() {
		
		

		int expected=6;
		int reality=obj.add(3, 4); // this returns 6
		assertEquals(expected, reality);

	}
	
	
	@Test
	void testDivide() {
	
		/*
		 * Here we use assertThrows in order to expect a exception from the testing method
		 * we can give the testable method in the 2nd parameter which is of Executable type
		 * it takes a lambda expression as input
		 * you can pass the testable method in the lambda expression
		 * Here we don't need any try catch methods
		 */
		assertThrows(ArithmeticException.class, ()->obj.divide(1, 0), "divide method");
	}
	
	/*
	 * with the test annotation you no need to create instance of the class to run this method
	 * i.e you no need an instance and main method for these methods to hook up and run
	 * if you annotate a method with test and run the class with Junit it will directly run and
	 * shows up in the junit window
	 */
	@Test
	void testCalculateCircleArea() {
	
		double radius=45;
		assertEquals(Math.PI*Math.pow(radius,2),obj.calculateCircleArea(radius),
				"This method returns circle area");
		
	}
	
	@Test
	@DisplayName("this is a deliberately failed method")
	/*
	 * this annotation makes this test does not appear for execution
	 * it is displayed in the junit window but 
	 * it wont execute , it is kept aside 
	 */
	@Disabled
	void failMethod() {
		fail("its gonna fail");
	}
	
	@Test
	@DisplayName("multiplying method")
	void testMultiply() {
		
		boolean isServerUp=true;
		/*
		 * assumeTrue is a assumption method which takes a boolean value
		 * and it continues only if and if assumption value is true
		 * otherwise the method is stopped from execution
		 * this method is same as of @Disable annotation but here
		 * additionally we have programmatic control over disabling a method
		 * 
		 */
		assumeTrue(isServerUp);
		
		/*
		 * with assertAll you can test the method multiple times
		 * it takes a collection of executables(lambdas)
		 * even if one goes wrong the test case fails
		 * with this we don't need @Nested annotation and multiple 
		 * test methods
		 */
		assertAll(
				()->assertEquals(6, obj.multiply(2, 3)),
				()->assertEquals(0, obj.multiply(0, 3)),
				()->assertEquals(14, obj.multiply(2, 7))
				);
		
	}
	
	/*
	 * @RepeatedTest annotation is used to repeat the same test as many times as we want
	 * it takes an argument to how many times it have to repeat the test
	 * this annotation serves as an alternative to @Test
	 * It even provides a default string in the junit run window
	 */
	@RepeatedTest(5)
	void testSubtract() {
		
		/*
		 * Here instead of using a string message in assertEquals we are passing a 
		 * supplier which returns a string
		 * With this lambda expressions we can have programmatic control over 
		 * displaying the string we want
		 * 
		 */
		assertEquals(-2, obj.subtract(-4, -2), 
				()->{
			if(obj.subtract(-4, -2)!=-2) 
				return "should return the subtracted";
			return null;
		});
	}
	void aNormalMeth() {
		System.out.println("hello guys , I am not annotated with @Test");
	}

}
