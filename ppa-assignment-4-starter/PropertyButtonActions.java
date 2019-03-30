import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.util.Duration;
import java.util.Iterator;

public class PropertyButtonActions {

    public static void setPropertyButtonActions(Criteria criteria){
        Iterator<Button> buttonIterator = MapWindow.getButtonToProperties().keySet().iterator();
        while(buttonIterator.hasNext()){
            Button button = buttonIterator.next();
            Criteria newCriteria = new Criteria(criteria.getNeighborhood(), criteria.getRoomType(), criteria.getLowPrice(), criteria.getHighPrice(), button);
            button.setOnAction(
                    (event) -> {
                        PropertyViewer propertyViewer = new PropertyViewer(MapWindow.buttonToProperties.get(button), newCriteria);
                        TabCreator.createTab(propertyViewer, propertyViewer.makeFullPropertyWindow(button.getText()), "Properties", Airbnb.PROPERTYGRAPHIC, true, newCriteria);
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
