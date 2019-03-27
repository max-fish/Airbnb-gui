public class Criteria {
    private String neighborhood;
    private String roomType;
    private int lowPrice;
    private int highPrice;

    public Criteria(String neighborhood, String roomType, int lowprice, int highPrice){
        this.neighborhood = neighborhood;
        this.roomType = roomType;
        this.lowPrice = lowprice;
        this.highPrice = highPrice;
    }
}
