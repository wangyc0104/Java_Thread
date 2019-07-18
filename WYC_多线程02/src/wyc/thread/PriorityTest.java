package wyc.thread;

/**
 * 线程优先级 <br>
 * 优先级只是一个概率，优先级高的运行概率高 <br>
 * MAX_PRIORITY 10 <br>
 * MIN_PRIORITY 1 <br>
 * NORM_PRIORITY 5 <br>
 * 
 * @author 王以诚
 */
public class PriorityTest {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getPriority());
		MyPriority mp = new MyPriority();
		Thread t1 = new Thread(mp, "A");
		Thread t2 = new Thread(mp, "B");
		Thread t3 = new Thread(mp, "C");
		Thread t4 = new Thread(mp, "D");
		Thread t5 = new Thread(mp, "E");
		Thread t6 = new Thread(mp, "F");
		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MAX_PRIORITY);
		t3.setPriority(Thread.MAX_PRIORITY);
		t4.setPriority(Thread.MIN_PRIORITY);
		t5.setPriority(Thread.MIN_PRIORITY);
		t6.setPriority(Thread.MIN_PRIORITY);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}
}

class MyPriority implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " --> " + Thread.currentThread().getPriority());
		Thread.yield();
	}
}