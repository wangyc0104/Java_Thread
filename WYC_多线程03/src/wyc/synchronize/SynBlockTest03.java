package wyc.synchronize;

/**
 * 线程安全：在并发时保证数据的准确性、效率尽可能高 <br>
 * synchronized <br>
 * 1.同步方法 <br>
 * 2.同步块 <br>
 * 数据有重复和负数 <br>
 * 
 * @author 王以诚
 */
public class SynBlockTest03 {
	public static void main(String[] args) {
		// 一份资源，多个代理
		SynWeb12306 web = new SynWeb12306();
		System.out.println(Thread.currentThread().getName());
		new Thread(web, "码畜").start();
		new Thread(web, "码农").start();
		new Thread(web, "码磺").start();
	}
}

class SynWeb12306 implements Runnable {
	private int ticketNums = 10;
	private boolean flag = true;

	public void run() {
		while (flag) {
			// test1();
			// 模拟网络延时
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// run方法不能throw异常
				e.printStackTrace();
			}
			test5();
		}
	}

	// 同步块（粒度适中，范围合理，性能合适，线程安全，尽可能锁定合理的范围，不是指代码，是指数据的完整性）
	public void test5() { // 考虑的是没有票的情况
		// 双重检测（考虑临界值的问题）
		if (ticketNums <= 0) {
			flag = false;
			return;
		}
		synchronized (this) {
			if (ticketNums <= 0) { // 重要：考虑最后的一张票拿掉后，当它进来了，发现没票了，就退出去了
				flag = false;
				return;
			}
			// 模拟网络延时
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// run方法不能throw异常
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " --> " + ticketNums--);
		}
	}

	// 同步块（粒度更小，但范围太小，锁不住，线程不安全）
	public void test4() {
		synchronized (this) {
			if (ticketNums <= 0) {
				flag = false;
				return;
			}
		}
		// 模拟网络延时
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// run方法不能throw异常
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " --> " + ticketNums--);
	}

	// 线程不安全（ticketNums的对象地址一直在变，锁不住）
	public void test3() {
		synchronized ((Integer) ticketNums) {
			if (ticketNums <= 0) {
				flag = false;
				return;
			}
			// 模拟网络延时
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// run方法不能throw异常
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " --> " + ticketNums--);
		}
	}

	// synchronized同步块（范围太大，性能太低）
	public void test2() { // 锁的是方法体内涉及到的对象而不是该方法（此处的对象是this）
		synchronized (this) {
			if (ticketNums <= 0) {
				flag = false;
				return;
			}
			// 模拟网络延时
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// run方法不能throw异常
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " --> " + ticketNums--);
		}
	}

	// synchronized方法同步
	public synchronized void test1() { // 锁的是方法体内涉及到的对象而不是该方法（此处的对象是this）
		if (ticketNums <= 0) {
			flag = false;
			return;
		}
		// 模拟网络延时
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// run方法不能throw异常
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " --> " + ticketNums--);
	}

}