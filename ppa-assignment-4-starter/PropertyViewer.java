import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.effect.DropShadow;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.LinkedHashSet;


public class PropertyViewer extends Application {

    private LinkedHashSet<AirbnbListing> properties = new LinkedHashSet<>();

    public PropertyViewer(LinkedHashSet<AirbnbListing> properties){
        this.properties = properties;
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
       /* AirbnbDataLoader loader = new AirbnbDataLoader();
        Scene scene = new Scene(makePropertyList(), 300,300);
        primaryStage.setTitle("JavaFX Example");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }

    public Pane propertyListContainer(){
        Pane container = new Pane();
        container.getChildren().add(makePropertyList());
        return container;
    }

    public TilePane makePropertyList(){
        TilePane propertyList = new TilePane();
        propertyList.setHgap(10);
        propertyList.setVgap(10);
        propertyList.setPadding(new Insets(10,10,10,10));
        for(AirbnbListing property : properties){
            propertyList.getChildren().add(makeIcon(property));
        }
        return propertyList;
    }

    public StackPane makeIcon(AirbnbListing property){
        StackPane icon = new StackPane();
        Rectangle rect = new Rectangle();
        DropShadow ds = new DropShadow();
        ds.setOffsetY(5);
        ds.setColor(Color.LIGHTGREY);

        rect.setFill(Color.WHITE);
        rect.setHeight(215);
        rect.setWidth(200);
        rect.setArcWidth(20);
        rect.setArcHeight(20);
        rect.setFill(MainViewer.CORAL);

        GridPane infoLayout = new GridPane();
        infoLayout.setBorder(new Border(new BorderStroke(MainViewer.CORAL,
                BorderStrokeStyle.SOLID, new CornerRadii(18,18,0,0,false), new BorderWidths(2,2,0,2))));
        infoLayout.setStyle("-fx-background-color: #FFFFFF;");
        infoLayout.setMaxHeight(200);
        Label picLabel = new Label("Picture here");
        Label nameLabel = new Label(property.getName());
        infoLayout.addRow(0, picLabel);
        infoLayout.addRow(1, nameLabel);
        GridPane.setHalignment(picLabel, HPos.CENTER);
        GridPane.setHalignment(nameLabel, HPos.CENTER);
        infoLayout.setAlignment(Pos.CENTER);

        RowConstraints pictureRow = new RowConstraints();
        pictureRow.setPercentHeight(66);

        RowConstraints infoRow = new RowConstraints();
        infoRow.setPercentHeight(34);


        infoLayout.getRowConstraints().addAll(pictureRow,infoRow);
        icon.setMaxHeight(200);
        icon.setMaxWidth(200);
        icon.getChildren().add(rect);
        icon.getChildren().add(infoLayout);
        StackPane.setAlignment(infoLayout, Pos.TOP_CENTER);

        icon.setOnMouseEntered(
                (event) -> {rect.setEffect(ds);}

        );
        icon.setOnMouseExited(
                (event) -> {rect.setEffect(null);}
        );

        return icon;

    }
}
