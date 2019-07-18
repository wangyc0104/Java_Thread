package wyc.thread;

/**
 * Lambda推导
 * 
 * @author 王以诚
 */
public class LambdaTest03 {

	public static void main(String[] args) {

		// 只有一个抽象方法，可以使用Lambda表达式
		IInterest interest = (int a, int b) -> {
			System.out.println("I am interested in lambda --> " + (a + b));
			return a + b;
		};
		interest.lambda(100, 200);

		// 可以省略类型定义
		interest = (a, b) -> {
			System.out.println("I am interested in lambda --> " + (a + b));
			return a + b;
		};
		interest.lambda(100, 200);

		// 只有一行return代码，花括号可以省略，return关键字也可以省略
		interest = (a, b) -> {
			return a + b;
		};
		interest.lambda(100, 200);

		interest = (a, b) -> a + b;
		System.out.println("I am interested in lambda --> " + interest.lambda(100, 200));

	}
}

interface IInterest {
	int lambda(int a, int b); // 形参名可以不同
}

class Interest implements IInterest {
	public int lambda(int a1, int b1) {
		System.out.println("I am interested in lambda --> " + (a1 + b1));
		return (a1 + b1);
	}
}