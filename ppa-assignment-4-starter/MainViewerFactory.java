import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainViewerFactory {
    public static void styleRoot(BorderPane root, Scene scene){
        root.minHeightProperty().bind(scene.heightProperty());
        root.minWidthProperty().bind(scene.widthProperty());
    }
    public static void stylePane(BorderPane pane){
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setMinSize(300, 300);
        pane.setPrefSize(800,600);
        pane.setStyle("-fx-background-color: #FFFFFF;");
    }
    public static void styleWelcomeText(Text welcomeText){
        welcomeText.setText("Welcome to Airbnb");
        welcomeText.setFill(Color.WHITE);
        welcomeText.setFont(Airbnb.HEADERFONT);
    }
    public static void styleInstructionText(Text instructionText){
        instructionText.setText("Please fill out the criteria above with your liking.");
        instructionText.setFont(Airbnb.INSTRUCTIONFONT);
        instructionText.setFill(Color.WHITE);
    }
    public static void styleWelcomePane(StackPane welcomePane, ImageView airbnbLogoView, Text instructionText){
        welcomePane.setMargin(airbnbLogoView, new Insets(-150,0,0,0));
        welcomePane.setMargin(instructionText, new Insets(0,0,-100,0));
    }
    public static void styleWelcomePaneContainer(Rectangle welcomePaneContainer){
        welcomePaneContainer.setFill(Airbnb.CORAL);
        welcomePaneContainer.setArcHeight(20);
        welcomePaneContainer.setArcWidth(10);
    }
    public static void fadeInProtocol(ImageView airbnbLogoView, Text welcomeText, Text instructionText,
                                      FlowPane neighborhoodPanel, FlowPane roomTypePanel, FlowPane lowPricePanel, FlowPane highPricePanel, Button search, Button next, Button previous){
        airbnbLogoView.setOpacity(0);
        welcomeText.setOpacity(0);
        instructionText.setOpacity(0);
        neighborhoodPanel.setOpacity(0);
        roomTypePanel.setOpacity(0);
        lowPricePanel.setOpacity(0);
        highPricePanel.setOpacity(0);
        search.setOpacity(0);
        next.setOpacity(0);
        previous.setOpacity(0);

        FadeTransition logoFadeIn = new FadeTransition(Duration.millis(1500), airbnbLogoView);
        logoFadeIn.setFromValue(0);
        logoFadeIn.setToValue(1);
        logoFadeIn.play();

        FadeTransition welcomeTextFadeIn = new FadeTransition(Duration.millis(1500), welcomeText);
        welcomeTextFadeIn.setFromValue(0);
        welcomeTextFadeIn.setToValue(1);
        logoFadeIn.setOnFinished(
                (event) -> {welcomeTextFadeIn.play();}
        );


        FadeTransition instructionTextFadeIn = new FadeTransition(Duration.millis(1500), instructionText);
        instructionTextFadeIn.setFromValue(0);
        instructionTextFadeIn.setToValue(1);
        welcomeTextFadeIn.setOnFinished(
                (event) -> {instructionTextFadeIn.play();}
        );

        FadeTransition neighborhoodPanelFadeIn = new FadeTransition(Duration.millis(1000), neighborhoodPanel);
        neighborhoodPanelFadeIn.setFromValue(0);
        neighborhoodPanelFadeIn.setToValue(1);

        FadeTransition roomTypePanelFadeIn = new FadeTransition(Duration.millis(1000), roomTypePanel);
        roomTypePanelFadeIn.setFromValue(0);
        roomTypePanelFadeIn.setToValue(1);

        FadeTransition lowPricePanelFadeIn = new FadeTransition(Duration.millis(1500), lowPricePanel);
        lowPricePanelFadeIn.setFromValue(0);
        lowPricePanelFadeIn.setToValue(1);

        FadeTransition highPricePanelFadeIn = new FadeTransition(Duration.millis(1500), highPricePanel);
        highPricePanelFadeIn.setFromValue(0);
        highPricePanelFadeIn.setToValue(1);

        FadeTransition searchButtonFadeIn = new FadeTransition(Duration.millis(1500), search);
        searchButtonFadeIn.setFromValue(0);
        searchButtonFadeIn.setToValue(1);

        FadeTransition nextButtonFadeIn = new FadeTransition(Duration.millis(1500), next);
        nextButtonFadeIn.setFromValue(0);
        nextButtonFadeIn.setToValue(1);

        FadeTransition previousButtonFadeIn = new FadeTransition(Duration.millis(1500), previous);
        previousButtonFadeIn.setFromValue(0);
        previousButtonFadeIn.setToValue(1);

        instructionTextFadeIn.setOnFinished(
                (event) -> {neighborhoodPanelFadeIn.play();
                    roomTypePanelFadeIn.play();
                    lowPricePanelFadeIn.play();
                    highPricePanelFadeIn.play();
                    searchButtonFadeIn.play();
                    nextButtonFadeIn.play();
                    previousButtonFadeIn.play();
                }
        );
    }
}
