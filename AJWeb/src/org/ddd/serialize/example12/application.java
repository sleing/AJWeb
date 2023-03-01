package org.ddd.serialize.example12;

public class application {
	public static void main(String[] args) throws Exception {
		Container c = new Container("beans.xml");
		Boy b = (Boy)c.getBean("boy");
		b.sendGit();
	}
}
