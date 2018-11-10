
package Correction.dataStructures;

/**
 * Fifo.java

 * Classe générique pour la représentation et la manipulation d'une "file d'attente"
 * avec ses opérations primitives usuelles.

 * Représentation purement dynamique sans utiliser "d'outils" Java prédéfinis
 * (et sans implémenter l'interface Java "Queue" existante).
 * 
 * @author Erick Timmerman  [Erick.Timmerman@univ-lille.fr]
 * @param <E> type générique
 */
public class Fifo<E>
{

    private class Cell<T>
    {
        T valeur;
        Cell<T> lien;

        Cell(T data)
        {
            valeur = data;
            lien   = null;
        }
    }   // fin de la classe interne
    

    // Attributs (fields) de la classe Fifo

    private Cell<E> premier;
    private Cell<E> dernier;


    /** Creates a new instance of Fifo */
    public Fifo()                       // inutile, = constructeur par défaut
    {
        premier = null;
        dernier = null;
    }

    /**
     * Détermine si la file d'attente est vide ou non. 
     * @return true ssi la file d'attente est vide.
     */
    public boolean estVide()            //   boolean isEmpty()
    {
        return premier == null;
    }

    /**
     * Fournit la valeur située en tête de la file d'attente, 
     * la file d'attente ne doit pas être vide.
     * @return la valeur située en tete de la file d'attente.
     */
    public E enTete()                   //  ~ T peek()
    {
        return premier.valeur;
    }

    /**
     * Fournit la valeur située en fin de la file d'attente, 
     * la file d'attente ne doit pas être vide.
     * @return la valeur située en de la file d'attente.
     */
    public E enFin()                    // pas d'équivalent dasn l'interface Queue
    {
        return dernier.valeur;
    }

    /**
     * Ajoute à la file d'attente (forcément en fin) une valeur fournie.
     * @param toAdd valeur à ajouter à la file d'attente.
     */
    public void ajouter(E toAdd)        //  ~ boolean offer(T e)
    {
        Cell<E> nouveau = new Cell<>(toAdd);
        if (!estVide())
            dernier.lien = nouveau;
        else
            premier = nouveau;
        dernier = nouveau;
    }

    /**
     * Supprime et retourne une valeur de la file d'attente (celle située en tête),
     * la file d'attente ne doit pas être vide.
     * @return la valeur qui a été supprimée de la file d'attente.
     */
    public E sortie()                //  ~ T poll()
    {
        E result = premier.valeur;
        premier = premier.lien;
        if (estVide())
            dernier = null;
        return result;
    }

    /**
     * Fournit une chaîne de caractères comportant toutes les valeurs présentes 
     * dans la file d'attente. 
     * <pre> 
     *      Format choisi: "]valeur en tête|... |valeur en fin[".
     * </pre>
     * @return a String representation of the values in this queue.
     */
    @Override
    public String toString()
    {
        if (this.estVide())
            return "][";
        
        String result = "";
        Cell<E> link = premier;
        while (link.lien != null)
        {
            result += link.valeur + "|";
            link = link.lien;
        }
        return "]" + result + link.valeur + "[";
    }

    /**
     * Détermine si un élément donné est ou non présent dans la file d'attente.
     * @param element l'élément recherché.
     * @return true ssi l'élément fourni apparaît dans la file d'attente.
     */
    public boolean contient(E element)
    {
        Cell<E> link = premier;
        while (link != null)
            if (element.equals(link.valeur))
                return true;
            else
                link = link.lien;
        return false;
    }
}
