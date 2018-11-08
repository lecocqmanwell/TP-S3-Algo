package tp3;

import tp2.Pile;

public class Expression {
	
	private static Pile<Character> maPile =  new Pile<>();
	
	public static boolean bienParentheser(String exp) {
		
		int longueur = exp.length();
		
		char parentheseOuvrante [] = {'(','{','['};
		
		char parentheseFermante [] = {')','}',']'};
		
		for(int i = 0; i < longueur;i++) {
			
			char caractere = exp.charAt(i);
			
			if (estParenthese(parentheseOuvrante, caractere)) {
				
				maPile.empiler(caractere);
			}
			if (estParenthese(parentheseFermante, caractere)){
				
				if(maPile.estVide()) {
				
					return false;
				}
				if(correspondanceParenthese(parentheseFermante ,parentheseOuvrante,caractere,maPile.sommet())) {
					
					maPile.depiler();
				}
			}
		}
		
		return maPile.estVide();		
	}
	
	public static boolean estParenthese(char tab [],char c) {
		
		for(int i = 0;i < tab.length ; i++) {
			
			if (c == tab[i]) {
				
				return true;
			}
		}
		return false;
	}
	
	public static boolean correspondanceParenthese(char tabRef [],char tabVerif [], char cChaine, char cPile) {
		
		int indice = 0;
		
		for(int i = 0 ; i < tabRef.length ; i++) {
			
			if(tabRef[i] == cChaine) {
				
				indice = i;
			}
		}
		
		return tabVerif[indice] == cPile;
	}
	
	
	//Probleme 2
	
	public static String parentheseToSuffixe(String exp) {
		
		while(!maPile.estVide()) {
			maPile.depiler();
		}
		
		String resultat = "";
		
		char tabOperateur[] = {'+','-','/','*','%'};
		
		for(int i = 0 ; i < exp.length(); i++) {
			
			char caractere = exp.charAt(i);
			
			if(estParenthese(tabOperateur, caractere)) {
			
				maPile.empiler(caractere);
			}else {
				if(caractere != '(' && caractere !=')') {
					
					resultat += caractere;
				}
				else {
					if(caractere == ')') {
						
						resultat += maPile.sommet();
						
						maPile.depiler();
					}
				}
			}
		}
		return resultat;
				
	}
}
