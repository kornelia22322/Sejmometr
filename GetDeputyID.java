package com.json;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetDeputyID {
	
	/* return deputyID from String deputy */

	String getID(String deputy) throws IOException {
		
		FileHandle som = new FileHandle();
		String resStr = som.run("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8");
		JSONObject json = new JSONObject(resStr);

		JSONObject Links = json.getJSONObject("Links");
		String last = Links.getString("last");

		String next = "";		
		String ID = "";
		boolean found = false;

		while (!next.equals(last) && !found) {
			JSONArray dataobject = json.getJSONArray("Dataobject");

			for (int i = 0; i < dataobject.length(); i++) {
				if (!found) {
					JSONObject obj4 = dataobject.getJSONObject(i);
					ID = obj4.getString("id");

					JSONObject obj5 = obj4.getJSONObject("data");
					String name = obj5.getString("ludzie.nazwa");

					if (name.equals(deputy)) {
						found = true;
						//System.out.println("ID pos³a " + ID + ". Pose³:  " + name);
					}
				}
			}

			if (!found) {
				next = Links.getString("next");
				resStr = som.run(next);
				json = new JSONObject(resStr);
				Links = json.getJSONObject("Links");
			}

		}
		
		return ID;
	}

}
