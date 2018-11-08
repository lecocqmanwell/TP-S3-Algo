package tp2;

public class Fifo <E>{
	
	private class Cellule<T>
    {
        T valeur;              
        Cellule<T> lien;
        Cellule(T data)        
        {
            valeur = data;
            lien = null;
        }
    }
	
	 private Cellule<E> debut; 
	 private Cellule<E> fin; 
	 
	 public Fifo() {
		 
		 debut = null;
		 fin = null;
	 }
	 
	 public boolean estVide() {
		 
		 return debut == null;
	 }
	 
	 public void enfiler(E data) {
		 
		 Cellule <E> c = new Cellule<>(data);
		 
		 if(this.estVide()) {
			 debut = c;
		 }
		 else {
			 
			 fin.lien = c;
			 
		 }
		 fin = c;
	 }
	 
	 public E defiler() {
		 
		 if(this.estVide()) {
			 
			 return null;
		 }
		 else {
			 
			 E result;			 
			 result = debut.valeur;
			 debut = debut.lien;
			 return result;
		 }
	 }
	 @Override
	 public String toString() {
		
		 StringBuilder result = new StringBuilder();
		 
		 Cellule<E> link = debut;
		 
		 while(link != null) {
			 result.append("|").append(link.valeur);
			 link = link.lien;
		 }
		 result = result.append("]");
		 
		 return result.toString();
		 
	 }
	 
	 public boolean contient(E element) {   
		 
	        Cellule<E> link = debut;
	        while (link != null)
	            if (element.equals(link.valeur))
	                return true;
	            else
	                link = link.lien;
	        return false;
	    }
	 
	 

}
