package wyc.thread;

/**
 * 使用Runnable共享资源 <br>
 * 会出现并发问题<br>
 * 
 * @author 王以诚
 */

public class Web12306 implements Runnable {

	private int ticketNums = 99;

	public void run() {
		while (true) {
			if (ticketNums < 0) {
				break;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// run方法不能throw异常
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "--" + ticketNums--);
		}
	}

	public static void main(String[] args) {
		// 一份资源，多个代理
		Web12306 web = new Web12306();
		new Thread(web, "码畜").start();
		new Thread(web, "码农").start();
		new Thread(web, "码奴").start();
	}

}
