import javafx.scene.control.Button;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javafx.scene.text.*;
import javafx.scene.chart.PieChart;


/**
 * @version 0.0.1
 */

public class StatisticsPage {
    private static ArrayList<AirbnbListing> dataloaded = new AirbnbDataLoader().load();;
    public ArrayList<Boolean> displayedMethods = new ArrayList<>(8);
    public ArrayList<Integer> currentShown = new ArrayList<>(4);


    public void showStats(){

        displayedMethods.add(0, true);
        displayedMethods.add(1, true);
        displayedMethods.add(2, true);
        displayedMethods.add(3, true);
        displayedMethods.add(4, false);
        displayedMethods.add(5, false);
        displayedMethods.add(6, false);
        displayedMethods.add(7, false);

        currentShown.add(0,0);
        currentShown.add(1,1);
        currentShown.add(2,2);
        currentShown.add(3,3);

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

        gridpane.add(button00,0,0);
        gridpane.add(button01,2,0);
        gridpane.add(button10,3,0);
        gridpane.add(button11,5,0);
        gridpane.add(button20,0,1);
        gridpane.add(button21,2,1);
        gridpane.add(button30,3,1);
        gridpane.add(button31,5,1);

        Pane container0 = new Pane();
        container0.getChildren().add(new TextFlow(new Text("Average Number of Reviews \n" + calcAvgRevNum())));

        Pane container1 = new Pane();
        container1.getChildren().add(new TextFlow(new Text("Total Available properties: \n" + availableProp())));

        Pane container2 = new Pane();
        container2.getChildren().add(new TextFlow(new Text("Available homes/apts: \n" + homeApt())));

        Pane container3 = new Pane();
        container3.getChildren().add(new TextFlow(new Text("Most Expensive Neighborhood: \n" + expNeigh())));

        Pane container4 = new Pane();
        container4.getChildren().add(new TextFlow(new Text("Neighborhood with highest probability night: \n" + mostLikelyNight())));


        Pane container5 = new Pane(neighDistribution());

        Pane container6 = new Pane();
        container6.getChildren().add(new TextFlow(new Text("Neighborgood with highest probability night: \n" + avgLatLong())));

        Pane container7 = new Pane();
        container7.getChildren().add(new TextFlow(new Text("Borough with the highest average reviews per listing: \n" + mostAvgReviewed())));

        gridpane.add(container0,1,0);
        gridpane.add(container1,4,0);
        gridpane.add(container2,1,1);
        gridpane.add(container5,4,1);
        gridpane.setGridLinesVisible(true);

        RowConstraints rowconstraints = new RowConstraints();
        rowconstraints.setVgrow(Priority.ALWAYS);

        ColumnConstraints columnconstraints = new ColumnConstraints();
        columnconstraints.setHgrow(Priority.ALWAYS);

        gridpane.getRowConstraints().addAll(rowconstraints, rowconstraints);
        gridpane.getColumnConstraints().addAll(columnconstraints, columnconstraints, columnconstraints, columnconstraints);

        TabCreator.createSingularTab(gridpane, "Statistics", null, true);
    }

/*
    public void showSelectedPane(int whichButton){
        //when button pressed

        while(currentShown.get(whichButton) < displayedMethods.size() - 1){
            if current
        }
    }
*/

    /*
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
                //return "most expensive neighborhood \n" + expNeigh();
                return neighDistribution();
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
*/

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

            //goes through all listings in AirbnbDataListings

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

        //Initialize Map with all boroughs(keys) and total number of nights(values)
        Map<String, Integer> boroughNights = new HashMap<String, Integer>();
        //Initialize map with all boroughs(keys) and total number of properties(values)
        Map<String, Integer> neighTotal = new HashMap<String, Integer>();


        String highestNights = "";
        double highestAvg = 0;
        double neighAvg;

        for (AirbnbListing listing : dataloaded){
            // if borough not present in neighborhood map, add borough to map with its base value
            if (boroughNights.get(listing.getNeighbourhood()) == null && neighTotal.get(listing.getNeighbourhood()) == null){
                //set the first borough availability 365
                boroughNights.put(listing.getNeighbourhood(),listing.getAvailability365());
                // set the first instance of a neighborhood to 1, will be increased later
                neighTotal.put(listing.getNeighbourhood(),1);

            }
            // if borough present in neighborhood, add availability365 of current listing to that of the previous available listings
            else{
                boroughNights.put(listing.getNeighbourhood(),boroughNights.get(listing.getNeighbourhood()) + listing.getAvailability365());

                //increment neighborhood list
                neighTotal.put(listing.getNeighbourhood(),neighTotal.get(listing.getNeighbourhood()) + 1);
            }
        }

        for (String neigh : boroughNights.keySet()){
            neighAvg = boroughNights.get(neigh) / (365 * neighTotal.get(neigh));

            if (highestAvg < neighAvg){
                highestNights = neigh;
                highestAvg = neighAvg;

            }
        }
        return highestNights + " with " + highestAvg + " chance of finding a night";
    }

    public PieChart neighDistribution(){

        //Initialize map with all boroughs(keys) and total number of properties(values)
        Map<String, Integer> neighTotal = new HashMap<String, Integer>();

        for (AirbnbListing listing : dataloaded){
            if (neighTotal.get(listing.getNeighbourhood()) == null){
                neighTotal.put(listing.getNeighbourhood(),1);
            }
            else{
                neighTotal.put(listing.getNeighbourhood(),neighTotal.get(listing.getNeighbourhood()) + 1);
            }
        }

        PieChart piechart = new PieChart();

        for (String name : neighTotal.keySet()){
            PieChart.Data slice = new PieChart.Data(name, neighTotal.get(name));
            piechart.getData().add(slice);
        }
        return piechart;
    }

    //returns the average lat,long of the dataset

    public String avgLatLong(){
        int lat= 0;
        int lon= 0;
        int total = 0;
        for (AirbnbListing listing : dataloaded){
            lat += listing.getLatitude();
            lon += listing.getLongitude();
            total += 1;
        }
        return "" + (lat/total) + ", " + (lon/total);
    }

    //return the borough with the most average reviews

    public String mostAvgReviewed(){
        Map<String,Double> reviewTotal = new HashMap<String,Double>();
        Map<String,Integer> neighTotal = new HashMap<String,Integer>();

        for (AirbnbListing listing : dataloaded){
            if (reviewTotal.get(listing.getNeighbourhood()) == null && neighTotal.get(listing.getNeighbourhood()) == null){
                reviewTotal.put(listing.getNeighbourhood(),listing.getReviewsPerMonth());
                neighTotal.put(listing.getNeighbourhood(),1);
            }
            else{
                reviewTotal.put(listing.getNeighbourhood(),reviewTotal.get(listing.getNeighbourhood()) + listing.getReviewsPerMonth());
                neighTotal.put(listing.getNeighbourhood(),neighTotal.get(listing.getNeighbourhood()) + 1);
            }
        }

        String highestRev = "";
        double highestAvg = 0;
        double currAvg = 0;

        for (String borough : reviewTotal.keySet()){
            currAvg = (reviewTotal.get(borough) / neighTotal.get(borough));
            if (currAvg > highestAvg){
                highestAvg = currAvg;
                highestRev = borough;
            }
        }
        return highestRev;

    }
    public static List<AirbnbListing> getListings(){
        return dataloaded;
    }
}
