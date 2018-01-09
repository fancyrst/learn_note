package interview.fancy.override;

public class ClassA {
	
	String one, two;
	
	public int getNo(int a) {
		return a+1;
	}
	
	protected ClassA(){}
	
	public ClassA(String s1, String s2){
		one = s1;
		two = s2;
	}
	
	void print(){
		System.out.println(one);
	}
}
