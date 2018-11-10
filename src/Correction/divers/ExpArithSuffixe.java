
package Correction.divers;
import Correction.dataStructures.Pile;

/**
 * @author Erick Timmerman  (Erick.Timmerman@univ-lille.fr)
 */
public class ExpArithSuffixe
{
    
    /**
     * Transformation d'une expression infixe complètement parenthésée (supposée correcte)
     * en l'expression suffixe correspondante.
     * 
     * @param ecp expression complètement parenthésée
     * @return expression suffixe
     */
    public static String suffixeFromEcp(String ecp)
    {
        /* L'expression (infixe) complètement parenthésée est supposée valide,
           aucun contrôle n'est effectué ici (pour simplifier)!
         */
        Pile<Character> pile = new Pile<>();
        String result = "";
        char courant;
        int ecpLength = ecp.length();
        
        for (int index = 0; index < ecpLength; index ++)
        {
            courant = ecp.charAt(index);
            if (estUneVariable(courant))
                result = result + courant;
            else
                if (estUnOperateur(courant))
                    pile.empiler(courant);
                else
                    if (courant == ')')
                        result = result + pile.depiler();
        }
        return result;
    }

    /**
     * Evaluation d'une expression suffixe (supposée correcte).
     * 
     * @param expSuffixe expression suffixe
     * @return valeur de l'expression 
     */
    public static int valueOfExpSuffixe(String expSuffixe)
    {
        /* L'expression suffixe (postfixée) est supposée valide,
           aucun contrôle n'est effectué ici (pour simplifier)!
           Algorithme basé sur un simple parcours séquentiel de l'expression suffixe.
         */
        char courant;
        int opde1, opde2, suffixeLength = expSuffixe.length();
        Pile<Integer> pile = new Pile<>();
        
        for (int index = 0; index < suffixeLength; index ++)
        {
            courant = expSuffixe.charAt(index);
            if (estUneVariable(courant))
                pile.empiler(valeur(courant));
            else
            {
                opde2 = pile.depiler();
                opde1 = pile.depiler();
                pile.empiler(resultatDe(opde1, courant, opde2));
            }
        }
        
        return pile.depiler();
    }
    
    
    /**
     * Transformation d'une expression infixe (supposée corecte)
     * en l'expression suffixe correspondante.
     * 
     * @param expInfixe expression infixe
     * @return expression suffixe
     */
    public static String suffixeFromInfixe(String expInfixe)
    {
        /* L'expression infixe est supposée valide,
           aucun contrôle n'est effectué ici (pour simplifier)! 
        
           Algorithme basé sur un simple parcours séquentiel de l'expression infixe.
        */
        Pile<Character> pile = new Pile<>();
        String result = "";
        char courant;
        int expLength = expInfixe.length();
        for (int index = 0; index < expLength; index ++)
        {
            courant = expInfixe.charAt(index);
            if (estUneVariable(courant))
                result = result + courant;
            else
                if (estUnOperateur(courant))
                {   
                    while (!pile.estVide()
                            && priorite(courant)<= priorite(pile.sommet())
                          )
                        result = result + pile.depiler();
                    pile.empiler(courant); 
                }
                else
                    if (courant == '(')
                        pile.empiler(courant);
                    else
                        if (courant == ')')
                        {
                            while (pile.sommet() != '(')
                                result = result + pile.depiler();
                            pile.depiler(); // enlever la '(' de la pile
                        }
        }
        
        while (!pile.estVide())
            result = result + pile.depiler();
        
        return result; 
    }
    
    public static boolean estUneVariable(char car)
    {
        return car >= 'a' && car <='z';
    }

    public static boolean estUnOperateur(char car)
    {
        return car == '+' || car == '-' || car == '*' || car == '/' || car == '%';
    }

    public static int priorite(char operateur)
    {
        switch (operateur)
        {
            case '(': return 0;
            case '+':
            case '-': return 1;
            case '*':
            case '/':
            case '%': return 2;
            default : return -1;
        }
    }


    public static int valeur(char variable)
    {
        return variable - 'a' + 1;  // pour tester l'évaluation (a=1, b=2, ...)!
    }
   
    public static int resultatDe(int operande1, char operateur, int operande2)
    {
        switch (operateur)
        {
            case '+':   return operande1 + operande2;
            case '-':   return operande1 - operande2;
            case '*':   return operande1 * operande2;
            case '/':   return operande1 / operande2;
            case '%':   return operande1 % operande2;
            default :   return 0;
        }
    }
   
    

    public static void main(String [] args)
    {
        String infixe = "a + b*c - d";
        System.out.println("Infixe:    \"" + infixe + "\"");
        String suffixe = suffixeFromInfixe(infixe);
        System.out.println("suffixe:   \"" +  suffixe + "\"");
        System.out.println("Evaluation: " +  valueOfExpSuffixe(suffixe));
        
        infixe = "a *(b+c)/d - e + f*g - h + (i + j*(k-l)/m) * n";
        System.out.println("Infixe:    \"" + infixe + "\"");
        suffixe = suffixeFromInfixe(infixe);
        System.out.println("suffixe:   \"" +  suffixe + "\"");
        System.out.println("Evaluation: " +  valueOfExpSuffixe(suffixe));
    }
}
