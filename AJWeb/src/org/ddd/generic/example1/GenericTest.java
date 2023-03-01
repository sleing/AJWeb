package org.ddd.generic.example1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenericTest {
	public static void main(String[] args) {
		List listIntger = new ArrayList();
		List listString = new ArrayList();
		List listDate = new ArrayList();
		
		listIntger.add(new Integer(1));
		listString.add(new String("字符串"));
		listDate.add(new Date());
		
		listIntger.add(new String("字符串")); //逻辑不正确，但语法正确，这很容易引起错误
		
		Integer i = (Integer)listIntger.get(0);
		String str = (String)listString.get(0);
		Date date = (Date)listDate.get(0);
		
		System.out.println("第一个数组中存放的是数字：" + i);
		System.out.println("第二个数组中存放的是字符串：" + str);
		System.out.println("第三个数组中存放的是日期：" + date.toString());
	}
}
