package wyc.synchronize;

/**
 * 取钱（线程不安全） <br>
 * 
 * @author 王以诚
 */
public class UnsafeTest02 {
	public static void main(String[] args) {
		Account account = new Account(100, "结婚礼金");
		Drawing you = new Drawing(account, 80, "王以诚");
		Drawing wife = new Drawing(account, 90, "迟迟不出现的对象");
		you.start();
		wife.start();
	}
}

class Drawing extends Thread {
	Account account; // 取钱的账户
	int drawingMoney; // 取的钱数
	int pocketTotal; // 取的总数

	public Drawing(Account account, int drawingMoney, String name) {
		super(name);
		this.account = account;
		this.drawingMoney = drawingMoney;
	}

	@Override
	public void run() {
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