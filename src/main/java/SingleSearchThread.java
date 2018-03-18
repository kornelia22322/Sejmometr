import org.json.JSONObject;

public class SingleSearchThread implements Runnable {

    private JSONObject jsonObject;
    private int lengthofjsonArray;
    private String searchedname;
    private static volatile Deputy deputy;

    public static Deputy getDeputy() {
        return deputy;
    }

    public SingleSearchThread(JSONObject jsonObject, String searchedname) {
        this.jsonObject = jsonObject;
        this.searchedname = searchedname;
        this.lengthofjsonArray = jsonObject.getJSONArray("Dataobject").length();
    }

    @Override
    public void run() {
        for(int i = 0; i < lengthofjsonArray; i++) {
            JSONObject deputyData = jsonObject.getJSONArray("Dataobject")
                    .getJSONObject(i)
                    .getJSONObject("data");
            if(deputyData.getString("ludzie.nazwa").equals(searchedname)) {
                Deputy deputy = new Deputy(searchedname, deputyData);
                this.deputy = deputy;
                break;
            }
        }
    }
}
