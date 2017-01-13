package com.json;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FileHandle {

	public static OkHttpClient client;

	String run(String url) throws IOException {

		try {
			client = new OkHttpClient();

			client = new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS)
					.readTimeout(30, TimeUnit.SECONDS).build();

			Request request = new Request.Builder().url(url).build();

			Response response = client.newCall(request).execute();
			return response.body().string();

		} catch (SocketTimeoutException|UnknownHostException exception) {
			System.out.println("Przekroczono limit czasu po��czenia!");
			System.exit(0);
			return null;
		}

	}

	public static void main(String[] args) throws IOException, ParseException, IllegalArgument, NoSuchaDeputy {

		/*
		 Opracuj system, kt�ry na podstawie argument�w linii polece� wy�wietla
		 nast�puj�ce informacje (dla okre�lonej kadencji sejmu): 
		 S - suma wydatk�w pos�a/pos�anki o okre�lonym imieniu i nazwisku 
		 W - wysoko�ci wydatk�w na 'drobne naprawy i remonty biura poselskiego' okre�lonego pos�a/pos�anki
		 SR - �redniej warto�ci sumy wydatk�w wszystkich pos��w 
		 NP - pos�a/pos�anki, kt�ry wykona� najwi�cej podr�y zagranicznych
		 NG - pos�a/pos�anki, kt�ry najd�u�ej przebywa� za granic� 
		 NPZ - pos�a/pos�anki, kt�ry odby� najdro�sz� podr� zagraniczn� 
		 L - list� wszystkich pos��w, kt�rzy odwiedzili W�ochy
		 */
		
		  ArgumentsChecker arg=new ArgumentsChecker(args);	
		  arg.convert();

		    

	}

}