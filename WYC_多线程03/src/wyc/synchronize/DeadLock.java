package wyc.synchronize;

/**
 * 死锁：过多的同步可能造成相互不释放资源从而相互等待<br>
 * 一般发生于同步中持有多个对象的锁 <br>
 * 解决方法：尽量使每个synchronized块里只有一个对象被锁，不要在同一个同步块中占用多个锁 <br>
 * 
 * @author 王以诚
 */
public class DeadLock {
	public static void main(String[] args) {
		Makeup g1 = new Makeup(0, "蔡文姬");
		Makeup g2 = new Makeup(1, "貂蝉");
		g1.start();
		g2.start();
	}
}

class Lipstick {

}

class Mirror extends Thread {

}

class Makeup extends Thread {

	static Lipstick lipstick = new Lipstick();
	static Mirror mirror = new Mirror();

	// 选择
	int choice;
	String girl;

	public Makeup(int choice, String girl) {
		this.choice = choice;
		this.girl = girl;
	}

	@Override
	public void run() {
		makeup();
	}

	// 相互持有对方的对象锁 --> 可能造成死锁
	private void makeup() {
		if (choice == 0) {
			// 先拿起口红
			synchronized (lipstick) {
				System.out.println(this.girl + "涂口红");
				// 1秒后想拥有镜子的锁
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				/*
					synchronized (mirror) {// 获得镜子的锁
						System.out.println(this.girl + "照镜子");
					}
				*/
			}
			synchronized (mirror) {// 获得镜子的锁
				System.out.println(this.girl + "照镜子");
			}
		} else {
			// 先拿起镜子
			synchronized (mirror) {
				System.out.println(this.girl + "照镜子");
				// 2秒后想拥有口红的锁
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				/*
					synchronized (lipstick) {// 获得口红的锁
						System.out.println(this.girl + "涂口红");
					}
				*/
			}
			synchronized (lipstick) {// 获得口红的锁
				System.out.println(this.girl + "涂口红");
			}
		}

	}

}
