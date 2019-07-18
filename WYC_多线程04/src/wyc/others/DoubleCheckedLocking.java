package wyc.others;

/**
 * DCL单例模式：懒汉式套路基础上加入并发控制，保证在多线程环境下，对外只存在一个对象 <br>
 * 1.提供私有的静态属性 --> 存储对象的地址 <br>
 * 2.构造器私有化 --> ，外部不能new对象 <br>
 * 3.提供公共的静态方法 --> 获取属性 <br>
 * 
 * @author 王以诚
 */
public class DoubleCheckedLocking {
	// 1.提供私有的静态属性 --> 存储对象的地址
	// 如果没有volatile，其它线程可能访问一个没有初始化的对象
	private volatile static DoubleCheckedLocking instance;

	// 2.构造器私有化 --> 外部不能new对象
	private DoubleCheckedLocking() {
	}

	// 产生对象（同步）
	public static DoubleCheckedLocking getInstance(long time) {
		// 再次检测
		if (null != instance) { // 避免不必要的同步，已经存在对象了（双重检测）
			return instance;
		}
		synchronized (DoubleCheckedLocking.class) { // 锁定对象的class类，防止多new对象
			if (instance == null) {
				// 模拟网络延迟
				try {
					Thread.sleep(time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				instance = new DoubleCheckedLocking(); // 此处可能发生指令同排
				// 1.开辟空间
				// 2.初始化对象信息
				// 3.返回对象的地址给引用（此步可能比第2步优先执行，因此要在instance对象声明前加上volatile）
			}
		}
		return instance;
	}

	// 产生对象（不同步）
	public static DoubleCheckedLocking getInstance1(long time) {
		// 再次检测
		if (null != instance) { // 避免不必要的同步，已经存在对象了（双重检测）
			return instance;
		}
		// 如果不同步，则可能产生两个不同的对象
		if (instance == null) {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			instance = new DoubleCheckedLocking(); // 此处可能发生指令同排
			// 1.开辟空间
			// 2.初始化对象信息
			// 3.返回对象的地址给引用（此步可能比第2步优先执行，因此要在instance对象声明前加上volatile）
		}
		return instance;
	}

	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			System.out.println(DoubleCheckedLocking.getInstance(150));
		});
		t.start();
		System.out.println(DoubleCheckedLocking.getInstance(100));
	}
}
