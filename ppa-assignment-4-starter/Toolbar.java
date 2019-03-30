import javafx.geometry.Insets;
import javafx.geometry.Orientation;;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Toolbar {
    public static ToolBar makeToolBar(){
        ToolBar myAirbnb = new ToolBar();
        myAirbnb.setOrientation(Orientation.VERTICAL);

        myAirbnb.setStyle("-fx-background-color: rgba(0,0,0,0.5)");

        Rectangle toolBarContainer = new Rectangle();
        toolBarContainer.heightProperty().bind(myAirbnb.heightProperty());
        toolBarContainer.widthProperty().bind(myAirbnb.widthProperty());
        toolBarContainer.setArcWidth(18);
        toolBarContainer.setArcHeight(18);

        myAirbnb.setShape(toolBarContainer);

        Text myAribnbText = new Text("My Airbnb");
        myAribnbText.setFont(Airbnb.TOOLBARFONT);
        myAribnbText.setFill(Airbnb.CORAL);
        myAribnbText.setUnderline(true);


        Button showFavourites = new Button("Favourites");
        showFavourites.setStyle("-fx-background-color: #FD5C63");
        showFavourites.setFont(Airbnb.BUTTONFONT);
        showFavourites.setTextFill(Color.WHITE);
        showFavourites.setGraphic(Airbnb.getImageView(Airbnb.Graphic.FAVOURITEGRAPHIC));
        showFavourites.setOnAction(
                (event) -> {FavouriteProperties.showFavoriteProperties();}
        );

        Button statistics = new Button("Statistics");
        statistics.setStyle("-fx-background-color: #FD5C63");
        statistics.setFont(Airbnb.BUTTONFONT);
        statistics.setTextFill(Color.WHITE);
        statistics.setGraphic(Airbnb.getImageView(Airbnb.Graphic.STATISTICSGRAPHIC));
        statistics.setOnAction(
                (event) -> {new StatisticsPage().showStats();}
        );

        Button fullMap = new Button("Full Map");
        fullMap.setStyle("-fx-background-color: #FD5C63");
        fullMap.setFont(Airbnb.BUTTONFONT);
        fullMap.setTextFill(Color.WHITE);
        fullMap.setGraphic(Airbnb.getImageView(Airbnb.Graphic.MAPGRAPHIC));

        fullMap.setOnAction(
                (event) -> {TabCreator.createSingularTab(new GetBingMaps().getFullMap(StatisticsPage.getListings().subList(0,100)), "Full Map", Airbnb.getImageView(Airbnb.Graphic.MAPGRAPHIC), true);}
        );

        Button showHelp = new Button("Help");
        showHelp.setStyle("-fx-background-color: #FD5C63");
        showHelp.setFont(Airbnb.BUTTONFONT);
        showHelp.setTextFill(Color.WHITE);
        showHelp.setGraphic(Airbnb.getImageView(Airbnb.Graphic.HELPGRAPHIC));

        showHelp.setOnAction(e -> AlertBox.display("User Guidelines", "Welcome to Airbnb HomeFinder. This is an application that allows you to search\n for Airbnb listings in London. The first thing you should know about this\n application is that " +
                "you can use the next and previous buttons at the\n bottom of the window, as well as the tabs at the top of the window to traverse\n through the different panels. The top panel in the welcome page\n is made up of different criteria that allows " +
                "a user to personalize\n their home-finding process. Once a user presses search this will bring up \na map of all the boroughs in London. It is designed as a heat map, so darker colours\n indicate more homes found, given the search criteria" +
                ", while lighter\n colours indicate fewer homes found. You can then press on the borough buttons to\n show all the properties in that borough. As always have fun finding your next unique home or experience.\n Happy HomeFinding - Airbnb", 500, 1400));


        myAirbnb.getItems().addAll(myAribnbText, makeSeparator(), showFavourites,
                makeSeparator(), statistics, makeSeparator(), fullMap,
            makeSeparator(), showHelp);

        myAirbnb.setTranslateX(-200);

        return myAirbnb;
    }

    private static Separator makeSeparator(){
        Separator separator = new Separator(Orientation.HORIZONTAL);
        separator.setPadding(new Insets(10,0,10,0));
        return separator;
    }
}
