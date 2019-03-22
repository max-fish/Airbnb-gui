

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.*;
import java.security.Key;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 *
 * @version 0.1.0
 */
public class MainViewer extends Application
{

    public static final Color CORAL = Color.rgb(253,92,99);

    private Integer userLowPrice;

    private Integer userHighPrice;

   private BorderPane root;

    private TabPane panels;

    private Tab welcomeTab;

    private BorderPane pane;

   private ComboBox<String> lowPrice = new ComboBox<String>();

    private ComboBox<String> highPrice = new ComboBox<String>();

    @Override
    public void start(Stage stage) throws Exception
    {

        root = new BorderPane();


        root.setPadding(new Insets(0,0,0,0));


        panels = new TabPane();

        pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setPrefSize(800,600);
        pane.setStyle("-fx-background-color: #FFFFFF;");




        Text welcomeText = new Text();

        welcomeText.setText("Welcome to Airbnb");
        welcomeText.setFill(Color.WHITE);
        welcomeText.setOpacity(0);

        welcomeText.setFont(Font.loadFont(getClass().getResourceAsStream("Montserrat/MontserratAlternates-Regular.otf"), 50));

        Image airbnbLogo = new Image(getClass().getResourceAsStream("thin_white_airbnb_logo.png"));


        StackPane welcomePane = new StackPane();

        ImageView airbnbLogoView = new ImageView(airbnbLogo);
        airbnbLogoView.setFitHeight(100);
        airbnbLogoView.setPreserveRatio(true);
        airbnbLogoView.setOpacity(0);


        welcomePane.setMargin(airbnbLogoView, new Insets(-150,0,0,0));

        Text instructionText = new Text();
        welcomePane.setMargin(instructionText, new Insets(0,0,-100,0));
        instructionText.setText("Please fill out the criteria above with your liking.");
        instructionText.setFont(Font.loadFont(getClass().getResourceAsStream("Montserrat/MontserratAlternates-Light.otf"), 25));
        instructionText.setFill(Color.WHITE);
        instructionText.setOpacity(0);

        Rectangle welcomePaneContainer = new Rectangle();
        welcomePaneContainer.setFill(CORAL);
        welcomePaneContainer.setArcHeight(20);
        welcomePaneContainer.setArcWidth(10);

        welcomePane.getChildren().addAll(welcomePaneContainer, airbnbLogoView, welcomeText, instructionText);

       pane.setCenter(welcomePane);

       // pane.getCenter().setStyle("-fx-background-color: #FD5C63;");


        HBox selection = new HBox();


        FlowPane lowPricePanel = new FlowPane();

        FlowPane highPricePanel = new FlowPane();


        Label lowPriceLabel = new Label("Low Price: ");

        Label highPriceLabel = new Label("High Price: ");

        lowPrice.setEditable(true);
        lowPrice.getItems().addAll("100","200","300");


        highPrice.setEditable(true);
        highPrice.getItems().addAll("1000","2000","5000");

        lowPrice.setOnAction(this::selectedLowPrice);

        highPrice.setOnAction(this::selectedHighPrice);

        lowPricePanel.getChildren().addAll(lowPriceLabel, lowPrice);

        highPricePanel.getChildren().addAll(highPriceLabel, highPrice);

        //pricePanel.setAlignment(Pos.CENTER_RIGHT);

        lowPricePanel.setHgap(10);
        highPricePanel.setHgap(10);

        Button search = new Button("Search");
        search.setId("MainButtons");
        //search.setTextFill(Color.WHITE);
        //search.setFont(Font.loadFont(getClass().getResourceAsStream("Montserrat/MontserratAlternates-Bold.otf"), 12));


        search.setOnAction(this::searchProperties);

        selection.getChildren().addAll(lowPricePanel, highPricePanel, search);
        selection.setAlignment(Pos.CENTER_RIGHT);



        pane.setTop(selection);


        AnchorPane traverse = new AnchorPane();
        traverse.setId("traverseBar");

        Button previous = new Button("Previous");
        previous.setId("MainButtons");

        Button next = new Button("next");
        next.setId("MainButtons");


        traverse.getChildren().addAll(previous,next);

        traverse.setLeftAnchor(previous, (double) 10);

        traverse.setRightAnchor(next, (double) 10);

        traverse.setPadding(new Insets(0,10,10,10));


        previous.setOnAction(
                (event) -> {
                    panels.getSelectionModel().selectPrevious();
                }
        );

        next.setOnAction(
                (event) ->  {panels.getSelectionModel().selectNext();}
        );



        welcomePaneContainer.heightProperty().bind(pane.heightProperty().subtract(selection.heightProperty().multiply(1.7)));

        welcomePaneContainer.widthProperty().bind(pane.widthProperty().subtract(10));


        //adds borders under the selection bar and above the traverse bar

       // selection.setBorder(new Border(new BorderStroke(Color.GREY,
         //       BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,1,0), new Insets(0,0,-10,0))));

        selection.setPadding(new Insets(0,0,20,0));


        //traverse.setBorder(new Border(new BorderStroke(Color.GREY,
          //      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1,0,0,0), new Insets(-10,10,0,10))));

        //traverse.setPadding(new Insets(10,0,0,0));

        Timeline fadeInTimeline = new Timeline();
        lowPrice.setOpacity(0);
        lowPriceLabel.setOpacity(0);
        highPrice.setOpacity(0);
        highPriceLabel.setOpacity(0);
        search.setOpacity(0);
        next.setOpacity(0);
        previous.setOpacity(0);


        KeyValue seenLogo = new KeyValue(airbnbLogoView.opacityProperty(), 1);
        KeyValue welcomeTextPause = new KeyValue(welcomeText.opacityProperty(), 0);//do nothing
        KeyValue seenWelcomeText = new KeyValue(welcomeText.opacityProperty(), 1);
        KeyValue instructionTextPause = new KeyValue(instructionText.opacityProperty(), 0);//do nothing
        KeyValue seenInstructionText = new KeyValue(instructionText.opacityProperty(), 1);

        KeyValue lowPricePause = new KeyValue(lowPrice.opacityProperty(), 0);//do nothing
        KeyValue lowPriceLabelPause = new KeyValue(lowPriceLabel.opacityProperty(), 0);//do nothing
        KeyValue highPricePause = new KeyValue(highPrice.opacityProperty(), 0);//do nothing
        KeyValue highPriceLabelPause = new KeyValue(highPriceLabel.opacityProperty(), 0);//do nothing
        KeyValue searchButtonPause = new KeyValue(search.opacityProperty(), 0);//do nothing
        KeyValue nextButtonPause = new KeyValue(next.opacityProperty(), 0);//do nothing
        KeyValue previousButtonPause = new KeyValue(previous.opacityProperty(), 0);//do nothing

        KeyValue seenLowPrice = new KeyValue(lowPrice.opacityProperty(), 1);
        KeyValue seenLowPriceLabel = new KeyValue(lowPriceLabel.opacityProperty(), 1);
        KeyValue seenHighPrice = new KeyValue(highPrice.opacityProperty(), 1);
        KeyValue seenHighPriceLabel = new KeyValue(highPriceLabel.opacityProperty(), 1);
        KeyValue seenSearchButton = new KeyValue(search.opacityProperty(), 1);
        KeyValue seenNextButton = new KeyValue(next.opacityProperty(), 1);
        KeyValue seenPreviousButton = new KeyValue(previous.opacityProperty(), 1);


        KeyFrame logoFadeIn = new KeyFrame(Duration.millis(2100), seenLogo);
        KeyFrame welcomeTextDelay = new KeyFrame(Duration.millis(1000), welcomeTextPause);
        KeyFrame welcomeTextFadeIn = new KeyFrame(Duration.millis(2000), seenWelcomeText);
        KeyFrame instructionTextDelay = new KeyFrame(Duration.millis(1500), instructionTextPause);
        KeyFrame instructionTextFadeIn = new KeyFrame(Duration.millis(2500), seenInstructionText);

        KeyFrame lowPriceDelay = new KeyFrame(Duration.millis(2000), lowPricePause);
        KeyFrame lowPriceLabelDelay = new KeyFrame(Duration.millis(2000), lowPriceLabelPause);
        KeyFrame highPriceDelay = new KeyFrame(Duration.millis(2000), highPricePause);
        KeyFrame highPriceLabelDelay = new KeyFrame(Duration.millis(2000), highPriceLabelPause);
        KeyFrame searchButtonDelay = new KeyFrame(Duration.millis(2000), searchButtonPause);
        KeyFrame nextButtonDelay = new KeyFrame(Duration.millis(2000), nextButtonPause);
        KeyFrame previousButtonDelay = new KeyFrame(Duration.millis(2000), previousButtonPause);

        KeyFrame lowPriceFadeIn = new KeyFrame(Duration.millis(2500), seenLowPrice);
        KeyFrame lowPriceLabelFadeIn = new KeyFrame(Duration.millis(2500), seenLowPriceLabel);
        KeyFrame highPriceFadeIn = new KeyFrame(Duration.millis(2500), seenHighPrice);
        KeyFrame highPriceLabelFadeIn = new KeyFrame(Duration.millis(2500), seenHighPriceLabel);
        KeyFrame searchButtonFadeIn = new KeyFrame(Duration.millis(2500), seenSearchButton);
        KeyFrame nextButtonFadeIn = new KeyFrame(Duration.millis(2500), seenNextButton);
        KeyFrame previousButtonFadeIn = new KeyFrame(Duration.millis(2500), seenPreviousButton);

        fadeInTimeline.getKeyFrames().addAll(logoFadeIn, welcomeTextDelay, welcomeTextFadeIn, instructionTextDelay, instructionTextFadeIn,
                lowPriceDelay, lowPriceLabelDelay, highPriceDelay, highPriceLabelDelay, searchButtonDelay, nextButtonDelay, previousButtonDelay,
                lowPriceFadeIn, lowPriceLabelFadeIn, highPriceFadeIn, highPriceLabelFadeIn, searchButtonFadeIn, nextButtonFadeIn, previousButtonFadeIn);
        fadeInTimeline.setCycleCount(1);
        fadeInTimeline.play();

        welcomeTab = new Tab();
        welcomeTab.setClosable(false);
        welcomeTab.setText("Welcome");
        welcomeTab.setContent(pane);
        panels.getTabs().add(welcomeTab);

        root.setCenter(panels);

        root.setBottom(traverse);


        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(root, 800,600);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);


