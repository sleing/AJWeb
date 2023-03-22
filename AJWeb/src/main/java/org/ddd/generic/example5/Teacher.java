package org.ddd.generic.example5;

public class Teacher<V,S> extends Person<V> {
	protected V v;
	private S s;
	public Teacher(V t) {
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
