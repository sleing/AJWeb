package org.ddd.generic.example15;

public class Parrot extends Bird implements Speakable, Flyable {
	@Override
	public String speak() {
		return "我是一只鹦鹉！";
	}
}
