
package Correction.calculRecursif;
/**
 *
 * @author Erick Timmerman [Erick.Timmerman@univ-lille.fr]
 */
import Correction.dataStructures.Fifo;
import Correction.dataStructures.Pile;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Outils d'exploration complète d'un système de fichiers à partir d'un nom de 
 * répertoire fourni.
 * 
 * Méthode main avec création d'un fichier texte comportant le résultat:
 *  - d'une exploration récursive,
 *  - d'une exploration itérative utilisant une pile ("en profondeur d'abord"),
 *  - d'une exploration itérative utilisant une file d'attente ("en largeur").
*/
public class Explorer
{
    private static PrintWriter out; // pour le ficheir résultat

    public static void main(String[] args)
    {
        File elt;
        String name = "."; // ==> répertoire courant de l'application!
          // à remplacer par un chemin relatif ou absolu de l'un de vos répertoires
        
    /*
     *    Pour lancer l'application en fournissant le répertoire à explorer
     *    en argument de la ligne de commande lorsqu'il est fourni:
     */
        if (args.length != 1)
            System.out.println("Lancement possible: java Explorer <nom du répertoire à explorer>");
        else
            name = args[0];

        elt = new File(name);
        
        if (!elt.exists())
        {
            System.out.println("Répertoire " + elt + " invalide!");
            return;     // ==> arrêt de la méthode et donc programme!
        }
        if (!elt.isDirectory())
        {
            System.out.println(elt + " n'est pas un répertoire!");
            return;     // id.
        }

        try  // PrintWriter et elt.getCanonicalPath()) obligent!
        {
            System.out.println("Répertoire à explorer:" + elt.getCanonicalPath());
            out = new PrintWriter("explore.txt");
            out.println("\t\tExplorations de " + elt.getCanonicalPath() + "\n");
            
            out.println("\t\tExploration récursive de " + elt.getCanonicalPath() + "\n");
            explorer(elt);
            out.println("\nFin de l'exploration!\n");
            out.println("\t\tExploration récursive (2ème version)de " + elt.getCanonicalPath() + "\n");
            explorerV2(elt);
            out.println("\nFin de l'exploration!\n");
            out.println("\t\tExploration itérative en profondeur de " + elt.getPath() + "\n");
            explorerIteratif(elt);
            out.println("End!\n");
            out.println("\t\tExploration itérative par niveau de " + elt.getAbsolutePath() + "\n");
            explorerParNiveau(elt);
            out.println("End!\n");
            out.close();
        } 


        catch (IOException ioe)
        {
            System.out.println(ioe);
        }
        System.out.println("Terminé, voir le fichier résultat \"explore.txt\" dans \""
                  + new File ("").getAbsolutePath() + "\"");
    }

    /*
     * Exploration "en profondeur d'abord", version récursive.
     */
    public static void explorer(File elt) throws IOException
    {
        // précondition: elt "existe" et est un répertoire

        out.println(elt);
        File[] fils = elt.listFiles();

        for (File fil : fils)
            if (fil.isDirectory())
                explorer(fil);
            else
                out.println(fil);
    }
    
    /*
     * Exploration "en profondeur d'abord", autre version récursive.
     */
    public static void explorerV2(File elt) throws IOException
    {
        // précondition: elt "existe"

        out.println(elt);
        if (elt.isDirectory())
        {
            File[] fils = elt.listFiles();

            for (File fil : fils)
                explorerV2(fil);
        }
    }

    /*
     * Exploration "en profondeur d'abord", version itérative avec une pile.
     */
    public static void explorerIteratif(File elt) throws IOException
    {
        // elt "existe" et est un répertoire

        File[] fils;
        Pile<File> pile = new Pile<>();
        pile.empiler(elt);

        while (!pile.estVide())
        {
            elt = pile.depiler();
            out.println(elt);
            fils = elt.listFiles();

            for (int i = fils.length - 1; i >= 0; i--)
                if (fils[i].isDirectory())
                    pile.empiler(fils[i]);
                else
                    out.println(fils[i]);
        }
    }

    /*
     * Exploration "en largeur d'abord" (par niveau) avec une file d'attente.
     */




    public static void explorerParNiveau(File elt) throws IOException
    {
        // précondition: elt "existe"

        File[] fils;
        Fifo<File> attente = new Fifo<>();
        attente.ajouter(elt);

        while (!attente.estVide())
        {
            elt = attente.sortie();
            out.println(elt);
            if (elt.isDirectory())
            {
                fils = elt.listFiles();
                for (File fil : fils)
                    attente.ajouter(fil);
            }
        }
    }
}
