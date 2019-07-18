package wyc.thread;

/**
 * Sleep模拟网络延时，放大了发生问题的可能性 <br>
 * 
 * @author 王以诚
 */
public class BlockedSleep01 {
	public static void main(String[] args) {
		// 一份资源，多个代理
		Web12306 web = new Web12306();
		System.out.println(Thread.currentThread().getName());
		new Thread(web, "码畜").start();
		new Thread(web, "码农").start();
		new Thread(web, "码磺").start();
	}
}

class Web12306 implements Runnable {

	private int ticketNums = 20;

	public void run() {
		while (true) {
			if (ticketNums < 0) {
				break;
			}
			// 模拟网络延时
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// run方法不能throw异常
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "--" + ticketNums--);
		}
	}

}