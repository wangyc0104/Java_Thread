package wyc.thread;

/**
 * 其它方法 <br>
 * isAlive() 线程是否活着 <br>
 * currentThread() 当前线程 <br>
 * setName、getName：代理名称 <br>
 * 程序猿的劳斯莱斯 <br>
 * 
 * @author 王以诚
 */
public class InfoTest {
	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.currentThread().isAlive());
		// 设置名称：真实角色+代理角色
		MyInfo info = new MyInfo("战斗机");
		Thread t = new Thread(info);
		t.setName("公鸡");
		t.start();
		Thread.sleep(1000);
		System.out.println(t.isAlive());
	}
}

class MyInfo implements Runnable {
	private String name;

	public MyInfo(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " --> " + name);
	}
}
