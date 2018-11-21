package binaryTree;

import Correction.dataStructures.RecursiveList;

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
	
	 public BinaryTree(E[] elts){
	        this();
	        setTree(perfectlyBalancedTreeFrom(elts, 0, elts.length - 1));
	 }
	 
	 private BinaryTree<E> perfectlyBalancedTreeFrom(E[] elts, int indexDebut, int indexFin){
	     
		 if (indexDebut > indexFin)    // portion de vecteur vide
			 return new BinaryTree<>();

	     int nb = (indexFin + indexDebut + 1) / 2;

	        // Construction des sous-arbres.
	     BinaryTree<E> g = perfectlyBalancedTreeFrom(elts, indexDebut + 1, nb);
	     BinaryTree<E> d = perfectlyBalancedTreeFrom(elts, nb + 1, indexFin);

	     return new BinaryTree<>(elts[indexDebut], g, d);
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
		
		//this.Noeud = other.Noeud
		
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
		
		//CORRECTION
		
		/*if (this.estVide() || this.estUneFeuille())
            return true;
        if (this.gauche().estVide() || this.droit().estVide())
            return false;

        return this.gauche().estBinairePur() && this.droit().estBinairePur();*/
		
		
		if(this.estVide()) {
			return false;
		}
		if(this.estUneFeuille()) {
			return true;
		}
		return this.right().estBinairepur() && this.left().estBinairepur();
	}
	
	/**
     * Fournit la liste des valeurs situées en feuille de l'arbre (ordre gauche --> droit).
     * Version récursive sans tenir compte du coût, de la complexité, algorithmique.
     * @return une liste des valeurs en feuille de l'arbre
     */
	public RecursiveList<E> feuillage(){
		
		RecursiveList<E> result = new RecursiveList<>();
		
		if(this.estVide()) {
			
			return result;
		}
		if(this.estUneFeuille()) {
			
			result.ajouterEnFin(this.root());
			return result;
		}
		return this.left().feuillage().concatenation(this.right().feuillage());
	}
	
	/**
     * @return la suite "préfixe" des valeurs de l'arbre, version itérative.
     */
    public String prefixeIteratif()
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return la suite "infixe" des valeurs de l'arbre, version itérative.
     */
    public String infixeIteratif()
    {
       // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return la suite obtenue avec un parcours en "largeur d'abord" des valeurs de l'arbre.
     */
    public String parNiveau()
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * @return la suite "suffixe" des valeurs de l'arbre, version itérative.
     */
    public String suffixeIteratif()
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Fournit la liste des valeurs situées en feuille de l'arbre (ordre gauche --> droit).
     * Version récursive moins coûteuse évitant la construction intempestive de listes.
     * @return une liste des valeurs en feuille de l'arbre
     */
    public RecursiveList<E> feuillageEco()
    {
        if (this.estVide())
            return new RecursiveList<>();
        if (this.estUneFeuille())
            return new RecursiveList<>(root());
        RecursiveList<E> res = this.left().feuillageEco();
        res.concatener(this.right().feuillageEco());
        return res;
    }
    
    /**
     * Fournit la liste des valeurs situées en feuille de l'arbre (ordre gauche --> droit).
     * Version itérative efficace, parcours en profondeur type préfixe (RDG).
     * @return une liste des valeurs en feuille de l'arbre
     */
    public RecursiveList<E> feuillageIteratif()
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
