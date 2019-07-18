package wyc.synchronize;

/**
 * 快乐购票
 * 
 * @author 王以诚
 */
public class Happy12306 {
	public static void main(String[] args) {
		Web12306 c = new Web12306(2, "Happy影院");
		new Passenger(c, "Michael Jackson", 2).start();
		new Passenger(c, "大诚", 1).start();
	}
}

// 乘客
class Passenger extends Thread {
	int seats;

	public Passenger(Runnable target, String name, int seats) {
		super(target, name);
		this.seats = seats;
	}

}

// 火车票网
class Web12306 implements Runnable {
	int available; // 可用的位置
	String name;// 名称

	public Web12306(int available, String name) {
		this.available = available;
		this.name = name;
	}

	// 购票
	public synchronized boolean bookTickets(int seats) {
		System.out.println("可用位置为" + available);
		if (seats > available) {
			return false;
		}
		available -= seats;
		return true;
	}

	public void run() {
		Passenger p = (Passenger) Thread.currentThread();
		boolean flag = this.bookTickets(p.seats);
		if (flag) {
			System.out.println("出票成功！" + Thread.currentThread().getName() + "位置为 --> " + p.seats);
		} else {
			System.out.println("出票失败！位置不够！");
		}
	}
}