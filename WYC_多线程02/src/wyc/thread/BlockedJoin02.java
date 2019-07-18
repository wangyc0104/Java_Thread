package wyc.thread;

/**
 * join:合并线程，插队线程
 * 
 * @author 王以诚
 */
public class BlockedJoin02 {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("老婆叫老公买酱油的故事");
		new Thread(new Father()).start();
	}
}

class Father extends Thread {
	public void run() {
		System.out.println("老婆炒菜炒到一半，发现酱油没了");
		System.out.println("让老公去买酱油");
		Thread t = new Thread(new Son());
		t.start();
		try {
			t.join(); // father被阻塞
			System.out.println("老婆接过酱油，亲了老公一下");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Son extends Thread {

	@Override
	public void run() {
		System.out.println("老公马上出门去买酱油了");
		System.out.println("路边有个美女，上前去搭讪");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + "分钟过去了...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println("老婆迟迟等不到酱油，出去找老公了，结果发现老公在和孩子的班主任聊天，班主任说孩子成绩不错，就是有点顽皮。夫妻俩和班主任又聊了会，然后回家了");
			}
		}
		System.out.println("老公回过神来，赶紧去超市买酱油");
		System.out.println("带着酱油回家了");
	}
}