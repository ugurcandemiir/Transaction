package hw03;

public class Tester {

	public static void main(String[] args) {

		Account acct1 = new Account(500.0);
		acct1.report();
		acct1.StandartDeposit(100);
		acct1.printStatement();	
		acct1.Withdraw(550.00);
		acct1.printStatement();
		
		
		Account acct2 = new Account(1000.0);
		acct2.report();
		acct2.StandartDeposit(10000.0);
		acct2.printStatement();
		acct2.Withdraw(850.00);
		acct2.printStatement();
		
		
		
		Account acct3 = new Account(250.0);
		acct3.report();
		acct3.StandartDeposit(100);
		acct3.printStatement();	
		acct3.Withdraw(3000.00);
		acct3.printStatement();
		
		Account acct4 = new Account(4000.0);
		acct4.report();
		acct4.StandartDeposit(110);
		acct4.printStatement();	
		acct4.Withdraw(1000.00);
		acct4.printStatement();

	}
}


