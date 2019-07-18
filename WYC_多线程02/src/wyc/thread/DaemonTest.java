package wyc.thread;

/**
 * 守护线程：是为用户线程服务的；JVM停止不用等待守护线程执行完毕 <br>
 * 默认：用户线程（JVM等待用户线程执行完毕后才会停止 <br>
 * 
 * @author 王以诚
 */
public class DaemonTest {
	public static void main(String[] args) {
		God god = new God();
		You you = new You();
		Thread t = new Thread(god);
		
		t.setDaemon(true); // 将用户线程调整为守护线程
		t.start(); // 设置完守护线程后，启动该线程
		new Thread(you).start();
	}
}

class You implements Runnable {
	public void run() {
		for (int i = 1; i <= 365 * 100; i++) {
			System.out.println("happy life..." + i);
		}
		System.out.println("oooooo...");
	}
}

class God implements Runnable {
	public void run() {
		for (int i = 1; i <= 365 * 100000; i++) {
			System.out.println("bless you..." + i);
		}
		System.out.println("oooooo...");
	}
}