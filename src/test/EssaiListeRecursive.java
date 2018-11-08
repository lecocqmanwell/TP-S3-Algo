package test;

import listeRecursive.RecursiveList;

public class EssaiListeRecursive {

	public static void main(String[] args) {
		RecursiveList<Integer> liste = new RecursiveList<Integer>();
		
		for(int i = 5; i > 0;i--)
		{
		liste.ajouterEnTete(i);
		}
		
//		for(int i = 1; i <= 5;i++) {
//			
//			liste.ajouterEnFin(i);
//		}
		
		System.out.println(liste);
		System.out.println("La longueur de la liste est " + liste.longueur());
		System.out.println("La liste contient le nombre " + 1 + " ? (version recursive) : " + liste.contient(new Integer(1)));
		System.out.println("La liste contient le nombre " + 100 + " ? (version recursive) : " + liste.contient(new Integer(100)));
		System.out.println("La liste contient le nombre " + 1 + " ? (version iterative) : " + liste.contientIt(new Integer(1)));
		System.out.println("La liste contient le nombre " + 100 + " ? (version iterative) : " + liste.contientIt(new Integer(100)));
		
		System.out.println("On ajoute le nombre 3 a la fin");
		
		liste.ajouterEnFin(3);
		
		System.out.println(liste);
		
		System.out.println("Liste commencant par 3 = " + liste.sublistBeginningWith(3));
		
		System.out.println("On supprime tout les 3");
		
		liste.supprimerAll(3);
		
		System.out.println(liste);
		
		System.out.println("copie de la liste = " + liste.copie());
		
		System.out.println("liste equals liste.copie = " + liste.equals(liste.copie()));
		
		System.out.println("liste equals new RL(1) = " + (new RecursiveList<>(1)).equals(new RecursiveList<>(1)));
		
		System.out.println("creation d'une nouvelle liste");
		
		RecursiveList<Integer> liste2 = new RecursiveList<>(5);
		liste2.ajouterEnFin(4);
		liste2.ajouterEnFin(2);
		liste2.ajouterEnFin(1);
		
		System.out.println(liste2);
		
		System.out.println("On concatene la liste2 a la liste (en modifiant liste)");
		
		liste.concatener(liste2);
		
		System.out.println(liste);
		
		System.out.println(liste.concatenation(liste2));
		
		RecursiveList<Integer> listeMiroir = new RecursiveList<>();
		
		listeMiroir = liste.miroirIteratif();
		
		System.out.println(listeMiroir);
	} 

}
