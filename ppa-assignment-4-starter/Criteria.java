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


    public boolean equals(Criteria criteria){
        if(neighborhood.equals(criteria.getNeighborhood()) && roomType.equals(criteria.getRoomType())
        && lowPrice == criteria.getLowPrice() && highPrice == criteria.getHighPrice()){
            return true;
        }
        else{
            return false;
        }
    }
}
