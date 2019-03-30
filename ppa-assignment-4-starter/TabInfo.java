import javafx.scene.control.Tab;

public class TabInfo {
    private Object contentCreator;
    private Criteria criteria;
    private Tab tab;

    public TabInfo(Object contentCreator, Criteria criteria, Tab tab){
        this.contentCreator = contentCreator;
        this.criteria = criteria;
        this.tab = tab;
    }

    public Object getContentCreator(){
        return contentCreator;
    }

    public Criteria getCriteria(){
        return criteria;
    }

    public Tab getTab(){
        return tab;
    }
}
