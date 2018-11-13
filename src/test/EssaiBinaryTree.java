package test;

import binaryTree.BinaryTree;

public class EssaiBinaryTree {

	public static void main(String[] args) {
		
		BinaryTree<String> arbreH = new BinaryTree<>("H");
		
		BinaryTree<String> arbreI = new BinaryTree<>("I");
		
		BinaryTree<String> arbreE = new BinaryTree<>("E",arbreH,new BinaryTree<String>());
		
		BinaryTree<String> arbreF = new BinaryTree<>("F",new BinaryTree<String>(),arbreI);
		
		BinaryTree<String> arbreD = new BinaryTree<>("D");
		
		BinaryTree<String> arbreG = new BinaryTree<>("G");
		
		BinaryTree<String> arbreB = new BinaryTree<>("B",arbreD,arbreE);
		
		BinaryTree<String> arbreC = new BinaryTree<>("C",arbreF,arbreG);
		
		BinaryTree<String> arbreA = new BinaryTree<>("A",arbreB,arbreC);
		
		System.out.println(arbreA);
		
		System.out.println("Racine abre A = " + arbreA.root());
		
		System.out.println("Droite abre A = " + arbreA.right());
		
		System.out.println("Gauche abre A = " + arbreA.left());
		
//		arbreA.setTree(arbreB);
//		
//		System.out.println("set racine A en B = " + arbreA);
		
		System.out.println("Preffixe  = " + arbreA.affichagePreffixe());
		
		System.out.println("Inffixe  = " +arbreA.affichageInffixe());
		
		System.out.println("Suffixe  = " +arbreA.affichageSuffixe());
		
		System.out.println("Nombre de noeud de l'arbre A =  " + arbreA.numberOfNode());
		
		System.out.println("Hauteur de l'arbre A  = " + arbreA.hight());
		
		System.out.println("arbre A est binaire pur = " + arbreA.estBinairepur());
		
		System.out.println("arbre D est binaire pur = " + arbreD.estBinairepur());
		
		System.out.println(arbreA.feuillage());
	}

}
