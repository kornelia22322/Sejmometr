import org.json.JSONObject;

public class Deputy {
    private String name;
    private int jsonId;
    private JSONObject jsonObject;

    public Deputy(String name, JSONObject jsonObject) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getjsonId() {
        return jsonId;
    }
}
