import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.HashMap;
import javafx.scene.text.*;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

public class StatisticsPage extends Application {
    public ArrayList<AirbnbListing> dataloaded;

    public ArrayList<Pane> paneArray = new ArrayList<>(8);
    public ArrayList<Boolean> shownArray = new ArrayList<>(8);

    public void start (Stage stage){

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
        container6.getChildren().add(avgLatLong());

        Pane container7 = new Pane();
        container7.getChildren().add(new TextFlow(new Text("Borough with the highest average reviews per listing: \n" + mostAvgReviewed())));

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

        StatsPane Pane0 = new StatsPane(0);
        Pane0.getChildren().add(container0);

        StatsPane Pane1 = new StatsPane(1);
        Pane1.getChildren().add(container1);

        StatsPane Pane2 = new StatsPane(2);
        Pane2.getChildren().add(container2);

        StatsPane Pane3 = new StatsPane(3);
        Pane3.getChildren().add(container3);

        gridpane.add(Pane0,1,0);
        gridpane.add(Pane1,4,0);
        gridpane.add(Pane2,1,1);
        gridpane.add(Pane3,4,1);



        Scene scene = new Scene(gridpane, 700,500);
        gridpane.minHeightProperty().bind(scene.heightProperty());
        gridpane.minWidthProperty().bind(scene.widthProperty());
        gridpane.setGridLinesVisible(true);

        RowConstraints rowconstraints = new RowConstraints();
        rowconstraints.setVgrow(Priority.ALWAYS);

        ColumnConstraints columnconstraints = new ColumnConstraints();
        columnconstraints.setHgrow(Priority.ALWAYS);

        gridpane.getRowConstraints().addAll(rowconstraints, rowconstraints);
        gridpane.getColumnConstraints().addAll(columnconstraints, columnconstraints, columnconstraints, columnconstraints);



        stage.setScene(scene);
        stage.show();
    }


    public StatisticsPage() {
        //load the airbnb data in
        dataloaded = new AirbnbDataLoader().load();
    }

    public Pane nextAvailableStat(StatsPane currpane){
        shownArray.add(currpane.getCurrentPos(), false);
        currpane.next();
        while(!shownArray.get(currpane.getCurrentPos())){
            currpane.next();
        }

        shownArray.add(currpane.getCurrentPos(), true);

        return paneArray.get(currpane.getCurrentPos());
    }

    public Pane prevAvailableStat(StatsPane currpane){
        shownArray.add(currpane.getCurrentPos(), false);
        currpane.prev();
        while(!shownArray.get(currpane.getCurrentPos())){
            currpane.prev();
        }

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
                boroughNights.put(listing.getNeighbourhood(),boroughNights.get(listing.getNeighbourhood()) + listing.getAvailability365());

                //increment neighborhood list
                neighTotal.put(listing.getNeighbourhood(),neighTotal.get(listing.getNeighbourhood()) + 1);
            }
        }

        for (String neigh : boroughNights.keySet()){
            neighAvg = boroughNights.get(neigh) / (365.0 * neighTotal.get(neigh));
            System.out.println(neighAvg);
            if (highestAvg < neighAvg){
                highestNights = neigh;
                highestAvg = neighAvg;

            }
        }
        return highestNights + " with " + (int)(highestAvg * 100.0) + "% chance of finding a night";
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

    public Pane avgLatLong(){
        double lat= 0;
        double lon= 0;
        int total = 0;
        for (AirbnbListing listing : dataloaded){
            lat += listing.getLatitude();
            lon += listing.getLongitude();
            total += 1;
        }

        return new GetGoogleMaps().getMapPane("", (lat/total), (lon/total));

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

    public static void main(String[] args){
        StatisticsPage stats = new StatisticsPage();
        launch(args);
    }
}