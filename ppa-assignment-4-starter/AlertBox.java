import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String title, String message) {
        Stage alertWindow = new Stage();
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);
        alertWindow.setHeight(200);
        alertWindow.setWidth(400);

        Text alertText = new Text();
        alertText.setText(message);
        alertText.setFont(Font.font("Circular", FontWeight.SEMI_BOLD, 20));
        Button closeButton = new Button("   OK   ");
        closeButton.setStyle("-fx-base: #ff5a5f; -fx-text-fill: white; -fx-focus-color: #ffffff; -fx-faint-focus-color: #ffffff; -fx-background-radius: 10, 10, 10, 10");
        closeButton.setOnAction(e -> alertWindow.close());

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
