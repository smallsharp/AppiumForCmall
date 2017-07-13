package com.cmall.reference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;

/**
 * Java List 和 Map 的遍历
 * @author cm
 *
 */
public class TestJava {

	@Test
	public void testList() {
		
		List<String> list = new ArrayList<String>();
		list.add("Jimmy");
		list.add("Jack");
		list.add("Smith");

		// 第一种遍历方法使用foreach遍历List，也可以改写for(int i=0;i<list.size();i++)
		System.out.println("通过foreach遍历List：");
		for (String str : list) { 
			System.out.println(str);
		}

		// 第二种遍历，把链表变为数组相关的内容进行遍历
		System.out.println("通过把链表变为数组相关的内容进行遍历：");
		String[] strArray = new String[list.size()];
		list.toArray(strArray);
		// 这里也可以改写为 foreach(String str:strArray)这种形式
		for (int i = 0; i < strArray.length; i++) {
			System.out.println(strArray[i]);
		}

		// 第三种遍历 使用迭代器进行相关遍历
		System.out.println("通过迭代器进行相关遍历：");
		Iterator<String> it = list.iterator();
		while (it.hasNext())// 判断下一个元素之后有值
		{
			System.out.println(it.next());
		}
	}
	
//	@Test
	public void testMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "value1");
		map.put("2", "value2");
		map.put("3", "value3");

		// 第一种：普遍使用，二次取值
		System.out.println("通过Map.keySet遍历key和value：");
		for (String key : map.keySet()) {
			System.out.println("key= " + key + " and value= " + map.get(key));
		}

		// 第二种
		System.out.println("通过Map.entrySet使用iterator遍历key和value：");
		Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}

		// 第三种：推荐，尤其是容量大时
		System.out.println("通过Map.entrySet遍历key和value");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
		}

		// 第四种
		System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
		for (String v : map.values()) {
			System.out.println("value= " + v);
		}
	}
}
