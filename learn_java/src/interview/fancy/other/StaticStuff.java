package interview.fancy.other;

/**
 * 一个类中可以并存多个静态块
 *
 */
public class StaticStuff {

	static int x = 2; //step1: init first
	
	static {
		x += 8; //step2:运行第一个静态块
	}
	
	public static void main(String args[]) {
		System.out.println("X is "+ x);
	}
	
	static {
		x /= 2;//step2:运行第二个静态块
	}
}
