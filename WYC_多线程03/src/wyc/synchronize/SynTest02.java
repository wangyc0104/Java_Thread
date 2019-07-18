package wyc.synchronize;

/**
 * 取钱（线程还是不安全） <br>
 * 锁错了仍会出现并发问题 <br>
 * 
 * @author 王以诚
 */
public class SynTest02 {
	public static void main(String[] args) {
		Account account = new Account(100, "结婚礼金");
		SafeDrawing you = new SafeDrawing(account, 80, "可悲的你");
		SafeDrawing wife = new SafeDrawing(account, 90, "happy的她");
		you.start();
		wife.start();
	}
}

class SafeDrawing extends Thread {
	Account account; // 取钱的账户
	int drawingMoney; // 取的钱数
	int pocketTotal; // 取的总数

	public SafeDrawing(Account account, int drawingMoney, String name) {
		super(name);
		this.account = account;
		this.drawingMoney = drawingMoney;
	}

	@Override
	public void run() {
		test();
	}

	// 目标不对，锁定失败，这里不是锁this，应该锁定account
	public synchronized void test() {
		if (account.money - drawingMoney < 0) {
			return;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		account.money -= drawingMoney;
		pocketTotal += drawingMoney;
		System.out.println(this.getName() + "-->账户余额为：" + account.money);
		System.out.println(this.getName() + "-->口袋的钱为：" + pocketTotal);
	}
}
