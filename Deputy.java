package com.json;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class Deputy {

	private static HashMap<String, String> hmap;

	public Deputy() throws IOException {
		hmap = new HashMap<String, String>();
		Deputy.hmap=Deputy.createMap();
	}
	
	public HashMap<String, String> getMap()
	{
		return hmap;
	}

	static HashMap<String, String> createMap() throws IOException {

		FileHandle som = new FileHandle();
		String resStr = som.run("https://api-v3.mojepanstwo.pl/dane/poslowie.json?conditions[poslowie.kadencja]=8");
		JSONObject json = new JSONObject(resStr);

		JSONObject Links = json.getJSONObject("Links");
		String last = Links.getString("last");

		String next = "";
		String ID = "";

		while (!next.equals(last)) {
			JSONArray dataobject = json.getJSONArray("Dataobject");

			for (int i = 0; i < dataobject.length(); i++) {

				JSONObject obj4 = dataobject.getJSONObject(i);
				ID = obj4.getString("id");
				

				JSONObject obj5 = obj4.getJSONObject("data");
				String name = obj5.getString("ludzie.nazwa");
				
				//System.out.println(ID+" "+name);
				hmap.put(ID, name);
				
			}

		

		next = Links.getString("next");
		resStr = som.run(next);
		json = new JSONObject(resStr);
		Links = json.getJSONObject("Links");
		
				
		}
		
		return hmap;
	}
}
