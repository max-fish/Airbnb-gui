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

    /**
     * A method designed to create a singular tab, which is a tab that can only exist once.
     * @param content The content to be put into the tab.
     * @param name The name of the tab.
     * @param graphic The graphic icon displayed alongside the name of the tab.
     * @param closable Whether or not you can close the tab.
     */
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

    /**
     * A method designed to create a tab, which can exist any number of times as long as it has different criteria.
     * @param contentCreator The class that the tab was made from.
     * @param content The content to be displayed in the tab.
     * @param name The name of the tab.
     * @param graphic The graphic that goes alongside the name of the tab.
     * @param closable Whether or not you can close the tab.
     * @param criteria The user criteria inputted by the user.
     */
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
