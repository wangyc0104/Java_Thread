package wyc.others;

/**
 * 可重入锁：锁可以延续使用 <br>
 * 锁实际上就是一个自定义的类 <br>
 * 可重入锁内有一个计数器 <br>
 * 
 * @author 王以诚
 */
public class LockTest03 {
	ReLock lock = new ReLock();

	public void a() throws InterruptedException {
		lock.lock();
		System.out.println(lock.getHoldCount());
		doSomething();
		lock.unlock();
		System.out.println(lock.getHoldCount());
	}

	// 不可重入，陷入死循环
	public void doSomething() throws InterruptedException {
		lock.lock();
		System.out.println(lock.getHoldCount());
		// ......
		lock.unlock();
		System.out.println(lock.getHoldCount());
	}

	public static void main(String[] args) throws InterruptedException {
		LockTest03 test = new LockTest03();
		test.a();
		Thread.sleep(1000);
		System.out.println(test.lock.getHoldCount());
	}
}

class ReLock {
	// 是否被占用
	private boolean isLocked = false;
	Thread lockedBy = null; // 存储线程
	private int holdCount = 0; // 计数器，统计锁的使用

	// 使用锁和释放锁
	public synchronized void lock() throws InterruptedException {
		Thread t = Thread.currentThread();
		while (isLocked && lockedBy != Thread.currentThread()) {
			wait();
		}
		isLocked = true;
		lockedBy = t;
		holdCount++;
	}

	// 释放锁
	public synchronized void unlock() {
		if (Thread.currentThread() == lockedBy) {
			if (holdCount-- == 0) {
				isLocked = false;
				notify();
				lockedBy = null;
			}
		}
	}

	public int getHoldCount() {
		return holdCount;
	}

	public void setHoldCount(int holdCount) {
		this.holdCount = holdCount;
	}

}