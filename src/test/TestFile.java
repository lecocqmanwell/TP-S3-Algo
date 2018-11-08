package test;

import tp2.Fifo;

public class TestFile {

	public static void main(String[] args) {
		
		Fifo<Integer> f = new Fifo<>();
		
		Integer i1 = new Integer(1);
		
		Integer i2 = new Integer(2);
		
		Integer i3 = new Integer(3);
		
		f.enfiler(i1);
		
		System.out.println(f);
		
		f.enfiler(i2);
		
		System.out.println(f);
		
		f.enfiler(i3);
		
		System.out.println(f);
		
		f.defiler();
		
		System.out.println(f);
		
		f.defiler();
		
		System.out.println(f);
		
		f.defiler();
		
		System.out.println(f);
		
		
	}
}
