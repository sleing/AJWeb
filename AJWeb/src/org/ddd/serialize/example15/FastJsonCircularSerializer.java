package org.ddd.serialize.example15;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonCircularSerializer {	 
	public static void main(String[] args) {
		 final SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, // 输出空置字段
			 	SerializerFeature.PrettyFormat, //输出时增加缩进，换行
	            SerializerFeature.WriteDateUseDateFormat // 日期格式化yyyy-MM-dd HH:mm:ss
		  };       

		Book book = new Book("Java高级程序设计",345,true,45.12d,null,new Date(), Arrays.asList("徐传运","张杨"),null);
		System.out.println(JSON.toJSONString(book,features));
	 
		
		List<Book> books = new ArrayList();
		books.add(new Book("Java高级程序设计",345,true,45.12d,null,new Date(),Arrays.asList("徐传运","张杨"),null));
		books.add(new Book("系统分析与设计",440,true,55.24d,null,new Date(),Arrays.asList("黎天送","刘洁"),null));
		System.out.println(JSON.toJSONString(books,features));	

		
		List<Chapter> chapters = new ArrayList<>();
		chapters.add(new Chapter(1, "关于代码"));
		chapters.add(new Chapter(2, "WEB编程"));
		book = new Book("Java高级程序设计",345,true,45.12d,null,new Date(),Arrays.asList("徐传运","张杨"),chapters);
		System.out.println(JSON.toJSONString(book,features));
	
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("no", 1);
		map1.put("title", "关于代码");
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("no", 2);
		map2.put("title", "Web编程");
		list.add(map1);
		list.add(map2);
		System.out.println(JSON.toJSONString(list,features));	
	}
}

