package wyc.others;

/**
 * InheritableThreadLocal：继承上下文环境的数据，拷贝一份数据给子线程 <br>
 * 
 * @author 王以诚
 */
public class ThreadLocalTest04 {

	private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);

	public static void main(String[] args) {
		threadLocal.set(2);
		System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
		// 此线程由main线程开辟，会拷贝一份数据给子线程，而不是共享变量
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
			threadLocal.set(200);
			System.out.println(Thread.currentThread().getName() + "-->" + threadLocal.get());
		}).start();
	}
}
