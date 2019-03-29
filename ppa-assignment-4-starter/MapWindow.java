import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.util.*;
import javafx.scene.shape.Polygon;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * @version 0.1.5
 */
public class MapWindow {

    public static final HashMap<Button, List<AirbnbListing>> buttonToProperties = new HashMap<>();

    private static int[] offset = new int[]{7, 4, 1, 0, 1, 2, 3};

    private List<ButtonArrayDetails> buttonDetails;

    private static int maxButtonSize = 100;
    private static int buttonHeight = maxButtonSize*3/4;
    private Criteria criteria;
    private int lower;
    private int higher;

    public MapWindow(int lower, int higher, Criteria criteria) {
        buttonDetails = new ArrayList<ButtonArrayDetails>();
        this.lower = lower;
        this.higher = higher;
        this.criteria = criteria;
    }

    public void addButtonRow(int offset, String[] names) {
        buttonDetails.add(new ButtonArrayDetails(offset, names));
    }

     public BorderPane fullBoroughWindow(String homeType) {
         BorderPane fullWindow = new BorderPane();
         Pane internal =  SearchPane(lower, higher, homeType);
         Text headerText = new Text();
         headerText.setText("Boroughs of London");
         headerText.setFont(Airbnb.HEADERFONT);
         headerText.setFill(Color.rgb(72, 72, 72));
         GridPane titlePane = new GridPane();
         titlePane.addRow(0, headerText);
         Text totalPropertiesLoaded = new Text("Total properties loaded: " + totalPropertiesLoaded(lower, higher, homeType));
         titlePane.addRow(1, totalPropertiesLoaded);
         fullWindow.setTop(titlePane);
         fullWindow.setCenter(internal);

         ScaleTransition grow = new ScaleTransition();
         grow.setFromX(0);
         grow.setFromY(0);
         grow.setToX(1);
         grow.setToY(1);
         grow.setDuration(Duration.millis(1500));
         grow.setNode(internal);
         PropertyButtonActions.setPropertyButtonActions(criteria);
         grow.play();
         return fullWindow;
     }

    public Pane SearchPane(int lower, int higher, String roomType) {
        Pane tbr = new FlowPane();
        // stands for 'To Be Returned'

        Map<String, List<AirbnbListing>> housesInRange;
        int max = 0;

        if(roomType == null) {
            housesInRange = LondonCSVUtilities.filteredResults(lower, higher);
            max = housesInRange.values().stream().max(Comparator.comparing(v -> v.size())).get().size();
        }
        else {
            housesInRange = LondonCSVUtilities.filteredResults(lower, higher, roomType);
            max = housesInRange.values().stream().max(Comparator.comparing(v -> v.size())).get().size();
        }

        Pane internal = new Pane();
        ((FlowPane) tbr).setAlignment(Pos.CENTER);
        tbr.getChildren().add(internal);
        for (int height = 0; height < buttonDetails.size(); height++) {
            for (int buttons = 0; buttons < buttonDetails.get(height).getRow(); buttons++) {
                String nameOfBorough = LondonCSVUtilities.getNameFromAcronym(buttonDetails.get(height).getString(buttons));
                Button added = new Button(nameOfBorough);
                added.setLayoutY(height * buttonHeight);

                added.setLayoutX((buttonDetails.get(height).getOffset() * maxButtonSize / 2) + buttons * maxButtonSize);
                added.setShape(new Polygon(new double[]{
                        // In form X, Y
                        maxButtonSize / 2, 0,
                        maxButtonSize, maxButtonSize / 4,
                        maxButtonSize, 3 * maxButtonSize / 4,
                        maxButtonSize / 2, maxButtonSize,
                        0, 3 * maxButtonSize / 4,
                        0, maxButtonSize / 4
                }));

                added.setMinSize(maxButtonSize, maxButtonSize);
                added.setMaxSize(maxButtonSize, maxButtonSize);


                added.setId("boroughButton");

                if (housesInRange.get(nameOfBorough).size() == 0) {
                    added.setDisable(true);
                } else if (housesInRange.get(nameOfBorough).size() / max <= 0.25) {
                    added.getStyleClass().add("lowBuildings");
                } else if (housesInRange.get(nameOfBorough).size() / max <= 0.5) {
                    added.getStyleClass().add("midBuildings");

                } else if (housesInRange.get(nameOfBorough).size() / max <= 0.75) {
                    added.getStyleClass().add("highBuildings");
                } else {
                    added.getStyleClass().add("maxBuildings");
                }


                internal.getChildren().add(added);

                buttonToProperties.put(added, housesInRange.get(LondonCSVUtilities.getNameFromAcronym(buttonDetails.get(height).getString(buttons))));
            }
        }
        ScaleTransition grow = new ScaleTransition();
        grow.setFromX(0);
        grow.setFromY(0);
        grow.setToX(1);
        grow.setToY(1);
        grow.setDuration(Duration.millis(1500));
        grow.setNode(internal);
        PropertyButtonActions.setPropertyButtonActions(criteria);
        grow.play();
        return tbr;
    }

    private int totalPropertiesLoaded(int low, int high, String homeType) {
        int totalPropertiesCount = 0;
        Map<String, List<AirbnbListing>> results;
        if(homeType == null) {
            results = LondonCSVUtilities.filteredResults(low, high);
        }
        else {
             results = LondonCSVUtilities.filteredResults(low, high, homeType);
        }
        for (List<AirbnbListing> listings : results.values()) {
            totalPropertiesCount += listings.size();
        }
        return totalPropertiesCount;
    }
}
