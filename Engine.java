package com.json;

import java.io.IOException;

public class Engine {

	private Expenses expenses;
	private Voyage voyage;
	String deputy;
	GetDeputyID getIDObject;		

	public Engine(String[] arguments) throws IOException {
		switch(arguments[0]){
		case "S": {
			String name = arguments[1];
			String surname = arguments[2];
			this.deputy = name + " " + surname;
			this.expenses=new Expenses();
			this.getIDObject=new GetDeputyID();
			expenses.getExpensesForDeputy(getIDObject.getID(deputy));
			break;
		}
		case "W": {
			String name = arguments[1];
			String surname = arguments[2];
			this.deputy = name + " " + surname;
			this.expenses=new Expenses();
			this.getIDObject=new GetDeputyID();
			expenses.getRenovationExpenses(getIDObject.getID(deputy));	
			break;
		}
		case "SR":{
			this.expenses=new Expenses();
			expenses.getAverage();	
			break;
		}
		case "NP":{
			this.voyage=new Voyage();
			voyage.whoTheMost();
			break;
		}
		case "NG":{
			this.voyage=new Voyage();
			voyage.whoTheLong();
			break;
		}
		case "NPZ":{
			this.voyage=new Voyage();
			voyage.whichWasTheMostExpensive();
			break;
		}
		case "L":{
			this.voyage=new Voyage();
			voyage.whoVisitItaly();
			break;
		}
		
		
	}
	}
}
