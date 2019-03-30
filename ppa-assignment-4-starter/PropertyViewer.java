import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.util.List;


/**
 *
 * @version 0.2.0
 */
public class PropertyViewer extends Application {

    private List<AirbnbListing> properties;
    private Criteria criteria;


    public PropertyViewer(List<AirbnbListing> properties, Criteria criteria) {
        this.properties = properties;
        this.criteria = criteria;
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
    }

    public ScrollPane makeFullPropertyWindow(String neighborhoodName) {
        ScrollPane propertyScroll = new ScrollPane(makePropertyWindow(neighborhoodName));
        propertyScroll.setFitToWidth(true);
        propertyScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        propertyScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        return propertyScroll;
    }


    private BorderPane makePropertyWindow(String neighborhoodName) {
        BorderPane fullWindow = new BorderPane();
        AnchorPane header = new AnchorPane();
        Label headerText = new Label();

        PropertyViewerFactory.styleHeaderText(headerText);

        ComboBox<String> sortBy = new ComboBox<>();
        sortBy.setPromptText("Sort by");
        sortBy.getItems().addAll("Price: Low to High", "Price: High to Low", "Reviews", "Host Name");

        sortBy.setOnAction(

                (event) -> {
                    if (sortBy.getValue().equals("Price: Low to High")) {
                        LondonCSVUtilities.sort(properties, LondonCSVUtilities.sortBy.PRICE_LOW_TO_HIGH);
                    } else if (sortBy.getValue().equals("Price: High to Low")) {
                        LondonCSVUtilities.sort(properties, LondonCSVUtilities.sortBy.PRICE_HIGH_TO_LOW);
                    } else if (sortBy.getValue().equals("Reviews")) {
                        LondonCSVUtilities.sort(properties, LondonCSVUtilities.sortBy.REVIEWS);
                    } else if (sortBy.getValue().equals("Host Name")) {
                        LondonCSVUtilities.sort(properties, LondonCSVUtilities.sortBy.HOST_NAME);
                    }
                    fullWindow.setCenter(makePropertyList());
                }
        );

        headerText.setText(getNumberOfProperties() + " homes found in " + neighborhoodName);
        headerText.setFont(Airbnb.HEADERFONT);
        headerText.setTextFill(Color.rgb(72,72,72));
        header.getChildren().addAll(headerText, sortBy);


        AnchorPane.setLeftAnchor(headerText, 20.0);
        AnchorPane.setRightAnchor(sortBy, 10.0);

        fullWindow.setTop(header);
        fullWindow.setCenter(makePropertyList());

        return fullWindow;
    }

    private TilePane makePropertyList() {
        TilePane propertyList = new TilePane();
        PropertyViewerFactory.styleTilePane(propertyList);
        for (AirbnbListing property : properties) {
            propertyList.getChildren().add(new Icon(property, false, criteria).makeIcon());
        }
        return propertyList;
    }

    private int getNumberOfProperties() {
        return properties.size();
    }


    private Pane getImage() {
        FlowPane tbr = new FlowPane();
        return tbr;
    }
}
