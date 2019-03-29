import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 *
 * @version 0.3.2
 */
public class GetGoogleMaps {
    public Pane getMapPaneLightCanvas(String name, double latit, double longit){
        StackPane tbr = new StackPane();
        WebView main = new WebView();
        WebEngine webEngine = main.getEngine();
        webEngine.load(getClass().getResource("maps.html").toExternalForm());
        webEngine.getLoadWorker().stateProperty().addListener(
            (ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) -> {
                if (newValue == Worker.State.SUCCEEDED){
                        try {
                            webEngine.executeScript("document.loadFile('" + name + "', " + latit + ", " + longit + ");");
                        }
                        catch (netscape.javascript.JSException exc){
                            webEngine.reload();
                        }
                }
            }
        );
        tbr.getChildren().add(main);

        return tbr;
    }

    public Pane getMapPaneBirdsEye(String name, double latit, double longit){
        StackPane tbr = new StackPane();
        WebView main = new WebView();
        WebEngine webEngine = main.getEngine();
        webEngine.load(getClass().getResource("MapsBirdsEye.html").toExternalForm());
        webEngine.getLoadWorker().stateProperty().addListener(
                (ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) -> {
                    if (newValue == Worker.State.SUCCEEDED){
                        try {
                            webEngine.executeScript("document.loadFile('" + name + "', " + latit + ", " + longit + ");");
                        }
                        catch (netscape.javascript.JSException exc){
                            webEngine.reload();
                        }
                    }
                }
        );
        tbr.getChildren().add(main);

        return tbr;
    }
}