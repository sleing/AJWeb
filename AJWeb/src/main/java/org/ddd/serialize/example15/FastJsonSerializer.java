package org.ddd.serialize.example15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJsonSerializer {
	public static void main(String[] args) {
		SerializerFeature[] features1 = { SerializerFeature.WriteMapNullValue, // 输出空置字段
				SerializerFeature.PrettyFormat, // 输出时增加缩进，换行
				SerializerFeature.DisableCircularReferenceDetect, // 取消循环引用检查
				SerializerFeature.WriteDateUseDateFormat// 日期格式化yyyy-MM-dd HH:mm:ss
		};
		List list2 = new ArrayList();
		Map map = new HashMap();
		map.put("no", 2);
		map.put("title", "Web编程");
		list2.add(map);
		list2.add(map); // 同一个对象，连续添加两次，即List中有两个相同的对象
		System.out.println(JSON.toJSONString(list2, features1));

//		List list1 = new ArrayList();
//		map = new HashMap();
//		map.put("name", "徐传运");
//		map.put("map", map);
//		map.put("list", list1); // map引用了list1,形成循环引用
//		list1.add(map);
//		list1.add(map);
//		System.out.println(JSON.toJSONString(list1, features1));

		SerializerFeature[] features2 = { SerializerFeature.WriteMapNullValue, SerializerFeature.PrettyFormat, // 输出时增加缩进，换行
				// SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteDateUseDateFormat // 日期格式化yyyy-MM-dd HH:mm:ss
		};
		List list3 = new ArrayList();
		map = new HashMap();
		map.put("name", "徐传运");
		map.put("map", map);
		map.put("list", list3); // map引用了list1,形成循环引用
		list3.add(map);
		list3.add(map);
		System.out.println(JSON.toJSONString(list3, features2));
	}
}
