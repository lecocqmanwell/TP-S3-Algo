package binaryTree;

import listeRecursive.RecursiveList;

public class BinaryTree<E> {
	
	private Node<E> Noeud;
	
	private class Node<E>{
		
		private E value;
		private BinaryTree<E> left;
		private BinaryTree<E> right;
		
		
		public Node(E value) {
			this.value = value;
			this.left = new BinaryTree<>();
			this.right = new BinaryTree<>();
		}
		public Node(E value,BinaryTree<E> left,BinaryTree<E> right) {
			this.value = value;
			this.left = left;
			this.right = right;
		}
		
	}
	
	public BinaryTree() {
		
		this.Noeud = null;
	}
	
	public BinaryTree(E value) {
		
		this.Noeud = new Node(value);
	}

	public BinaryTree(E value,BinaryTree<E> left,BinaryTree<E> right) {
		
		this.Noeud = new Node(value,left,right);
	}
	
	public boolean estVide() {
		
		return this.Noeud == null;
	}
	
	public E root() {
		return this.Noeud.value;
	}
	
	public BinaryTree<E> left(){
		return this.Noeud.left;
	}
	
	public BinaryTree<E> right(){
		return this.Noeud.right;
	}
	
	public void setRoot(E value) {
		
		this.Noeud.value = value;
	}
	
	public void setLeft(BinaryTree<E> tree) {
		this.Noeud.left = tree;
	}
	
	public void setRight(BinaryTree<E> tree) {
		this.Noeud.right = tree;
	}
	
	public void setTree(BinaryTree<E> other) {
		
		this.Noeud.value = other.Noeud.value;
		this.Noeud.left = other.Noeud.left;
		this.Noeud.right = other.Noeud.right;
	}
	
	public boolean estUneFeuille() {
		return this.left().estVide() && this.right().estVide();
	}
	public String toString() {
		
		if (this.estVide()) {
			return "";
		}
		
		if(this.estUneFeuille()) {
			return this.root().toString();
		}
		
		return this.root() + "("+this.left() + ","+ this.right() + ")";
		
	}
	
	public String affichagePreffixe() {
		
		if(!this.estVide()) {
			return this.root() + this.left().affichagePreffixe() + this.right().affichagePreffixe();
		}
		
		return "";
	}
	
	public String affichageInffixe() {
		
		if(!this.estVide()) {
			return this.left().affichageInffixe() + this.root() + this.right().affichageInffixe();
		}
		
		return "";
	}
	public String affichageSuffixe() {
		
		if(!this.estVide()) {
			return this.left().affichageSuffixe() + this.right().affichageSuffixe() + this.root() ;
		}
		
		return "";
	}
	
	public int numberOfNode() {
		
		if(this.estVide()) {
			
			return 0;
		}
		return 1 + this.left().numberOfNode() + this.right().numberOfNode();
	}
	
	public int hight() {
		
		if(this.estVide()) {
			return 0;
		}
		return 1 + Math.max(this.left().hight(), this.right().hight());
	}
	
	public boolean estBinairepur() {
		
		if(this.estVide()) {
			return true;
		}
		return this.right().estBinairepur() && this.left().estBinairepur();
	}
	
	public RecursiveList<E> feuillage(){
		
		if(this.estVide()) {
			
			return new RecursiveList<>();
		}
		if(this.estUneFeuille()) {
			
			
			
			result.ajouterEnFin(this.root());
			return new RecursiveList<>();
		}
		return this.left().feuillage().concatenation(this.right().feuillage());
}

}
