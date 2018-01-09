package org.fancy.socket;


public class StackHeap {

	String s1 = "1";
	String s2 = "1";
	
	public static void main(String[] args) {
		
		StackHeap heap = new StackHeap();
		System.out.println(heap.s1 == heap.s2);
		
		String s3 = "2";
		String s4 = "2";
		System.out.println(s3==s4);
		
		heap.testStr(s3, s4);
	}
	
	public void testStr(String s3, String s4) {
		String s5 = s3;
		String s6 = s4;
		System.out.println(s5==s6);
	}
}
