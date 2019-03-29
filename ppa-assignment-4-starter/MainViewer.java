import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.ToolBar;
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

/**
 *
 * @version 0.1.0
 */
public class MainViewer extends Application
{
    private Integer userLowPrice;

    private Integer userHighPrice;

    private String userNeighborhood;

    private String userRoomType;

   private BorderPane root;

    private static TabPane panels;

    private Tab welcomeTab;

    private BorderPane pane;

    private static ToolBar myAirbnb = new ToolBar();

   private ComboBox<String> lowPrice;

    private ComboBox<String> highPrice;

    private ComboBox<String> neighborhood;

    private ComboBox<String> roomType;

    private static ObservableList<String> homeTypes = FXCollections.observableArrayList("All", "Private room", "Entire home/apt", "Shared room");

    private static ObservableList<String> neighborhoods = FXCollections.observableArrayList("All", "Barking and Dagenham",
            "Barnet", "Bexley", "Brent", "Bromley", "Camden", "City of London", "Croydon",
            "Ealing", "Enfield", "Greenwich", "Hackney", "Hammersmith and Fulham", "Haringey", "Harrow",
            "Havering", "Hillingdon", "Hounslow", "Islington", "Kensington and Chelsea", "Kingston upon Thames", "Lambeth",
            "Lewisham", "Merton", "Newham", "Redbridge", "Richmond upon Thames", "Southwark", "Sutton",
            "Tower Hamlets", "Waltham Forest", "Wandsworth", "Westminster");

