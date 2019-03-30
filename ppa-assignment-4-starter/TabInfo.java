import javafx.scene.control.Tab;

/**
 * This is a class that checks whether the two tabs are of the same type, and whether they store the same criteria.
 */
public class TabInfo {
    private Object contentCreator;
    private Criteria criteria;
    private Tab tab;

    /**
     * Create a new tabInfo object.
     * @param contentCreator The class that the tab was made from.
     * @param criteria The criteria inputted by the user.
     * @param tab The object tab that is being referred to.
     */
    public TabInfo(Object contentCreator, Criteria criteria, Tab tab){
        this.contentCreator = contentCreator;
        this.criteria = criteria;
        this.tab = tab;
    }

    /**
     * A method that returns the class that the tab was made from.
     * @return contentCreator The class that made the tab.
     */
    public Object getContentCreator(){
        return contentCreator;
    }

    /**
     * A method that returns the criteria that the user inputted.
     * @return criteria The criteria that the user inputted.
     */
    public Criteria getCriteria(){
        return criteria;
    }

    /**
     * A method that returns the tab that is referred to.
     * @return tab The tab that is being referred to.
     */
    public Tab getTab(){
        return tab;
    }
}
