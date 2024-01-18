package com.mahi.junit.custom;

import org.junit.jupiter.api.Test;

public class Calculations {
	
	public static int add(int a , int b) {
		System.out.println("the addition of "+a+" and "+b+" is "+a+b);
		return a+b;
	
	}
	
	public int divide(int a,int b) {
		return a/b;
	}
	
	public int multiply(int a , int b) {
		return a*b;
	}
	
	public double calculateCircleArea(double radius) {
		return Math.PI*Math.pow(radius, 2);
	}
	
	public int subtract(int a,int b) {
		return a-b;
	}
//	@Test
//	public void say() {
//		System.out.println("hello");
//	}

	public static void main(String[] args) {
		

	}

}
