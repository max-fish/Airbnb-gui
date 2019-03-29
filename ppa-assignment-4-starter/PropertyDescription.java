import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.Stack;

public class PropertyDescription {

    AirbnbListing property;
    StackPane icon;
    PropertyDescription(AirbnbListing property, StackPane icon){
        this.property = property;
        this.icon = icon;
        this.icon.setDisable(true);
    }
    public GridPane makeDescriptionWindow(){
        Font infoFont = Font.loadFont(getClass().getResourceAsStream("Montserrat/MontserratAlternates-Medium.otf"), 18);
        GridPane fullGrid = new GridPane();
        fullGrid.prefHeightProperty().bind(MainViewer.getPanels().heightProperty().subtract(5));
        fullGrid.setHgap(10);

        GridPane descriptionGrid = new GridPane();
        descriptionGrid.setVgap(20);
        descriptionGrid.setAlignment(Pos.CENTER);


        TextFlow id = new TextFlow(new Text("ID: " + property.getId()));
        TextFlow hostID = new TextFlow(new Text("Host ID: " + property.getHost_id()));
        TextFlow neighbourhood = new TextFlow(new Text("Neighbourhood: " + property.getNeighbourhood()));
        TextFlow lastReview = new TextFlow(new Text("Last review: " + property.getLastReview()));
        TextFlow reviewsPerMonth = new TextFlow(new Text("Reviews per month: " + property.getReviewsPerMonth()));
        TextFlow totalHostListings = new TextFlow(new Text("Total host listings: " + property.getCalculatedHostListingsCount()));
        TextFlow availability = new TextFlow(new Text("Availability: " + property.getAvailability365()));

        Pane map = new GetGoogleMaps().getMapPane(property.getName(), property.getLatitude(), property.getLongitude());
        map.setPrefHeight(300);
        map.setPrefWidth(500);
        descriptionGrid.addRow(0, map);

        GridPane descriptionSubGrid = new GridPane();
        descriptionSubGrid.setHgap(10);
        descriptionSubGrid.add(id,0,0);
        descriptionSubGrid.add(hostID, 1,0);
        descriptionSubGrid.add(neighbourhood, 2,0);
        descriptionSubGrid.add(lastReview, 0,1);
        descriptionSubGrid.add(reviewsPerMonth, 1, 1);
        descriptionSubGrid.add(totalHostListings, 2,1);
        descriptionSubGrid.add(availability,3,1);


        for(Node node : descriptionSubGrid.getChildren()){
            if(node instanceof TextFlow) {
                TextFlow container = (TextFlow) node;
                Text text = (Text) container.getChildren().get(0);
                text.setFont(infoFont);
                text.setFill(Color.rgb(72, 72, 72));
            }
        }

        descriptionGrid.addRow(1, descriptionSubGrid);

        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100);
        fullGrid.getRowConstraints().addAll(rowConstraints);

        StackPane iconContainer = new StackPane(icon);
        icon.setAlignment(Pos.CENTER);
        iconContainer.prefHeightProperty().bind(fullGrid.heightProperty());
        iconContainer.setBorder(new Border(new BorderStroke(Airbnb.CORAL,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0,3,0,0))));
        GridPane.setValignment(icon, VPos.CENTER);
        iconContainer.setPadding(new Insets(0,10,0,10));


        fullGrid.addColumn(0, iconContainer);
        fullGrid.addColumn(1, descriptionGrid);

        return fullGrid;
    }
}