        selection.prefWidthProperty().bind(panels.widthProperty());

        panels.minWidthProperty().bind(root.widthProperty());

        root.minHeightProperty().bind(scene.heightProperty());
        root.minWidthProperty().bind(scene.widthProperty());

        scene.getStylesheets().add(MainViewer.class.getResource("Styling.css").toExternalForm());

        // Show the Stage (window)
        stage.show();
    }

    private void selectedLowPrice(ActionEvent event){
         userLowPrice = Integer.parseInt(lowPrice.getValue());
    }

    private void selectedHighPrice(ActionEvent event){
        userHighPrice = Integer.parseInt(highPrice.getValue());
    }

    private void searchProperties(ActionEvent event)
    {
        Tab boroughTab = new Tab();
        boroughTab.setText("Boroughs");
        boroughTab.setContent(MapFactory.getMapWindow(userLowPrice, userHighPrice));
        panels.getTabs().add(boroughTab);

       /* ScrollPane propertyScrollBar = new ScrollPane();
        propertyScrollBar.setContent(propertyList);
        propertyScrollBar.setFitToWidth(true);
        propertyScrollBar.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        propertyScrollBar.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);


        Tab propertyTab = new Tab();
        propertyTab.setText("Properties");
        propertyTab.setContent(propertyScrollBar);
        panels.getTabs().add(propertyTab);*/
    }

    /**
     * This will be executed when the button is clicked
     * It increments the count by 1
     */
}
