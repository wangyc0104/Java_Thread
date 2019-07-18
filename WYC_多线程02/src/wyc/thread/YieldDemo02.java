package wyc.thread;

/**
 * yield 礼让线程，暂停线程，直接进入就绪状态，不是阻塞状态 <br>
 * 
 * @author 王以诚
 */
public class YieldDemo02 {
	public static void main(String[] args) throws InterruptedException {
		new Thread(() -> {
			for (int i = 0; i < 100; i++) {
				System.out.println("lambda" + i);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		for (int i = 0; i < 100; i++) {
			if (i % 2 == 0) {
				Thread.yield(); // main线程礼让
			}
			System.out.println("main..." + i);
			Thread.sleep(10);
		}
	}
}