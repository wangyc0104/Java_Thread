package wyc.thread;

import java.lang.Thread.State;

/**
 * 观察线程的状态
 * 
 * @author 王以诚
 */
public class AllState {
	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(200);
					System.out.println("...");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		// 观察状态
		State state = t.getState();
		System.out.println(state); // NEW

		t.start();
		state = t.getState();
		System.out.println(state); // RUNNABLE

//		while (state != Thread.State.TERMINATED) {
//			try {
//				Thread.sleep(300);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			state = t.getState();
//			System.out.println(state); // TIME_WAITING
//		}

//		while (true) {
//			// 活动的线程数
//			int num = Thread.activeCount();
//			System.out.println(num);
//			if (num == 1)
//				break;
//			try {
//				Thread.sleep(300);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			state = t.getState();
//			System.out.println(state); // TIME_WAITING
//		}
		
		
	}
}
