import javafx.concurrent.Worker;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

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
    public Pane getMapPane(String name, double latitude, double longitude){
        StackPane tbr = new StackPane();

        // create a WebView (for displaying) and it's WebEngine (for rendering and passing though data)
        WebView main = new WebView();
        WebEngine webEngine = main.getEngine();

        // Load the html page into the webengine and start it loading
        webEngine.load(getClass().getResource("maps.html").toExternalForm());

        // Once the page state changes trigger an action
        webEngine.getLoadWorker().stateProperty().addListener(
            (observable, oldValue, newValue) -> {
                // When the page has loaded there is an attempt to call a javascript method inside the webEngine
                if (newValue == Worker.State.SUCCEEDED){
                    // If the script cannot be reloaded the program then reloads the page until it can load successfully
                    try {
                        webEngine.executeScript(
                                "document.loadFile('" + name.replace("'", "") +
                                "', " + latitude +
                                ", " + longitude + ");");
                    }
                    catch (netscape.javascript.JSException exc){
                        webEngine.reload();
                        System.out.println("Error: " + exc);
                    }
                }
            }
        );
        tbr.getChildren().add(main);

        return tbr;
    }
}
