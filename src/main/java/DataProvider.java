import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.nio.charset.Charset;
import java.net.URL;
import java.util.ArrayList;

//Builder Design Pattern
public class DataProvider {
    private ArrayList<JSONObject> jsonObjects;

    private DataProvider(Builder builder) {
        jsonObjects = builder.jsonObjects;
    }

    public ArrayList<JSONObject> getJsonObjects(){
        return jsonObjects;
    }

    public static class Builder {
        private ArrayList<JSONObject> jsonObjects;
        private static final String URL = "https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8";

        private static JSONObject readJsonFromAPI(String url) throws IOException, JSONException {
            InputStream inputStream = new URL(url).openStream();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
                String jsonText = readAll(bufferedReader);
                JSONObject json = new JSONObject(jsonText);
                return json;
            } finally {
                inputStream.close();
            }
        }

        private static String readAll(Reader reader) throws IOException {
            StringBuilder stringBuilder = new StringBuilder();
            int cp;
            while ((cp = reader.read()) != -1) {
                stringBuilder.append((char) cp);
            }
            return stringBuilder.toString();
        }

        private void retrieveJSONObjectArray() {
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            try {
                JSONObject object = readJsonFromAPI(URL);
                String lastObject = object.getJSONObject("Links").getString("last");
                while(!object.getJSONObject("Links").getString("self").equals(lastObject)) {
                    jsonObjects.add(object);
                    object = readJsonFromAPI(object.getJSONObject("Links").getString("next"));
                }
                jsonObjects.add(object); //add last one
            } catch (IOException e) {
                System.out.println("Cannot get JSON from server.");
                System.exit(1);
            }
            this.jsonObjects = jsonObjects;
        }

        public DataProvider build() {
            retrieveJSONObjectArray();
            return new DataProvider(this);
        }
    }
}
