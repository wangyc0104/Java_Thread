package wyc.thread;

/**
 * 静态代理 <br>
 * 接口： <br>
 * -真实角色 <br>
 * -代理角色 <br>
 * 
 * @author 王以诚
 */
public class StaticProxy {
	public static void main(String[] args) {
		new WeddingCompany(new You()).happyMarry();
	}
}

interface Marry {
	void happyMarry();
}

class You implements Marry {
	public void happyMarry() {
		System.out.println("结婚");
	}
}

// 代理你结婚
class WeddingCompany implements Marry {
	// 真实角色
	private Marry target;

	public WeddingCompany(Marry target) {
		this.target = target;
	}

	public void happyMarry() {
		ready();
		this.target.happyMarry();
		after();

	}

	private void ready() {
		System.out.println("收代理预付款");
	}

	private void after() {
		System.out.println("收代理尾款");
	}
}