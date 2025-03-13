package bankAccountPeterson;

public class BankAccountTraad2 extends Thread {
	private BankAccount ba;

	public BankAccountTraad2(BankAccount ba) {
		this.ba = ba;
	}

	public void run() {
		int i = 0;
		while (i < 4) {
			try {
				this.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			TestBankAccount.setFlag(1, true);
			TestBankAccount.setTurn(0);

			while (TestBankAccount.getFlag(0) && TestBankAccount.getTurn() == 1); // break line

			ba.setBalance(100, "d"); // critical section

			TestBankAccount.setFlag(1, false); // change flag
			i++;
		}
		System.out.println("Balancen er: " + ba.getBalance());
	}

}
