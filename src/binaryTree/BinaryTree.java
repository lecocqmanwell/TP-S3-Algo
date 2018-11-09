package binaryTree;

public class BinaryTree<E> {
	
	private Node<E> Noeud;
	
	private class Node<E>{
		
		private E value;
		private BinaryTree<E> left;
		private BinaryTree<E> right;
		
		public Node(){
			this.value = null;
			this.left = new BinaryTree<>();
			this.right = new BinaryTree<>();
		}
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
		
		this.Noeud = new Node();
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
	
	

}
