package wyc.thread;

/**
 * Lambda推导
 * 
 * @author 王以诚
 */
public class LambdaTest04 {

	public static void main(String[] args) {
		new Thread(() -> {
			for (int i = 0; i < 1000; i++) {
				System.out.println("线程1");
			}
		}).start();

		new Thread(() -> System.out.println("线程2")).start();
	}
}