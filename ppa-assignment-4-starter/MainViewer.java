

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Rectangle;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 *
 * @version 0.1.0
 */
public class MainViewer extends Application
{

    private int userLowPrice;
    private int userHighPrice;
    BorderPane pane;

    ComboBox<Integer> lowPrice = new ComboBox<Integer>();

    ComboBox<Integer> highPrice = new ComboBox<Integer>();

    @Override
    public void start(Stage stage) throws Exception
    {


        // Create a new border pane
        pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setPrefSize(800,600);

        Rectangle welcome = new Rectangle();



        welcome.widthProperty().bind(pane.widthProperty());


        //welcome.setFill(Color.rgb(189,189,189));
        //welcome.setFill(Color.rgb(253,92,99));


        Text welcomeText = new Text();

        welcomeText.setText("Welcome to Airbnb");
        welcomeText.setFill(Color.WHITE);

        welcomeText.setFont(Font.loadFont(getClass().getResourceAsStream("Raleway/Raleway-Regular.ttf"), 50));

        Image airbnbLogo = new Image(getClass().getResourceAsStream("AirBNB logo.png"));


        StackPane welcomePane = new StackPane();

        ImageView airbnbLogoView = new ImageView(airbnbLogo);
        airbnbLogoView.setFitHeight(150);
        airbnbLogoView.setFitWidth(150);
        airbnbLogoView.setPreserveRatio(true);

        welcomePane.getChildren().addAll(airbnbLogoView, welcomeText);
        welcomePane.setMargin(airbnbLogoView, new Insets(-150,0,0,0));
       //pane.setCenter(welcome);

       pane.setCenter(welcomePane);

        pane.getCenter().setStyle("-fx-background-color: #FD5C63;");


        HBox selection = new HBox();


        FlowPane pricePanel = new FlowPane();


        Label lowPriceLabel = new Label("Low Price: ");

        Label highPriceLabel = new Label("High Price: ");

        lowPrice.getItems().addAll(100,200,300);

        highPrice.getItems().addAll(1000,2000,5000);

        lowPrice.setOnAction(this::selectedLowPrice);

        highPrice.setOnAction(this::selectedHighPrice);

        pricePanel.getChildren().addAll(lowPriceLabel, lowPrice);

        pricePanel.getChildren().addAll(highPriceLabel, highPrice);

        //pricePanel.setAlignment(Pos.CENTER_RIGHT);

        pricePanel.setHgap(10);

        Button search = new Button("search");

        search.setOnAction(this::searchProperties);

        selection.getChildren().addAll(pricePanel, search);
        selection.setAlignment(Pos.CENTER_RIGHT);



        pane.setTop(selection);


        AnchorPane traverse = new AnchorPane();

        welcome.heightProperty().bind(pane.heightProperty().subtract(selection.heightProperty().add(traverse.heightProperty()).multiply(1.6)));


        Button previous = new Button("Previous");

        Button next = new Button("next");


        traverse.getChildren().addAll(previous,next);

        traverse.setLeftAnchor(previous, (double) 10);

        traverse.setRightAnchor(next, (double) 10);


        pane.setBottom(traverse);


        //adds borders under the selection bar and above the traverse bar

        selection.setBorder(new Border(new BorderStroke(Color.GREY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,1,0), new Insets(0,0,-10,0))));

        selection.setPadding(new Insets(0,0,5,0));


        traverse.setBorder(new Border(new BorderStroke(Color.GREY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1,0,0,0), new Insets(-10,0,0,0))));

        traverse.setPadding(new Insets(10,0,0,0));

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 800,600);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);

        selection.prefWidthProperty().bind(scene.widthProperty());


        // Show the Stage (window)
        stage.show();
    }

    private void selectedLowPrice(ActionEvent event){
        userLowPrice = lowPrice.getValue();
    }

    private void selectedHighPrice(ActionEvent event){
        userHighPrice = highPrice.getValue();
    }

    private void searchProperties(ActionEvent event)
    {
        pane.setCenter(MapFactory.getMapWindow(userLowPrice, userHighPrice));
        Iterator<LinkedHashSet<AirbnbListing>> propertyIt =  MapWindow.getButtonToProperties().values().iterator();
        Pane propertyList = new PropertyViewer(propertyIt.next()).propertyListContainer();
        propertyList.setMaxHeight(Region.USE_PREF_SIZE);
        propertyList.setMaxWidth(Region.USE_PREF_SIZE);
        pane.setCenter(propertyList);
    }

    /**
     * This will be executed when the button is clicked
     * It increments the count by 1
     */
}
