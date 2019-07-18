package wyc.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 创建线程的方式三：实现Callable方法<br>
 * 
 * @author 王以诚
 */
public class CDownloader implements Callable<Boolean> {
	private String url; // 远程路径
	private String name; // 存储名字

	public CDownloader(String url, String name) {
		this.url = url;
		this.name = name;
	}

	@Override
	public Boolean call() throws Exception {
		WebDownloader wd = new WebDownloader();
		wd.download(url, name);
		System.out.println(name);
		return true;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CDownloader cd1 = new CDownloader("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=777637458,2481737582&fm=26&gp=0.jpg", "pic1.jpg");
		CDownloader cd2 = new CDownloader("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1317868903,312405283&fm=26&gp=0.jpg", "pic2.jpg");
		CDownloader cd3 = new CDownloader("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2802483573,2205289073&fm=200&gp=0.jpg", "pic3.jpg");
		// 创建执行服务
		ExecutorService ser = Executors.newFixedThreadPool(3);
		Future<Boolean> result1 = ser.submit(cd1);
		Future<Boolean> result2 = ser.submit(cd2);
		Future<Boolean> result3 = ser.submit(cd3);
		boolean r1 = result1.get();
		boolean r2 = result2.get();
		boolean r3 = result3.get();
		System.out.println(r1);
		System.out.println(r2);
		System.out.println(r3);
		ser.shutdownNow();
	}
}
