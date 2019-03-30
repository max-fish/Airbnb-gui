import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

public class Icon {
    private AirbnbListing property;
    private boolean favourite;
    private Criteria criteria;
    public Icon(AirbnbListing property, boolean favourite, Criteria criteria){
        this.property = property;
        this.favourite = favourite;
        this.criteria = criteria;
    }
    public StackPane makeIcon(){
        StackPane icon = new StackPane();

        GridPane infoLayout = new GridPane();

        PropertyViewerFactory.styleInfoGrid(infoLayout);

        Text priceText = new Text("Price: £" + property.getPrice());
        Text reviewsText = new Text("# of Reviews: " + property.getNumberOfReviews());
        Text nightsText = new Text("Minimum nights: " + property.getMinimumNights());

        Pane internetMapDiaplay = new GetGoogleMaps().getMapPaneLightCanvas(property.getName(), property.getLatitude(), property.getLongitude());



        TextFlow priceLabelContainer = new TextFlow(priceText);
        TextFlow reviewsLabelContainer = new TextFlow(reviewsText);
        TextFlow nightsLabelContainer = new TextFlow(nightsText);

        addRowsToGridpane(
                infoLayout,
                new Pane[]{
                        internetMapDiaplay,
                        priceLabelContainer,
                        reviewsLabelContainer,
                        nightsLabelContainer
                }
        );

        for(Node node : infoLayout.getChildren()){
            if(node instanceof TextFlow){
                TextFlow container = (TextFlow) node;
                container.setMinWidth(infoLayout.getMaxWidth()-3);
                container.setMaxWidth(infoLayout.getMaxWidth()-3);
                if(container.getChildren().get(0) instanceof Text){
                    Text label = (Text) container.getChildren().get(0);
                    label.setWrappingWidth(infoLayout.getWidth()-3);
                    label.setFill(Color.rgb(72,72,72));
                    container.setMaxHeight(label.getFont().getSize());
                }
            }
        }

        PropertyViewerFactory.styleGridContent(infoLayout);

        Rectangle rect = new Rectangle();

        PropertyViewerFactory.styleRectangle(rect, infoLayout);

        PropertyViewerFactory.styleStackPane(infoLayout, rect);

        ImageView favouriteIcon = new ImageView(new Image(getClass().getResourceAsStream("favourite_icon.png")));

        PropertyViewerFactory.styleFavouriteIcon(favouriteIcon, rect, infoLayout);

        Lighting lighting = new Lighting();
        lighting.setSpecularConstant(0.0);
        lighting.setSpecularExponent(0.0);
        lighting.setSurfaceScale(0.0);

        favouriteIcon.setOnMouseEntered(
                (event) -> {
                    lighting.setDiffuseConstant(1.6);
                    lighting.setLight(new Light.Distant(45, 45, Airbnb.CORAL));
                    favouriteIcon.setEffect(lighting);
                }
        );


        favouriteIcon.setOnMouseExited(
                (event) -> {
                    lighting.setLight(null);
                    favouriteIcon.setEffect(lighting);
                }

        );


        favouriteIcon.setOnMouseClicked(
                (event) -> {
                    Lighting pressedLighting = new Lighting();
                    pressedLighting.setDiffuseConstant(1.6);
                    pressedLighting.setSpecularConstant(0.0);
                    pressedLighting.setSpecularExponent(0.0);
                    pressedLighting.setSurfaceScale(0.0);
                    pressedLighting.setLight(new Light.Distant(45, 45, Airbnb.CORAL));
                    if(favourite){
                        favourite = false;
                        favouriteIcon.setOnMouseEntered(
                                (subEvent) -> {
                                    lighting.setDiffuseConstant(1.6);
                                    lighting.setLight(new Light.Distant(45, 45, Airbnb.CORAL));
                                    favouriteIcon.setEffect(lighting);
                                }
                        );


                        favouriteIcon.setOnMouseExited(
                                (subEvent) -> {
                                    lighting.setLight(null);
                                    favouriteIcon.setEffect(lighting);
                                }

                        );

                        FavouriteProperties.removeFavouriteProperty(this);

                        TranslateTransition favouriteBarSlidIn = new TranslateTransition();
                        favouriteBarSlidIn.setByY(-100);
                        favouriteBarSlidIn.setNode(MainViewer.getUnfavouriteBar());
                        favouriteBarSlidIn.setDuration(Duration.millis(1000));
                        favouriteBarSlidIn.setCycleCount(2);
                        favouriteBarSlidIn.setAutoReverse(true);
                        favouriteBarSlidIn.play();
                    }
                    else{
                        favourite = true;
                        lighting.setDiffuseConstant(1.6);
                        lighting.setLight(new Light.Distant(45, 45, Airbnb.CORAL));
                        favouriteIcon.setEffect(pressedLighting);
                        favouriteIcon.setOnMouseEntered(null);
                        favouriteIcon.setOnMouseExited(null);


                        FavouriteProperties.addFavouriteProperty(this);

                        TranslateTransition favouriteBarSlidIn = new TranslateTransition();
                        favouriteBarSlidIn.setByY(-100);
                        favouriteBarSlidIn.setNode(MainViewer.getFavouriteBar());
                        favouriteBarSlidIn.setCycleCount(2);
                        favouriteBarSlidIn.setDuration(Duration.millis(1000));
                        favouriteBarSlidIn.setAutoReverse(true);
                        favouriteBarSlidIn.play();
                    }
                }
        );

        icon.getChildren().add(rect);
        icon.getChildren().add(infoLayout);
        icon.getChildren().add(favouriteIcon);


        PropertyViewerFactory.styleIcon(icon, infoLayout);


        infoLayout.setOnMouseClicked(
                (event) -> {
                    if(event.getButton().equals(MouseButton.PRIMARY)){
                        if(event.getClickCount() == 2) {
                            PropertyDescription propertyDescription = new PropertyDescription(property, clone(false).makeIcon());
                            TabCreator.createTab(propertyDescription, propertyDescription.makeDescriptionWindow(), "Property", null, true, criteria);
                        }
                    }
                }
        );

        rect.setOnMouseClicked(
                (event) -> {
                    if(event.getButton().equals(MouseButton.PRIMARY)) {
                        if(event.getClickCount() == 2) {
                            PropertyDescription propertyDescription = new PropertyDescription(property, clone(false).makeIcon());
                            TabCreator.createTab(propertyDescription, propertyDescription.makeDescriptionWindow(), "Property", null, true, criteria);

                        }
                    }
                }

        );

        return icon;
    }
    private void addRowsToGridpane(GridPane toBeAddedTo, Pane[] panes){
        for(int x = 0; x < panes.length; x++){
            toBeAddedTo.addRow(x, panes[x]);
        }
    }

    public Icon clone(boolean favourite){
        return new Icon(property, favourite, criteria);
    }

    public AirbnbListing getAirbnbListing(){
        return property;
    }

    public boolean isFavourite(){
        return favourite;
    }
    public void unfavourite(){
        favourite = false;
    }


    }
