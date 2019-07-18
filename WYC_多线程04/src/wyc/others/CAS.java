package wyc.others;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS：CompareAndSet <br>
 * 注：凡是看到Atomic的类，都可能使用到了CAS操作
 * @author 王以诚
 */
public class CAS {
	// 库存
	private static AtomicInteger stock = new AtomicInteger(5);

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				// 模拟网络延迟
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Integer left = stock.decrementAndGet();
				if (left < 1) {
					System.out.println("抢完了");
					return;
				}
				System.out.println(Thread.currentThread().getName() + "抢了一个商品");
				System.out.println("还剩" + left + "件商品");
			}).start();

		}
	}
}
