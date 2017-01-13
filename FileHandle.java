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
			System.out.println("Przekroczono limit czasu po³¹czenia!");
			System.exit(0);
			return null;
		}

	}

	public static void main(String[] args) throws IOException, ParseException, IllegalArgument, NoSuchaDeputy {

		/*
		 Opracuj system, który na podstawie argumentów linii poleceñ wyœwietla
		 nastêpuj¹ce informacje (dla okreœlonej kadencji sejmu): 
		 S - suma wydatków pos³a/pos³anki o okreœlonym imieniu i nazwisku 
		 W - wysokoœci wydatków na 'drobne naprawy i remonty biura poselskiego' okreœlonego pos³a/pos³anki
		 SR - œredniej wartoœci sumy wydatków wszystkich pos³ów 
		 NP - pos³a/pos³anki, który wykona³ najwiêcej podró¿y zagranicznych
		 NG - pos³a/pos³anki, który najd³u¿ej przebywa³ za granic¹ 
		 NPZ - pos³a/pos³anki, który odby³ najdro¿sz¹ podró¿ zagraniczn¹ 
		 L - listê wszystkich pos³ów, którzy odwiedzili W³ochy
		 */
		
		  ArgumentsChecker arg=new ArgumentsChecker(args);	
		  arg.convert();

		    

	}

}