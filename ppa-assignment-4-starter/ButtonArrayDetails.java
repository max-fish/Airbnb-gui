import java.util.List;

/**
 * Stores details about a single row of buttons.
 *
 * @version 0.2.0
 */
public class ButtonArrayDetails {

    private Integer offset;
    private List<String> names;


    public ButtonArrayDetails(int newOffset, List<String> newNames){
        offset = newOffset;
        names = newNames;
    }

    public int getRow(){
        return names.size();
    }

    public int getOffset(){
        return offset;
    }

    public String getString(int index){
        if(getRow() > index){
            return names.get(index);
        }
        return "";
    }
}
