
package Correction.dataStructures;

/**
 * @author Erick Timmerman (Erick.Timmerman@univ-lille.fr)
 * @param <E> type générique des éléments de la liste
 */
public class Deque<E>
{

    private class CelluleBilatere<T>
    {
        private CelluleBilatere<T> previous;
        private final T value;
        private CelluleBilatere<T> next;

        CelluleBilatere(T value)
        {
            this.value = value;
            previous = null;            // inutile, valeur par défaut!
            next = null;		// idem
        }
    } 	 // Fin de la classe interne

    // attributs de la classe Deque
    private CelluleBilatere<E> debut;
    private CelluleBilatere<E> fin;

    /**
     * Construit une liste bilatère vide
     */
    public Deque()		// inutile,  = constructeur par défaut!
    {
        debut = null;   fin = null;
    }

    /**
     * @return vrai ssi cette liste bilatère est vide
     */
    public boolean estVide()
    {
        return debut == null;
    }

    /**
     * @return la valeur du premier élément
     */
    public E firstElement()
    {
        return debut.value;
    }

    /**
     * @return la valeur du dernier élément
     */
    public E lastElement()
    {
        return fin.value;
    }

    /**
     * Ajoute une valeur au début
     * @param value valeur à ajouter au début
     */
    public void addFirstElement(E value)
    {
        CelluleBilatere<E> nouveau = new CelluleBilatere<>(value);
        nouveau.next = debut;
        if (estVide())
            fin = nouveau;
        else
            debut.previous = nouveau;
        debut = nouveau;
    }

    /**
     * Ajoute une valeur en fin
     * @param value valeur à ajouter en fin
     */
    public void addLastElement(E value)
    {
        CelluleBilatere<E> nouveau = new CelluleBilatere<>(value);
        nouveau.previous = fin;
        if (estVide())
            debut = nouveau;
        else
            fin.next = nouveau;
        fin = nouveau;
    }

    /**
     * Supprime et retourne la première valeur
     * @return  la valeur ayant été supprimée
     */
    public E removeFirstElement()
    {
        E result = debut.value;
        debut = debut.next;
        if (debut == null)
            fin = null;
        else
            debut.previous = null;
        return result;
    }

    /**
     * Supprime et retourne la dernière valeur de la liste
     * @return la valeur ayant été supprimée
     */
    public E removeLastElement()
    {
        E result = fin.value;
        fin = fin.previous;
        if (fin == null)
            debut = null;
        else
            fin.next = null;
        return result;
    }
    
    /**
     * @return une representation sous forme de chaîne de caractères 
     * de cette liste bilatère
     */
    @Override
    public String toString()
    {
        if (this.estVide())
            return "][";
        
        String res = "]";
        CelluleBilatere<E> cell = this.debut;
        
        while (cell.next !=null)
        {
            res += cell.value + "|";
            cell = cell.next;
            
        }
        
        return res + cell.value + "[";
    }
    
    public boolean contains(E value)
    {
        CelluleBilatere<E> cell = this.debut;

        while (cell != null)
        {
            if (cell.value.equals(value))
            {
                return true;
            }
            cell = cell.next;

        }
        return false;
    }
    
    public boolean containsWithReverseSearching(E value)
    {
        CelluleBilatere<E> cell = this.fin;

        while (cell != null)
        {
            if (cell.value.equals(value))
            {
                return true;
            }
            cell = cell.previous;

        }
        return false;
    }
}
