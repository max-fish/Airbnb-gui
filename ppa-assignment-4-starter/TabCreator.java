import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TabCreator {
    public static void createTab(Node content, String name, ImageView graphic, boolean closable) {

        for (Tab existingTab : MainViewer.getPanels().getTabs()) {
            if (equals(existingTab.getContent(), content)) {
                System.out.println("equal");
                MainViewer.getPanels().getSelectionModel().select(existingTab);
                return;
            }
        }
        System.out.println("new");
        Tab newTab = new Tab(name);
        newTab.setContent(content);
        newTab.setClosable(closable);
        if (graphic != null) {
            newTab.setGraphic(graphic);
        }
        MainViewer.getPanels().getTabs().add(newTab);
        MainViewer.getPanels().getSelectionModel().select(newTab);
    }

    private static boolean equals(Node content1, Node content2) {
        if (content1 instanceof Pane && content2 instanceof Pane) {
            Pane content1Pane = (Pane) content1;
            Pane content2Pane = (Pane) content2;

            if (content1Pane.getClass() == content2.getClass()) {
                if (!content1Pane.getChildren().isEmpty() && !content2Pane.getChildren().isEmpty()) {
                    if (content1Pane.getChildren().get(0).getClass() == content2Pane.getChildren().get(0).getClass()) {
                        return true;
                    }
                }
                if (content1Pane.getChildren().isEmpty() && !content2Pane.getChildren().isEmpty()) {
                    return false;
                }
                if (content1Pane.getChildren().isEmpty() && !content2Pane.getChildren().isEmpty()) {
                    return false;
                }
                if (content1Pane.getChildren().isEmpty() && content2Pane.getChildren().isEmpty()) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
}
