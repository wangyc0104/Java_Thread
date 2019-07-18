package wyc.others;

/**
 * volatile用于保证数据 的同步，也就是可见性 <br>
 * volatile和synchronized相比只保证数据的同步，不保证原子性 <br>
 * 
 * @author 王以诚
 */
public class VolatileTest {
	private volatile static int num = 0;

	public static void main(String[] args) throws InterruptedException {
		new Thread(() -> {
			while (num == 0) { // 此处不要编写代码

			}
		}).start();
		Thread.sleep(1000);
		num = 1; // 该步不保证原子性，不保证数据同步
	}
}
