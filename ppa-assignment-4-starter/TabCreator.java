import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.ArrayList;

/**
 * A class designed to create tabs.
 */
public class TabCreator {

    private static ArrayList<TabInfo> tabInfos = new ArrayList<>();


    public static void createSingularTab(Node content, String name, ImageView graphic, boolean closable){
        for(Tab existingTab : MainViewer.getPanels().getTabs()){
            if(existingTab.getText().equals(name)){
                MainViewer.getPanels().getSelectionModel().select(existingTab);
                return;
            }
        }
        Tab newTab = new Tab(name);
        newTab.setContent(content);
        newTab.setClosable(closable);
        if (graphic != null) {
            newTab.setGraphic(graphic);
        }
        newTab.getContent().setOnMouseMoved(
                (event) -> {
                    TranslateTransition slideIn = new TranslateTransition(Duration.millis(250), MainViewer.getToolBar());
                    if(event.getSceneX() > 0 && event.getSceneX() < 30) {
                        slideIn.setToX(0);
                        slideIn.setCycleCount(1);
                        slideIn.play();
                    }
                    else{
                        slideIn.setToX(-150);
                        slideIn.setCycleCount(1);
                        slideIn.play();
                    }

                });
        MainViewer.getPanels().getTabs().add(newTab);
        MainViewer.getPanels().getSelectionModel().select(newTab);
    }

    public static void createTab(Object contentCreator, Node content, String name, ImageView graphic, boolean closable, Criteria criteria) {

        for (TabInfo existingTab : tabInfos) {
            if (contentCreator.getClass() == existingTab.getContentCreator().getClass()) {
                if(criteria.equals(existingTab.getCriteria())) {
                    MainViewer.getPanels().getSelectionModel().select(existingTab.getTab());
                    return;
                }
            }
        }
        Tab newTab = new Tab(name);
        newTab.setContent(content);
        newTab.setClosable(closable);
        if (graphic != null) {
            newTab.setGraphic(graphic);
        }
        newTab.getContent().setOnMouseMoved(
                (event) -> {
                    TranslateTransition slideIn = new TranslateTransition(Duration.millis(250), MainViewer.getToolBar());
                    if(event.getSceneX() > 0 && event.getSceneX() < 30) {
                        slideIn.setToX(0);
                        slideIn.setCycleCount(1);
                        slideIn.play();
                    }
                    else{
                        slideIn.setToX(-100);
                        slideIn.setCycleCount(1);
                        slideIn.play();
                    }

                }
        );
        TabInfo tabInfo = new TabInfo(contentCreator, criteria, newTab);
        tabInfos.add(tabInfo);
        newTab.setOnClosed(
                (event) -> {
                    tabInfos.remove(tabInfo);
                }
        );

        MainViewer.getPanels().getTabs().add(newTab);
        MainViewer.getPanels().getSelectionModel().select(newTab);
    }

    }
