package wyc.thread;

/**
 * Lambda推导
 * 
 * @author 王以诚
 */
public class LambdaTest02 {

	public static void main(String[] args) {

		// 只有一个抽象方法，可以使用Lambda表达式
		ILove love = (int a) -> {
			System.out.println("I love lambda -->" + a);
		};
		love.lambda(10);

		// 可以省略类型定义
		love = (a) -> {
			System.out.println("I love lambda -->" + a);
		};
		love.lambda(100);

		// 只有一个参数的话，括号可以省略
		love = a -> {
			System.out.println("I love lambda -->" + a);
		};
		love.lambda(1000);

		// 方法体只有一行代码的话，括号也可以省略
		love = a -> System.out.println("I love lambda -->" + a);
		love.lambda(10000);
	}
}

interface ILove {
	void lambda(int a);
}

class Love implements ILove {
	public void lambda(int a) {
		System.out.println("I love lambda1 --> " + a);
	}
}