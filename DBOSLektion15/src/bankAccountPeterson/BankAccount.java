package bankAccountPeterson;

public class BankAccount {
	private double balance;

	public void setBalance(double amount, String action) {
		if (action.equals("c")) {
			balance = balance + amount;
		}
		if (action.equals("d")){
			balance = balance - amount;
		}
	}

	public double getBalance() {
		return balance;
	}
}