package org.ddd.serialize.example2;


public class SerializeTest {
	public static void main(String[] args) throws Exception{
		Person p = new Person();
		SerializeTool.serialize(p,"person");
		SerializeTool.printFileInfo("person");
	}
}
