package org.ddd.generic.example14;

public class Sortor {
	public <V extends Comparable<V>> V getMax(V x, V y){ 
		if(x.compareTo(y) > 0){
			return x;
		}else{
			return y;
		}
	}
}
