package com.json;

import java.io.IOException;
import java.util.ArrayList;

public interface IExpenses {
	
	void getExpensesForDeputy(String ID) throws IOException;
	
	/* suma wydatków pos³a/pos³anki o okreœlonym imieniu i nazwisku */
	
	void getRenovationExpenses(String ID) throws IOException;
	
	/* wysokoœci wydatków na 'drobne naprawy i remonty biura poselskiego' okreœlonego pos³a/pos³anki */
	
	void getAverage() throws IOException;
	
	
}
