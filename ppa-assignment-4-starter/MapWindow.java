
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.ArrayList;
import javafx.scene.shape.Polygon;

/**
 *
 * @version 0.1.0
 */
public class MapWindow extends Application {
    private static String[][] boroughs = new String[][]{{"ENFI"}, {"BARN", "HRGY", "WALT"}, {"HRRW", "BREN", "CAMD", "ISLI",
    "HACK", "REDB", "HAVE"}, {"HILL", "EALI", "KENS", "WSTM", "TOWH", "NEWH", "BARK"}, {"HOUN", "HAMM", "WAND", "CITY",
    "GWCH", "BEXL"}, {"RICH", "MERT", "LAMB", "STHW", "LEWS"}, {"KING", "SUTT", "CROY", "BROM"}};

    private static HashMap<String, String> buttonTitleToBorough = new HashMap<>();

    private static HashMap<Button, ArrayList<AirbnbListing>> buttonToProperties = new HashMap<>();

    private static int gridHeight = 7;
    private static int[] gridWidths = new int[]{1, 3, 7, 7, 6, 5, 4};
    private static int[] offset = new int[]{7, 4, 1, 0, 1, 2, 3};

    private static int buttonHeight = 25;
    private static int buttonwidth = 75;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Search Range");

        createAssociations();

        Pane root = SearchPane(0, 0);

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    public Pane SearchPane(int lower, int higher){
        Pane tbr = new FlowPane();
        Pane internal = new Pane();
        ((FlowPane) tbr).setAlignment(Pos.CENTER);
        tbr.getChildren().add(internal);
        for(int height = 0; height < gridHeight; height++) {
            for (int buttons = 0; buttons < gridWidths[height]; buttons++) {
                Button added = new Button(boroughs[height][buttons]);
                added.setLayoutY(height * buttonHeight);
                added.setLayoutX((offset[height] * buttonwidth / 2) + buttons * buttonwidth);
                added.setMinSize(buttonwidth, buttonHeight);
                internal.getChildren().add(added);
                //buttonToProperties.put(added, STATS.findPropertiesInBorough(buttonTitleToBorough.get(added.getText())), lower, higher);
            }
        }
        return tbr;
    }

    /**
     * Translates the abbreviation from the buttons to the actual names of the boroughs
     */
    private void createAssociations(){
        buttonTitleToBorough.put("ENFI", "Enfield");
        buttonTitleToBorough.put("BARN", "Barnet");
        buttonTitleToBorough.put("HRGY", "Haringey");
        buttonTitleToBorough.put("WALT", "Waltham forest");
        buttonTitleToBorough.put("HRRW", "Harrow");
        buttonTitleToBorough.put("BREN", "Brent");
        buttonTitleToBorough.put("CAMD", "Camden");
        buttonTitleToBorough.put("ISLI", "Islington");
        buttonTitleToBorough.put("HACK", "Hackney");
        buttonTitleToBorough.put("REDB", "Redbridge");
        buttonTitleToBorough.put("HAVE", "Havering");
        buttonTitleToBorough.put("HILL", "Hillington");
        buttonTitleToBorough.put("EALI", "Ealing");
        buttonTitleToBorough.put("KENS", "Kensington and Chelsea");
        buttonTitleToBorough.put("WSTM", "Westminster");
        buttonTitleToBorough.put("TOWH", "Tower Hamlets");
        buttonTitleToBorough.put("NEWH", "Newham");
        buttonTitleToBorough.put("Bark", "Barking and Dagenham");
        buttonTitleToBorough.put("HOUN", "Hounslow");
        buttonTitleToBorough.put("HAMM", "Hammersmith and Fulham");
        buttonTitleToBorough.put("WAND", "Wandsworth");
        buttonTitleToBorough.put("CITY", "City of London");//not certain about this one
        buttonTitleToBorough.put("GWCH", "Greenwich");
        buttonTitleToBorough.put("BEXL", "Bexley");
        buttonTitleToBorough.put("RICH", "Richmond upon Thames");
        buttonTitleToBorough.put("MERT", "Merton");
        buttonTitleToBorough.put("LAMB", "Lambeth");
        buttonTitleToBorough.put("STHW", "Southwork");
        buttonTitleToBorough.put("LEWS", "Lewisham");
        buttonTitleToBorough.put("KING", "Kingston upon Thames");
        buttonTitleToBorough.put("SUTT", "Sutton");
        buttonTitleToBorough.put("CROY", "Croydon");
        buttonTitleToBorough.put("BROM", "Bromley");

    }
}
