package junit.fancy.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * final 关键字测试
 * 1. 测试成员变量是final类型，进行两次赋值会如？
 * 报编译错误(赋null值)，不能被分配（can't be assgined)
 */
public class TestFinal {
	
	//不可变对象初始化安全
	private final Map<String, Object> finalMap;//如果此处赋值(null值也不允许)，后续finalMap将不能再被分配(assigned)
	
	private List<String> list;
	
	public TestFinal() {
		finalMap = new HashMap<String, Object>();// final修饰的对象引用不可变，在构造初始化是线程安全的，不需同步
		finalMap.put("abc", 1);	
		finalMap.put("bac", "test");	
		
		list = new ArrayList<String>(); //非final的成员变量，在构造初始化是非线程安全的，初试话需同步.
		list.add("abc");
	}

}
