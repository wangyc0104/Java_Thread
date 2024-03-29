package wyc.synchronize;

import java.util.ArrayList;
import java.util.List;

/**
 * 快乐影院
 * 
 * @author 王以诚
 */
public class HappyCinema2 {
	public static void main(String[] args) {
		// 可用位置
		List<Integer> available = new ArrayList<Integer>();
		available.add(1);
		available.add(2);
		available.add(3);
		available.add(6);
		available.add(7);
		// 顾客需要的位置
		List<Integer> seats1 = new ArrayList<Integer>();
		seats1.add(1);
		seats1.add(2);
		List<Integer> seats2 = new ArrayList<Integer>();
		seats2.add(3);
		seats2.add(6);
		seats2.add(7);

		SxtCinema c = new SxtCinema(available, "Happy Sxt");
		new Thread(new HappyCustomer(c, seats1), "隔壁老王").start();
		new Thread(new HappyCustomer(c, seats2), "楼上的包租婆").start();
		new Thread().start();
	}
}

// 顾客
class HappyCustomer implements Runnable {
	SxtCinema cinema;
	List<Integer> seats;

	public HappyCustomer(SxtCinema cinema, List<Integer> seats) {
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

class SxtCinema {
	List<Integer> available; // 可用的位置
	String name;// 名称

	public SxtCinema(List<Integer> available, String name) {
		this.available = available;
		this.name = name;
	}

	// 购票
	public boolean bookTickets(List<Integer> seats) {
		System.out.println("欢迎光临" + this.name + "！可用位置为" + available);
		List<Integer> copy = new ArrayList<Integer>(); // 先复制出可用的票，用该变量进行操作，防止提前改动available
		copy.addAll(available);
		// 相减
		copy.removeAll(seats);
		if (available.size() - copy.size() != seats.size()) { // 购票失败
			return false;
		}
		// 成功
		available = copy;
		return true;
	}
}