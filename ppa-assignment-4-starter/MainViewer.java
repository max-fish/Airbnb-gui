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
import javafx.scene.layout.*;
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

    private BorderPane pane;

    private static ToolBar myAirbnb;

    private static StackPane favouriteBar = MainViewerFactory.makeNotificationBar("Added as favourite");

    private static StackPane unfavouriteBar = MainViewerFactory.makeNotificationBar("Removed as favourite");

   private ComboBox<String> lowPrice;

    private ComboBox<String> highPrice;

    private ComboBox<String> neighborhood;

    private ComboBox<String> roomType;

    private static ObservableList<String> homeTypes = FXCollections.observableArrayList("All", "Private room", "Entire home/apt", "Shared room");

    @Override
    public void start(Stage stage) throws Exception
    {

        FavouriteProperties.setUp();

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

        ImageView airbnbLogoView = Airbnb.getImageView(Airbnb.Graphic.AIRBNBLOGO);

        MainViewerFactory.styleWelcomePane(welcomePane, airbnbLogoView, instructionText);

        Rectangle welcomePaneContainer = new Rectangle();

        MainViewerFactory.styleWelcomePaneContainer(welcomePaneContainer);

        welcomePane.getChildren().addAll(welcomePaneContainer, airbnbLogoView, welcomeText, instructionText);

       pane.setCenter(welcomePane);

        HBox selection = new HBox();
        selection.setFillHeight(false);

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

        
        lowPrice.setPromptText("Minimum per night");

        highPrice.setPromptText("Maximum per night");

        Button search = new Button("Search");
        search.setFont(Airbnb.BUTTONFONT);
        search.setMinSize(75,29);
        search.setId("MainButtons");

        search.setOnAction(this::searchProperties);
        
        selection.getChildren().addAll(neighborhoodPanel, roomTypePanel, lowPricePanel, highPricePanel, search);


        selection.setAlignment(Pos.CENTER);

        pane.setTop(selection);

        StackPane bottom = new StackPane();

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

        bottom.getChildren().addAll(favouriteBar, unfavouriteBar, traverse);

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

        MainViewerFactory.fadeInProtocol(Airbnb.getImageView(Airbnb.Graphic.AIRBNBLOGO), welcomeText, instructionText, neighborhoodPanel, roomTypePanel, lowPricePanel, highPricePanel,
                search, next, previous);

        TabCreator.createSingularTab(pane, "Welcome", Airbnb.getImageView(Airbnb.Graphic.HOMEGRAPHIC), false);

        panels.setTranslateX(-70);

        root.setCenter(panels);

        root.setBottom(bottom);

        myAirbnb = Toolbar.makeToolBar();

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

    public static StackPane getFavouriteBar(){return favouriteBar;}

    public static StackPane getUnfavouriteBar(){return unfavouriteBar;}

    private void selectedNeighborhood(ActionEvent event){userNeighborhood = neighborhood.getValue();}

    private void selectedRoomType(ActionEvent event){userRoomType = roomType.getValue();}

    /**
     * This method assigns the selected price inside the lowPrice combo-box to the userLowPrice variable
     * when a user clicks on the highPrice combo-box.
     * @param event
     */
    private void selectedLowPrice(ActionEvent event){
        if(lowPrice.getValue() != null) {
            try {
                userLowPrice = Integer.parseInt(lowPrice.getValue());
            } catch (java.lang.NumberFormatException e) {
                AlertBox.display("Oh no!", "You seem to have selected an incorrect\n" + "price range.", 200, 550);
            }
        }
    }

    /**
     * This method assigns the selected price inside the highPrice combo-box to the userHighPrice variable
     * when a user clicks on the highPrice combo-box.
     * @param event
     */
    private void selectedHighPrice(ActionEvent event) {
        if (highPrice.getValue() != null) {
            try {
                userHighPrice = Integer.parseInt(highPrice.getValue());
            } catch (java.lang.NumberFormatException e) {
                AlertBox.display("Oh no!", "You seem to have selected an incorrect\n" + "price range.", 200, 550);
            }
        }
    }


/**
This is a method that searches for properties that match the given inputted user criteria. A user can search
 for a given type of property in a given borough, but they can also make their search as general as searching
 all property types in all boroughs within a given price range. If for any reason a user inputs wrong criteria
 or if the system cannot find any properties a message will displayed to the user indicating what went wrong
 when searching for properties.
 @param event When the user clicks the search button on the first panel.
 */
    private void searchProperties(ActionEvent event) {
        if((lowPrice.getValue() != null) && (highPrice.getValue() != null) && (neighborhood.getValue() != null) && (roomType.getValue() != null)) {
            Criteria userCriteria = new Criteria(userNeighborhood, userRoomType, userLowPrice, userHighPrice, null);
            if (userLowPrice >= 0 && userHighPrice >= userLowPrice) {
                if (userNeighborhood.equals("All")) {
                    if(userRoomType.equals("All")){
                        TabCreator.createTab(MapFactory.getMapWindow(userLowPrice, userHighPrice, userCriteria), MapFactory.getMapWindow(userLowPrice, userHighPrice, userCriteria).fullBoroughWindow(null), "Boroughs", Airbnb.getImageView(Airbnb.Graphic.BOROUGHGRAPHIC), true, userCriteria);

                    }
                    else {
                        TabCreator.createTab(MapFactory.getMapWindow(userLowPrice, userHighPrice, userCriteria), MapFactory.getMapWindow(userLowPrice, userHighPrice, userCriteria).fullBoroughWindow(userRoomType), "Boroughs", Airbnb.getImageView(Airbnb.Graphic.BOROUGHGRAPHIC), true, userCriteria);
                    }
                }

                else {
                    if (userRoomType.equals("All")) {
                        if (LondonCSVUtilities.filteredResults(userLowPrice, userHighPrice).get(userNeighborhood).size() != 0) {
                            PropertyViewer propertyViewer = new PropertyViewer(LondonCSVUtilities.filteredResults(userLowPrice, userHighPrice).get(userNeighborhood), userCriteria);
                            TabCreator.createTab(propertyViewer, propertyViewer.makeFullPropertyWindow(userNeighborhood), "Properties", Airbnb.getImageView(Airbnb.Graphic.PROPERTYGRAPHIC), true, userCriteria);
                        } else {
                            AlertBox.display("Oh no!", "There are no properties in this area\n" + "for the price range you selected.", 200, 550);
                        }
                    }
                    else {
                        if (LondonCSVUtilities.filteredResults(userLowPrice, userHighPrice, userRoomType).get(userNeighborhood).size() != 0) {
                            PropertyViewer propertyViewer = new PropertyViewer(LondonCSVUtilities.filteredResults(userLowPrice, userHighPrice, userRoomType).get(userNeighborhood), userCriteria);
                            TabCreator.createTab(propertyViewer, propertyViewer.makeFullPropertyWindow(userNeighborhood), "Properties", Airbnb.getImageView(Airbnb.Graphic.PROPERTYGRAPHIC), true, userCriteria);
                        } else {
                            AlertBox.display("Oh no!", "There are no properties in this area\n" + "for the price range you selected.", 200, 550);
                        }
                    }
                }
            }
            else {
                AlertBox.display("Oh no!", "You seem to have selected an incorrect\n" + "price range.", 200, 550);
            }
        }
        else {
            AlertBox.display("Oh no!", "You have not selected all criteria in the\n" + "in the top panel above. Please fix this!", 200, 550);
        }
    }
}