    @Override
    public void start(Stage stage) throws Exception
    {

        Airbnb.styleGraphics();

        root = new BorderPane();

        panels = new TabPane();

        pane = new BorderPane();

        lowPrice = new ComboBox<>();

        highPrice = new ComboBox<>();

        neighborhood = new ComboBox<>();

        roomType = new ComboBox<>();

        MainViewerFactory.stylePane(pane);
        
        Text welcomeText = new Text();

       MainViewerFactory.styleWelcomeText(welcomeText);

        StackPane welcomePane = new StackPane();

        Text instructionText = new Text();

        MainViewerFactory.styleInstructionText(instructionText);

        MainViewerFactory.styleWelcomePane(welcomePane, Airbnb.AIRBNBLOGO, instructionText);

        Rectangle welcomePaneContainer = new Rectangle();

        MainViewerFactory.styleWelcomePaneContainer(welcomePaneContainer);

        welcomePane.getChildren().addAll(welcomePaneContainer, Airbnb.AIRBNBLOGO, welcomeText, instructionText);

       pane.setCenter(welcomePane);

        HBox selection = new HBox();

        neighborhood.setPromptText("Neighborhood");

        Label neighborhoodLabel = new Label("Neighborhood: ");
        neighborhoodLabel.setFont(Airbnb.COMBOBOXFONT);

        neighborhood.getItems().addAll(LondonCSVUtilities.getNeighborhoods());
        neighborhood.setOnAction(this::selectedNeighborhood);
        neighborhood.getStyleClass().add("non-editable-combo-box");

        FlowPane neighborhoodPanel = new FlowPane();
        neighborhoodPanel.getChildren().addAll(neighborhoodLabel, neighborhood);

        Label roomTypeLabel = new Label("Room type: ");
        roomTypeLabel.setFont(Airbnb.COMBOBOXFONT);

        roomType.getItems().addAll(homeTypes);
        roomType.setOnAction(this::selectedRoomType);
        roomType.getStyleClass().add("non-editable-combo-box");

        FlowPane roomTypePanel = new FlowPane();
        roomTypePanel.getChildren().addAll(roomTypeLabel, roomType);
        
        roomType.setPromptText("Hometype");
        
        neighborhood.setPromptText("Neighborhood");

        Label lowPriceLabel = new Label("Low Price: ");
        lowPriceLabel.setFont(Airbnb.COMBOBOXFONT);

        Label highPriceLabel = new Label("High Price: ");
        highPriceLabel.setFont(Airbnb.COMBOBOXFONT);

        lowPrice.setEditable(true);
        lowPrice.getItems().addAll("100","200","300");

        highPrice.setEditable(true);
        highPrice.getItems().addAll("1000","2000","5000");

        lowPrice.setOnAction(this::selectedLowPrice);

        highPrice.setOnAction(this::selectedHighPrice);

        Tooltip lowPriceToolTip = new Tooltip();
        lowPriceToolTip.setText("You must input \n" + "a positive value. \n" + "Lower than the \n" + "high price.");
        lowPriceToolTip.setFont(Airbnb.TOOLTIPFONT);
        lowPrice.setTooltip(lowPriceToolTip);

        Tooltip highPriceToolTip = new Tooltip();
        highPriceToolTip.setText("You must input \n" + "a positive value. \n" + "Higher than the \n" + "minimum price.");
        highPriceToolTip.setFont(Airbnb.TOOLTIPFONT);
        highPrice.setTooltip(highPriceToolTip);
        
        Image attentionImage = new Image (getClass().getResourceAsStream("attentionIcon.png"));
        ImageView attentionImageView = new ImageView(attentionImage);
        attentionImageView.setFitHeight(40);
        attentionImageView.setFitWidth(40);
        lowPriceToolTip.setGraphic(attentionImageView);
        highPriceToolTip.setGraphic(attentionImageView);

        FlowPane lowPricePanel = new FlowPane();

        FlowPane highPricePanel = new FlowPane();

        lowPricePanel.getChildren().addAll(lowPriceLabel, lowPrice);

        highPricePanel.getChildren().addAll(highPriceLabel, highPrice);

        highPrice.setOnMouseClicked(this::clickedHighPriceComboBox);
        
        lowPrice.setPromptText("Minimum amount per night");

        highPrice.setPromptText("Maximum amount per night");

        Button search = new Button("Search");
        search.setFont(Airbnb.BUTTONFONT);
        search.setId("MainButtons");

        search.setOnAction(this::searchProperties);
        
        selection.getChildren().addAll(neighborhoodPanel, roomTypePanel, lowPricePanel, highPricePanel, search);

        selection.prefWidthProperty().bind(root.widthProperty());

        selection.setAlignment(Pos.CENTER);

        pane.setTop(selection);

        AnchorPane traverse = new AnchorPane();
        traverse.setId("traverseBar");

        Button previous = new Button("Previous");
        previous.setFont(Airbnb.BUTTONFONT);
        previous.setId("MainButtons");

        Button next = new Button("next");
        next.setFont(Airbnb.BUTTONFONT);
        next.setId("MainButtons");

        traverse.getChildren().addAll(previous, next);

        traverse.setLeftAnchor(previous, (double) 10);

        traverse.setRightAnchor(next, (double) 10);

        traverse.setPadding(new Insets(0,10,10,10));

        previous.setOnAction(
                (event) -> {
                    if(panels.getSelectionModel().getSelectedIndex() == 0){
                        panels.getSelectionModel().selectLast();
                    }
                    else {
                        panels.getSelectionModel().selectPrevious();
                    }
                }
        );

        next.setOnAction(
                (event) ->  {
                    if(panels.getSelectionModel().getSelectedIndex() == (panels.getTabs().size()-1)){
                        panels.getSelectionModel().selectFirst();
                    }
                    else {
                        panels.getSelectionModel().selectNext();
                    }
                }
        );

        welcomePaneContainer.heightProperty().bind(pane.heightProperty().subtract(selection.heightProperty().multiply(1.7)));

        welcomePaneContainer.widthProperty().bind(pane.widthProperty().subtract(10));

        selection.setPadding(new Insets(0,0,20,0));

        MainViewerFactory.fadeInProtocol(Airbnb.AIRBNBLOGO, welcomeText, instructionText, neighborhoodPanel, roomTypePanel, lowPricePanel, highPricePanel,
                search, next, previous);

        TabCreator.createSingularTab(pane, "Welcome", Airbnb.HOMEGRAPHIC, false);

        panels.setTranslateX(-40);

        root.setCenter(panels);

        root.setBottom(traverse);
        
        myAirbnb.setOrientation(Orientation.VERTICAL);

        myAirbnb.setStyle("-fx-background-color: rgba(0,0,0,0.5)");

        Rectangle toolBarContainer = new Rectangle();
        toolBarContainer.heightProperty().bind(myAirbnb.heightProperty());
        toolBarContainer.widthProperty().bind(myAirbnb.widthProperty());
        toolBarContainer.setArcWidth(18);
        toolBarContainer.setArcHeight(18);

        myAirbnb.setShape(toolBarContainer);

        Text myAribnbText = new Text("My Airbnb");
        myAribnbText.setFont(Airbnb.TOOLBARFONT);
        myAribnbText.setFill(Airbnb.CORAL);
        myAribnbText.setUnderline(true);


        Button showFavourites = new Button("Favourites");
        showFavourites.setStyle("-fx-background-color: #FD5C63");
        showFavourites.setFont(Airbnb.BUTTONFONT);
        showFavourites.setTextFill(Color.WHITE);
        Button showHelp = new Button("Help");
        showFavourites.setOnAction(
                (event) -> {FavouriteProperties.showFavoriteProperties();}
        );
        showHelp.setOnAction(e -> AlertBox.display("User Guidelines", "Welcome to Airbnb HomeFinder. This is an application that allows you to search\n for Airbnb listings in London. The first thing you should know about this\n application is that " +
                "you can use the next and previous buttons at the\n bottom of the window, as well as the tabs at the top of the window to traverse\n through the different panels. The top panel in the welcome page\n is made up of different criteria that allows " +
                "a user to personalize\n their home-finding process. Once a user presses search this will bring up \na map of all the boroughs in London. It is designed as a heat map, so darker colours\n indicate more homes found, given the search criteria" +
                ", while lighter\n colours indicate fewer homes found. You can then press on the borough buttons to\n show all the properties in that borough. As always have fun finding your next unique home or experience.\n Happy HomeFinding - Airbnb"));

        myAirbnb.getItems().addAll(myAribnbText, showFavourites, showHelp);

        myAirbnb.setTranslateX(-100);

        root.setLeft(myAirbnb);


        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(root, 1700,800);
        stage.setTitle("Airbnb HomeFinder");
        stage.setScene(scene);

        panels.minWidthProperty().bind(root.widthProperty());

        MainViewerFactory.styleRoot(root, scene);

        scene.getStylesheets().add(MainViewer.class.getResource("Styling.css").toExternalForm());

        // Show the Stage (window)
        stage.show();

    }

