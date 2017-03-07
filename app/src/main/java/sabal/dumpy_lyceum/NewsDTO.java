package sabal.dumpy_lyceum;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by Valentun on 07.03.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsDTO {
    ArrayList<New> items;


    public NewsDTO() {
    }

    public ArrayList<New> getItems() {
        return items;
    }

    public void setItems(ArrayList<New> items) {
        this.items = items;
    }
}
