package org.example.jvm.lock;
 
public class TestLock {
 
	public void onlyMe(Object f) {
		synchronized (f) {
			doSomething();
		}
	}
 
	private void doSomething() {
		System.out.println("执行方法");
	}
}

//	 0 aload_1			f入操作数栈
//	 1 dup				复制一份f，放到操作数栈顶
//	 2 astore_2			从操作数栈顶拿出f，放到局部变量表Slot2
//	 3 monitorenter		取出栈顶的f，sync锁开始
//	 4 aload_0			this入操作数栈
//	 5 invokespecial #2 <org/example/jvm/lock/TestLock.doSomething>	 拿出栈顶的this，调用this.doSth()
//	8 aload_2			用于锁的f对象入栈
//	9 monitorexit		取出栈顶的f，sync锁结束
//	10 goto 18 (+8)		跳到偏移量18，方法正常结束
//	13 astore_3			？LVT中的3是什么
//	14 aload_2
//	15 monitorexit
//	16 aload_3
//	17 athrow
//	18 return

// 问题:
//  1.局部变量表中只有Slot0:this、Slot1:f，那么Slot2存的f是不是专用于锁的对象？还有Slot3位置存的是做什么的