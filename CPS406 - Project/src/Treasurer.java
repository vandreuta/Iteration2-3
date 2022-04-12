import java.util.HashMap;

public class Treasurer extends User {
    
    private HashMap<String, Double> incomeList;
	private HashMap<String, Double> expenseList;
	private HashMap<String, Double> expense;
	private Double profit;
	private String month;
	private String year;

    public Treasurer(String name, String email, String month, String year){
        super(name, email);

        this.incomeList = new HashMap<String, Double>();
        this.expenseList = new HashMap<String, Double>();
        this.expense = new HashMap<String, Double>();
        this.profit = 0.0;
        this.month = month;
        this.year = year;

    }

    public void addIncome(String info, Double value) {
		//Member payment and any other income should go here//
		this.incomeList.put(info, value);
		this.profit += value;
	}
	
	public void addExpense(String info, Double value) {
		//Coach's fee, hall expense, and other expense should go here//
		this.expenseList.put(info, value);
		this.expense.put(info, value);
		this.profit -= value;
	}
	
	public void payExpense(String info, Double value) {
		if(this.expense.containsKey(info)) {
			this.expense.remove(info);
		}
	}
	
	public void payExpense(String info) {
		if(this.expense.containsKey(info)) {
			this.expense.remove(info);
		}
	}
	
	public double getProfit() {
		return profit;
	}
	
	public String getLoggingList() {
		String report = "Logging Infomation | Year: " + this.year + " | Month: " + this.month;
		report += "\nIncome:";
		for(String info : this.incomeList.keySet()) {
			report += "\n" + info + " " + this.incomeList.get(info);
		}
		report += "\nExpense:";
		for(String info : this.expenseList.keySet()) {
			if (this.expense.containsKey(info)) {
				report += "\n" + info + " " + this.expenseList.get(info) + "(Unpaid)";
			}
			else {
				report += "\n" + info + " " + this.expenseList.get(info) + "(Paid)";
			}
		}
		report += "\nProfit: " + this.getProfit();
		return report;
	}

}
