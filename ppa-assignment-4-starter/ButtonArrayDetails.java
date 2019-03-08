import java.util.List;

/**
 *
 * @version 0.2.0
 */
public class ButtonArrayDetails {

    private Integer rows;
    private Integer offset;
    private List<String> names;


    public ButtonArrayDetails(int newRows, int newOffset, List<String> newNames){
        rows = newRows;
        offset = newOffset;
        names = newNames;
    }

    public int getRow(){
        return rows;
    }

    public int getOffset(){
        return offset;
    }

    public String getString(int index){
        if(names.size() > index){
            return names.get(index);
        }
        return "";
    }
}
