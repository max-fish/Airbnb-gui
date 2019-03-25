
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.animation.ScaleTransition;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.*;
import javafx.scene.shape.Polygon;
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

    public MapWindow() {
        buttonDetails = new ArrayList<ButtonArrayDetails>();
    }

    public void addButtonRow (int offset, String[] names){
        buttonDetails.add(new ButtonArrayDetails(offset, names));
    }




     public BorderPane fullBoroughWindow(int lower, int higher){
            BorderPane fullWindow = new BorderPane();

            Text headerText = new Text();
            headerText.setText("Boroughs of London");
            headerText.setFont(Font.loadFont(getClass().getResourceAsStream("Montserrat/MontserratAlternates-Regular.otf"), 50));
            headerText.setFill(Color.rgb(72,72,72));

            fullWindow.setTop(headerText);
            fullWindow.setCenter(SearchPane(lower,higher));
            return fullWindow;
        }

    public Pane SearchPane (int lower, int higher){
        Pane tbr = new FlowPane();
        // stands for 'To Be Returned'

        Map<String, List<AirbnbListing>> housesInRange = LondonCSVUtilities.filteredResults(lower, higher);
        int max = housesInRange.values().stream().max(Comparator.comparing(v -> v.size())).get().size();

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
                        maxButtonSize/2, 0,
                        maxButtonSize, maxButtonSize/4,
                        maxButtonSize, 3*maxButtonSize/4,
                        maxButtonSize/2, maxButtonSize,
                        0, 3*maxButtonSize/4,
                        0, maxButtonSize/4
                }));
              
                added.setMinSize(maxButtonSize, maxButtonSize);
                added.setMaxSize(maxButtonSize, maxButtonSize);


                added.setId("boroughButton");

                if(housesInRange.get(nameOfBorough).size() == 0){
                    added.setDisable(true);
                }
                else if(housesInRange.get(nameOfBorough).size()/max <= 0.25){
                    added.getStyleClass().add("lowBuildings");
                }
                else if(housesInRange.get(nameOfBorough).size()/max <= 0.5){
                    added.getStyleClass().add("midBuildings");

                }
                else if(housesInRange.get(nameOfBorough).size()/max <= 0.75){
                    added.getStyleClass().add("highBuildings");
                }
                else{
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
        PropertyButtonActions.setPropertyButtonActions();
        grow.play();
        return tbr;
    }
}
