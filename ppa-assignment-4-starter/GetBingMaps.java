import javafx.concurrent.Worker;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.List;

/**
 * Creates a pane that stores a html web page, the page contains a bing maps display.
 *
 * @version 0.3.2
 */
public class GetBingMaps {

    /**
     * Creates a Pane containing a WebView of Bing Maps inside it. The method puts a pin on the map based on the
     * co-ordinates displayed and a name under the pin based on the  name input.
     *
     * @param name the name of the pin
     * @param latitude the latitude of the map location renedered
     * @param longitude the longitude of the map
     * @return a panel with a Bing maps instance inside it
     */
    public Pane getLightCanvas(String name, double latitude, double longitude){
        StackPane tbr = new StackPane();

        // create a WebView (for displaying) and it's WebEngine (for rendering and passing though data)
        WebView main = new WebView();
        WebEngine webEngine = main.getEngine();

        loadWebengine(webEngine, "maps.html", name, latitude, longitude);
        tbr.getChildren().add(main);

        return tbr;
    }

    /**
     * Creates the same map pane but uses a birds eye view instead of a light canvas view
     *
     * @param name the name of the pin
     * @param latitude the latitude of the map location renedered
     * @param longitude the longitude of the map
     * @return a panel with a Bing maps instance inside it
     */

    public Pane getBirdsEye(String name, double latitude, double longitude){
        StackPane tbr = new StackPane();

        // create a WebView (for displaying) and it's WebEngine (for rendering and passing though data)
        WebView main = new WebView();
        WebEngine webEngine = main.getEngine();

        loadWebengine(webEngine, "MapsBirdsEye.html", name, latitude, longitude);

        tbr.getChildren().add(main);

        return tbr;
    }

    /**
     * Create a map with pins where all the buildings are listed
     *
     * @param buildings a list of the buildings to display on a map
     * @return a pane with a html page full of
     */
    public Pane getFullMap(List<AirbnbListing> buildings){
        StackPane tbr = new StackPane();

        // create a WebView (for displaying) and it's WebEngine (for rendering and passing though data)
        WebView main = new WebView();
        WebEngine webEngine = main.getEngine();

        // Load the html page into the webengine and start it loading
        webEngine.load(getClass().getResource("mapsAll.html").toExternalForm());

        // Once the page state changes trigger an action
        webEngine.getLoadWorker().stateProperty().addListener(
                (observable, oldValue, newValue) -> {
                    // When the page has loaded there is an attempt to call a javascript method inside the webEngine
                    if (newValue == Worker.State.SUCCEEDED){
                        // If the script cannot be reloaded the program then reloads the page until it can load successfully
                        try {
                            webEngine.executeScript("document.loadMap()");
                        }
                        catch (netscape.javascript.JSException exc){
                            webEngine.reload();
                            System.out.println("Error: " + exc);
                        }
                        try {
                            for ( AirbnbListing listing : buildings) {
                                webEngine.executeScript(
                                        "document.addPin('" + listing.getName().replace("'", "") +
                                                "', " + listing.getLatitude() +
                                                ", " + listing.getLongitude() + ");");
                            }
                        }
                        catch (netscape.javascript.JSException exc){
                            System.out.println("Error: " + exc);
                        }
                    }
                }
        );
        tbr.getChildren().add(main);

        return tbr;
    }

    private void loadWebengine(WebEngine engine,String fileLoad, String name, double latitude, double longitude){
        engine.load(getClass().getResource(fileLoad).toExternalForm());

        engine.getLoadWorker().stateProperty().addListener(
                (observable, oldValue, newValue) -> {
                    // When the page has loaded there is an attempt to call a javascript method inside the webEngine
                    if (newValue == Worker.State.SUCCEEDED){
                        // If the script cannot be reloaded the program then reloads the page until it can load successfully
                        try {
                            engine.executeScript(
                                    "document.loadFile('" + name.replace("'", "") +
                                            "', " + latitude +
                                            ", " + longitude + ");");
                        }
                        catch (netscape.javascript.JSException exc){
                            engine.reload();
                            System.out.println("Error: " + exc);
                        }
                    }
                }
        );
    }
}
