
import com.sun.corba.se.spi.activation.ServerAlreadyRegisteredHelper;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.*;
import javafx.scene.shape.Polygon;
import javafx.application.Application;
import java.util.ArrayList;

/**
 * @version 0.1.3
 */

public class MapWindow extends Application {
    private static String[][] boroughs = new String[][]{{"ENFI"}, {"BARN", "HRGY", "WALT"}, {"HRRW", "BREN", "CAMD", "ISLI",
            "HACK", "REDB", "HAVE"}, {"HILL", "EALI", "KENS", "WSTM", "TOWH", "NEWH", "BARK"}, {"HOUN", "HAMM", "WAND", "CITY",
            "GWCH", "BEXL"}, {"RICH", "MERT", "LAMB", "STHW", "LEWS"}, {"KING", "SUTT", "CROY", "BROM"}};

    public static final HashMap<Button, LinkedHashSet<AirbnbListing>> buttonToProperties = new HashMap<>();

    private static int gridHeight = 7;
    private static int[] gridWidths = new int[]{1, 3, 7, 7, 6, 5, 4};
    private static int[] offset = new int[]{7, 4, 1, 0, 1, 2, 3};


    private List<ButtonArrayDetails> buttonDetails;

    private static int buttonHeight = 35;
    private static int buttonwidth = 150;

    public MapWindow() {
        buttonDetails = new ArrayList<>();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Search Range");


        Pane root = SearchPane(0, 0);

        primaryStage.setScene(new Scene(root,300, 250));
        primaryStage.show();

    }

        public static HashMap getButtonToProperties(){
        return buttonToProperties;
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
            Pane internal = new Pane();
            ((FlowPane) tbr).setAlignment(Pos.CENTER);
            tbr.getChildren().add(internal);
            for (int height = 0; height < buttonDetails.size(); height++) {
                for (int buttons = 0; buttons < buttonDetails.get(height).getRow(); buttons++) {
                    Button added = new Button(LondonCSVUtilities.getNameFromAcronym(buttonDetails.get(height).getString(buttons)));
                    added.setLayoutY(height * buttonHeight);
                    added.setLayoutX((offset[height] * buttonwidth / 2) + buttons * buttonwidth);
                    added.setMinSize(buttonwidth, buttonHeight);

                    added.setLayoutX((buttonDetails.get(height).getOffset() * buttonwidth / 2) + buttons * buttonwidth);
                    added.setMinSize(buttonwidth, buttonHeight);
                    internal.getChildren().add(added);
                    buttonToProperties.put(added, LondonCSVUtilities.findPropertiesForUser(added.getText(), lower, higher));
                }
            }
            //for testing
            /*for (Button button: buttonToProperties.keySet()){
                String key = button.toString();
                int value = buttonToProperties.get(button).size();
                System.out.println(key + " " + value);
            }*/
            return tbr;
        }
}
