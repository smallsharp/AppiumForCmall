package com.cmall.temp;

public class TestJava {

	Person per = null;
	public static void main(String[] args) {
		TestJava tj = new TestJava();
		tj.test();
	}
	
	void test(){
//		per = new Person();
		Car car = new Car();
		car.tell();
	}
}


class Person{
	
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public Person() {
		System.out.println("a new person name is "+ name + ",age:"+age);
	}

	public Person(String name,int age) {
		this.name = name;
		this.age = age;
		System.out.println("a new person name is"+ name + ",age:"+age);
	}
}

class Car {
	
	Person per = new Person();
	public void tell() {
		System.out.println(per.getName());
		System.out.println(per.getAge());
	}
	
}
