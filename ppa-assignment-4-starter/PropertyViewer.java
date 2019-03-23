import com.sun.rowset.internal.Row;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import sun.plugin.javascript.navig.Anchor;

import java.util.LinkedHashSet;
import java.util.List;


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
        headerText.setText("Your Properties");
        headerText.setFont(Font.loadFont(getClass().getResourceAsStream("Montserrat/MontserratAlternates-Regular.otf"), 50));
        headerText.setTextFill(Color.rgb(72,72,72));

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
        propertyList.setAlignment(Pos.CENTER);
        propertyList.setHgap(10);
        propertyList.setVgap(20);
        propertyList.setPadding(new Insets(10,10,10,10));
        for(AirbnbListing property : properties){
            propertyList.getChildren().add(makeIcon(property));
        }
        return propertyList;
    }

    private StackPane makeIcon(AirbnbListing property){
        StackPane icon = new StackPane();

        GridPane infoLayout = new GridPane();

        infoLayout.setMinHeight(300);
        infoLayout.setMaxHeight(300);
        infoLayout.setMinWidth(500);
        infoLayout.setMaxWidth(500);
        RowConstraints pictureRow = new RowConstraints();
        pictureRow.setPercentHeight(60);



        infoLayout.getRowConstraints().add(pictureRow);

        infoLayout.setBorder(new Border(new BorderStroke(MainViewer.CORAL,
                BorderStrokeStyle.SOLID, new CornerRadii(18,18,0,0,false), new BorderWidths(2,2,0,2))));
        infoLayout.setStyle("-fx-background-color: #FFFFFF;");

        Text picText = new Text("Picture here");
        Text nameText = new Text(property.getName());
        Text priceText = new Text("Price: £" + property.getPrice());
        Text reviewsText = new Text("# of Reviews: " + property.getNumberOfReviews());
        Text nightsText = new Text("Minimum nights: " + property.getMinimumNights());

        TextFlow picLabelContainer = new TextFlow(picText);
        picLabelContainer.setTextAlignment(TextAlignment.CENTER);
        TextFlow nameLabelContainer = new TextFlow(nameText);
        TextFlow priceLabelContainer = new TextFlow(priceText);
        TextFlow reviewsLabelContainer = new TextFlow(reviewsText);
        TextFlow nightsLabelContainer = new TextFlow(nightsText);

        infoLayout.addRow(0, picLabelContainer);
        infoLayout.addRow(1, nameLabelContainer);
        infoLayout.addRow(2, priceLabelContainer);
        infoLayout.addRow(3, reviewsLabelContainer);
        infoLayout.addRow(4, nightsLabelContainer);

        nameLabelContainer.setBorder(new Border(new BorderStroke(MainViewer.CORAL,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1,0,0,0))));

        Font infoFont = Font.loadFont(getClass().getResourceAsStream("Montserrat/MontserratAlternates-Medium.otf"), 18);
        for(Node node : infoLayout.getChildren()){
            if(node instanceof Pane){
                TextFlow container = (TextFlow) node;
                container.setMinWidth(infoLayout.getMinWidth()-3);
                container.setMaxWidth(infoLayout.getMinWidth()-3);
                container.setPadding(new Insets(5,0,5,0));
                Text label = (Text) container.getChildren().get(0);
                label.setWrappingWidth(infoLayout.getMinWidth()-3);
                label.setFont(infoFont);
                label.setFill(Color.rgb(72,72,72));
            }
        }
        infoLayout.setAlignment(Pos.CENTER);

        StackPane.setAlignment(infoLayout, Pos.TOP_CENTER);




        Rectangle rect = new Rectangle();
        DropShadow ds = new DropShadow();
        ds.setOffsetY(5);
        ds.setColor(Color.LIGHTGREY);

        rect.heightProperty().bind(infoLayout.heightProperty().add(15));
        rect.widthProperty().bind(infoLayout.widthProperty());
        rect.setArcWidth(20);
        rect.setArcHeight(20);
        rect.setFill(MainViewer.CORAL);

        StackPane.setAlignment(infoLayout, Pos.TOP_CENTER);

        icon.maxHeightProperty().bind(rect.heightProperty());
        icon.maxWidthProperty().bind(rect.widthProperty());
        icon.getChildren().add(rect);
        icon.getChildren().add(infoLayout);





        icon.setOnMouseEntered(
                (event) -> {icon.setEffect(ds);}

        );
        icon.setOnMouseExited(
                (event) -> {icon.setEffect(null);}
        );

        return icon;

    }
}
