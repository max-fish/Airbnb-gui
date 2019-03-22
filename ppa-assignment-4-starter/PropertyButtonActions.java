import javafx.scene.control.Button;

import java.awt.event.ActionEvent;
import java.util.Iterator;

public class PropertyButtonActions {

    public void setPropertyButtonActions(){
        Iterator<Button> buttonIterator = MapWindow.buttonToProperties.keySet().iterator();
        while(buttonIterator.hasNext()){
            Button button = buttonIterator.next();
          //  button.setOnAction(this::buttonAction);
        }

    }
    public void buttonAction(ActionEvent event){

    }

}
