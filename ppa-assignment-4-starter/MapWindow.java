
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import java.util.*;
import javafx.scene.shape.Polygon;
import java.util.ArrayList;

/**
 * @version 0.1.5
 */

public class MapWindow {

    private List<ButtonArrayDetails> buttonDetails;

    private static int maxButtonSize = 100;
    private static int buttonHeight = maxButtonSize*3/4;

    public MapWindow() {
        buttonDetails = new ArrayList<ButtonArrayDetails>();
    }

    public void addButtonRow (int offset, String[] names){
        buttonDetails.add(new ButtonArrayDetails(offset, names));
    }

    public Pane SearchPane (int lower, int higher){
        Pane tbr = new FlowPane();
        // stands for 'To Be Returned'

        Map<String, List<AirbnbListing>> housesInRange = LondonCSVUtilities.filteredResults(lower, higher);

        Pane internal = new Pane();
        ((FlowPane) tbr).setAlignment(Pos.CENTER);
        tbr.getChildren().add(internal);
        for (int height = 0; height < buttonDetails.size(); height++) {
            for (int buttons = 0; buttons < buttonDetails.get(height).getRow(); buttons++) {
                Button added = new Button(LondonCSVUtilities.getNameFromAcronym(buttonDetails.get(height).getString(buttons)));
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

                System.out.println(housesInRange.get(LondonCSVUtilities.getNameFromAcronym(buttonDetails.get(height).getString(buttons))).size());

                if(housesInRange.get(LondonCSVUtilities.getNameFromAcronym(buttonDetails.get(height).getString(buttons))).size() == 0){
                    added.setDisable(true);
                }

                internal.getChildren().add(added);
            }
        }
        return tbr;
    }
}
