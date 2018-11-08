package tp1;

public class Persistance {
	
	public static int persist(int n) {
		
		if(n < 10 ) return 0;
		
		else {
			
			return 1 + persist(calcul(n));
			
		}
	}
	
	public static int calcul(int n) {
		
		if(n < 10) return n;
		
		return (n%10) * calcul((n/10));
	}
}
