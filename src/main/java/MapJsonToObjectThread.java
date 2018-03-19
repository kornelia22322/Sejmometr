import org.json.JSONObject;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MapJsonToObjectThread implements Runnable{

    private JSONObject jsonObject;
    private int lengthofjsonArray;
    private static List<Deputy> syncList = new CopyOnWriteArrayList<>();

    public MapJsonToObjectThread(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        this.lengthofjsonArray = jsonObject.getJSONArray("Dataobject").length();
    }

    public static List<Deputy> getSyncList() {
        return syncList;
    }

    @Override
    public void run() {
        for(int i = 0; i < lengthofjsonArray; i++) {
            JSONObject deputyData = jsonObject.getJSONArray("Dataobject")
                    .getJSONObject(i)
                    .getJSONObject("data");
            int id = jsonObject.getJSONArray("Dataobject")
                    .getJSONObject(i)
                    .getInt("id");
            Deputy deputy = new Deputy.Builder().setid(id)
                    .setName(deputyData.getString("ludzie.nazwa"))
                    .setArea(deputyData.getInt("poslowie.okreg_wyborczy_numer"))
                    .setAttendance(deputyData.getInt("poslowie.frekwencja"))
                    .setClub(deputyData.getString("sejm_kluby.nazwa"))
                    .setInterpolation_count(deputyData.getInt("poslowie.liczba_interpelacji"))
                    .setFlight_count(deputyData.getInt("poslowie.liczba_przelotow"))
                    .build();
            syncList.add(deputy);

        }
    }
}
