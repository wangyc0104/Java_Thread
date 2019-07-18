package wyc.thread;

/**
 * 创建线程方式二：<br>
 * 1、创建：实现Runnable，重写run <br>
 * 2、启动线程：创建实现线程对象，再启动start <br>
 * 推荐使用该方法，避免单继承的局限性，优先使用接口 <br>
 * 方便共享资源 <br>
 * 
 * @author 王以诚
 */
public class StartRun implements Runnable {
	// 线程入口点
	public void run() {
		for (int i = 0; i < 2000; i++)
			System.out.println("一边听歌");
	}

	public static void main(String[] args) {
		// for (int i = 0; i < 2000; i++)
		// System.out.println("一边写代码");
		// 创建实现类对象
		StartRun sr = new StartRun();
		// 创建代理线程对象
		Thread t = new Thread(sr);
		// 合三为一（如果一个对象只使用一次，可以这么用）
		new Thread(new StartRun()).start();
		t.start(); // start是开启一个新的线程，加入调度器，并行执行（不保证立即运行，由CPU调用）
		for (int i = 0; i < 2000; i++)
			System.out.println("一边写代码");
	}
}
