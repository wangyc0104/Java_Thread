package wyc.others;

/**
 * ThreadLocal：分析上下文环境 <br>
 * 1.构造器：哪里调用，就属于哪里 <br>
 * 2.run方法：本线程自己的 <br>
 * 
 * @author 王以诚
 */
public class ThreadLocalTest03 {

	private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
		threadLocal.set(10);
		new Thread(new MyRun()).start();
	}

	public static class MyRun implements Runnable {
		public MyRun() { // 此处属于main线程
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
		}

		public void run() { // 此处属于新启动的线程
			System.out.println(Thread.currentThread().getName() + "还剩下-->" + threadLocal.get());
		}
	}

}
