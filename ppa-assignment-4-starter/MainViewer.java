import javafx.animation.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

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

    private static TabPane panels;

    private Tab welcomeTab;

    private BorderPane pane;

   private ComboBox<String> lowPrice = new ComboBox<String>();

    private ComboBox<String> highPrice = new ComboBox<String>();

    private static ObservableList<String> homeTypes = FXCollections.observableArrayList("All", "Private room", "Entire Home/apt");

    private ComboBox homeTypesComboBox = new ComboBox(homeTypes);

    private boolean reviewedProperties;

    private static ObservableList<String> neighborhoods = FXCollections.observableArrayList("All", "Barking",
            "Barnet", "Bexley", "Brent", "Bromley", "Camden", "City of London", "Croydon",
            "Ealing", "Enfield", "Greenwich", "Hackney", "Hammersmith", "Haringey", "Harrow",
            "Havering", "Hillingdon", "Hounslow", "Islington", "Kensington", "Kingston", "Lambeth",
            "Lewisham", "Merton", "Newham", "Redbridge", "Richmond", "Southwark", "Sutton",
            "Tower Hamlets", "Waltham Forest", "Wandsworth", "Westminster");

    private ComboBox neighborHoodsComboBox = new ComboBox(neighborhoods);

    private CheckBox reviewsCheckbox = new CheckBox("Yes");

    @Override
    public void start(Stage stage) throws Exception
    {

        root = new BorderPane();


        root.setPadding(new Insets(0,0,0,0));


        panels = new TabPane();

        pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setPrefSize(1200,700);
        pane.setStyle("-fx-background-color: #FFFFFF;");

        Text welcomeText = new Text();

        welcomeText.setText("Welcome to Airbnb");
        welcomeText.setFill(Color.WHITE);
        welcomeText.setOpacity(0);

        welcomeText.setFont(Font.loadFont(getClass().getResourceAsStream("Montserrat/MontserratAlternates-Regular.otf"), 50));

        Image airbnbLogo = new Image(getClass().getResourceAsStream("thin_white_airbnb_logo.png"));


        StackPane welcomePane = new StackPane();

        //welcomePane.setPrefSize(1000, 400);

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

        welcomePane.setMinSize(1000, 200);
        welcomePane.setMaxSize(1000, 200);

       pane.setCenter(welcomePane);

       // pane.getCenter().setStyle("-fx-background-color: #FD5C63;");

        HBox selection = new HBox();
        selection.setPrefSize(1000, 25);

        BorderPane topPanel = new BorderPane();
        topPanel.setPadding(new Insets(5));
        topPanel.setPrefSize(1000, 200);

        GridPane header = new GridPane();

        Image smallAirbnbLogo = new Image(getClass().getResourceAsStream("AirBNB logo.png"));

        ImageView smallAirbnbLogoView = new ImageView(smallAirbnbLogo);
        smallAirbnbLogoView.setFitHeight(70);
        smallAirbnbLogoView.setFitWidth(70);
        smallAirbnbLogoView.setPreserveRatio(true);

        Text topPanelWelcomeText = new Text("Book unique homes and experiences.");
        topPanelWelcomeText.setFont(Font.font("Circular", FontWeight.BOLD, 20));
        topPanelWelcomeText.setStyle("-fx-font-color: #808080");

        header.addColumn(0, smallAirbnbLogoView);
        header.addColumn(1, topPanelWelcomeText);

        VBox houseInformationPanel = new VBox();
        header.setPadding(new Insets(10, 0, 10, 0));
        houseInformationPanel.setPadding(new Insets(15));
        houseInformationPanel.setSpacing(5);
        houseInformationPanel.setMinSize(100, 50);

        Text neighborhoodIndicator = new Text("Select a neighborhood: ");
        Text typeOfHomeIndicator = new Text("Select a type of home: ");

        neighborHoodsComboBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dcdcdc; -fx-background-radius: 10, 10, 10, 10;");

        neighborHoodsComboBox.setPromptText("Neighborhood");

        neighborHoodsComboBox.setOnAction(e -> getNeighborhoodSearched());

        homeTypesComboBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #dcdcdc; -fx-background-radius: 10, 10, 10, 10;");

        homeTypesComboBox.setPromptText("Hometype");

        homeTypesComboBox.setOnAction(e -> getHomeTypeSearched());

        neighborhoodIndicator.setFont(Font.font("Circular", FontWeight.SEMI_BOLD, 20));

        typeOfHomeIndicator.setFont(Font.font("Circular", FontWeight.SEMI_BOLD, 20));

        houseInformationPanel.getChildren().addAll(neighborhoodIndicator, neighborHoodsComboBox, typeOfHomeIndicator, homeTypesComboBox);

        VBox pricePanel = new VBox();
        pricePanel.setPadding(new Insets(5));
        pricePanel.setSpacing(2);
        pricePanel.setPrefHeight(50);

        Text lowPriceIndicator = new Text("Minimum price: ");

        lowPriceIndicator.setFont(Font.font("Circular", FontWeight.SEMI_BOLD,20));

        Text highPriceIndicator = new Text("Maximum price: ");

        highPriceIndicator.setFont(Font.font("Circular", FontWeight.SEMI_BOLD, 20));

        Tooltip lowPriceIndicatorToolTip = new Tooltip();
        lowPriceIndicatorToolTip.setText("You must input \n" + "a positive value. \n" + "Lower than the \n" + "high price.");
        lowPrice.setTooltip(lowPriceIndicatorToolTip);

        Tooltip highPriceIndicatorToolTip = new Tooltip();
        highPriceIndicatorToolTip.setText("You must input \n" + "a positive value. \n" + "Higher than the \n" + "minimum price.");
        highPrice.setTooltip(highPriceIndicatorToolTip);

        Image attentionImage = new Image (getClass().getResourceAsStream("attentionIcon.png"));
        ImageView attentionImageView = new ImageView(attentionImage);
        attentionImageView.setFitHeight(40);
        attentionImageView.setFitWidth(40);
        lowPriceIndicatorToolTip.setGraphic(attentionImageView);
        highPriceIndicatorToolTip.setGraphic(attentionImageView);

        pricePanel.getChildren().addAll(lowPriceIndicator, lowPrice, highPriceIndicator, highPrice);

        lowPrice.setEditable(true);
        lowPrice.getItems().addAll("100","200","300");

        highPrice.setEditable(true);
        highPrice.getItems().addAll("1000","2000","5000");

        lowPrice.setOnAction(this::selectedLowPrice);

        highPrice.setOnAction(this::selectedHighPrice);

        lowPrice.setOnMouseClicked(this::clickedLowPriceCombBox);

        highPrice.setOnMouseClicked(this::clickedHighPriceComboBox);

        lowPrice.setPromptText("Minimum amount per night");

        highPrice.setPromptText("Maximum amount per night");

        lowPrice.setPrefWidth(225);
        highPrice.setPrefWidth(225);

        VBox numberOfNightsSliderContainer = new VBox();

        numberOfNightsSliderContainer.setPadding(new Insets(20));
        numberOfNightsSliderContainer.setSpacing(2);
        numberOfNightsSliderContainer.setMinSize(100, 50);

        Text numberOfNightsSliderIndicator = new Text("Number of nights: ");

        numberOfNightsSliderIndicator.setFont(Font.font("Circular", FontWeight.SEMI_BOLD, 20));

        Slider numberOfNightsSlider = new Slider();
        numberOfNightsSlider.setMin(1);
        numberOfNightsSlider.setMax(5);
        numberOfNightsSlider.setValue(4);
        numberOfNightsSlider.setMajorTickUnit(1);
        numberOfNightsSlider.setMinorTickCount(0);
        numberOfNightsSlider.setShowTickLabels(true);
        numberOfNightsSlider.setShowTickMarks(true);
        numberOfNightsSlider.snapToTicksProperty().set(true);

        numberOfNightsSlider.setStyle("-fx-focus-color: #ff5a5f; -fx-faint-focus-color: #ff5a5f;");

        numberOfNightsSliderContainer.getChildren().addAll(numberOfNightsSliderIndicator, numberOfNightsSlider);

        VBox numberOfReviewsCheckboxesContainer = new VBox();
        numberOfReviewsCheckboxesContainer.setPadding(new Insets (20));
        numberOfReviewsCheckboxesContainer.setMinSize(100, 50);
        numberOfReviewsCheckboxesContainer.setSpacing(7);

        Text numberOfReviewsCheckboxes = new Text("Select reviewed properties: ");

        numberOfReviewsCheckboxes.setFont(Font.font("Circular", FontWeight.SEMI_BOLD, 20));

        numberOfReviewsCheckboxes.setOnMouseClicked(this::returnReviewedProperties);

        reviewsCheckbox.setStyle("-fx-focus-color: #ff5a5f; -fx-faint-focus-color: #ff5a5f; -fx-mark-color: #808080; -fx-background: #ffffff");

        numberOfReviewsCheckboxesContainer.getChildren().addAll(numberOfReviewsCheckboxes, reviewsCheckbox);

        Button search = new Button("Search");

        search.setOnAction(this::searchProperties);

        search.setFont(Font.font("Circular", FontWeight.EXTRA_BOLD, 22));

        search.setStyle("-fx-base: #ff5a5f; -fx-text-fill: white; -fx-focus-color: #ffffff; -fx-faint-focus-color: #ffffff; -fx-background-radius: 10, 10, 10, 10");

        search.setMinWidth(900);
        search.setMinHeight(30);
        search.setAlignment(Pos.CENTER);

        selection.getChildren().addAll(houseInformationPanel, numberOfNightsSlider, numberOfReviewsCheckboxesContainer, pricePanel);
        selection.setAlignment(Pos.CENTER);

        topPanel.setTop(header);
        topPanel.setCenter(selection);
        topPanel.setBottom(search);

        pane.setTop(topPanel);

        topPanel.setBorder(new Border(new BorderStroke(Color.GREY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,1,0), new Insets(0,0,-10,0))));

        topPanel.setPadding(new Insets(0,0,5,0));

        AnchorPane traverse = new AnchorPane();
        traverse.setId("traverseBar");

        Button previous = new Button("Previous");
        previous.setId("MainButtons");

        previous.setStyle("-fx-base: #ff5a5f; -fx-text-fill: white; -fx-focus-color: #ffffff; -fx-faint-focus-color: #ffffff; -fx-background-radius: 10, 10, 10, 10");

        Button next = new Button("next");
        next.setId("MainButtons");

        next.setStyle("-fx-base: #ff5a5f; -fx-text-fill: white; -fx-focus-color: #ffffff; -fx-faint-focus-color: #ffffff; -fx-background-radius: 10, 10, 10, 10");

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

        traverse.setBorder(new Border(new BorderStroke(Color.GREY,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1,0,0,0), new Insets(-10,10,0,10))));

        traverse.setPadding(new Insets(10,0,0,0));

        Timeline fadeInTimeline = new Timeline();
        lowPrice.setOpacity(0);
        lowPriceIndicator.setOpacity(0);
        highPrice.setOpacity(0);
        highPriceIndicator.setOpacity(0);
        topPanel.setOpacity(0);
        search.setOpacity(0);
        next.setOpacity(0);
        previous.setOpacity(0);

        KeyValue seenLogo = new KeyValue(airbnbLogoView.opacityProperty(), 1);
        KeyValue welcomeTextPause = new KeyValue(welcomeText.opacityProperty(), 0);//do nothing
        KeyValue seenWelcomeText = new KeyValue(welcomeText.opacityProperty(), 1);
        KeyValue instructionTextPause = new KeyValue(instructionText.opacityProperty(), 0);//do nothing
        KeyValue seenInstructionText = new KeyValue(instructionText.opacityProperty(), 1);

        KeyValue lowPricePause = new KeyValue(lowPrice.opacityProperty(), 0);//do nothing
        KeyValue lowPriceLabelPause = new KeyValue(lowPriceIndicator.opacityProperty(), 0);//do nothing
        KeyValue highPricePause = new KeyValue(highPrice.opacityProperty(), 0);//do nothing
        KeyValue highPriceLabelPause = new KeyValue(highPriceIndicator.opacityProperty(), 0);//do nothing
        KeyValue searchButtonPause = new KeyValue(search.opacityProperty(), 0);//do nothing
        KeyValue nextButtonPause = new KeyValue(next.opacityProperty(), 0);//do nothing
        KeyValue previousButtonPause = new KeyValue(previous.opacityProperty(), 0);//do nothing
        KeyValue topPanelPause = new KeyValue(previous.opacityProperty(), 0);

        KeyValue seenLowPrice = new KeyValue(lowPrice.opacityProperty(), 1);
        KeyValue seenLowPriceLabel = new KeyValue(lowPriceIndicator.opacityProperty(), 1);
        KeyValue seenHighPrice = new KeyValue(highPrice.opacityProperty(), 1);
        KeyValue seenHighPriceLabel = new KeyValue(highPriceIndicator.opacityProperty(), 1);
        KeyValue seenSearchButton = new KeyValue(search.opacityProperty(), 1);
        KeyValue seenNextButton = new KeyValue(next.opacityProperty(), 1);
        KeyValue seenPreviousButton = new KeyValue(previous.opacityProperty(), 1);
        KeyValue seenTopPanel = new KeyValue(topPanel.opacityProperty(), 1);

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
        KeyFrame topPanelDelay = new KeyFrame(Duration.millis(2000), topPanelPause);

        KeyFrame lowPriceFadeIn = new KeyFrame(Duration.millis(2500), seenLowPrice);
        KeyFrame lowPriceLabelFadeIn = new KeyFrame(Duration.millis(2500), seenLowPriceLabel);
        KeyFrame highPriceFadeIn = new KeyFrame(Duration.millis(2500), seenHighPrice);
        KeyFrame highPriceLabelFadeIn = new KeyFrame(Duration.millis(2500), seenHighPriceLabel);
        KeyFrame searchButtonFadeIn = new KeyFrame(Duration.millis(2500), seenSearchButton);
        KeyFrame nextButtonFadeIn = new KeyFrame(Duration.millis(2500), seenNextButton);
        KeyFrame previousButtonFadeIn = new KeyFrame(Duration.millis(2500), seenPreviousButton);
        KeyFrame topPanelFadeIn = new KeyFrame(Duration.millis(2500), seenTopPanel);

        fadeInTimeline.getKeyFrames().addAll(logoFadeIn, welcomeTextDelay, welcomeTextFadeIn, instructionTextDelay, instructionTextFadeIn,
                lowPriceDelay, lowPriceLabelDelay, highPriceDelay, highPriceLabelDelay, searchButtonDelay, nextButtonDelay, previousButtonDelay,
                lowPriceFadeIn, lowPriceLabelFadeIn, highPriceFadeIn, highPriceLabelFadeIn, searchButtonFadeIn, nextButtonFadeIn, previousButtonFadeIn, topPanelFadeIn, topPanelDelay);
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
        Scene scene = new Scene(root, 1200,700);
        stage.setTitle("Airbnb HomeFinder");
        stage.setScene(scene);

        topPanel.prefWidthProperty().bind(panels.widthProperty());
        panels.minWidthProperty().bind(root.widthProperty());

        root.minHeightProperty().bind(scene.heightProperty());
        root.minWidthProperty().bind(scene.widthProperty());

        scene.getStylesheets().add(MainViewer.class.getResource("Styling.css").toExternalForm());

        // Show the Stage (window)
        stage.show();
    }

    public static TabPane getPanels(){
        return panels;
    }

    private void selectedLowPrice(ActionEvent event){
         userLowPrice = Integer.parseInt(lowPrice.getValue());
    }

    private void selectedHighPrice(ActionEvent event){
        userHighPrice = Integer.parseInt(highPrice.getValue());
    }

    public String getNeighborhoodSearched()
    {
        System.out.println(neighborHoodsComboBox.getValue());
        return "" + neighborHoodsComboBox.getValue();
    }

    public String getHomeTypeSearched()
    {
        System.out.println(homeTypesComboBox.getValue());
        return "" + homeTypesComboBox.getValue();
    }

    private void clickedLowPriceCombBox(MouseEvent event) {
        lowPrice.setStyle("-fx-faint-focus-color: #ff5a5f");
    }

    private void clickedHighPriceComboBox(MouseEvent event) {
        highPrice.setStyle("-fx-faint-focus-color: #ff5a5f");
    }

    public boolean returnReviewedProperties(MouseEvent event)
    {
        if(reviewsCheckbox.isSelected() == true) {
            reviewedProperties = true;
        }
        else {
            reviewedProperties = false;
        }
        return reviewedProperties;
    }

    private void searchProperties(ActionEvent event) {
        if(userLowPrice >= 0 && userHighPrice >= userLowPrice) {
            if (getNeighborhoodSearched().equals("All")) {
                Tab boroughTab = new Tab();
                boroughTab.setText("Boroughs");
                boroughTab.setContent(MapFactory.getMapWindow(userLowPrice, userHighPrice));
                panels.getTabs().add(boroughTab);
                panels.getSelectionModel().select(boroughTab);
            } else {
                PropertyViewer propertyViewer = new PropertyViewer(LondonCSVUtilities.boroughListings(userLowPrice, userHighPrice).get(getNeighborhoodSearched()));
                Tab propertyTab = new Tab();
                propertyTab.setText("Properties");
                propertyTab.setContent(propertyViewer.makeFullPropertyWindow());
                MainViewer.getPanels().getTabs().add(propertyTab);
                MainViewer.getPanels().getSelectionModel().select(propertyTab);
            }
        }
        else {
            AlertBox.display("Alert", "You have selected an incorrect\n" + "   price range please fix this!");
        }
    }
}
