package wyc.others;

/**
 * 不可重入锁：锁不可以延续使用 <br>
 * 锁实际上就是一个自定义的类 <br>
 * 
 * @author 王以诚
 */
public class LockTest02 {
	Lock lock = new Lock();

	public void a() throws InterruptedException {
		lock.lock();
		doSomething();
		lock.unlock();
	}

	// 不可重入，陷入死循环
	public void doSomething() throws InterruptedException {
		lock.lock();
		// ......
		lock.unlock();
	}

	public static void main(String[] args) throws InterruptedException {
		LockTest02 test = new LockTest02();
		test.a();
		test.doSomething();
	}
}

class Lock {
	// 是否被占用
	private boolean isLocked = false;

	// 使用锁和释放锁
	public synchronized void lock() throws InterruptedException {
		while (isLocked) {
			wait();
		}
		isLocked = true;
	}

	// 释放锁
	public synchronized void unlock() {
		isLocked = false;
		notify();
	}
}