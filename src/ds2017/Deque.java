package ds2017;

public class Deque<E> {

	private class Cellule <E>{
		Cellule<E> pred;
		E valeur;
		Cellule<E> succ;

		Cellule (E data){
			this.pred =null;
			this.valeur = data;
			this.succ = null;
		}
	}
	private Cellule<E> debut;
	private Cellule<E> fin;

	public Deque(){
		this.debut = null;
		this.fin = null;
	}

	public boolean estVide() {
		return debut == null;
	}
	public int longueur() {

		Cellule<E> cell = debut;
		int compteur = 0;

		while (cell != null) {
			
			cell = cell.succ;
			compteur ++;
		}
		return compteur;

	}

	public void ajouterDebut (E data){

		Cellule<E> cell = new Cellule<>(data);

		if(this.estVide()) {
			fin = cell;
			debut = cell;
		}
		else {
			if(this.longueur() == 1) {
				cell.succ = fin;
				debut = cell;
				fin.pred = debut;
			
			}else {
				debut.pred = cell;
				cell.succ = debut;
				debut = cell;
			}
		}
	}

	public void supprimerFin() {

		fin = fin.pred;
		fin.succ = null;
	}

	@Override
	public String toString() {
		
		String result = "[ ";
		
		Cellule<E> cell = debut;
		
		while (cell != null) {

			result += cell.valeur;
			cell = cell.succ;
			
			if(cell != null) {
				result += ", ";
			}
		}
		result += " ]";

		return result;
	}
}
