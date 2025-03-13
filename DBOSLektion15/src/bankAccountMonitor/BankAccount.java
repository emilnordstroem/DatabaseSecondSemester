package bankAccountMonitor;

public class BankAccount {

	private double balance;

	// synchronized in method-signature
	public synchronized void setBalance(double amount, String action) {
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
