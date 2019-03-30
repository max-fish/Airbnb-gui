import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.HashMap;

public class FavouriteProperties {

    private static BorderPane favouriteWindow = new BorderPane();

    private static TilePane favouritePropertiesContainer = new TilePane();

    private static HashMap<Icon, StackPane> favouriteIconToView = new HashMap<>();

    private static HashMap<Icon, Icon> copyToOrigIcon = new HashMap<>();

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


    public static void addFavouriteProperty(Icon favouriteProperty){
        Icon favouritePropertyCopy = favouriteProperty.clone(true);
        copyToOrigIcon.put(favouritePropertyCopy, favouriteProperty);
        StackPane favouritePropertyCopyIcon = favouritePropertyCopy.makeIcon();
        favouriteIconToView.put(favouritePropertyCopy, favouritePropertyCopyIcon);
        favouritePropertiesContainer.getChildren().add(favouritePropertyCopyIcon);
    }

    public static void removeFavouriteProperty(Icon favouriteProperty){
        for(Icon favouriteIcon : favouriteIconToView.keySet()){
            if(favouriteIcon.getAirbnbListing().equals(favouriteProperty.getAirbnbListing())){
                copyToOrigIcon.remove(favouriteIcon).unfavourite();
                favouritePropertiesContainer.getChildren().remove(favouriteIconToView.get(favouriteIcon));
                favouriteIconToView.remove(favouriteIcon);
            }
        }

    }

    public static void showFavoriteProperties(){
            TabCreator.createSingularTab(favouriteWindow, "Favourites", null, true);
    }

}
