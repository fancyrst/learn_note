package interview.fancy.override;

public class ClassB extends ClassA {
	
	
	public static void main(String[] args) {
		ClassA b = new ClassB();
		System.out.println(b.getNo(0));//方法重载
		
		ClassA printA = new ClassA("south", "north");
		ClassA printB = new ClassB("east", "west");
		printA.print();//方法重写，不一定需要关键字override
		printB.print();
	}
	
	public ClassB(String s1, String s2) {
		one = s1;
		two = s2;
	}
	
	protected ClassB(){
		for(int i=0;i<10;i++) {
			System.out.println(i);
		}
	}
	
	/**
	方法名相同
	方法的参数类型，个数顺序至少有一项不同
	方法的返回类型可以不相同
	方法的修饰符可以不相同
	 */
	public int getNo(int a, char c) {
		return a+2;
	}
	
	/**
	 * 重写需方法名、返回类型、参数个数及类型一致，方法的修饰符子类可比父类更宽
	 */
	@Override
	public void print() {
		System.out.println(one + " to " + two);
	}
	
}
