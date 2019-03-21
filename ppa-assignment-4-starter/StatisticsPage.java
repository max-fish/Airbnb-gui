import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import javafx.scene.text.*;

public class StatisticsPage extends Application {
    public ArrayList<AirbnbListing> dataloaded;
    public ArrayList<Boolean> displayedMethods = new ArrayList<>(8);

    public void start (Stage stage){

        displayedMethods.add(0, false);
        displayedMethods.add(1, false);
        displayedMethods.add(2, false);
        displayedMethods.add(3, false);
        displayedMethods.add(4, false);
        displayedMethods.add(5, false);
        displayedMethods.add(6, false);
        displayedMethods.add(7, false);

        GridPane gridpane = new GridPane();

        //adding buttons
        Button button00 = new Button("<");
        Button button01 = new Button(">");
        Button button10 = new Button("<");
        Button button11 = new Button(">");
        Button button20 = new Button("<");
        Button button21 = new Button(">");
        Button button30 = new Button("<");
        Button button31 = new Button(">");

        //adding text
        Text textPane0 = new Text();
        Text textPane1 = new Text();
        Text textPane2 = new Text();
        Text textPane3 = new Text();

        //set text

        textPane0.setText(methodSelector(0));
        textPane1.setText(methodSelector(1));
        textPane2.setText(methodSelector(2));
        textPane3.setText(methodSelector(3));

        gridpane.add(button00,0,0);
        gridpane.add(button01,2,0);
        gridpane.add(button10,3,0);
        gridpane.add(button11,5,0);
        gridpane.add(button20,0,1);
        gridpane.add(button21,2,1);
        gridpane.add(button30,3,1);
        gridpane.add(button31,5,1);

        gridpane.add(textPane0,1,0);
        gridpane.add(textPane1,4,0);
        gridpane.add(textPane2,1,1);
        gridpane.add(textPane3,4,1);






        Scene scene = new Scene(gridpane, 700,500);


        stage.setScene(scene);
        stage.show();
    }

    public String methodSelector(int whichmethod){
        if (!displayedMethods.get(whichmethod)){
            displayedMethods.set(whichmethod, true);
            if (whichmethod == 0){
                return "average number of reviews \n" + calcAvgRevNum();
            }
            else if (whichmethod == 1){
                return "total available properties \n" + availableProp();
            }
            else if (whichmethod == 2){
                return "Available homes/apts \n" + homeApt();
            }
            else if (whichmethod == 3){
                return "most expensive neighborhood \n" + expNeigh();
            }
            else if (whichmethod == 4){
                return "4";
            }
            else if (whichmethod == 5){
                return "5";
            }
            else if (whichmethod == 6){
                return "6";
            }
            else if (whichmethod == 7){
                return "7";
            }
        }
        else{
            methodSelector(whichmethod + 1);
        }
        return "not working";
    }


    public StatisticsPage() {
        int avgRevNum; //average number of reviews
        int availableProp; //number of available properties
        String mostExpBur; //most expensive borough
        int homeapt; //entire number of homes and apartments

        //load the airbnb data in
        dataloaded = new AirbnbDataLoader().load();
    }

        //calculates average number of reviews over the whole data set
        public double calcAvgRevNum(){
            int totalReviews = 0;
            int totalListings = 0;
            for(AirbnbListing listing : dataloaded){
                totalReviews += listing.getNumberOfReviews();
                totalListings += 1;
            }

            return (totalReviews/totalListings);
        }

        //calculate total available properties
        public int availableProp(){
            int totalListings = 0;
            for(AirbnbListing listing : dataloaded){
                totalListings += 1;
            }
            return totalListings;
        }

        //runs through all listings and returns number of Homes or Apts
        public int homeApt(){
            int homeApts = 0;
            for(AirbnbListing listing : dataloaded){
                if (listing.getRoom_type().equals("Entire home/apt")){
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

            for (AirbnbListing listing : dataloaded){
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
        return "placeholder";
    }

    public static void main(String[] args){
        StatisticsPage stats = new StatisticsPage();
        launch(args);
    }
}
