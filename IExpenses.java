package com.json;

import java.util.ArrayList;

public interface IExpenses {
	
	double getExpensesForDeputy(Deputy deputy);
	
	/* suma wydatk�w pos�a/pos�anki o okre�lonym imieniu i nazwisku */
	
	double getRenovationExpenses(Deputy deputy);
	
	/* wysoko�ci wydatk�w na 'drobne naprawy i remonty biura poselskiego' okre�lonego pos�a/pos�anki */
	
	double getAverage(ArrayList<Deputy> deputies);
	
	
}
