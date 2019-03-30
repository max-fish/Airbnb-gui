import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * A class that holds all the fonts and styling used for our project. We use multiple different types of fonts in different windows,
 * and we also use multiple different images that are used to style our GUI. We also define the static colors here that we use to style
 * our GUI.
 */
public class Airbnb {
    public static final Font HEADERFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Regular.otf").toExternalForm(), 50);

    public static final Font INSTRUCTIONFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Light.otf").toExternalForm(), 25);

    public static final Font PROPERTYINFOFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Medium.otf").toExternalForm(), 18);

    public static final Font BUTTONFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-SemiBold.otf").toExternalForm(), 15);

    public static final Font COMBOBOXFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Light.otf").toExternalForm(), 15);

    public static final Font TOOLTIPFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Light.otf").toExternalForm(), 20);

    public static final Font TOOLBARFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-BoldItalic.otf").toExternalForm(), 15);

    public static final Font FAVOURITEBARFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Regular.otf").toExternalForm(), 25);

    public static final Color CORAL = Color.rgb(253, 92, 99);

    public static final Color GREY = Color.rgb(72, 72, 72);

    private static final Image AIRBNBLOGO = new Image(Airbnb.class.getResourceAsStream("thin_white_airbnb_logo.png"));

    private static final Image BOROUGHGRAPHIC = new Image(Airbnb.class.getResourceAsStream("borough_icon.png"));

    private static final Image HOMEGRAPHIC = new Image(Airbnb.class.getResourceAsStream("airbnb_heart_icon.png"));

    private static final Image PROPERTYGRAPHIC = new Image(Airbnb.class.getResourceAsStream("house_icon.png"));

    private static final Image PROPERTYDESCRIPTIONGRAPHIC = new Image(Airbnb.class.getResourceAsStream("property_description_icon.png"));

    private static final Image MAPGRAPHIC = new Image(Airbnb.class.getResourceAsStream("map_icon.png"));

    private static final Image STATISTICSGRAPHIC = new Image(Airbnb.class.getResourceAsStream("statistics_icon.png"));

    private static final Image HELPGRAPHIC = new Image(Airbnb.class.getResourceAsStream("help_icon.png"));

    private static final Image FAVOURITEGRAPHIC = new Image(Airbnb.class.getResourceAsStream("favourite_icon.png"));


    public enum Graphic {
        AIRBNBLOGO, BOROUGHGRAPHIC, HOMEGRAPHIC, PROPERTYGRAPHIC, PROPERTYDESCRIPTIONGRAPHIC, MAPGRAPHIC, STATISTICSGRAPHIC, HELPGRAPHIC, FAVOURITEGRAPHIC
    }

    public static ImageView getImageView(Graphic image) {
        switch (image) {
            case AIRBNBLOGO:
                ImageView logoView = new ImageView(AIRBNBLOGO);
                logoView.setFitHeight(100);
                logoView.setPreserveRatio(true);
                logoView.setSmooth(true);
                return logoView;
            case BOROUGHGRAPHIC:
                ImageView boroughView = new ImageView(BOROUGHGRAPHIC);
                boroughView.setFitHeight(20);
                boroughView.setPreserveRatio(true);
                return boroughView;
            case HOMEGRAPHIC:
                ImageView homeView = new ImageView(HOMEGRAPHIC);
                homeView.setFitHeight(20);
                homeView.setPreserveRatio(true);
                return homeView;
            case PROPERTYGRAPHIC:
                ImageView propertyView = new ImageView(PROPERTYGRAPHIC);
                propertyView.setFitHeight(20);
                propertyView.setPreserveRatio(true);
                return propertyView;
            case PROPERTYDESCRIPTIONGRAPHIC:
                ImageView propertyDescriptionView = new ImageView(PROPERTYDESCRIPTIONGRAPHIC);
                propertyDescriptionView.setFitHeight(20);
                propertyDescriptionView.setPreserveRatio(true);
                return propertyDescriptionView;
            case MAPGRAPHIC:
                ImageView mapView = new ImageView(MAPGRAPHIC);
                mapView.setFitHeight(20);
                mapView.setPreserveRatio(true);
                return mapView;
            case STATISTICSGRAPHIC:
                ImageView statisticsView = new ImageView(STATISTICSGRAPHIC);
                statisticsView.setFitHeight(20);
                statisticsView.setPreserveRatio(true);
                return statisticsView;

            case HELPGRAPHIC:
                ImageView helpView = new ImageView(HELPGRAPHIC);
                helpView.setFitHeight(20);
                helpView.setPreserveRatio(true);
                return helpView;

            case FAVOURITEGRAPHIC:
                ImageView favouriteView = new ImageView(FAVOURITEGRAPHIC);
                favouriteView.setFitHeight(20);
                favouriteView.setPreserveRatio(true);
                return favouriteView;
        }
        return null;
    }
}
