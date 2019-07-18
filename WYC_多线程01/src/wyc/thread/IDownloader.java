package wyc.thread;

public class IDownloader implements Runnable {
	private String url; // 远程路径
	private String name; // 存储名字

	public IDownloader(String url, String name) {
		this.url = url;
		this.name = name;
	}

	@Override
	public void run() {
		WebDownloader wd = new WebDownloader();
		wd.download(url, name);
	}
	
	public static void main(String[] args) {
		IDownloader td1 = new IDownloader("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=777637458,2481737582&fm=26&gp=0.jpg","pic1.jpg");
		IDownloader td2 = new IDownloader("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1317868903,312405283&fm=26&gp=0.jpg","pic2.jpg");
		IDownloader td3 = new IDownloader("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2802483573,2205289073&fm=200&gp=0.jpg","pic3.jpg");
		// 线程开启的顺序不一定
		new Thread(td1).start();
		new Thread(td2).start();
		new Thread(td3).start();
	}
}
