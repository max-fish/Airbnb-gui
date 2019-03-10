
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @version 0.1.2
 */
public class MapWindow {
    private List<ButtonArrayDetails> buttonDetails;

    private static int buttonHeight = 35;
    private static int buttonwidth = 150;

    public MapWindow(){
        buttonDetails = new ArrayList<>();
    }

    public void addButtonRow(int offset, String[] names){
        buttonDetails.add(new ButtonArrayDetails(offset, names));
    }

    public Pane SearchPane(int lower, int higher){
        Pane tbr = new FlowPane();
        Pane internal = new Pane();
        ((FlowPane) tbr).setAlignment(Pos.CENTER);
        tbr.getChildren().add(internal);
        for(int height = 0; height < buttonDetails.size(); height++) {
            for (int buttons = 0; buttons < buttonDetails.get(height).getRow(); buttons++) {
                Button added = new Button(LondonCSVUtilities.getNameFromAcronym(buttonDetails.get(height).getString(buttons)));
                added.setLayoutY(height * buttonHeight);
                added.setLayoutX((buttonDetails.get(height).getOffset() * buttonwidth / 2) + buttons * buttonwidth);
                added.setMinSize(buttonwidth,   buttonHeight);
                internal.getChildren().add(added);
            }
        }
        return tbr;
    }
}
