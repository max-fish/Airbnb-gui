import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.List;

public class PropertyViewer extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

    }

    public static void makePropertyList(List<AirbnbListing> properties){
        FlowPane propertyList = new FlowPane();
        for(AirbnbListing property : properties){
            propertyList.getChildren().add(makeIcon(property));
        }
    }

    public static StackPane makeIcon(AirbnbListing property){
        StackPane icon = new StackPane();
        Label label = new Label(property.getName() + property.getPrice() + property.getAvailability365() + property.getLastReview());
        icon.getChildren().addAll(new Rectangle(), label);
        return icon;

    }
}
