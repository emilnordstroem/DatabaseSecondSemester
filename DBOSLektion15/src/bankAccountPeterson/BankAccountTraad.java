package bankAccountPeterson;

public class BankAccountTraad extends Thread {
	private BankAccount ba;

	public BankAccountTraad(BankAccount ba) {
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
			TestBankAccount.setFlag(0,true);
			TestBankAccount.setTurn(1);

			while(TestBankAccount.getFlag(1) && TestBankAccount.getTurn() == 1); // Break line

			ba.setBalance(100, "c"); // Critical section

			TestBankAccount.setFlag(0,false); // change flag
			i++;
		}
		System.out.println("Balancen er: " + ba.getBalance());
	}

}
