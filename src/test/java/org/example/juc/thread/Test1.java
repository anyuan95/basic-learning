package org.example.juc.thread;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Test1 {
	public static void main(String[] args) throws Exception{
		System.out.println(LocalDateTime.now());
		System.out.println("0: " + Thread.currentThread().getName());
		Thread a = new Thread(() -> {
			System.out.println("a: " + Thread.currentThread().getName());
		});
		Thread b = new Thread(() -> {
			System.out.println("b: " + Thread.currentThread().getName());
		});
		a.start();
		b.start();
		a.sleep(5000);		//问题
		System.out.println(LocalDateTime.now());
		try {

		}catch (Exception e) {

		}
		List<String> testContainer = new ArrayList<String>();
//		final int size = new Test().getSize(testContainer);
		final int size2 = new Test1().getSize2(testContainer);
	}

	public int getSize(Collection<Object> container){return container.size();}
	public int getSize2(Collection<?> container){return container.size();}


}