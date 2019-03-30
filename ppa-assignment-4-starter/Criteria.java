import javafx.scene.control.Button;

/**
 * A class designed to hold information about the search criteria that a user has inputted.
 */
public class Criteria {
    private String neighborhood;
    private String roomType;
    private int lowPrice;
    private int highPrice;
    private Button button;

    /**
     * Creates a new criteria object that holds all the search criteria that a user could input.
     * @param neighborhood The neighborhood inputted by the user.
     * @param roomType The room type inputted by the user.
     * @param lowprice The low price inputted by the user.
     * @param highPrice The high price inputted by the user.
     * @param button The map window button.
     */
    public Criteria(String neighborhood, String roomType, int lowprice, int highPrice, Button button){
        this.neighborhood = neighborhood;
        this.roomType = roomType;
        this.lowPrice = lowprice;
        this.highPrice = highPrice;
        this.button = button;
    }

    /**
     * A method designed to return the neighborhood inputted by the user.
     * @return neighborhood The neighborhood/borough inputted by the user.
     */
    public String getNeighborhood(){
        return neighborhood;
    }

    /**
     * A method designed to return the room type inputted by the user.
     * @return roomType The room type inputted by the user.
     */
    public String getRoomType(){
        return roomType;
    }

    /**
     * A method designed to return the low price inputted by the user.
     * @return lowPrice The low price inputted by the user.
     */
    public int getLowPrice(){
        return lowPrice;
    }

    /**
     * A method designed to return the high price inputted by the user.
     * @return highPrice The high price inputted by the user.
     */
    public int getHighPrice(){
        return highPrice;
    }

    /**
     * A method designed to return the button clicked by the user.
     * @return button The button that was pressed by the user.
     */
    public Button getButton(){
        return button;
    }

    /**
     * A method designed to check whether the inputted user criteria in the GUI equals the criteria stored in the
     * Criteria class.
     * @param criteria
     * @return boolean Whether it is true that the criteria stored in the Criteria class is the same as the criteria in
     * the GUI window.
     */
    public boolean equals(Criteria criteria){
        if(neighborhood.equals(criteria.getNeighborhood()) && roomType.equals(criteria.getRoomType())
        && lowPrice == criteria.getLowPrice() && highPrice == criteria.getHighPrice() && button == criteria.getButton()){
            return true;
        }
        else{
            return false;
        }
    }
}
