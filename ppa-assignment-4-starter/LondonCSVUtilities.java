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
