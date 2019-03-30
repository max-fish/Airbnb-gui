import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.*;

/**
 * A class designed to store information about how to sort Airbnb listings, and to store information
 * about how to filer properties based on user input.
 * @version 0.1.2
 */
public class LondonCSVUtilities {
    private static final Map<String, String> acronymToName = new HashMap<String, String>() {{
        put("ENFI", "Enfield"); put("BARN", "Barnet"); put("HRGY", "Haringey"); put("WALT", "Waltham Forest");
        put("HRRW", "Harrow"); put("BREN", "Brent"); put("CAMD", "Camden"); put("ISLI", "Islington"); put("HACK", "Hackney");
        put("REDB", "Redbridge"); put("HAVE", "Havering"); put("HILL", "Hillingdon"); put("EALI", "Ealing");
        put("KENS", "Kensington and Chelsea"); put("TOWH", "Tower Hamlets"); put("WSTM", "Westminster");
        put("NEWH", "Newham"); put("BARK", "Barking and Dagenham"); put("HOUN", "Hounslow");
        put("HAMM", "Hammersmith and Fulham"); put("WAND", "Wandsworth"); put("CITY", "City of London");
        put("GWCH", "Greenwich"); put("BEXL", "Bexley"); put("RICH", "Richmond upon Thames"); put("MERT", "Merton");
        put("LAMB", "Lambeth"); put("STHW", "Southwark"); put("LEWS", "Lewisham"); put("KING", "Kingston upon Thames");
        put("SUTT", "Sutton"); put("CROY", "Croydon"); put("BROM", "Bromley");
    }};

    private static ObservableList<String> neighborhoods = FXCollections.observableArrayList("All", "Barking and Dagenham",
            "Barnet", "Bexley", "Brent", "Bromley", "Camden", "City of London", "Croydon",
            "Ealing", "Enfield", "Greenwich", "Hackney", "Hammersmith and Fulham", "Haringey", "Harrow",
            "Havering", "Hillingdon", "Hounslow", "Islington", "Kensington and Chelsea", "Kingston upon Thames", "Lambeth",
            "Lewisham", "Merton", "Newham", "Redbridge", "Richmond upon Thames", "Southwark", "Sutton",
            "Tower Hamlets", "Waltham Forest", "Wandsworth", "Westminster");

    /**
     * A method that returns the name of a borough from its acronym name.
     * @param acronym The shorthand acronym for the borough.
     * @return String The full name of the borough.
     */
    public static String getNameFromAcronym(String acronym){
        return acronymToName.get(acronym);
    }

    /**
     * A method that returns the all the boroughs/neighborhoods specified in the CSV file.
     * @return ObservableList<String> All the neighborhoods in the CSV file.
     */
    public static ObservableList<String> getNeighborhoods() {return neighborhoods;}

    /**
     * A method designed to search through Airbnb listings by checking which listings are within the specified
     * user criteria.
     * @param low The low price inputted by the user.
     * @param high The high price inputted by the user.
     * @return
     */
    public static Map<String, List<AirbnbListing>> filteredResults(int low, int high){
        Map<String, List<AirbnbListing>> tbr = new HashMap<String, List<AirbnbListing>>();
        for(String value : acronymToName.values()){
            tbr.put(value, new ArrayList<>());
        }
        for(AirbnbListing items : new AirbnbDataLoader().load()){
            if((items.getPrice() > low) && (items.getPrice() < high)){
                List<AirbnbListing> current = tbr.get(items.getNeighbourhood());
                current.add(items);
                tbr.put(items.getNeighbourhood(), current);
            }
        }
        return tbr;
    }

    /**
     * A method designed to search through Airbnb listings by checking which listings are within the specified
     * user criteria, and by checking whether the listings are of the room type that the user specified.
     * @param low The low price selected by the user.
     * @param high The high price selected by the user.
     * @param typeOfRoom The type of room selected by the user.
     * @return
     */
    public static Map<String, List<AirbnbListing>> filteredResults(int low, int high, String typeOfRoom) {
        Map<String, List<AirbnbListing>> tbr = new HashMap<String, List<AirbnbListing>>();
        for(String value : acronymToName.values()) {
            tbr.put(value, new ArrayList<>());
        }
        for(AirbnbListing items : new AirbnbDataLoader().load()) {
            if((items.getPrice() > low) && (items.getPrice() < high) && (typeOfRoom.equals(items.getRoom_type()))) {
                List<AirbnbListing> current = tbr.get(items.getNeighbourhood());
                current.add(items);
                tbr.put(items.getNeighbourhood(), current);
            }
        }
        return tbr;
    }

    /**
     * A method that declares all the enums that are used to sort the properties in the properties tab.
     */
    public enum sortBy {
        PRICE_LOW_TO_HIGH, PRICE_HIGH_TO_LOW, REVIEWS, HOST_NAME;
    }

    /**
     * Sorts the properties by the given cases, whether it is sorting by price, reviews or host name.
     * @param properties The properties to be sorted.
     * @param sortType The type of sorting to be done.
     */
    public static void sort(List<AirbnbListing> properties, sortBy sortType){
        switch(sortType)
        {
            case PRICE_LOW_TO_HIGH:
                properties.sort(new Comparator<AirbnbListing>() {
                @Override
                public int compare(AirbnbListing o1, AirbnbListing o2) {
                    if(o1.getPrice() < o2.getPrice()){
                        return -1;
                    }
                    else if (o1.getPrice() == o2.getPrice()){
                        return 0;
                    }
                    else{
                        return 1;
                    }
                }
            });
            break;

            case PRICE_HIGH_TO_LOW:
                properties.sort(new Comparator<AirbnbListing>() {
                    @Override
                    public int compare(AirbnbListing o1, AirbnbListing o2) {
                            if (o1.getPrice() < o2.getPrice()) {
                                return 1;
                            }
                            else if (o1.getPrice() == o2.getPrice()) {
                                return 0;
                            }
                            else {
                                return -1;
                            }
                        }
                    });

            case REVIEWS: properties.sort(new Comparator<AirbnbListing>() {
                @Override
                public int compare(AirbnbListing o1, AirbnbListing o2) {
                    if(o1.getNumberOfReviews() < o2.getNumberOfReviews()){
                        return 1;
                    }
                    else if (o1.getNumberOfReviews() == o2.getNumberOfReviews()){
                        return 0;
                    }
                    else{
                        return -1;
                    }
                }
            });
            break;

            case HOST_NAME: properties.sort(new Comparator<AirbnbListing>() {
                @Override
                public int compare(AirbnbListing o1, AirbnbListing o2) {
                   return o1.getHost_name().compareTo(o2.getHost_name());
                }
            });
            break;
        }
    }
}
