import javafx.geometry.Insets;
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
    public static ArrayList<AirbnbListing> dataloaded;

    public ArrayList<Pane> paneArray = new ArrayList<>(8);
    public ArrayList<Boolean> shownArray = new ArrayList<>(8);

    public void showStats(){

        GridPane gridpane = new GridPane();

        //adds containers and Panes to hold them

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
        container6.getChildren().add(avgLatLong());

        Pane container7 = new Pane();
        container7.getChildren().add(new TextFlow(new Text("Borough with the highest average reviews per listing: \n" + mostAvgReviewed())));

        StatsPane Pane0 = new StatsPane(0);
        Pane0.getChildren().add(container0);

        StatsPane Pane1 = new StatsPane(1);
        Pane1.getChildren().add(container1);

        StatsPane Pane2 = new StatsPane(2);
        Pane2.getChildren().add(container2);

        StatsPane Pane3 = new StatsPane(3);
        Pane3.getChildren().add(container3);

        //initializes essential arrays for pane transition calculations

        paneArray.add(container0);
        paneArray.add(container1);
        paneArray.add(container2);
        paneArray.add(container3);
        paneArray.add(container4);
        paneArray.add(container5);
        paneArray.add(container6);
        paneArray.add(container7);

        shownArray.add(true);
        shownArray.add(true);
        shownArray.add(true);
        shownArray.add(true);
        shownArray.add(false);
        shownArray.add(false);
        shownArray.add(false);
        shownArray.add(false);

        //adding buttons
        Button button00 = new Button("<");
        Button button01 = new Button(">");

        Button button10 = new Button("<");
        Button button11 = new Button(">");

        Button button20 = new Button("<");
        Button button21 = new Button(">");

        Button button30 = new Button("<");
        Button button31 = new Button(">");

        //button action events call prev available stat or next available stat and set the new container to be placed in the pane

        button00.setOnAction(
                (event) -> {
                    Pane0.getChildren().remove(0);
                    Pane0.getChildren().add(prevAvailableStat(Pane0));
                }
        );

        button10.setOnAction(
                (event) -> {
                    Pane1.getChildren().remove(0);
                    Pane1.getChildren().add(prevAvailableStat(Pane1));
                }
        );

        button20.setOnAction(
                (event) -> {
                    Pane2.getChildren().remove(0);
                    Pane2.getChildren().add(prevAvailableStat(Pane2));
                }
        );

        button30.setOnAction(
                (event) -> {
                    Pane3.getChildren().remove(0);
                    Pane3.getChildren().add(prevAvailableStat(Pane3));
                }
        );

        button01.setOnAction(
                (event) -> {
                    Pane0.getChildren().remove(0);
                    Pane0.getChildren().add(nextAvailableStat(Pane0));
                }
        );

        button11.setOnAction(
                (event) -> {
                    Pane1.getChildren().remove(0);
                    Pane1.getChildren().add(nextAvailableStat(Pane1));
                }
        );

        button21.setOnAction(
                (event) -> {
                    Pane2.getChildren().remove(0);
                    Pane2.getChildren().add(nextAvailableStat(Pane2));
                }
        );
        button31.setOnAction(
                (event) -> {
                    Pane3.getChildren().remove(0);
                    Pane3.getChildren().add(nextAvailableStat(Pane3));
                }
        );


        //adds buttons to gridpane

        gridpane.add(button00,0,0);
        gridpane.add(button01,2,0);
        gridpane.add(button10,3,0);
        gridpane.add(button11,5,0);
        gridpane.add(button20,0,1);
        gridpane.add(button21,2,1);
        gridpane.add(button30,3,1);
        gridpane.add(button31,5,1);

        gridpane.add(Pane0,1,0);
        gridpane.add(Pane1,4,0);
        gridpane.add(Pane2,1,1);
        gridpane.add(Pane3,4,1);

        RowConstraints rowconstraints = new RowConstraints();
        rowconstraints.setVgrow(Priority.ALWAYS);

        ColumnConstraints columnconstraints = new ColumnConstraints();
        columnconstraints.setHgrow(Priority.ALWAYS);

        gridpane.getRowConstraints().addAll(rowconstraints, rowconstraints);
        gridpane.getColumnConstraints().addAll(columnconstraints, columnconstraints, columnconstraints, columnconstraints);

        gridpane.setPadding(new Insets(0,0,0,50));

        TabCreator.createSingularTab(gridpane, "Statistics", Airbnb.getImageView(Airbnb.Graphic.STATISTICSGRAPHIC), true);
    }

    public StatisticsPage() {
        //load the airbnb data in
        dataloaded = new AirbnbDataLoader().load();
    }

    /**
     * this finds the next available Pane to show. loops around if cannot find next array index
     * @param currpane the current pane template and its fields, so that the method can tell which pane to transition to
     * @return the pane to show
     */

    public Pane nextAvailableStat(StatsPane currpane){
        shownArray.remove(currpane.getCurrentPos());
        shownArray.add(currpane.getCurrentPos(), false);
        do{currpane.next();}
        while(shownArray.get(currpane.getCurrentPos()));
        shownArray.remove(currpane.getCurrentPos());
        shownArray.add(currpane.getCurrentPos(), true);

        return paneArray.get(currpane.getCurrentPos());
    }

    /**
     * This finds the previous available (not shown) Pane to show. loops around if goes to array index below 0
     * @param currpane the current pane template and its fields, so that the method can tell which pane to transition to
     * @return the pane to show
     */


    public Pane prevAvailableStat(StatsPane currpane){
        shownArray.remove(currpane.getCurrentPos());
        shownArray.add(currpane.getCurrentPos(), false);
        do{currpane.prev();}
        while(shownArray.get(currpane.getCurrentPos()));
        shownArray.remove(currpane.getCurrentPos());
        shownArray.add(currpane.getCurrentPos(), true);

        return paneArray.get(currpane.getCurrentPos());

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
                boroughNights.put(listing.getNeighbourhood(),(boroughNights.get(listing.getNeighbourhood()) + listing.getAvailability365()));
                //increment neighborhood list
                neighTotal.put(listing.getNeighbourhood(),(neighTotal.get(listing.getNeighbourhood()) + 1));
            }
        }

        for (String neigh : boroughNights.keySet()){
            neighAvg = boroughNights.get(neigh) / (365.0 * neighTotal.get(neigh));
            if (highestAvg < neighAvg){
                highestNights = neigh;
                highestAvg = neighAvg;

            }
        }
        return highestNights + " with " + (int)(highestAvg * 100.0) + "% chance of finding a night";
    }

    //returns a piechart with the distribution of neighborhoods
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

    public Pane avgLatLong(){
        double lat= 0;
        double lon= 0;
        int total = 0;
        for (AirbnbListing listing : dataloaded){
            lat += listing.getLatitude();
            lon += listing.getLongitude();
            total += 1;
        }

        return new GetBingMaps().getLightCanvas("", (lat/total), (lon/total));

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

    //extended Pane class to include a new field, currentpos so that methods can tell which next pane to show
    private class StatsPane extends Pane{
        public int currentPos;
        public StatsPane(int position){
            currentPos = position;
        }

        public void next(){
            if (currentPos + 1 == 8){
                currentPos = 0;
            }
            else{
                currentPos++;
            }
        }

        public void prev(){
            if (currentPos - 1 < 0){
                currentPos = 7;
            }
            else{
                currentPos--;
            }
        }

        public int getCurrentPos(){
            return currentPos;
        }

        public void setPost(int position){
            currentPos = position;
        }

    }
    public static List<AirbnbListing> getListings(){
        return dataloaded;
    }
}