import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.HashMap;

/**
 * A class designed to store the user's selected favourite properties.
 */
public class FavouriteProperties {

    private static BorderPane favouriteWindow = new BorderPane();

    private static TilePane favouritePropertiesContainer = new TilePane();

    private static HashMap<Icon, StackPane> favouriteIconToView = new HashMap<>();

    private static HashMap<Icon, Icon> copyToOrigIcon = new HashMap<>();

    /**
     * A method designed to set up the content that will be displayed inside the favourite property tab.
     */
    public static void setUp(){
        Text favouritesText = new Text("Your favourite properties will show up here");
        favouritesText.wrappingWidthProperty().bind(favouriteWindow.widthProperty());
        favouritesText.setFont(Airbnb.HEADERFONT);
        favouritesText.setFill(Color.BLACK);
        favouritePropertiesContainer.setHgap(20);
        favouritePropertiesContainer.setVgap(20);
        favouriteWindow.setTop(favouritesText);
        favouriteWindow.setCenter(favouritePropertiesContainer);
    }

    /**
     * A method designed to add a property to the favourite property list.
     * @param favouriteProperty
     */
    public static void addFavouriteProperty(Icon favouriteProperty){
        Icon favouritePropertyCopy = favouriteProperty.clone(true);
        copyToOrigIcon.put(favouritePropertyCopy, favouriteProperty);
        StackPane favouritePropertyCopyIcon = favouritePropertyCopy.makeIcon();
        favouriteIconToView.put(favouritePropertyCopy, favouritePropertyCopyIcon);
        favouritePropertiesContainer.getChildren().add(favouritePropertyCopyIcon);
    }

    /**
     * A method designed to remove a property from the favourite property list .
     * @param favouriteProperty
     */
    public static void removeFavouriteProperty(Icon favouriteProperty){
        for(Icon favouriteIcon : favouriteIconToView.keySet()){
            if(favouriteIcon.getAirbnbListing().equals(favouriteProperty.getAirbnbListing())){
                copyToOrigIcon.remove(favouriteIcon).unfavourite();
                favouritePropertiesContainer.getChildren().remove(favouriteIconToView.get(favouriteIcon));
                favouriteIconToView.remove(favouriteIcon);
            }
        }

    }

    /**
     * A method which creates a new tab which displays the properties that were selected as a favourite by the user.
     */
    public static void showFavoriteProperties(){
            TabCreator.createSingularTab(favouriteWindow, "Favourites", null, true);
    }

}
