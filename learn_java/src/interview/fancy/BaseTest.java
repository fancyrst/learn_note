package interview.fancy;

import interview.fancy.override.ClassB;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class BaseTest {
	public static void main(String[] args) {
		
	}
	
	public void testOverride() {
		
	}
	
	public void testPrintWriter(){
		try {
			PrintWriter printWriter = new PrintWriter(new FileOutputStream("E:/ABC.txt"));
			printWriter.print("my name is Sam!");
			printWriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
