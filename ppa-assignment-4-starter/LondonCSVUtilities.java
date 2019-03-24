import javafx.scene.text.Font;

import java.util.*;

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

    public enum sortBy {
        PRICE, REVIEWS, HOST_NAME;
    }

    public static void sort(List<AirbnbListing> properties, sortBy sortType){
        switch(sortType)
        {
            case PRICE :
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
