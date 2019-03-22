import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Map;

/**
 *
 * @version  0.1.1
 */
public class MapFactory {
    private static String[][] boroughs = new String[][]{{"ENFI"}, {"BARN", "HRGY", "WALT"}, {"HRRW", "BREN", "CAMD", "ISLI",
            "HACK", "REDB", "HAVE"}, {"HILL", "EALI", "KENS", "WSTM", "TOWH", "NEWH", "BARK"}, {"HOUN", "HAMM", "WAND", "CITY",
            "GWCH", "BEXL"}, {"RICH", "MERT", "LAMB", "STHW", "LEWS"}, {"KING", "SUTT", "CROY", "BROM"}};

    private static int[] offset = new int[]{7, 4, 1, 0, 1, 2, 3};

    private static Map<String, List<AirbnbListing>> boroughsToListings;

    private static MapWindow mapWin;

    public static Pane getMapWindow(int lower, int upper){
        if(mapWin == null){
            mapWin = new MapWindow();
            for(int x = 0; x < boroughs.length; x++){
                mapWin.addButtonRow(offset[x], boroughs[x]);
            }
        }
        return mapWin.fullBoroughWindow(lower, upper);
    }
}
