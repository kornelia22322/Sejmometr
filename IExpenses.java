package com.json;

import java.io.IOException;
import java.util.ArrayList;

public interface IExpenses {
	
	void getExpensesForDeputy(String ID) throws IOException;
	
	/* suma wydatk�w pos�a/pos�anki o okre�lonym imieniu i nazwisku */
	
	void getRenovationExpenses(String ID) throws IOException;
	
	/* wysoko�ci wydatk�w na 'drobne naprawy i remonty biura poselskiego' okre�lonego pos�a/pos�anki */
	
	void getAverage() throws IOException;
	
	
}
