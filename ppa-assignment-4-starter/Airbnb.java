import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Airbnb {
    public static final Font HEADERFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Regular.otf").toExternalForm(), 50);

    public static final Font INSTRUCTIONFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Light.otf").toExternalForm(), 25);

    public static final Font PROPERTYINFOFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Medium.otf").toExternalForm(), 18);

    public static final Font BUTTONFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-SemiBold.otf").toExternalForm(), 15);

    public static final Font COMBOBOXFONT = Font.loadFont(Airbnb.class.getResource("Montserrat/MontserratAlternates-Light.otf").toExternalForm(), 15);

    public static final Color CORAL = Color.rgb(253,92,99);

    public static final Color GREY = Color.rgb(72,72,72);

    public static final ImageView AIRBNBLOGO = new ImageView(new Image(Airbnb.class.getResourceAsStream("thin_white_airbnb_logo.png")));

    public static final ImageView BOROUGHGRAPHIC = new ImageView(new Image(Airbnb.class.getResourceAsStream("airbnb_borough.png")));

    public static final ImageView HOMEGRAPHIC = new ImageView(new Image(Airbnb.class.getResourceAsStream("airbnb_home.png")));
}
