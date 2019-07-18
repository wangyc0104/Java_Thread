package wyc.thread;

/**
 * Lambda表达式简化线程的使用 <br>
 * -一般用Lambda的线程只用一次 <br>
 * 
 * @author 王以诚
 */
public class LambdaThread {
	// 静态内部类（只运行一次，编译后自动运行一次）
	static class Test implements Runnable {
		public void run() {
			for (int i = 0; i < 2000; i++)
				System.out.println("一边静态内部类");
		}
	}

	public static void main(String[] args) {

		new Thread(new Test()).start();

		// 局部内部类
		class Test2 implements Runnable {
			@Override
			public void run() {
				for (int i = 0; i < 2000; i++)
					System.out.println("一边局部内部类");
			}
		}
		new Thread(new Test2()).start();

		// 匿名内部类，必须借助接口或者父类
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 2000; i++)
					System.out.println("一边匿名内部类");
			}
		}).start();

		// JDK8 简化 lambld表达式 （用于简单线程类）
		new Thread(() -> {
			for (int i = 0; i < 2000; i++)
				System.out.println("一边lambda表达式");
		}).start();
	}
}
