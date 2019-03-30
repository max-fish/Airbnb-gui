
/**
 * Creates a MapWindow when called. This is a panel with a set of hexagonal buttons which are coloured according to
 * what percentage the province has in relation to the rest of the other boroughs.
 *
 * @version  0.1.2
 */
public class MapFactory {

    // A 2d list of strigns that stores all the corresponding borough names.
    private static String[][] boroughs =  {{"ENFI"}, {"BARN", "HRGY", "WALT"}, {"HRRW", "BREN", "CAMD", "ISLI",
            "HACK", "REDB", "HAVE"}, {"HILL", "EALI", "KENS", "WSTM", "TOWH", "NEWH", "BARK"}, {"HOUN", "HAMM", "WAND", "CITY",
            "GWCH", "BEXL"}, {"RICH", "MERT", "LAMB", "STHW", "LEWS"}, {"KING", "SUTT", "CROY", "BROM"}};

    // Stores the offset of each row of buttons, define where the row starts
    private static int[] offset = {7, 4, 1, 0, 1, 2, 3};

    /**
     * A method designed to return a new map window.
     * @param lower The low price inputted by the user in their search criteria.
     * @param upper The high price inputted by the user in their search criteria.
     * @param criteria The criteria inputted by the user in their search criteria.
     * @return
     */
    public static MapWindow getMapWindow(int lower, int upper, Criteria criteria){
        // creates a new mapwindow and creates all the buttons
        MapWindow mapWin = new MapWindow(lower, upper, criteria);
        for(int x = 0; x < boroughs.length; x++){
            // adds a rtow of buttons
            mapWin.addButtonRow(offset[x], boroughs[x]);
        }
        return mapWin;
    }
}
