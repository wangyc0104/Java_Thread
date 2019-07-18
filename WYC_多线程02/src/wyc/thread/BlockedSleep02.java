package wyc.thread;

/**
 * Sleep模拟网络延时，放大了发生问题的可能性 <br>
 * 
 * @author 王以诚
 */
public class BlockedSleep02 {
	public static void main(String[] args) {
		Racer racer = new Racer();
		new Thread(racer, "tortoise").start();
		new Thread(racer, "rabbit").start();
	}
}

class Racer implements Runnable {

	private static String winner; // 胜利者

	@Override
	public void run() {
		for (int steps = 1; steps <= 100; steps++) {
			// 模拟休息
			if ((Thread.currentThread().getName().equals("rabbit")) && ((steps % 10) == 0)) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "-->" + steps);
			// 比赛是否结束
			boolean flag = gameOver(steps);
			if (flag) {
				break;
			}
		}
	}

	private boolean gameOver(int steps) {
		if (winner != null) {
			return true; // 存在胜利者
		} else {
			if (steps == 100) {
				winner = Thread.currentThread().getName();
				System.out.println("winner ==> " + winner);
				return true;
			}
		}
		return false;

	}

}
