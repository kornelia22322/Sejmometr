package com.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Voyage implements IVoyage {

	Deputy deputy;
	HashMap<String, String> hmap;
	int theMost;
	String WhoTheMost;
	ArrayList<String> whoVisitItalyList;
	double MostExpensive;

	public Voyage() throws IOException {
		deputy = new Deputy();
		this.hmap = deputy.getMap();
		this.MostExpensive = 0.0;
	}

	@Override
	public void whoTheMost() throws IOException {

		theMost = 0;
		WhoTheMost = "";

		Iterator it = hmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			// System.out.println(pair.getKey() + " = " + pair.getValue());
			String ID = (String) pair.getKey();

			FileHandle som = new FileHandle();
			String URL = "https://api-v3.mojepanstwo.pl/dane/poslowie/" + ID + ".json?layers[]=wyjazdy";

			String resStr = som.run(URL);
			JSONObject json = new JSONObject(resStr);
			JSONObject layers = json.getJSONObject("layers");
			// JSONArray wyjazdy=layers.getJSONArray("wyjazdy");

			// kim jest pose³?
			JSONObject data = json.getJSONObject("data");
			String whoIs = data.getString("ludzie.nazwa");
			int howManyVoyage = data.getInt("poslowie.liczba_wyjazdow");

			if (howManyVoyage > theMost) {
				theMost = howManyVoyage;
				WhoTheMost = whoIs;
			}

			//System.out.println("Pose³ " + whoIs + ". Liczba wyjazdów zagranicznych: " + howManyVoyage);

		}

		System.out.println(
				"Najwiêcej wyjazdów zagranicznych:  " + WhoTheMost + ". Liczba wyjazdów zagranicznych: " + theMost);

	}

	@Override
	public void whoTheLong() throws IOException {

		int maxdni = 0;
		String whoTheLong = "";

		Iterator it = hmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			// System.out.println(pair.getKey() + " = " + pair.getValue());
			String ID = (String) pair.getKey();

			FileHandle som = new FileHandle();
			String URL = "https://api-v3.mojepanstwo.pl/dane/poslowie/" + ID + ".json?layers[]=wyjazdy";

			String resStr = som.run(URL);
			JSONObject json = new JSONObject(resStr);

			// kim jest pose³?
			JSONObject data = json.getJSONObject("data");
			String whoIs = data.getString("ludzie.nazwa");
			int howManyVoyage = data.getInt("poslowie.liczba_wyjazdow");

			int dni = 0;
			if (howManyVoyage != 0) {

				JSONObject layers = json.getJSONObject("layers");

				Object checkIf = layers.get("wyjazdy");

				if (checkIf instanceof JSONArray) {
					JSONArray wyjazdy = layers.getJSONArray("wyjazdy");

					for (int i = 0; i < wyjazdy.length(); i++) {

						JSONObject obj4 = wyjazdy.getJSONObject(i);

						dni += obj4.getInt("liczba_dni");

					}

					if (dni > maxdni) {
						maxdni = dni;
						whoTheLong = whoIs;
					}

				}

			}
		}

		System.out.println("Najd³u¿ej za granic¹: " + maxdni + " Pose³: " + whoTheLong);

	}

	@Override
	public void whichWasTheMostExpensive() throws IOException {

		double suma = 0.0;
		String nameof = "";
		Double maxnacodzien = 0.0;
		int maxdni = 0;
		double maxsuma = 0.0;
		String nazwamax = "";

		String nameof2 = "";
		Double maxnacodzien2 = 0.0;
		int maxdni2 = 0;
		double maxsuma2 = 0.0;
		String nazwamax2 = "";

		Iterator it = hmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			// System.out.println(pair.getKey() + " = " + pair.getValue());
			String ID = (String) pair.getKey();

			FileHandle som = new FileHandle();
			String URL = "https://api-v3.mojepanstwo.pl/dane/poslowie/" + ID + ".json?layers[]=wyjazdy";

			String resStr = som.run(URL);
			JSONObject json = new JSONObject(resStr);

			// kim jest pose³?
			JSONObject data = json.getJSONObject("data");
			String whoIs = data.getString("ludzie.nazwa");
			int howManyVoyage = data.getInt("poslowie.liczba_wyjazdow");

			if (howManyVoyage != 0) {

				JSONObject layers = json.getJSONObject("layers");

				Object checkIf = layers.get("wyjazdy");

				if (checkIf instanceof JSONArray) {
					JSONArray wyjazdy = layers.getJSONArray("wyjazdy");

					for (int i = 0; i < wyjazdy.length(); i++) {

						JSONObject obj4 = wyjazdy.getJSONObject(i);
						String koszt = obj4.getString("koszt_suma");
						suma = Double.parseDouble(koszt);

						String name = obj4.getString("delegacja");
						int dni = obj4.getInt("liczba_dni");
						double ilenadzien = suma / dni;

						if (ilenadzien > maxnacodzien) {
							maxnacodzien = ilenadzien;
							nameof = whoIs;
							maxdni = dni;
							maxsuma = suma;
							nazwamax = name;
						}

						if (suma > maxsuma) {
							maxnacodzien2 = ilenadzien;
							nameof2 = whoIs;
							maxdni2 = dni;
							maxsuma2 = suma;
							nazwamax2 = name;
						}

						Double round = BigDecimal.valueOf(maxnacodzien).setScale(3, RoundingMode.HALF_UP).doubleValue();
						maxnacodzien = round;

						Double round2 = BigDecimal.valueOf(maxnacodzien2).setScale(3, RoundingMode.HALF_UP)
								.doubleValue();
						maxnacodzien2 = round2;

					}

				}

			}

		}

		System.out.println("Najdro¿sza podró¿ w przeliczeniu na dzieñ: ");
		System.out.println("Pose³: " + nameof + " - " + nazwamax + " : " + maxsuma + " liczba dni: " + maxdni
				+ " Wydano: " + maxnacodzien + " z³/dzieñ");

		System.out.println("Najdro¿sza podró¿ (koszt ca³kowity) ");
		System.out.println("Pose³: " + nameof2 + " - " + nazwamax2 + " : " + maxsuma2 + " liczba dni: " + maxdni2
				+ " Wydano: " + maxnacodzien2 + " z³/dzieñ");
	}

	@Override
	public void whoVisitItaly() throws IOException {

		this.whoVisitItalyList = new ArrayList<String>();

		Iterator it = hmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();

			// System.out.println(pair.getKey() + " = " + pair.getValue());
			String ID = (String) pair.getKey();

			FileHandle som = new FileHandle();
			String URL = "https://api-v3.mojepanstwo.pl/dane/poslowie/" + ID + ".json?layers[]=wyjazdy";

			String resStr = som.run(URL);
			JSONObject json = new JSONObject(resStr);

			// kim jest pose³?
			JSONObject data = json.getJSONObject("data");
			String whoIs = data.getString("ludzie.nazwa");
			int howManyVoyage = data.getInt("poslowie.liczba_wyjazdow");

			boolean found = false;

			if (howManyVoyage != 0) {

				JSONObject layers = json.getJSONObject("layers");

				Object checkIf = layers.get("wyjazdy");

				if (checkIf instanceof JSONArray) {
					JSONArray wyjazdy = layers.getJSONArray("wyjazdy");

					String countryILook = "W³ochy";

					for (int i = 0; i < wyjazdy.length(); i++) {

						JSONObject obj4 = wyjazdy.getJSONObject(i);
						String country = obj4.getString("kraj");

						if (countryILook.equals(country)) {
							found = true;
						}
					}

				}

			}
			if (found) {
				System.out.println("Pose³ " + whoIs + " odwiedzi³ W³ochy ");
				this.whoVisitItalyList.add(whoIs);
			} else
				System.out.println("Pose³ " + whoIs + " nie odwiedzi³ W³ochy ");

		}

		for (int j = 0; j < whoVisitItalyList.size(); j++)
			System.out.println(whoVisitItalyList.get(j));

	}

}
