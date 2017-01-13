package com.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Expenses implements IExpenses {

	Deputy deputy;
	HashMap<String, String> hmap;

	@Override
	public void getExpensesForDeputy(String ID) throws IOException {

		FileHandle som = new FileHandle();
		String URL = "https://api-v3.mojepanstwo.pl/dane/poslowie/" + ID + ".json?layers[]=krs&layers[]=wydatki";
		String resStr = som.run(URL);
		JSONObject json = new JSONObject(resStr);
		JSONObject layers = json.getJSONObject("layers");
		JSONObject wydatki = layers.getJSONObject("wydatki");
		JSONArray roczniki = wydatki.getJSONArray("roczniki");

		// kim jest pose³?
		JSONObject data = json.getJSONObject("data");
		String whoIs = data.getString("ludzie.nazwa");

		System.out.println("Pose³ " + whoIs);

		// tablica iteruj¹ca po polach
		for (int i = 0; i < roczniki.length(); i++) {
			JSONObject obj4 = roczniki.getJSONObject(i);			
			JSONArray obj5 = obj4.getJSONArray("pola");
			double value = 0.0;
			for (int j = 0; j < obj5.length(); j++) {
				String txt = obj5.getString(j);
				value += Double.parseDouble(txt);
			}
			String year = obj4.getString("rok");

			Double truncatedDouble = BigDecimal.valueOf(value).setScale(3, RoundingMode.HALF_UP).doubleValue();

			System.out.println("Suma wydatków za rok " + year + " wynosi " + truncatedDouble);
		}

	}

	@Override
	public void getRenovationExpenses(String ID) throws IOException {

		FileHandle som = new FileHandle();
		String URL = "https://api-v3.mojepanstwo.pl/dane/poslowie/" + ID + ".json?layers[]=krs&layers[]=wydatki";
		String resStr = som.run(URL);
		JSONObject json = new JSONObject(resStr);
		JSONObject layers = json.getJSONObject("layers");
		JSONObject wydatki = layers.getJSONObject("wydatki");
		JSONArray roczniki = wydatki.getJSONArray("roczniki");

		// kim jest pose³?
		JSONObject data = json.getJSONObject("data");
		String whoIs = data.getString("ludzie.nazwa");

		System.out.println("Pose³ " + whoIs);

		// tablica iteruj¹ca po polach
		for (int i = 0; i < roczniki.length(); i++) {
			JSONObject obj4 = roczniki.getJSONObject(i);		
			JSONArray obj5 = obj4.getJSONArray("pola");
			double value = 0.0;
			String txt = obj5.getString(12); // 12 pole w tablicy - bo 'drobne wydatki..' to 13.
			value = Double.parseDouble(txt);

			String year = obj4.getString("rok");

			System.out.println("Suma wydatków na 'drobne naprawy i remonty biura poselskiego' w roku " + year
					+ " wynosi " + value);
		}

	}

	@Override
	public void getAverage() throws IOException {

		deputy = new Deputy();
		this.hmap = deputy.getMap();
		int counterFields = 0;
		Double sumowanie = 0.0;
		Double average = 0.0;

		Iterator it = hmap.entrySet().iterator();
		while (it.hasNext()) {
			
			Map.Entry pair = (Map.Entry) it.next();
			String ID = (String) pair.getKey();

			FileHandle som = new FileHandle();
			String URL = "https://api-v3.mojepanstwo.pl/dane/poslowie/" + ID + ".json?layers[]=wydatki";

			String resStr = som.run(URL);
			JSONObject json = new JSONObject(resStr);

			JSONObject layers = json.getJSONObject("layers");
			JSONObject wydatki = layers.getJSONObject("wydatki");
			JSONArray roczniki = wydatki.getJSONArray("roczniki");

			// tablica iteruj¹ca po polach
			for (int i = 0; i < roczniki.length(); i++) {

				counterFields++;

				JSONObject obj4 = roczniki.getJSONObject(i);				
				JSONArray obj5 = obj4.getJSONArray("pola");

				for (int j = 0; j < obj5.length(); j++) {
					String txt = obj5.getString(j);
					sumowanie += Double.parseDouble(txt);
				}

			}

		}
		average = sumowanie / counterFields;
		Double averageGoodForm = BigDecimal.valueOf(average).setScale(3, RoundingMode.HALF_UP).doubleValue();
		System.out.println("Œrednia suma wydatków za rok pos³a wynosi " + averageGoodForm);
		

	}
}
