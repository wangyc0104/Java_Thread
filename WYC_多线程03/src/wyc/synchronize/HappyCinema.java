package wyc.synchronize;

/**
 * 快乐影院
 * 
 * @author 王以诚
 */
public class HappyCinema {
	public static void main(String[] args) {
		Cinema c = new Cinema(2, "Happy影院");
		new Thread(new Customer(c, 2), "Justin Timberlake").start();
		new Thread(new Customer(c, 1), "Taylor Swift").start();
	}
}

// 顾客
class Customer implements Runnable {
	Cinema cinema;
	int seats;

	public Customer(Cinema cinema, int seats) {
		this.cinema = cinema;
		this.seats = seats;
	}

	public void run() {
		synchronized (cinema) {
			boolean flag = cinema.bookTickets(seats);
			if (flag) {
				System.out.println("出票成功！" + Thread.currentThread().getName() + "位置为 --> " + seats);
			} else {
				System.out.println("出票失败！位置不够！");
			}
		}
	}
}

class Cinema {
	int available; // 可用的位置
	String name;// 名称

	public Cinema(int available, String name) {
		this.available = available;
		this.name = name;
	}

	// 购票
	public boolean bookTickets(int seats) {
		System.out.println("可用位置为" + available);
		if (seats > available) {
			return false;
		}
		available -= seats;
		return true;
	}
}