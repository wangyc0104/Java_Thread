package wyc.synchronize;

import java.util.ArrayList;
import java.util.List;

/**
 * List操作数据丢失（线程不安全） <br>
 * 一般对数据存在又改又读的时候，就要控制线程安全。 <br>
 * 并发三要素：[同一个数据]被[多个对象][同时操作] <br>
 * @author 王以诚
 */
public class UnsafeTest03 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10000; i++) {
			new Thread(() -> {
				list.add(Thread.currentThread().getName());
			}).start();
		}
		System.out.println(list.size());
	}
}
