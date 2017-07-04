package com.cmall.temp;

public class TestJava {

	Person per = null;
	public static void main(String[] args) {
		TestJava tj = new TestJava();
		tj.test();
	}
	
	void test(){
		per = new Person("li", 20);
//		System.out.println(per);
		per = new Person("zhang", 22);
		System.out.println(per);

	}
}


class Person{
	
	private String name;
	private int age;
	
	public Person(String name,int age) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.age = age;
		System.out.println("a new person:"+ name + ",age:"+age);
	}
}
