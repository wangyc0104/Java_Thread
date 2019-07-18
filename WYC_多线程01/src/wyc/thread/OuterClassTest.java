package wyc.thread;

/**
 * 各种内部类匿名类外部类测试
 * @author Yicheng Wang
 *
 */
public class OuterClassTest {
	int out1 = 1;
	static int out2 = 1;

	void out() {
		System.out.println("非静态");
	}

	static void outstatic() {
		System.out.println("静态");
	}

	public class InnerClass {
		void InnerClass() {
			System.out.println("InnerClass!");
			System.out.println(out1);
			System.out.println(out2);
			out();
			outstatic();// 静态内部类只能够访问外部类的静态成员
		}

		// static void inner(){} static int i=1; //非静态内部类不能有静态成员（方法、属性）
	}

	public static class InnerstaticClass {
		void InnerstaticClass() {
			System.out.println("InnerstaticClass");
			// System.out.println(out1);out(); 静态内部类只能够访问外部类的静态成员
			System.out.println(out2);
			outstatic();
		}

		static void innerstatic() {
		}

		static int i = 1;// 静态内部类能有静态成员（方法、属性）
	}

	public static void main(String args[]) {
		OuterClassTest a = new OuterClassTest();
		OuterClassTest.InnerstaticClass b = new OuterClassTest.InnerstaticClass();// 创建静态内部类
		OuterClassTest.InnerClass c = a.new InnerClass();// 创建非静态内部类
	}
}