import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

   private BorderPane root;

    private static TabPane panels;

    private Tab welcomeTab;

    private BorderPane pane;

   private ComboBox<String> lowPrice = new ComboBox<String>();

    private ComboBox<String> highPrice = new ComboBox<String>();

    @Override
    public void start(Stage stage) throws Exception
    {

        root = new BorderPane();

        panels = new TabPane();

        pane = new BorderPane();

        MainViewerFactory.stylePane(pane);


        Text welcomeText = new Text();

       MainViewerFactory.styleWelcomeText(welcomeText);

        Image airbnbLogo = new Image(getClass().getResourceAsStream("thin_white_airbnb_logo.png"));

        StackPane welcomePane = new StackPane();

        ImageView airbnbLogoView = new ImageView(airbnbLogo);

        MainViewerFactory.styleAirbnbLogoView(airbnbLogoView);

        Text instructionText = new Text();

        MainViewerFactory.styleInstructionText(instructionText);

        MainViewerFactory.styleWelcomePane(welcomePane, airbnbLogoView, instructionText);

        Rectangle welcomePaneContainer = new Rectangle();

        MainViewerFactory.styleWelcomePaneContainer(welcomePaneContainer);

        welcomePane.getChildren().addAll(welcomePaneContainer, airbnbLogoView, welcomeText, instructionText);

       pane.setCenter(welcomePane);


        HBox selection = new HBox();
        selection.setSpacing(0);


        Label lowPriceLabel = new Label("Low Price: ");

        Label highPriceLabel = new Label("High Price: ");

        lowPrice.setEditable(true);
        lowPrice.getItems().addAll("100","200","300");


        highPrice.setEditable(true);
        highPrice.getItems().addAll("1000","2000","5000");

        lowPrice.setOnAction(this::selectedLowPrice);

        highPrice.setOnAction(this::selectedHighPrice);

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

        selection.getChildren().addAll(lowPricePanel, highPricePanel, search);

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


        //adds borders under the selection bar and above the traverse bar

       // selection.setBorder(new Border(new BorderStroke(Color.GREY,
         //       BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,0,1,0), new Insets(0,0,-10,0))));

        selection.setPadding(new Insets(0,0,20,0));


        //traverse.setBorder(new Border(new BorderStroke(Color.GREY,
          //      BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1,0,0,0), new Insets(-10,10,0,10))));

        //traverse.setPadding(new Insets(10,0,0,0));

        MainViewerFactory.fadeInProtocol(airbnbLogoView, welcomeText, instructionText, lowPricePanel, highPricePanel,
                search, next, previous);

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


        panels.minWidthProperty().bind(root.widthProperty());

        MainViewerFactory.styleRoot(root, scene);

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

    private void searchProperties(ActionEvent event)
    {
        Tab boroughTab = new Tab();
        boroughTab.setText("Boroughs");
        boroughTab.setContent(MapFactory.getMapWindow(userLowPrice, userHighPrice));
        panels.getTabs().add(boroughTab);
        panels.getSelectionModel().select(boroughTab);
    }
}
