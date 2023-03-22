package org.ddd.db.pool;


public class DBTest {
	public static void main(String[] args) throws Exception{
		System.out.println(ConPool.getIntance().toString());
		MyCon con = null;
		for(int i = 0; i< 5; i++){
			con = ConPool.getIntance().getCon();
		}
		System.out.println(ConPool.getIntance().toString());
		ConPool.getIntance().setFree(con);
		System.out.println(ConPool.getIntance().toString());
	}	
}
