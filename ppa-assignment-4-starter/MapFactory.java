import javafx.scene.layout.Pane;

public class MapFactory {
    private static String[][] boroughs = new String[][]{{"ENFI"}, {"BARN", "HRGY", "WALT"}, {"HRRW", "BREN", "CAMD", "ISLI",
            "HACK", "REDB", "HAVE"}, {"HILL", "EALI", "KENS", "WSTM", "TOWH", "NEWH", "BARK"}, {"HOUN", "HAMM", "WAND", "CITY",
            "GWCH", "BEXL"}, {"RICH", "MERT", "LAMB", "STHW", "LEWS"}, {"KING", "SUTT", "CROY", "BROM"}};

    private static int[] gridWidths = new int[]{1, 3, 7, 7, 6, 5, 4};
    private static int[] offset = new int[]{7, 4, 1, 0, 1, 2, 3};

    private static MapWindow mapWin;

    public static Pane getMapWindow(int lower, int upper){
        if(mapWin == null){
            mapWin = new MapWindow();
            for(int x = 0; x < boroughs.length; x++){
                mapWin.addButtonRow(offset[x], boroughs[x]);
            }
        }
        return mapWin.SearchPane(lower, upper);
    }

}
