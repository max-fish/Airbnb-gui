import javafx.animation.ScaleTransition;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PropertyButtonActions {

    public static void setPropertyButtonActions(){
        Iterator<Button> buttonIterator = MapWindow.buttonToProperties.keySet().iterator();
        while(buttonIterator.hasNext()){

            Button button = buttonIterator.next();
            button.setOnAction(
                    (event) -> {
                        PropertyViewer propertyViewer = new PropertyViewer(MapWindow.buttonToProperties.get(button));
                        Tab propertyTab = new Tab();
                        propertyTab.setText("Properties");
                        propertyTab.setContent(propertyViewer.makeFullPropertyWindow());
                        MainViewer.getPanels().getTabs().add(propertyTab);
                        MainViewer.getPanels().getSelectionModel().select(propertyTab);
                        });

            ScaleTransition scaleTransition = new ScaleTransition();
            scaleTransition.setFromX(0);
            scaleTransition.setFromY(0);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.setDuration(Duration.millis(1500));
            scaleTransition.setCycleCount(1);
            scaleTransition.setNode(button);
            scaleTransition.play();
        }

    }

}
