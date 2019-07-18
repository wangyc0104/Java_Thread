package wyc.synchronize;

/**
 * 取钱（线程安全） <br>
 * 
 * @author 王以诚
 */
public class SynBlockTest01 {
	public static void main(String[] args) {
		Account account = new Account(100, "结婚礼金");
		SynDrawing you = new SynDrawing(account, 100, "你");
		SynDrawing wife = new SynDrawing(account, 90, "她");
		you.start();
		wife.start();
	}
}

class SynDrawing extends Thread {
	Account account; // 取钱的账户
	int drawingMoney; // 取的钱数
	int pocketTotal; // 取的总数

	public SynDrawing(Account account, int drawingMoney, String name) {
		super(name);
		this.account = account;
		this.drawingMoney = drawingMoney;
	}

	@Override
	public void run() {
		test();
	}

	// 目标锁定account
	public void test() {
		// 提高性能
		if (account.money <= 0) {
			System.out.println(Thread.currentThread().getName() + "发现钱不够，气哭了");
			return;
		}
		// 同步块
		synchronized (account) { // account对象作为监视器
			if (account.money - drawingMoney < 0) {
				System.out.println(Thread.currentThread().getName() + "发现钱不够，气哭了");
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
}
