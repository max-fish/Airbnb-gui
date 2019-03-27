import com.sun.xml.internal.ws.api.FeatureListValidatorAnnotation;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;
import java.util.List;


/**
 *
 * @version 0.2.0
 */
public class PropertyViewer extends Application {

    private List<AirbnbListing> properties;


    public PropertyViewer(List<AirbnbListing> properties){
        this.properties = properties;
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
    }

    public ScrollPane makeFullPropertyWindow(){
        ScrollPane propertyScroll = new ScrollPane(makePropertyWindow());
        propertyScroll.setFitToWidth(true);
        propertyScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        propertyScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        return propertyScroll;
    }


    private BorderPane makePropertyWindow(){
        BorderPane fullWindow = new BorderPane();
        AnchorPane header = new AnchorPane();
        Label headerText = new Label();

        PropertyViewerFactory.styleHeaderText(headerText);

        ComboBox<String> sortBy = new ComboBox<>();

        sortBy.getItems().addAll("Price", "Reviews", "Host Name");

        sortBy.setOnAction(

                (event) -> {
                    if(sortBy.getValue().equals("Price")){
                        LondonCSVUtilities.sort(properties, LondonCSVUtilities.sortBy.PRICE);
                    }

                   else if(sortBy.getValue().equals("Reviews")){
                        LondonCSVUtilities.sort(properties, LondonCSVUtilities.sortBy.REVIEWS);
                    }

                   else{
                        LondonCSVUtilities.sort(properties, LondonCSVUtilities.sortBy.HOST_NAME);
                    }
                    fullWindow.setCenter(makePropertyList());
                }
        );

        header.getChildren().addAll(headerText, sortBy);


        AnchorPane.setLeftAnchor(headerText, 0.0);
        AnchorPane.setRightAnchor(sortBy, 10.0);

        fullWindow.setTop(header);
        fullWindow.setCenter(makePropertyList());

        return fullWindow;
    }

    private TilePane makePropertyList(){
        TilePane propertyList = new TilePane();
        PropertyViewerFactory.styleTilePane(propertyList);
        for(AirbnbListing property : properties){
            propertyList.getChildren().add(makeIcon(property));
        }
        return propertyList;
    }

    private StackPane makeIcon(AirbnbListing property){
        StackPane icon = new StackPane();

        GridPane infoLayout = new GridPane();

        PropertyViewerFactory.styleInfoGrid(infoLayout);

        Text priceText = new Text("Price: £" + property.getPrice());
        Text reviewsText = new Text("# of Reviews: " + property.getNumberOfReviews());
        Text nightsText = new Text("Minimum nights: " + property.getMinimumNights());

        Pane internetMapDiaplay = new GetGoogleMaps().getMapPane(property.getName(), property.getLatitude(), property.getLongitude());
        TextFlow priceLabelContainer = new TextFlow(priceText);
        TextFlow reviewsLabelContainer = new TextFlow(reviewsText);
        TextFlow nightsLabelContainer = new TextFlow(nightsText);

        addRowsToGridpane(
            infoLayout,
            new Pane[]{
                    internetMapDiaplay,
                    priceLabelContainer,
                    reviewsLabelContainer,
                    nightsLabelContainer
            }
        );

        internetMapDiaplay.setBorder(new Border(new BorderStroke(MainViewer.CORAL,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1,0,1,0))));

        Font infoFont = Font.loadFont(getClass().getResourceAsStream("Montserrat/MontserratAlternates-Medium.otf"), 18);
        for(Node node : infoLayout.getChildren()){
            if(node instanceof TextFlow){
                TextFlow container = (TextFlow) node;
                container.setMinWidth(infoLayout.getMaxWidth()-3);
                container.setMaxWidth(infoLayout.getMaxWidth()-3);
                if(container.getChildren().get(0) instanceof Text){
                    Text label = (Text) container.getChildren().get(0);
                    label.setWrappingWidth(infoLayout.getWidth()-3);
                    label.setFont(infoFont);
                    label.setFill(Color.rgb(72,72,72));
                }
            }
        }
        PropertyViewerFactory.styleNameLabelContainer(nameLabelContainer);


        PropertyViewerFactory.styleGridContent(infoLayout);
      
        Rectangle rect = new Rectangle();

        PropertyViewerFactory.styleRectangle(rect, infoLayout);

        PropertyViewerFactory.styleStackPane(infoLayout, rect);

        ImageView favouriteIcon = new ImageView(new Image(getClass().getResourceAsStream("favourite_icon.png")));

        PropertyViewerFactory.styleFavouriteIcon(favouriteIcon);
      
        icon.getChildren().add(rect);
        icon.getChildren().add(infoLayout);
        icon.getChildren().add(favouriteIcon);


        PropertyViewerFactory.styleIcon(icon, infoLayout);


        icon.setOnMouseClicked(
                (event) -> {
                    PropertyDescription propertyDescription = new PropertyDescription(property, makeIcon(property));
                    Tab propertyDescriptionTab = new Tab();
                    propertyDescriptionTab.setText("Property");
                    propertyDescriptionTab.setContent(propertyDescription.makeDescriptionWindow());
                    MainViewer.getPanels().getTabs().add(propertyDescriptionTab);
                    MainViewer.getPanels().getSelectionModel().select(propertyDescriptionTab);
                }

        );

        return icon;
    }

    private void addRowsToGridpane(GridPane toBeAddedTo, Pane[] panes){
        for(int x = 0; x < panes.length; x++){
            toBeAddedTo.addRow(x, panes[x]);
        }
    }

    private Pane getImage(){
        FlowPane tbr = new FlowPane();

        return tbr;
    }
}