    public static TabPane getPanels(){
        return panels;
    }

    public static ToolBar getToolBar(){
        return myAirbnb;
    }

    private void selectedNeighborhood(ActionEvent event){userNeighborhood = neighborhood.getValue();}

    private void selectedRoomType(ActionEvent event){userRoomType = roomType.getValue();}

    private void selectedLowPrice(ActionEvent event){
         userLowPrice = Integer.parseInt(lowPrice.getValue());
    }

    private void selectedHighPrice(ActionEvent event){
        userHighPrice = Integer.parseInt(highPrice.getValue());
    }
    
    private void clickedLowPriceCombBox(MouseEvent event) {
        lowPrice.setStyle("-fx-faint-focus-color: #ff5a5f");
    }

    private void clickedHighPriceComboBox(MouseEvent event) {
        highPrice.setStyle("-fx-faint-focus-color: #ff5a5f");
    }


    private void searchProperties(ActionEvent event) {
        if((lowPrice.getValue() != null) && (highPrice.getValue() != null) && (neighborhood.getValue() != null) && (roomType.getValue() != null)) {
            Criteria userCriteria = new Criteria(userNeighborhood, userRoomType, userLowPrice, userHighPrice, null);
            if (userLowPrice >= 0 && userHighPrice >= userLowPrice) {
                if (userNeighborhood.equals("All")) {
                    if(userRoomType.equals("All")){
                        TabCreator.createTab(MapFactory.getMapWindow(userLowPrice, userHighPrice, userCriteria), MapFactory.getMapWindow(userLowPrice, userHighPrice, userCriteria).fullBoroughWindow(null), "Boroughs", Airbnb.BOROUGHGRAPHIC, true, userCriteria);

                    }
                    else {
                        TabCreator.createTab(MapFactory.getMapWindow(userLowPrice, userHighPrice, userCriteria), MapFactory.getMapWindow(userLowPrice, userHighPrice, userCriteria).fullBoroughWindow(userRoomType), "Boroughs", Airbnb.BOROUGHGRAPHIC, true, userCriteria);
                    }
                }

                else {
                    if (userRoomType.equals("All")) {
                        if (LondonCSVUtilities.filteredResults(userLowPrice, userHighPrice).get(userNeighborhood).size() != 0) {
                            PropertyViewer propertyViewer = new PropertyViewer(LondonCSVUtilities.filteredResults(userLowPrice, userHighPrice).get(userNeighborhood));
                            TabCreator.createTab(propertyViewer, propertyViewer.makeFullPropertyWindow(userNeighborhood), "Properties", Airbnb.PROPERTYGRAPHIC, true, userCriteria);
                        } else {
                            AlertBox.display("Oh no!", "There are no properties in this area\n" + "for the price range you selected.");
                        }
                    }
                    else {
                        if (LondonCSVUtilities.filteredResults(userLowPrice, userHighPrice, userRoomType).get(userNeighborhood).size() != 0) {
                            PropertyViewer propertyViewer = new PropertyViewer(LondonCSVUtilities.filteredResults(userLowPrice, userHighPrice, userRoomType).get(userNeighborhood));
                            TabCreator.createTab(propertyViewer, propertyViewer.makeFullPropertyWindow(userNeighborhood), "Properties", Airbnb.PROPERTYGRAPHIC, true, userCriteria);
                        } else {
                            AlertBox.display("Oh no!", "There are no properties in this area\n" + "for the price range you selected.");
                        }
                    }
                }
            }
            else {
                AlertBox.display("Oh no!", "You seem to have selected an incorrect\n" + "price range.");
            }
        }
        else {
            AlertBox.display("Oh no!", "You have not selected all criteria in the\n" + "in the top panel above. Please fix this!");
        }
    }
}

