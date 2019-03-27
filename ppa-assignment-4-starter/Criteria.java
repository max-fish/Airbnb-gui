import javafx.scene.control.Button;

public class Criteria {
    private String neighborhood;
    private String roomType;
    private int lowPrice;
    private int highPrice;
    private Button button;
    public Criteria(String neighborhood, String roomType, int lowprice, int highPrice, Button button){
        this.neighborhood = neighborhood;
        this.roomType = roomType;
        this.lowPrice = lowprice;
        this.highPrice = highPrice;
        this.button = button;
    }

    public String getNeighborhood(){
        return neighborhood;
    }

    public String getRoomType(){
        return roomType;
    }

    public int getLowPrice(){
        return lowPrice;
    }

    public int getHighPrice(){
        return highPrice;
    }

    public Button getButton(){
        return button;
    }


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
