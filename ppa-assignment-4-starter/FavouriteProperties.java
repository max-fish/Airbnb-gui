import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FavouriteProperties {

    private static TilePane favouriteProperties = new TilePane();



    public static void addFavouriteProperty(StackPane favouriteProperty){
        favouriteProperties.getChildren().add(favouriteProperty);
    }

    public static void removeFavouriteProperty(StackPane favouriteProperty){
        favouriteProperties.getChildren().remove(favouriteProperty);
    }

    public static void showFavoriteProperties(){
        Tab favouritesTab = new Tab();
        if(favouriteProperties.getChildren().isEmpty()){
            favouritesTab.setContent(makeEmptyScreen());
        }
        else{
            favouritesTab.setContent(favouriteProperties);
        }
        MainViewer.getPanels().getTabs().add(favouritesTab);
    }

    private static Text makeEmptyScreen(){
        Text noFavourites = new Text();
        noFavourites.setText("You have not selected any properties as your favorite yet");
        noFavourites.setFont(Airbnb.HEADERFONT);
        noFavourites.setFill(Color.BLACK);
        noFavourites.setTextAlignment(TextAlignment.CENTER);
        return noFavourites;
    }

}
