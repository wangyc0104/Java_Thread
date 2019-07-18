package wyc.thread;

/**
 * yield 礼让线程，暂停线程，直接进入就绪状态，不是阻塞状态 <br>
 * 
 * @author 王以诚
 */
public class YieldDemo01 {
	public static void main(String[] args) {
		MyYield my = new MyYield();
		new Thread(my, "a").start();
		new Thread(my, "b").start();
	}
}

class MyYield implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " --> start");
		Thread.yield(); // 礼让线程，进行就绪模式
		System.out.println(Thread.currentThread().getName() + " --> end");
	}
}