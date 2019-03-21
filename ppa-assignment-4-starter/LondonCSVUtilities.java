import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.List;

/**
 *
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

    public static String getNameFromAcronym(String acronym){
        return acronymToName.get(acronym);
    }

    /**
     * @param borough
     * @param lowPrice
     * @param highPrice
     * @return a linked hashset of airbnb listings that correspond with user specification
     * if a parameter is null, that means the user does not want to filter using that criteria
     */

    public static LinkedHashSet<AirbnbListing> findPropertiesForUser(String borough, Integer lowPrice, Integer highPrice){
        LinkedHashSet<AirbnbListing> allProperties = new LinkedHashSet<>();

        if(borough != null && lowPrice != null && highPrice != null){
            allProperties.addAll(filterByBorough(borough));
            allProperties.addAll(filterByPrice(lowPrice, highPrice));
        }

       else if(borough != null){
            allProperties.addAll(filterByBorough(borough));
        }
        else if(lowPrice != null && highPrice != null){
            allProperties.addAll(filterByPrice(lowPrice, highPrice));
        }
        return allProperties;
    }


    /**
     * @param borough
     * @return all aibnb listings that are in a specified borough
     */
    private static ArrayList<AirbnbListing> filterByBorough(String borough){
        ArrayList<AirbnbListing> matchingBoroughProperties = new ArrayList<AirbnbListing>();
        for(AirbnbListing listing : StatisticsPage.dataLoaded){
            if(listing.getNeighbourhood().equals(borough)){
                matchingBoroughProperties.add(listing);
            }
        }
        return matchingBoroughProperties;
    }


    /**
     *
     * @param lowPrice
     * @param highPrice
     * @return all airbnb listing that are in a specified price range
     */
    private static ArrayList<AirbnbListing> filterByPrice(int lowPrice, int highPrice){
        ArrayList<AirbnbListing> matchingPriceProperties = new ArrayList<AirbnbListing>();
        for(AirbnbListing listing : StatisticsPage.dataLoaded){
            if(listing.getPrice() >= lowPrice && listing.getPrice() <= highPrice){
                matchingPriceProperties.add(listing);
            }
        }
        return matchingPriceProperties;
    }

    /**
     *
     * @param low
     * @param high
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
}
