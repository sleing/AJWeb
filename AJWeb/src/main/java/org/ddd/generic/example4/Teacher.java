package org.ddd.generic.example4;

public class Teacher<V,S> extends Person {
	protected V v;
	private S s;
	public Teacher(Object t) {
		super(t);
	}
	public void set(V v, S s){
		this.v = v;
		this.s = s;
	}
	public String toString(){
		return super.toString()+"\n"+
				"变量v的类型是：" + v.getClass().getCanonicalName()+"\n"+
				"变量s的类型是：" + s.getClass().getCanonicalName()+"\n";
	}
}

