package org.ddd.serialize.example15;

import java.io.File;
import java.net.URLDecoder;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FastJsonParser {
	public static void main(String[] args) throws Exception {
		String fileName = URLDecoder.decode(FastJsonParser.class.getResource("book.json").getFile(), "UTF-8");
		String json = FileUtils.readFileToString(new File(fileName), "UTF-8");
		Book book = (Book) JSON.parseObject(json, Book.class);
		System.out.println(book.getTitle());
		JSONObject bookJsonObject = (JSONObject) JSON.parseObject(json);
		System.out.println(bookJsonObject.get("title"));
		Map bookMap = (Map) JSON.parseObject(json, Map.class);
		System.out.println(bookJsonObject.get("title"));

		JSONObject bookJsonObject1 = (JSONObject) JSON.parseObject(json);
		System.out.println(bookJsonObject1.get("title"));

	}
}