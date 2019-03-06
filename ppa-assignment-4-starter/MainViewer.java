

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Rectangle;
/**
 *
 * @version 0.1.0
 */
public class MainViewer extends Application
{

    private int userLowPrice;
    private int userHighPrice;

    ComboBox<Integer> lowPrice = new ComboBox<Integer>();

    ComboBox<Integer> highPrice = new ComboBox<Integer>();

    @Override
    public void start(Stage stage) throws Exception
    {


        // Create a new border pane
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);

        Rectangle welcome = new Rectangle();

        welcome.heightProperty().bind(pane.heightProperty());

        welcome.widthProperty().bind(pane.widthProperty());


        welcome.setFill(Color.GREY);


        pane.setCenter(welcome);

        pane.getCenter().setScaleX(0.8);

        pane.getCenter().setScaleY(0.75);



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


        selection.getChildren().addAll(pricePanel);
        selection.setAlignment(Pos.CENTER_RIGHT);



        pane.setTop(selection);


        AnchorPane traverse = new AnchorPane();

        Button previous = new Button("Previous");

        Button next = new Button("next");


        traverse.getChildren().addAll(previous,next);

        traverse.setLeftAnchor(previous, (double) 10);

        traverse.setRightAnchor(next, (double) 10);


        pane.setBottom(traverse);


        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 300,300);
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

    /**
     * This will be executed when the button is clicked
     * It increments the count by 1
     */
}
