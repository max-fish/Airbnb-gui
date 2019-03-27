import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.*;
import java.util.Stack;

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

   private ComboBox<String> lowPrice;

    private ComboBox<String> highPrice;

    private ComboBox<String> neighborhood;

    private ComboBox<String> roomType;

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


        Label neighborhoodLabel = new Label("Neighborhood: ");
        neighborhoodLabel.setFont(Airbnb.COMBOBOXFONT);

        neighborhood.getItems().addAll(LondonCSVUtilities.getNeighborhoods());
        neighborhood.setOnAction(this::selectedNeighborhood);
        neighborhood.getStyleClass().add("non-editable-combo-box");

        FlowPane neighborhoodPanel = new FlowPane();
        neighborhoodPanel.getChildren().addAll(neighborhoodLabel, neighborhood);

        Label roomTypeLabel = new Label("Room type: ");
        roomTypeLabel.setFont(Airbnb.COMBOBOXFONT);

        roomType.getItems().addAll("Entire home/apt", "Private room", "Shared room");
        roomType.setOnAction(this::selectedRoomType);
        roomType.getStyleClass().add("non-editable-combo-box");

        FlowPane roomTypePanel = new FlowPane();
        roomTypePanel.getChildren().addAll(roomTypeLabel, roomType);


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


        FlowPane lowPricePanel = new FlowPane();


        FlowPane highPricePanel = new FlowPane();

        lowPricePanel.getChildren().addAll(lowPriceLabel, lowPrice);

        highPricePanel.getChildren().addAll(highPriceLabel, highPrice);


        lowPricePanel.setHgap(10);
        highPricePanel.setHgap(10);

        Button search = new Button("Search");
        search.setFont(Airbnb.BUTTONFONT);
        search.setId("MainButtons");


        search.setOnAction(this::searchProperties);

        selection.getChildren().addAll(neighborhoodPanel, roomTypePanel, lowPricePanel, highPricePanel, search);

        selection.prefWidthProperty().bind(root.widthProperty());

        pane.setTop(selection);



        AnchorPane traverse = new AnchorPane();
        traverse.setId("traverseBar");

        Button previous = new Button("Previous");
        previous.setFont(Airbnb.BUTTONFONT);
        previous.setId("MainButtons");

        Button next = new Button("next");
        next.setFont(Airbnb.BUTTONFONT);
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


        selection.setPadding(new Insets(0,0,20,0));


        MainViewerFactory.fadeInProtocol(Airbnb.AIRBNBLOGO, welcomeText, instructionText, neighborhoodPanel, roomTypePanel, lowPricePanel, highPricePanel,
                search, next, previous);


        TabCreator.createTab(pane, "Welcome", Airbnb.HOMEGRAPHIC, false);

        panels.setTranslateX(-40);

        root.setCenter(panels);

        root.setBottom(traverse);

        ToolBar myAirbnb = new ToolBar();

        myAirbnb.setOrientation(Orientation.VERTICAL);

        Button showFavourites = new Button("favourites");
        showFavourites.setOnAction(
                (event) -> {FavouriteProperties.showFavoriteProperties();}
        );
        myAirbnb.getItems().add(showFavourites);

        myAirbnb.setTranslateX(-100);

        root.setLeft(myAirbnb);




        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(root, 1700,800);
        stage.setTitle("JavaFX Example");
        stage.setScene(scene);

        for(Tab tab: panels.getTabs())
        tab.getContent().setOnMouseMoved(
                (event) -> {
                    TranslateTransition slideIn = new TranslateTransition(Duration.millis(250), myAirbnb);
                    if(event.getSceneX() > 0 && event.getSceneX() < 30) {
                        slideIn.setToX(0);
                        slideIn.setCycleCount(1);
                        slideIn.play();
                    }
                    else{
                        slideIn.setToX(-100);
                        slideIn.setCycleCount(1);
                        slideIn.play();
                    }

                }
        );



        panels.minWidthProperty().bind(root.widthProperty());

        MainViewerFactory.styleRoot(root, scene);

        scene.getStylesheets().add(MainViewer.class.getResource("Styling.css").toExternalForm());

        // Show the Stage (window)
        stage.show();

    }

    public static TabPane getPanels(){
        return panels;
    }

    private void selectedNeighborhood(ActionEvent event){userNeighborhood = neighborhood.getValue();}

    private void selectedRoomType(ActionEvent event){userRoomType = roomType.getValue();}

    private void selectedLowPrice(ActionEvent event){
         userLowPrice = Integer.parseInt(lowPrice.getValue());
    }

    private void selectedHighPrice(ActionEvent event){
        userHighPrice = Integer.parseInt(highPrice.getValue());
    }

    private void searchProperties(ActionEvent event) {
        if((lowPrice.getValue() != null) && (highPrice.getValue() != null) && (neighborhood.getValue() != null) && (roomType.getValue() != null)) {
            if (userLowPrice >= 0 && userHighPrice >= userLowPrice) {
                if (userNeighborhood.equals("All")) {
                   /* Tab boroughTab = new Tab();
                    boroughTab.setGraphic(Airbnb.BOROUGHGRAPHIC);
                    boroughTab.setText("Boroughs");
                    boroughTab.setContent(MapFactory.getMapWindow(userLowPrice, userHighPrice));
                    panels.getTabs().add(boroughTab);
                    panels.getSelectionModel().select(boroughTab);*/
                    TabCreator.createTab(MapFactory.getMapWindow(userLowPrice, userHighPrice), "Boroughs", Airbnb.BOROUGHGRAPHIC, true);
                }
                else {
                    if (userRoomType.equals("All")) {
                        if (LondonCSVUtilities.filteredResults(userLowPrice, userHighPrice).get(userNeighborhood).size() != 0) {
                            PropertyViewer propertyViewer = new PropertyViewer(LondonCSVUtilities.filteredResults(userLowPrice, userHighPrice).get(userNeighborhood));
                            Tab propertyTab = new Tab();
                            propertyTab.setText("Properties");
                            propertyTab.setContent(propertyViewer.makeFullPropertyWindow());
                            MainViewer.getPanels().getTabs().add(propertyTab);
                            MainViewer.getPanels().getSelectionModel().select(propertyTab);
                        } else {
                            AlertBox.display("Oh no!", "There are no properties in this area\n" + "for the price range you selected.");
                        }
                    }
                    else {
                        if (LondonCSVUtilities.filteredResults(userLowPrice, userHighPrice, userRoomType).get(userNeighborhood).size() != 0) {
                            PropertyViewer propertyViewer = new PropertyViewer(LondonCSVUtilities.filteredResults(userLowPrice, userHighPrice, userRoomType).get(userNeighborhood));
                            Tab propertyTab = new Tab();
                            propertyTab.setText("Properties");
                            propertyTab.setContent(propertyViewer.makeFullPropertyWindow());
                            MainViewer.getPanels().getTabs().add(propertyTab);
                            MainViewer.getPanels().getSelectionModel().select(propertyTab);
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

