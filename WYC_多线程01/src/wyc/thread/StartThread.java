package wyc.thread;

/**
 * 创建线程方式一： <br> 
 * 1、创建：继承Thread，重写run <br>
 * 2、启动线程 <br>
 * 
 * @author 王以诚
 */
public class StartThread extends Thread {
	// 线程入口点
	public void run() {
		for (int i = 0; i < 2000; i++)
			System.out.println("一边听歌");
	}

	public static void main(String[] args) {
		// for (int i = 0; i < 2000; i++)
		// System.out.println("一边写代码");
		// 启动线程（要看创建线程和启动线程的时机是否正确，是在运行其它代码之前还是之后）
		StartThread st = new StartThread();
		st.run(); // run方法必须执行完才能继续下一步骤
		st.start(); // start是开启一个新的线程，加入调度器，并行执行（不保证立即运行，由CPU调用）
		for (int i = 0; i < 2000; i++)
			System.out.println("一边写代码");
	}
}
