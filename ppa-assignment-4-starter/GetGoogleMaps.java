import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 *
 * @version 0.3.2
 */
public class GetGoogleMaps {
    public Pane getMapPane(String name, double latit, double longit){
        StackPane tbr = new StackPane();

        WebView main = new WebView();
        WebEngine webEngine = main.getEngine();
        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                        if(newValue == Worker.State.SUCCEEDED){
                            webEngine.executeScript("document.loadFile('" + name + "', "+ latit + ", " + longit + ");");
                        }
                    }
                }
        );
        webEngine.load(getClass().getResource("maps.html").toExternalForm());

        tbr.getChildren().add(main);

        return tbr;
    }
}