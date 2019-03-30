import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PropertyViewerFactory {
    public static void styleTilePane(TilePane propertyList){
        propertyList.setAlignment(Pos.CENTER);
        propertyList.setHgap(10);
        propertyList.setVgap(50);
        propertyList.setPadding(new Insets(10,10,10,10));
    }
    public static void styleInfoGrid(GridPane infoLayout){
        infoLayout.setMinSize(250, 300);
        infoLayout.setMaxSize(250, 300);

        RowConstraints pictureRow = new RowConstraints();
        pictureRow.setPercentHeight(60);
        pictureRow.setVgrow(Priority.NEVER);

        RowConstraints otherRows = new RowConstraints();
        otherRows.setVgrow(Priority.ALWAYS);

        infoLayout.getRowConstraints().addAll(pictureRow, otherRows, otherRows, otherRows);
        infoLayout.setBorder(new Border(new BorderStroke(Airbnb.CORAL,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2,2,0,2))));
        infoLayout.setStyle("-fx-background-color: #FFFFFF;");
    }
    public static void styleRectangle(Rectangle rect, GridPane infoLayout){
        rect.heightProperty().bind(infoLayout.heightProperty().add(15));
        rect.widthProperty().bind(infoLayout.widthProperty());
        rect.setArcWidth(20);
        rect.setArcHeight(20);
        rect.setFill(Airbnb.CORAL);
    }
    public static void styleDropShadow(DropShadow ds){
        ds.setOffsetY(5);
        ds.setColor(Color.LIGHTGREY);
    }
    public static void styleStackPane(GridPane infoLayout, Rectangle rect){
        StackPane.setAlignment(infoLayout, Pos.TOP_CENTER);
        StackPane.setAlignment(rect, Pos.TOP_LEFT);
    }
    public static void styleNameLabelContainer(TextFlow nameLabelContainer){
        nameLabelContainer.setBorder(new Border(new BorderStroke(Airbnb.CORAL,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1,0,1,0))));
    }
    public static void styleGridContent(GridPane infoLayout){
        for(Node node : infoLayout.getChildren()){
            if(node instanceof TextFlow){
                TextFlow container = (TextFlow) node;
                container.setMinWidth(infoLayout.getMaxWidth()-3);
                container.setMaxWidth(infoLayout.getMaxWidth()-3);
                Text label = (Text) container.getChildren().get(0);
                label.setWrappingWidth(infoLayout.getWidth()-3);
                label.setFont(Airbnb.PROPERTYINFOFONT);
                label.setFill(Airbnb.GREY);
                GridPane.setValignment(container, VPos.CENTER);
            }
        }
    }
    public static void styleIcon(StackPane icon, GridPane infoLayout){
        icon.maxHeightProperty().bind(infoLayout.heightProperty());
        icon.maxWidthProperty().bind(infoLayout.widthProperty());
        DropShadow ds = new DropShadow();

        styleDropShadow(ds);

        icon.setOnMouseEntered(
                (event) -> {icon.setEffect(ds);}
        );
        icon.setOnMouseExited(
                (event) -> {icon.setEffect(null);}
        );

    }

    public static void styleHeaderText(Label headerText){
        headerText.setText("Your Properties");
        headerText.setFont(Airbnb.HEADERFONT);
        headerText.setTextFill(Airbnb.GREY);
    }

    /**
     * This is a method that styles the favourite icon.
     * @param favouriteIcon The favourite icon to be styled.
     * @param rect
     * @param infoLayout
     */
    public static void styleFavouriteIcon(ImageView favouriteIcon, Rectangle rect, GridPane infoLayout){
        favouriteIcon.setFitHeight(40);
        favouriteIcon.setPreserveRatio(true);
        favouriteIcon.setSmooth(true);
        favouriteIcon.setTranslateX(-5);
        favouriteIcon.setTranslateY(5);

        StackPane.setAlignment(favouriteIcon, Pos.TOP_RIGHT);
    }
}
