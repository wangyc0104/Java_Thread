package wyc.thread;

/**
 * Lambda推导
 * 
 * @author 王以诚
 */
public class LambdaTest01 {

	// 静态内部类
	static class Like2 implements ILike {
		public void lambda() {
			System.out.println("I like lambda2");
		}
	}

	public static void main(String[] args) {

		// 外部类
		ILike like = new Like();
		like.lambda();

		// 静态内部类
		like = new Like2();
		like.lambda();

		// 局部内部类
		class Like3 implements ILike {
			public void lambda() {
				System.out.println("I like lambda3");
			}
		}
		like = new Like3();
		like.lambda();

		// 匿名内部类
		like = new ILike() {
			public void lambda() {
				System.out.println("I like lambda4");
			}
		};
		like.lambda();

		// Lambda表达式（受到推导的接口必须只有一个没有实现的方法）
		like = () -> {
			System.out.println("I like lambda5");
		};
		like.lambda();
		
		/*
		 * Lambda推导必须存在类型，下述代码报错，因无类型参考。
			() -> {
				System.out.println("I like lambda5");
			}.lambda();
		*/

	}
}

interface ILike {
	void lambda();
}

class Like implements ILike {
	public void lambda() {
		System.out.println("I like lambda1");
	}
}