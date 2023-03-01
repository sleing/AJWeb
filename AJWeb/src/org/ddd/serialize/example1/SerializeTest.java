package org.ddd.serialize.example1;


public class SerializeTest {
	public static void main(String[] args) throws Exception{
		Person p = new Person();
		SerializeTool.serialize(p,"person");
		SerializeTool.printFileInfo("person");
		Object obj = SerializeTool.deSerialize("person");
		Person dep = (Person)obj;
		System.out.println("Peron Name:	" + dep.getName());
	}
}
