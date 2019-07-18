package wyc.cooperation;

/**
 * 协作模型：生产者消费者实现方式二：信号灯法 <br>
 * 借助标志位
 * 
 * @author 王以诚
 */
public class CoTest02 {
	public static void main(String[] args) {
		Tv tv = new Tv();
		new Player(tv).start();
		new Watcher(tv).start();
	}
}

// 生产者：演员
class Player extends Thread {
	Tv tv;

	public Player(Tv tv) {
		this.tv = tv;
	}

	public void run() {
		for (int i = 0; i < 20; i++) {
			if (i % 2 == 0) {
				this.tv.play("奇葩说");
			} else {
				this.tv.play("太污了，喝瓶立白洗洗嘴");
			}
		}
	}
}

// 消费者：观众
class Watcher extends Thread {
	Tv tv;

	public Watcher(Tv tv) {
		this.tv = tv;
	}

	public void run() {
		for (int i = 0; i < 20; i++) {
			tv.watch();
		}
	}
}

// 同一个资源：电视
class Tv {
	// 表演内容
	String voice;
	// 加入信号灯
	boolean flag = true;

	// 表演
	public synchronized void play(String voice) {
		// 演员等待
		if (!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 演员表演
		System.out.println("表演了：" + voice);
		this.voice = voice;
		// 唤醒
		this.notifyAll();
		this.flag = !this.flag;
	}

	// 观看
	public synchronized void watch() {
		// 观众等待
		if (flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 观看电视
		System.out.println("听到了：" + voice);
		// 唤醒演员
		this.notifyAll();
		this.flag = !this.flag;
	}
}