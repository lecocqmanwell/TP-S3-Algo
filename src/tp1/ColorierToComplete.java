
package tp1;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Erick Timmerman  [Erick.Timmerman@univ-lille.fr] 
 */
public class ColorierToComplete
{
    
    public static void main (String [] args)
    {
       char [][] dessin;
       try  // fichier texte à lire oblige!
       {
            BufferedReader inputFile = new BufferedReader(new FileReader("./src/tp1/dessin.txt"));
            /* 
                <dessin.text> est ici un ficher texte du répertoire courant de l'application
                contenant le "dessin" à colorier.
            */
            
            int nbLignes = 0;                   // compter le nombre de lignes du dessin
            while (inputFile.readLine() != null)
                nbLignes++;
            inputFile.close ();
            
            /*  
                création du tableau de caractères, recopie du contenu du fichier
                dans ce tableau de caractères.
            */
            dessin = new char[nbLignes][];
            int lig = 0;
            
            inputFile = new BufferedReader(new FileReader("dessin.txt")); 
            String ligne = inputFile.readLine();
            while (ligne != null)
            {
                dessin[lig] = ligne.toCharArray(); // cf. doc de la classe String
                lig++;
                ligne = inputFile.readLine();
            }
            inputFile.close ();

            afficher(dessin);           // affichage initial avant coloriage
            
            colorier(dessin, 11, 25);   // coloriage à partir du point (11,25)
            
            afficher(dessin);           // affichage final apès coloriage
       }
       catch (FileNotFoundException fnfe)   // Le fichier de données n'est pas trouvé.
       {
            System.err.println("Erreur fichier non trouvé! " + fnfe);
       }
       catch (IOException ioe)              // Problème de lecture du fichier.
       {    
            System.err.println("Erreur de traitement de fichier! " + ioe);  
       } 
    }
    
    /**
     * affichage du tableau de caractères fourni en paramètre
     */
    private static void afficher(char [][] dessin)
    {
        for (char[] ligne : dessin)
            System.out.println(new String(ligne));
        
        System.out.println ();
    }
    

    /**
     * Méthode de coloriage du dessin à partir d'un point donné
     * Coloriage de l'intérieur du circuit fermé (si un tel circuit existe)
     * si le point fourni est bien intérieur à un circuit fermé.
     * 
     * @param dessin tableau contenant le dessin à colorier
     * @param lig numéro (index) de ligne d'un point initial
     * @param col numéro (index) de colonne de ce point
     */
    public static void colorier(char [][] dessin, int lig, int col)
    {  	
    	if(coordonneesValides(dessin,lig,col) && dessin[lig][col] == '.') {
    		
    		dessin[lig][col] = 'o';
    		
    		colorier(dessin,lig-1,col);
    		
    		colorier(dessin,lig+1,col);
    		
    		colorier(dessin,lig,col+1);
    		
    		colorier(dessin,lig,col-1);
    	}
    }
 
    public static boolean coordonneesValides(char [][] dessin, int lig, int col)
    {
        if (lig < 0 || lig >= dessin.length)
            return false;
        
        return col >= 0 && col < dessin[lig].length;
    }
}



/* run-single:
.....................+++++++....................................................
.....................+......+++++...++++++++++++++..............................
.....................+...........+..+.............+.............................
.....................+...........+...+..+.........+.............................
.....................++...........+++++..++.......+.............................
......................+...............+.+.........+.............................
......................+...............+.+.........+.............................
......................+.................+.+++++++++.............................
......................+++++++++++++++++++.+.....................................
........................................+..++++++++++++++++++++++++++++.........
....................++++++++++++++++++++++.............................+........
....................+..................................................+........
....................++++++++++++++++++++++++++++++++++++++++++++++++++.+........
....................+................................................+.+........
....................+..............................................+...+........
....................++++++++++++++++++++++++++++++++++++++++++++++++++++........
................................................................................
................................................................................

.....................+++++++....................................................
.....................+oooooo+++++...++++++++++++++..............................
.....................+ooooooooooo+..+ooooooooooooo+.............................
.....................+ooooooooooo+...+oo+ooooooooo+.............................
.....................++ooooooooooo+++++oo++ooooooo+.............................
......................+ooooooooooooooo+o+ooooooooo+.............................
......................+ooooooooooooooo+o+ooooooooo+.............................
......................+ooooooooooooooooo+o+++++++++.............................
......................+++++++++++++++++++o+.....................................
........................................+oo++++++++++++++++++++++++++++.........
....................++++++++++++++++++++++ooooooooooooooooooooooooooooo+........
....................+oooooooooooooooooooooooooooooooooooooooooooooooooo+........
....................++++++++++++++++++++++++++++++++++++++++++++++++++o+........
....................+oooooooooooooooooooooooooooooooooooooooooooooooo+o+........
....................+oooooooooooooooooooooooooooooooooooooooooooooo+ooo+........
....................++++++++++++++++++++++++++++++++++++++++++++++++++++........
................................................................................
................................................................................
*/
