import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

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
        GridPane descriptionGrid = new GridPane();

        descriptionGrid.minHeightProperty().bind(MainViewer.getPanels().heightProperty());

        RowConstraints rows = new RowConstraints();
        rows.setPrefHeight(30);


        descriptionGrid.getRowConstraints().addAll(rows, rows, rows, rows, rows, rows, rows);

        TextFlow id = new TextFlow(new Text("ID: " + property.getId()));
        TextFlow hostID = new TextFlow(new Text("Host ID: " + property.getHost_id()));
        TextFlow neighbourhood = new TextFlow(new Text("Neighbourhood: " + property.getNeighbourhood()));
        TextFlow lastReview = new TextFlow(new Text("Last review: " + property.getLastReview()));
        TextFlow reviewsPerMonth = new TextFlow(new Text("Reviews per month: " + property.getReviewsPerMonth()));
        TextFlow totalHostListings = new TextFlow(new Text("Total host listings: " + property.getCalculatedHostListingsCount()));
        TextFlow availability = new TextFlow(new Text("Availability: " + property.getAvailability365()));
        descriptionGrid.addColumn(0, icon);
        descriptionGrid.addRow(0, id);
        descriptionGrid.add(hostID, 1, 1);
        descriptionGrid.add(neighbourhood, 1,2);
        descriptionGrid.add(lastReview, 1,3);
        descriptionGrid.add(reviewsPerMonth, 1,4);
        descriptionGrid.add(totalHostListings, 1,5);
        descriptionGrid.add(availability, 1,6);

        for(Node node : descriptionGrid.getChildren()){
            if(node instanceof TextFlow) {
                TextFlow container = (TextFlow) node;
                Text text = (Text) container.getChildren().get(0);
                text.setFont(infoFont);
                text.setFill(Color.rgb(72, 72, 72));
            }
        }

        GridPane.setValignment(icon, VPos.TOP);
        return descriptionGrid;
    }
}
