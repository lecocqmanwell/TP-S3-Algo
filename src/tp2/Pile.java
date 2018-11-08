
package tp2;
/**
 * Classe générique pour la représentation et la manipulation d'une "pile"
 * avec ses opérations primitives usuelles.
 * Représentation purement dynamique sans utiliser "d'outils" Java (ni Collection ni tableau). 
 * 
 * @param <E> "type" générique. 
 * @author Erick Timmerman  Erick.Timmerman@univ-lille.fr 
 */
public class Pile<E>            // équivalent fonctionnel de la classe java.util.Stack
{   

    // classe interne pour la représentation d'un couple (valeur, lien).
    private class Cellule<T>
    {
        T valeur;               // attributs (fields)
        Cellule<T> lien;
        Cellule(T data)         // constructeur
        {
            valeur = data;
            lien = null;
        }
    }   // fin de la classe interne
    
    private Cellule<E> premier;         // attribut de la classe Pile

    /** Creates a new instance of Pile */
    public Pile()                       // Inutile, idem constructeur par défaut!
    {
        premier = null;
    }

    /**
     * Détermine si la pile est vide ou non. 
     * @return true ssi la pile est vide.
     */
    public boolean estVide()            // cf. méthode empty de la classe Stack
    {
        return premier == null;
    }

    /**
     * Fournit la valeur située au "sommet" de la pile, 
       la pile ne doit pas être vide.
     * @return la valeur située au sommet de la pile.
     */
    public E sommet()                   // cf. méthode peek de la classe Stack
    {
        return premier.valeur;
    }

    /**
     * Ajoute à la pile (au sommet) une valeur fournie.
     * @param aEmpiler valeur à empiler.
     */
    public void empiler(E aEmpiler)     // cf. méthode push de la classe Stack
    {
        Cellule<E> nouveau = new Cellule<>(aEmpiler);
        nouveau.lien = premier;
        premier = nouveau;
    }

    /**
     * Supprime et retourne une valeur de la pile (celle située au sommet),
       la pile ne doit pas être vide.
     * @return la valeur qui a été supprimée de la pile.
     */
    public E depiler()                  // cf. méthode pop de la classe Stack
    {
        Cellule<E> temp = premier;
        premier = premier.lien;
        return temp.valeur;
    }


    /**
     * Fournit une chaîne de caractères comportant toutes les valeurs présentes 
     * dans la pile. 
     * <pre> 
       Format choisi: "|valeur au sommet| ... |valeur à la base]".
      </pre>
     * @return a String representation of the values in this "pile".
     */
    @Override 
    public String toString()
    {
        StringBuilder res = new StringBuilder();
        
        Cellule<E> link = premier;
        while (link != null)
        {
            res.append("|").append(link.valeur);
            link = link.lien;
        }
        res = res.append("]");
        
        return res.toString();
    }

    /**
     * Détermine si un élément donné est ou non présent dans la pile.
     * @param element l'élément recherché.
     * @return true ssi l'élément fourni apparait dans la pile.
     */
    public boolean contient(E element)      // cf. ~méthode search de la classe Stack
    {
        Cellule<E> link = premier;
        while (link != null)
            if (element.equals(link.valeur))
                return true;
            else
                link = link.lien;
        return false;
    }
}

