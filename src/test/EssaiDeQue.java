package test;

import ds2017.Deque;

public class EssaiDeQue {
	
	public static void main(String[] args) {
		
		Deque<Integer> listeD = new Deque<>();
		
		listeD.ajouterDebut(1);
		listeD.ajouterDebut(2);
		listeD.ajouterDebut(3);
		listeD.ajouterDebut(4);
		listeD.ajouterDebut(5);
		listeD.ajouterDebut(6);
		
		
		
		System.out.println("longueur = " + listeD.longueur());
		
		System.out.println(listeD);
		
		listeD.supprimerFin();
		
		listeD.supprimerFin();
		
		listeD.supprimerFin();
		
		System.out.println("longueur = " + listeD.longueur());
		
		System.out.println(listeD);

	}
}
