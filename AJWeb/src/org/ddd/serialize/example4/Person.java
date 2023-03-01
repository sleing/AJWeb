package org.ddd.serialize.example4;

import java.io.Serializable;


public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name = "simple";
	private Tool tool = new Tool();
}
