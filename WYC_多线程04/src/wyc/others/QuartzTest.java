package wyc.others;

import static org.quartz.DateBuilder.evenSecondDateAfterNow;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Quartz学习入门（上网查查文献看看深入的用法）
 * 
 * @author 王以诚
 */
public class QuartzTest {

	public void run() throws Exception {

		// 1.调度器来自调度器工厂，首先创建SchedulerFactory
		SchedulerFactory sf = new StdSchedulerFactory();
		// 2.从工厂中获取调度器
		Scheduler sched = sf.getScheduler();
		// 3.创建JobDetail
		JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1").build();
		// 时间
		Date runTime = evenSecondDateAfterNow();
		// 4.触发器
		// Trigger trigger = newTrigger().withIdentity("trigger1",
		// "group1").startAt(runTime).build();
		Trigger trigger = newTrigger().withIdentity("trigger1", "group1").startAt(runTime)
				.withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(3)).build();
		// 5.注册任务和触发条件（何时触发）
		sched.scheduleJob(job, trigger);
		// 6.启动
		sched.start();
		try {
			// 5 秒后停止
			Thread.sleep(500L * 1000L);
		} catch (Exception e) {
		}
		// 7.停止
		sched.shutdown(true);
	}

	public static void main(String[] args) throws Exception {
		QuartzTest example = new QuartzTest();
		example.run();
	}

}
