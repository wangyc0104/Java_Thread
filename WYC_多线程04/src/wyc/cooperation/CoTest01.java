package wyc.cooperation;

/**
 * 协作模型：生产者消费者实现方式一：管程法
 * 
 * @author 王以诚
 */
public class CoTest01 {
	public static void main(String[] args) {
		SynContainer container = new SynContainer();
		new Productor(container).start();
		new Consumer(container).start();
	}
}

// 生产者
class Productor extends Thread {
	SynContainer container;

	public Productor(SynContainer container) {
		this.container = container;
	}

	public void run() {
		// 生产
		for (int i = 0; i < 100; i++) {
			System.out.println("生产第" + i + "个馒头");
			container.push(new Steamedbun(i));
		}
	}
}

// 消费者
class Consumer extends Thread {
	SynContainer container;

	public Consumer(SynContainer container) {
		this.container = container;
	}

	public void run() {
		// 消费
		for (int i = 0; i < 100; i++) {
			System.out.println("消费第" + container.pop().id + "个馒头");
		}
	}
}

// 缓冲区
class SynContainer {
	Steamedbun[] buns = new Steamedbun[10]; // 容器
	int count = 0; // 计数器

	// 存数据（生产）
	public synchronized void push(Steamedbun bun) {
		// 何时能生产？
		if (count >= buns.length) {
			try {
				this.wait(); // 此时线程阻塞，直到消费者通知生产
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 容器存在空间，即可以生产
		buns[count] = bun;
		count++;
		notifyAll(); // 存在数据了，通知消费者
	}

	// 取数据（消费）
	public synchronized Steamedbun pop() {
		// 何时消费？
		if (count <= 0) {// 没有数据，不能消费，只能等待
			try {
				this.wait(); // 此时线程阻塞，直到生产者通知消费
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else { // 存在数据，可以消费
			count--;
			Steamedbun bun = buns[count];
			this.notifyAll(); // 产生空间了，通知生产者
			return bun;
		}
		return null;
	}
}

//馒头
class Steamedbun {
	int id;

	public Steamedbun(int id) {
		this.id = id;
	}
}