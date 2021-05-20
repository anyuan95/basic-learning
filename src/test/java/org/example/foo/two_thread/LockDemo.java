package org.example.foo.two_thread;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo implements Runnable {
	
	// 定义公平锁,用来是线程1和线程2交替执行
	ReentrantLock lock = new ReentrantLock(true);

	@Override
	public void run() {
		for(int i = 1; i < 100; i++){
			//获取锁
			lock.lock();
			try{
				System.out.println(Thread.currentThread().getName()+" "+i);
			}finally{
				//释放锁
				lock.unlock();
			}
		}
	}

}