
package tp3;

/**
 *
 * @author Erick Timmerman  (Erick.Timmerman@univ-lille.fr)
 */
public class Coord
{
    private final int lig;
    private final int col;

    public Coord(int ligne, int colonne)
    {
        lig = ligne;
        col = colonne;
    }

    public int getLigne()
    {
        return lig;
    }

    public int getColonne()
    {
        return col;
    }

    @Override
    public String toString()
    {
        return "(" + lig + "," + col + ")";
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof Coord))
            return false;
        Coord point = (Coord) obj;
        return      this.lig == point.lig
                &&  this.col == point.col;
    }
}
