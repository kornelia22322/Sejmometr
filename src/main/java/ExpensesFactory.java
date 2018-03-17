public class ExpensesFactory {

    public static IExpenses chooseExpenses(String name) {
        switch(name) {
            case "SUM":
                return new SumExpenses();
            case "AVERAGE":
                return new AverageExpenses();
            case "LITTLE_EXPENSES":
                return new LittleExpensesAmount();
            default:
                return null;
        }
    }
}
