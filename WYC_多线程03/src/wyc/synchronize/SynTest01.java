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
public class SynTest01 {
	public static void main(String[] args) {
		// 一份资源，多个代理
		SafeWeb12306 web = new SafeWeb12306();
		System.out.println(Thread.currentThread().getName());
		new Thread(web, "码畜").start();
		new Thread(web, "码农").start();
		new Thread(web, "码磺").start();
	}
}

class SafeWeb12306 implements Runnable {
	private int ticketNums = 1000;
	private boolean flag = true;

	public void run() {
		while (flag) {
			test();
			// 模拟网络延时
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// run方法不能throw异常
				e.printStackTrace();
			}
		}
	}

	public synchronized void test() { // 锁的是方法体内涉及到的对象而不是该方法（此处的对象是this）
		if (ticketNums <= 0) {
			flag = false;
			return;
		}
		System.out.println(Thread.currentThread().getName() + " --> " + ticketNums--);
	}

}