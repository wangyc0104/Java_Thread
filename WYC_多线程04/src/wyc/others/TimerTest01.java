package wyc.others;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 任务调度：Timer和TimerTask类 <br>
 * 
 * @author 王以诚
 */
public class TimerTest01 {
	public static void main(String[] args) {
		Timer timer = new Timer();
		// 执行安排（1秒后执行一次）
		timer.schedule(new MyTask(), 1000);
		// 执行安排（1秒后执行多次，每次间隔200毫秒）
		timer.schedule(new MyTask(), 1000, 200); // 效果同下
		// 执行安排（在指定的时间开始，运行多次，每次间隔200毫秒）
		Calendar cal = new GregorianCalendar(2099999, 12, 31, 21, 53, 54);
		timer.schedule(new MyTask(), cal.getTime(), 200);
	}
}

// 任务类（工作类）
class MyTask extends TimerTask {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("放空大脑，休息一会儿~");
		}
	}
}