import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import java.util.HashMap;

public class FavouriteProperties {

    private static TilePane favouritePropertiesContainer = new TilePane();

    private static HashMap<Icon, StackPane> favouriteIconToView = new HashMap<>();

    private static HashMap<Icon, Icon> copyToOrigIcon = new HashMap<>();


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
                copyToOrigIcon.remove(favouriteIcon).setFavourite(false);
                favouritePropertiesContainer.getChildren().remove(favouriteIconToView.get(favouriteIcon));
                favouriteIconToView.remove(favouriteIcon);
            }
        }

    }

    public static void showFavoriteProperties(){
        if(favouritePropertiesContainer.getChildren().isEmpty()){
            TabCreator.createSingularTab(makeEmptyScreen(), "Favourites", null, true);
        }
        else{
            TabCreator.createSingularTab(favouritePropertiesContainer, "Favourites", null, true);
        }
    }

    private static TextFlow makeEmptyScreen(){
        Text noFavourites = new Text("You have not selected any properties as one of your favorites yet");
        noFavourites.setFont(Airbnb.HEADERFONT);
        noFavourites.setFill(Color.BLACK);
        noFavourites.setTextAlignment(TextAlignment.CENTER);
        TextFlow noFavouritesContainer = new TextFlow(noFavourites);
        return noFavouritesContainer;
    }

}
