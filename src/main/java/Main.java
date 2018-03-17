import org.json.JSONObject;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<JSONObject> arrayList = new DataProvider.Builder().build().getJsonObjects();


        for(JSONObject object: arrayList) {
            System.out.println(object.getJSONObject("Links").getString("self"));
        }
    }
}
