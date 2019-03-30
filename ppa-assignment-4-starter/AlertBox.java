import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Produces an alert box when called that states an error made, normally when the user has made an error in inputting
 * the search criteria. It is also used to create the user instruction box, whereby the user is informed of how
 * to use the application.
 * @version 0.1.0
 */
public class AlertBox {

    /**
     * A method that displays an alert box message, whether that is general information on how to use the software
     * or a message indicating that an error has occurred when selecting the search criteria.
     * @param title The title of the alert box window.
     * @param message The message that you want to display inside the alert box scene.
     * @param height The height of the AlertBox window.
     * @param width The width of the AlertBox window.
     */
    public static void display(String title, String message, int height, int width) {
        Stage alertWindow = new Stage();
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);
        alertWindow.setHeight(height);
        alertWindow.setWidth(width);

        Text alertText = new Text();
        alertText.setText(message);
        alertText.setFont(Airbnb.INSTRUCTIONFONT);
        Button closeButton = new Button("   OK   ");
        closeButton.setStyle("-fx-base: #ff5a5f; -fx-text-fill: white; -fx-focus-color: #ffffff; -fx-faint-focus-color: #ffffff; -fx-background-radius: 10, 10, 10, 10");
        closeButton.setOnAction(e -> alertWindow.close());

        TextFlow alertTextFlow = new TextFlow();
        alertTextFlow.getChildren().add(alertText);
        alertTextFlow.setTextAlignment(TextAlignment.CENTER);

        VBox alertMessageContainer = new VBox();
        alertMessageContainer.getChildren().addAll(alertText, closeButton);
        alertMessageContainer.setAlignment(Pos.CENTER);
        alertMessageContainer.setSpacing(20);
        alertMessageContainer.setStyle("-fx-background-color: #ffffff");

        Scene scene = new Scene(alertMessageContainer);
        alertWindow.setScene(scene);
        alertWindow.showAndWait();
    }
}
