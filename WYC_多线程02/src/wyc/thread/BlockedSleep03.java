package wyc.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 倒计时 <br>
 * 
 * @author 王以诚
 */
public class BlockedSleep03 {
	public static void main(String[] args) throws InterruptedException {
		// 倒计时
		Date endTime = new Date(System.currentTimeMillis() + 1000 * 10);
		long end = endTime.getTime();
		while (true) {
			System.out.println(new SimpleDateFormat("mm:ss").format(endTime));
			Thread.sleep(1000);
			endTime = new Date(endTime.getTime() - 1000);
			if (end - 10000 > endTime.getTime()) {
				break;
			}
		}
	}

	public static void test(String[] args) throws InterruptedException {
		// 倒计时10个数
		int num = 10;
		while (true) {
			Thread.sleep(1000); // main方法可以抛出异常，run方法不可以
			System.out.println(num--);
		}
	}

}