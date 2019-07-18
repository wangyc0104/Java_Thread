package wyc.thread;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

/**
 * 一个简易Web下载器 <br>
 * 
 * @author 王以诚
 */
public class WebDownloader {

	/**
	 * 下载方法 <br>
	 * 
	 */
	public void download(String url, String name) {
		try {
			FileUtils.copyURLToFile(new URL(url), new File(name));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("不合法的URL");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("下载失败");
		}
	}
}
