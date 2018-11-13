package Correction.dataStructures;

/**
 *
 * @author Erick Timmerman [Erick.Timmerman@univ-lille.fr]
 */
public class TwoInts
{
    private final int i1, i2;

    public TwoInts(int entier1, int entier2)
    {
        i1 = entier1;
        i2 = entier2;
    }

    public int getfirst()
    {
        return i1;
    }

    public int getSecond()
    {
        return i2;
    }

    @Override
    public String toString()
    {
         return "[" + i1 + "," + i2 + "]";
    }
}
