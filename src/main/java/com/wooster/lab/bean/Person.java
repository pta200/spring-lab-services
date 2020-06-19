package com.wooster.lab.bean;

public class Person {
	
	String name;
    String address;
    String cls;
 
    public Person(String name, String address, String cls) {
        super();
        this.name = name;
        this.address = address;
        this.cls = cls;
    }
 
    public String getName() {
        return name;
    }
 
    public String getAddress() {
        return address;
    }
 
    public String getCls() {
        return cls;
    }

}
