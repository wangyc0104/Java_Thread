package wyc.synchronize;

/**
 * 线程不安全 <br>
 * 数据有重复和负数 <br>
 * 
 * @author 王以诚
 */
public class UnsafeTest01 {
	public static void main(String[] args) {
		// 一份资源，多个代理
		UnsafeWeb12306 web = new UnsafeWeb12306();
		System.out.println(Thread.currentThread().getName());
		new Thread(web, "码畜").start();
		new Thread(web, "码农").start();
		new Thread(web, "码磺").start();
	}
}

class UnsafeWeb12306 implements Runnable {
	private int ticketNums = 10;
	private boolean flag = true;

	public void run() {
		while (flag) {
			test();
		}
	}

	public void test() {
		if (ticketNums < 0) {
			flag = false;
			return;
		}
		// 模拟网络延时
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// run方法不能throw异常
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " --> " + ticketNums--);
	}

}