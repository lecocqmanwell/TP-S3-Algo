
package Correction.calculRecursif;

import Correction.dataStructures.Pile;
import Correction.dataStructures.TwoInts;

/**
 * 
 * @author Erick Timmerman [Erick.Timmerman@univ-lille.fr]
 * @param <E> type générique "Comparable"
 */
public class QuickSort<E extends Comparable<E>>
{
    private E [] table;
    
    public QuickSort(E[] vecteur)
    {
        table = vecteur;
    }

    public void quickSort()
    {
        quickSort (0, table.length -1);
    }

    private void quickSort(int deb, int fin)
    {
        if (deb >= fin) return;
        
        int pivot = calculPivot(deb,fin);
        quickSort(deb, pivot-1);
        quickSort(pivot + 1, fin);
    }
    
    private int	calculPivot(int deb, int fin)
    {
        E val	= table[deb];
        int gauche = deb + 1;
        int droit = fin;
        
        while (gauche <= droit)
        {
	// çà:
            while (gauche <= droit && table[gauche].compareTo(val) <=  0)
                    gauche ++;
            while (gauche <= droit && table[droit].compareTo(val) >= 0)
                    droit--;
            if (gauche < droit)
            {
                permuter(gauche, droit);
                gauche++;	droit--;
            }   
            
    	/* ou:	if (table[gauche].compareTo(val) <=  0)   gauche ++;
                else	
			if (table[droit].compareTo(val) >= 0)    droit--;
                    	else {
                        	permuter(gauche, droit);
                        	gauche++; droit--;
                    	}
     	*/
        }
        permuter(deb, droit);
        return droit;
    }

    
    private void permuter(int i1, int i2)
    {
        E locale  = table[i1];
        table[i1] = table[i2];
        table[i2] = locale;	
        // System.out.println(this); // pour visualiser chaque permutation
    }
    
    
    @Override
    public String toString()
    {
        int last = table.length - 1;
        if (last < 0)
            return "[]";
        
        StringBuilder  result = new StringBuilder("[");
        for (int i = 0; i < last; i++)
            result.append(table[i]).append(", ");
        
        result.append(table[last]).append("]\n");
        return result.toString();
    }
    
    public void iterativeQuickSort()
    {
        int pivot, deb, fin;
        Pile<TwoInts> pile = new Pile<>();
        
        TwoInts intervalle = new TwoInts(0, table.length - 1);
        pile.empiler(intervalle);
        
        while(!pile.estVide())
        {
            intervalle = pile.depiler();
            deb = intervalle.getfirst();
            fin = intervalle.getSecond();
            
            pivot = calculPivot(deb, fin);
            
            if (deb < pivot - 1)
                pile.empiler(new TwoInts(deb, pivot - 1));
            if (pivot + 1 < fin)
                pile.empiler(new TwoInts(pivot + 1, fin));
        }
    }
    
    
    public static void main(String []args) // pour essayer!
    {
        String [] vect = {"zzz","ttt","ccc","lll","aaa","ppp","ddd","kkk",
                          "bbb","yyy","eee","xxx","www","vvv","fff","hhh",
                          "uuu","sss","ggg","iii","jjj","rrr","mmm","ooo",
                          "qqq","nnn"
                         };

        QuickSort<String> sort = new QuickSort<>(vect);
        
        System.out.println(sort);

        sort.quickSort();

        System.out.println(sort);
    }
 }
