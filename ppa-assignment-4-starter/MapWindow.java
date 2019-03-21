
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.*;
import javafx.scene.shape.Polygon;
import javafx.application.Application;
import java.util.ArrayList;

/**
 * @version 0.1.4
 */

public class MapWindow extends Application {

    private static HashMap<Button, LinkedHashSet<AirbnbListing>> buttonToProperties;
    private static int[] offset = new int[]{7, 4, 1, 0, 1, 2, 3};


    private List<ButtonArrayDetails> buttonDetails;

    private static int maxButtonHeight = 120;
    private static int maxButtonwidth = 120;

    private static int buttonHeight = maxButtonHeight*3/4;

    public MapWindow() {
        buttonToProperties = new HashMap<>();
        buttonDetails = new ArrayList<>();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Search Range");


        Pane root = SearchPane(0, 0);

        primaryStage.setScene(new Scene(root,300, 250));
        primaryStage.show();

    }

        public void addButtonRow (int offset, String[] names){
            buttonDetails.add(new ButtonArrayDetails(offset, names));
        }

        public Pane SearchPane (int lower, int higher){
            Pane tbr = new FlowPane();
            Pane internal = new Pane();
            ((FlowPane) tbr).setAlignment(Pos.CENTER);
            tbr.getChildren().add(internal);
            for (int height = 0; height < buttonDetails.size(); height++) {
                for (int buttons = 0; buttons < buttonDetails.get(height).getRow(); buttons++) {
                    Button added = new Button(LondonCSVUtilities.getNameFromAcronym(buttonDetails.get(height).getString(buttons)));
                    added.setLayoutY(height * buttonHeight);

                    added.setLayoutX((buttonDetails.get(height).getOffset() * maxButtonwidth / 2) + buttons * maxButtonwidth);
                    added.setShape(new Polygon(new double[]{
                            // In form X, Y
                            maxButtonwidth/2, 0,
                            maxButtonwidth, buttonHeight/4,
                            maxButtonwidth, 3*buttonHeight/4,
                            maxButtonwidth/2, buttonHeight,
                            0, 3*buttonHeight/4,
                            0, buttonHeight/4
                    }));
                    added.setMinSize(maxButtonwidth, maxButtonHeight);
                    added.setMaxSize(maxButtonwidth, maxButtonHeight);
                    added.wrapTextProperty();

                    internal.getChildren().add(added);
                    buttonToProperties.put(added, LondonCSVUtilities.findPropertiesForUser(added.getText(), lower, higher));
                }
            }
            return tbr;
        }
}
