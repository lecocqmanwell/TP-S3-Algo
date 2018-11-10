
package Correction.divers;

import Correction.dataStructures.Pile;
import Correction.dataStructures.Coord;
import Correction.dataStructures.Fifo;
import Correction.dataStructures.RecursiveList;

import java.util.Random;

/**
 *
 * @author Erick Timmerman  (Erick.Timmerman@univ-lille.fr)
 */
public class Labyrinthe
{
    private static final char MUR = '#',
                              VIDE = ' ',
                              MARQUE = '.',
                              DEPART = 'D',
                              ARRIVEE = 'A',
                              PASSAGE = '+';
    
    private static final int  DEFAULT_PERCENTAGE = 50,
                              DEFAULT_HEIGHT = 20,
                              DEFAULT_WIDTH = 40;

    private char [][] tab;
    private int nbLignes, nbColonnes;
    private Coord depart, arrivee;
    
    /* 
     *  Constructeurs avec initialisations et remplissage aléatoire du tableau
     */
    public Labyrinthe()
    {
        this(DEFAULT_PERCENTAGE);
    }

    public Labyrinthe(int pourcentage)
    {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH, pourcentage);
    }

    public Labyrinthe(int hauteur, int largeur, int pourcentage)
    {
        tab = new char[hauteur + 2][largeur + 2]; // + 2 pour le mur tout autour!
        nbLignes = hauteur;
        nbColonnes = largeur;
        initialiserBords(); // placer un mur sur les bords

        for (int i = 1; i <= nbLignes; i++)
            for (int j = 1; j <= nbColonnes; j++)
                tab[i][j] = VIDE;

        remplissageAleatoire(pourcentage);

        tab[1][1] = DEPART;                     // Choix arbitraire!
        depart = new Coord(1, 1);
        tab[nbLignes][nbColonnes] = ARRIVEE;    // idem.
        arrivee = new Coord(nbLignes, nbColonnes);
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for (char[] ligne : tab)
            result.append(new String(ligne)).append("\n");
        result.append("Départ: ").append(depart).append("  Arrivée: ").append(arrivee).append("\n");
        
        return result.toString();
    }
    
    
    private void initialiserBords()
    {
        for (int i = 0; i < nbColonnes + 2; i++)
        {
            tab[0][i] = MUR;
            tab[nbLignes + 1][i] = MUR;
        }
        for (int i = 1; i < nbLignes + 1; i++)
        {
            tab[i][0] = MUR;
            tab[i][nbColonnes +1] = MUR;
        }
    }

    private void remplissageAleatoire(int pourcentage)
    {
        Random rand = new Random();
        int nbMurs = (nbLignes * nbColonnes * pourcentage) / 100;
        for (int i = 0; i < nbMurs; i++)
            tab[rand.nextInt(nbLignes) + 1][rand.nextInt(nbColonnes) + 1] = MUR;
    }
       
    /*
        Détermine s'il existe un chemin du point de départ au point d'arrivée.
        Méthode "principale" appelant la suivante.
    */
    public boolean rechercheCheminRecursif()
    {
        if (depart.equals(arrivee))
            return true;
        
        tab[depart.getLigne()][depart.getColonne()] = VIDE; /* pour simplifier les traitements*/
        
        boolean res = rechercheCheminRecursif(depart);
        
        tab[depart.getLigne()][depart.getColonne()] = DEPART;
        return res;
    }
    
    /*
        Détermine s'il existe un chemin d'un point donné jusqu'au point d'arrivée.
        Version récursive. "Traçage" du chemin trouvé dans le tableau.
    */
    public boolean rechercheCheminRecursif(Coord point)
    {
        int lig = point.getLigne();
        int col = point.getColonne();
        
        if (tab[lig][col] == ARRIVEE)
            return true;
        
        if (tab[lig][col] != VIDE)
            return false;
        
        tab[lig][col] = PASSAGE;
        
        if (this.rechercheCheminRecursif(new Coord(lig + 1, col)))
            return true;
        
        if (this.rechercheCheminRecursif(new Coord(lig, col + 1)))
            return true;
        
        if (this.rechercheCheminRecursif(new Coord(lig - 1, col)))
            return true;
        
        if (this.rechercheCheminRecursif(new Coord(lig, col - 1)))
            return true;
        
        tab[lig][col] = MARQUE;
        return false;
    } 
    

    /*
        Détermine s'il existe un chemin du point de départ au point d'arrivée.
        Version itérative (avec "backtracking"), exploration "en profondeur d'abord".
        "Traçage" du chemin trouvé dans le tableau.
    */
    public boolean rechercheChemin()
    {
        boolean trouve = depart.equals(arrivee);
        Pile<Coord> pile = new Pile<>();
        pile.empiler(depart);
        
        tab[depart.getLigne()][depart.getColonne()] = VIDE;
        tab[arrivee.getLigne()][arrivee.getColonne()] = VIDE;
        /* pour simplifier les traitements*/
        
        boolean impasse;
        Coord courant;
        int lig, col, nextLig, nextCol;

        while (!pile.estVide() && !trouve)
        {
            courant = pile.sommet();
            lig = courant.getLigne();
            col = courant.getColonne();
            nextLig = lig;
            nextCol = col;
            impasse = false;
            
            if (tab[lig + 1][col] == VIDE)
                nextLig++;
            else 
                if (tab[lig][col + 1] == VIDE)
                    nextCol++;
                else
                    if (tab[lig - 1][col] == VIDE)
                        nextLig--;
                    else
                        if (tab[lig][col - 1] == VIDE)
                            nextCol--;
                        else
                            impasse = true;
            
            if (impasse)
            {
                tab[lig][col] = MARQUE;
                pile.depiler();
            }
            else
            {
                tab[nextLig][nextCol] = PASSAGE;
                Coord nextCase = new Coord(nextLig, nextCol);
                
                if (nextCase.equals(arrivee))
                    trouve = true;
                
                pile.empiler(nextCase);
            }
        }
        
        tab[arrivee.getLigne()][arrivee.getColonne()] = ARRIVEE;
        tab[depart.getLigne()][depart.getColonne()] = DEPART;

        /* if (trouve)  ...   
                                    la pile contient le chemin trouvé,
                                    on peut le récupérer!
         * sinon: pas de chemin possible!
         */
        return trouve;
    }
  
    public void effacerMarques()
    {
        for (int i = 1; i <= nbLignes; i++)
            for (int j = 1; j <= nbColonnes; j++)
                if (tab[i][j] == MARQUE || tab[i][j] == PASSAGE) 
                    tab[i][j] = VIDE;
    }
}

