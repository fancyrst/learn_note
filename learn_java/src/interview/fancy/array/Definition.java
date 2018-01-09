package interview.fancy.array;

public class Definition {

	/**
	 * 注释部分都是错误的定义
	 */
	void difinArray() {
		//int a[] = new int[]; error:必须初始化
		int a[] = new int[10];
		String s1[][] = new String[5][];
		//String s2[][] = new String[][5];// 二维数组第一个必须初始化
		int a2[][] = {{1,1,2},{2,2}};
		
	}
}
