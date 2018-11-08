
package calculRecursif;
/**
 * @author Erick Timmerman [Erick.Timmerman@univ-lille.fr]
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Outils d'exploration complète d'un système de fichiers à partir d'un nom de répertoire fourni.
 * Méthode main avec création d'un fichier texte comportant le résultat d'une exploration récursive.
 */
public class ExplorerToComplete
{
    private static PrintWriter out; // pour le fichier texte résultat

    public static void main(String[] args)
    {
        File elt;	// consultez la doc da la classe java.io.File

        String name = "/home/pierre";  // ==> répertoire courant de l'application!
                      // à remplacer par un chemin relatif ou absolu de l'un de vos répertoires
    /*
     *    Pour lancer l'application avec le répertoire à explorer s'il est fourni 
     *    en argument de la ligne de commande:
     */
        if (args.length != 1)
            System.out.println("Lancement possible: java Explorer " + name);
        else
            name = args[0];

        elt = new File(name);
        if (!elt.exists())
        {
            System.err.println("Répertoire " + elt + " invalide!");
            return;     // ==> arrêt de la méthode et donc du programme!
        }
        if (!elt.isDirectory())
        {
            System.err.println(elt + " n'est pas un répertoire!");
            return;     // id.
        }

        try  // elt.getCanonicalPath()), new PrintWriter(), ... obligent!
        {
            System.out.println("Répertoire à explorer:" + elt.getCanonicalPath());
            
            out = new PrintWriter("explore.txt");
            out.println("\t\tExploration récursive de " + elt.getCanonicalPath() + "\n");
            
            explorer(elt);  // lancement de l'exploration
            
            out.println("\nFin de l'exploration!\n");
            out.close();
            System.out.println("\nTerminé, voir le fichier résultat \"explore.txt\" dans: <"
                  + new File ("").getCanonicalPath() + ">");
        } 
        catch (IOException ioe)
        {
            System.err.println(ioe);
        }
    }

    /**
     * Exploration "en profondeur d'abord", version récursive.
     * @param elt objet File à explorer
     * @throws java.io.IOException en cas d'anomalie de tratement du fichier résultat
     */
    public static void explorer(File elt) throws IOException
    {
    	
    }
}
