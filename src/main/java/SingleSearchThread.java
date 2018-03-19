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
            if(deputy != null) return;
            JSONObject deputyData = jsonObject.getJSONArray("Dataobject")
                    .getJSONObject(i)
                    .getJSONObject("data");
            if(deputyData.getString("ludzie.nazwa").equals(searchedname)) {
                Deputy deputy = new Deputy.Builder()
                        .setName(deputyData.getString("ludzie.nazwa"))
                        .setArea(deputyData.getInt("poslowie.okreg_wyborczy_numer"))
                        .setAttendance(deputyData.getInt("poslowie.frekwencja"))
                        .setClub(deputyData.getString("sejm_kluby.nazwa"))
                        .setInterpolation_count(deputyData.getInt("poslowie.liczba_interpelacji"))
                        .setFlight_count(deputyData.getInt("poslowie.liczba_przelotow"))
                        .build();

                this.deputy = deputy;
                break;
            }
        }
    }
}
