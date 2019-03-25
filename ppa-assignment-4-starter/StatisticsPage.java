import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * @version 0.0.1
 */

public class StatisticsPage extends Application {
    public static final ArrayList<AirbnbListing> dataLoaded = new AirbnbDataLoader().load();


    public void start (Stage stage){

        StackPane root = new StackPane();
        Scene scene = new Scene(root, 300,100);

        stage.setScene(scene);
        stage.show();
    }

    public StatisticsPage() {
        int avgRevNum; //average number of reviews
        int availableProp; //number of available properties
        String mostExpBur; //most expensive borough
        int homeapt; //entire number of homes and apartments
    }

        //calculates average number of reviews over the whole data set
        public double calcAvgRevNum(){
            int totalReviews = 0;
            int totalListings = 0;
            for(AirbnbListing listing : dataLoaded){
                totalReviews += listing.getNumberOfReviews();
                totalListings += 1;
            }

            return (totalReviews/totalListings);
        }

        //calculate total available properties
        public int availableProp(){
            int totalListings = 0;
            for(AirbnbListing listing : dataLoaded){
                totalListings += 1;
            }
            return totalListings;
        }

        //runs through all listings and returns number of Homes or Apts
        public int homeApt(){
            int homeApts = 0;
            for(AirbnbListing listing : dataLoaded){
                if (listing.getRoom_type().equals("Entire Home/apt")){
                    homeApts += 1;
                }
            }

            return homeApts;
        }

        //find most expensive neighborhood
        public String expNeigh(){
            //Initialize Map with all boroughs(keys) and total cost of all properties(values)
            Map<String, Integer> neighMapCost = new HashMap<String, Integer>();

            Map<String, Integer> neighTotal = new HashMap<String, Integer>();

            String highestBorough = "";
            double highestAvg = 0;
            double neighAvg;

            for (AirbnbListing listing : dataLoaded){
                //checks if borough is listed in map
                if (neighMapCost.get(listing.getNeighbourhood()) == null && neighTotal.get(listing.getNeighbourhood()) == null){

                    //sets minimum price in neighborhood
                    neighMapCost.put(listing.getNeighbourhood(), listing.getPrice() * listing.getMinimumNights());

                    //lists total number of listings in borough as 1
                    neighTotal.put(listing.getNeighbourhood(),1);
                }
                else{
                    //adds cost of current neighborhood to total
                    neighMapCost.put(listing.getNeighbourhood(),
                            neighMapCost.get(listing.getNeighbourhood()) +
                                    listing.getPrice() * listing.getMinimumNights());

                    //increments number of listings in neighborhood
                    neighTotal.put(listing.getNeighbourhood(), neighTotal.get(listing.getNeighbourhood()) + 1);
                }
            }

            for(String neigh: neighMapCost.keySet()){
                //find neighborhood average cost
                neighAvg = neighMapCost.get(neigh) / neighTotal.get(neigh);

                //compare neighborhood average cost and reset if current neighborhood greater
                if(highestAvg < neighAvg){
                    highestBorough = neigh;
                    highestAvg = neighAvg;
                }
            }
            return highestBorough;
    }

    //shows the borough with the highest availability
    public String mostLikelyNight(){
        return "";
    }

    public static void main(String[] args){
        StatisticsPage stats = new StatisticsPage();
        //launch(args);
    }
}
