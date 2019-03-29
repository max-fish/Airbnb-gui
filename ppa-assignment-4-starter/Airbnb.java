import javafx.geometry.Pos;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Airbnb {
    public static final Font HEADERFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Regular.otf").toExternalForm(), 50);

    public static final Font INSTRUCTIONFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Light.otf").toExternalForm(), 25);

    public static final Font PROPERTYINFOFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Medium.otf").toExternalForm(), 18);

    public static final Font BUTTONFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-SemiBold.otf").toExternalForm(), 15);

    public static final Font COMBOBOXFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Light.otf").toExternalForm(), 15);

    public static final Font TOOLTIPFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Light.otf").toExternalForm(), 10);

    public static final Color CORAL = Color.rgb(253,92,99);

    public static final Color GREY = Color.rgb(72,72,72);

    public static final ImageView AIRBNBLOGO = new ImageView(new Image(Airbnb.class.getResourceAsStream("thin_white_airbnb_logo.png")));

    public static final ImageView BOROUGHGRAPHIC = new ImageView(new Image(Airbnb.class.getResourceAsStream("borough_icon.png")));

    public static final ImageView HOMEGRAPHIC = new ImageView(new Image(Airbnb.class.getResourceAsStream("airbnb_heart_icon.png")));

    public static final ImageView PROPERTYGRAPHIC = new ImageView(new Image(Airbnb.class.getResourceAsStream("house_icon.png")));



    public static void styleGraphics(){
        AIRBNBLOGO.setFitHeight(100);
        AIRBNBLOGO.setPreserveRatio(true);
        AIRBNBLOGO.setSmooth(true);
        BOROUGHGRAPHIC.setFitHeight(20);
        BOROUGHGRAPHIC.setPreserveRatio(true);
        HOMEGRAPHIC.setFitHeight(20);
        HOMEGRAPHIC.setPreserveRatio(true);
        PROPERTYGRAPHIC.setFitHeight(20);
        PROPERTYGRAPHIC.setPreserveRatio(true);
    }
}
