package org.ddd.generic.example25;

import java.util.List;

public class Tool {
	public void addList(List<? super Bird> list){
		list.add(new Parrot());
		list.add(new Bird());
//		list.add(new Animal()); //不合法
	}
}
