package com.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ArgumentsChecker {

	private String[] arguments;
	private String[] options;
	private String deputy;
	private Engine engine;

	public ArgumentsChecker(String[] arguments) {
		this.arguments = arguments;
		this.options = new String[] { "S", "W", "SR", "NP", "NG", "NPZ", "L" };
	}

	public void convert() throws IOException, IllegalArgument, NoSuchaDeputy {

		try {
			
			if((arguments[0].equals("S") || arguments[0].equals("W")) && arguments.length!=3)
			{
				IllegalArgument exc = new IllegalArgument("Nieprawid³owa liczba argumentów w linii poleceñ! Podaj imiê i nazwisko pos³a");
				throw exc;				
			}
				
			if((!arguments[0].equals("S") && !arguments[0].equals("W")) && arguments.length!=1)
			{
				IllegalArgument exc = new IllegalArgument("Nieprawid³owe argumenty w linii poleceñ!");
				throw exc;				
			}
				
			
			boolean ifok = false;
			
			for (int i = 0; i < options.length; i++) {
				if (arguments[0].equals(options[i]))
					ifok = true;
			}

			if (!ifok) {
				IllegalArgument exc = new IllegalArgument("Wybierz jedn¹ z dostêpnych opcji!");
				throw exc;
			}			
			
			else
			{
				if (arguments[0].equals("S") || arguments[0].equals("W")) {
					String name = arguments[1];
					String surname = arguments[2];
					this.deputy = name + " " + surname;
					if (!ArgumentsChecker.checkifExists(deputy)) {
						NoSuchaDeputy exc = new NoSuchaDeputy("Pose³ nie istnieje w bazie! ");
						throw exc;
					}
					else
						this.engine=new Engine(arguments);
				}
				else
					this.engine=new Engine(arguments);
			}

		} catch (IllegalArgument|NoSuchaDeputy ex) {
			System.out.println(ex.getMessage());
			System.exit(0);
		}

	}
	
	public static boolean checkifExists(String maybyDeputy) throws IOException
	{
		boolean iscorrectDeputy=false;
		
		Deputy deputy=new Deputy();
		HashMap<String, String> hmap=deputy.getMap();
		Iterator it = hmap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			String name = (String) pair.getValue();
			if(name.equals(maybyDeputy))
				iscorrectDeputy=true;
	}
		
		return iscorrectDeputy;
	}
	
	
	
}
