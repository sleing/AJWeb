package org.ddd.annotation.example4_10;

@ExtractInterface("IPerson")
public class Person {
	public void speak(String message){
		System.out.println(message);
	}
	public void useTool(String toolName){
		System.out.println(toolName);
	}
}
