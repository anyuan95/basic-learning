package org.example.foo.basic;

import java.util.HashSet;
import java.util.Set;

public class A{
	public int hashCode(){return 0;}
	public static void main(String[] args){
	Set<Object> set = new HashSet<Object>();
	set.add(new A());
	set.add(new A());
	set.add("hello");
	set.add("hello");
	System.out.println(set.size());
}
}
