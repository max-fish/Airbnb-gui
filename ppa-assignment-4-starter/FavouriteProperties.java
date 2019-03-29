import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class FavouriteProperties {

    private static TilePane favouriteProperties = new TilePane();



    public static void addFavouriteProperty(AirbnbListing favouriteProperty){

        PropertyViewer favouritePropertyIconCopy = new PropertyViewer(null);
        favouritePropertyIconCopy.favourite();
        favouriteProperties.getChildren().add(favouritePropertyIconCopy.makeIcon(favouriteProperty));
    }

    public static void removeFavouriteProperty(AirbnbListing favouriteProperty){
        favouriteProperties.getChildren().remove(favouriteProperty);
    }

    public static void showFavoriteProperties(){
        if(favouriteProperties.getChildren().isEmpty()){
            TabCreator.createSingularTab(makeEmptyScreen(), "Favourites", null, true);
        }
        else{
            TabCreator.createSingularTab(favouriteProperties, "Favourites", null, true);
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
