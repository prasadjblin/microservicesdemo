package com.venkat.microservice;

import java.util.concurrent.Callable;

public class Test {

	public int doFactorial(int n) {
		if(n==0) {
			return 1;
		} else {
			return doFactorial(n-1)*n;
		}
	}
	
	public void inverseStar() {
		for(int i=3; i > 0; i--) {
			for(int j=0; j < i; j++) 
					System.out.print("*");
			System.out.println();
		}
	} 
	
	interface HelloWorld {
		String hello(String name);
		//String hello2(String ip, String addr);
	}
	public void funInt() {
		HelloWorld helloWorld = (String name) -> {return "hello " + name; };
		System.out.println(helloWorld.hello("ve"));
	}
	public String wildAnimal = "lion";
	public void lambdaExp() {
		String domesticAnimal = "dog";
		new Thread(() -> {
			System.out.println("class level " + this.wildAnimal);
			System.out.println("method level " + domesticAnimal);
		}).start();
		//new Test().lambdaExp(); - to start in main method
	}
	public void callableEx() throws Exception {
		Callable[] animals = new Callable[] {()->"lion", ()->"dog"};
		System.out.println(animals[1].call());
	}
	
	interface Square {
		int calc(int x);
	}
	
	public static void main(String[] args) throws Exception {
		//new Test().callableEx();
		Square s = (x) ->  x*x;
		System.out.println(s.calc(5));
	}
	
}
