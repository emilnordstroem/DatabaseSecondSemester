package bankAccountPeterson;

public class TestBankAccount {
	private static boolean[] flag = new boolean[2];
	private static int turn;

	public static void main(String[] args) {
		flag[0] = false;
		flag[1] = false;
		turn = 1;

		BankAccount ba = new BankAccount();
			BankAccountTraad bat1 = new BankAccountTraad(ba);
			BankAccountTraad2 bat2 = new BankAccountTraad2(ba);
			
			bat1.start();
			bat2.start();

		try {
			bat1.join();
			bat2.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println(ba.getBalance());
	}

	public static boolean getFlag(int index) {
		return flag[index];
	}

	public static void setFlag(int index, boolean flag) {
		TestBankAccount.flag[index] = flag;
	}

	public static int getTurn() {
		return turn;
	}

	public static void setTurn(int turn) {
		TestBankAccount.turn = turn;
	}
}