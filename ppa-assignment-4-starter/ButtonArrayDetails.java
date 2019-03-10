import java.util.List;

/**
 * Stores details about a single row of buttons.
 *
 * @version 0.2.0
 */
public class ButtonArrayDetails {

    private Integer offset;
    private String[] names;


    public ButtonArrayDetails(int newOffset, String[] newNames){
        offset = newOffset;
        names = newNames;
    }

    public int getRow(){
        return names.length;
    }

    public int getOffset(){
        return offset;
    }

    public String getString(int index){
        if(getRow() > index){
            return names[index];
        }
        return "";
    }
}
