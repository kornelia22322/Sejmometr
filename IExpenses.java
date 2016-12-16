package com.json;

import java.util.ArrayList;

public interface IExpenses {
	
	double getExpensesForDeputy(Deputy deputy);
	
	/* suma wydatków pos³a/pos³anki o okreœlonym imieniu i nazwisku */
	
	double getRenovationExpenses(Deputy deputy);
	
	/* wysokoœci wydatków na 'drobne naprawy i remonty biura poselskiego' okreœlonego pos³a/pos³anki */
	
	double getAverage(ArrayList<Deputy> deputies);
	
	
}
