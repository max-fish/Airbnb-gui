import java.util.List;
import java.util.ArrayList;

/**
 *
 * @version 0.1.0
 */
public class ButtonArrayDetails {

    private List<Integer> rows;
    private List<Integer> offset;
    private ArrayList<ArrayList<String>> names;


    public ButtonArrayDetails(){
        rows = new ArrayList<Integer>();
        offset = new ArrayList<Integer>();
        names = new ArrayList<ArrayList<String>>();
    }

    public void addLayer(int newRowInt, int newRowOffset, ArrayList<String> newRowNames){
        rows.add(newRowInt);
        offset.add(newRowOffset);
        names.add(newRowNames);
    }

    public int getNumberOfRows(){
        return rows.size();
    }

    public int getRow(int index){
        if(getNumberOfRows() > index){
            return rows.get(index);
        }
        return -1;
    }

    public int getOffset(int index){
        if(getNumberOfRows() > index){
            return offset.get(index);
        }
        return -1;
    }

    public String getString(int index, int buttonName){
        if(getNumberOfRows() > index){
            return names.get(index).get(buttonName);
        }
        return "";
    }
}
