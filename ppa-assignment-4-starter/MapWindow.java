
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @version 0.1.0
 */
public class MapWindow extends Application {
    private static String[][] boroughs = new String[][]{{"ENFI"}, {"BARN", "HRGY", "WALT"}, {"HRRW", "BREN", "CAMD", "ISLI",
    "HACK", "REDB", "HAVE"}, {"HILL", "EALI", "KENS", "WSTM", "TOWH", "NEWH", "BARK"}, {"HOUN", "HAMM", "WAND", "CITY",
    "GWCH", "BEXL"}, {"RICH", "MERT", "LAMB", "STHW", "LEWS"}, {"KING", "SUTT", "CROY", "BROM"}};

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

        Pane root = SearchPane(0, 0);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    public Pane SearchPane(int lower, int higher){
        Pane tbr = new Pane();
        for(int height = 0; height < gridHeight; height++){
            for(int buttons = 0; buttons < gridWidths[height]; buttons++){
                Button added = new Button(boroughs[height][buttons]);
                added.setLayoutY(height*buttonHeight);
                added.setLayoutX((offset[height]*buttonwidth/2)+buttons*buttonwidth);
                added.setMinSize(buttonwidth, buttonHeight);
                added.setMaxSize(buttonwidth, buttonHeight);
                tbr.getChildren().add(added);
            }
        }
        return tbr;
    }
}
