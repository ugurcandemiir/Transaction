package hw03;

import java.time.YearMonth;

public class Account {
	private int accountNumber;
	private double currentBalance;
	private Transaction[] arrayOfTransaction ;
	private YearMonth currentday;
	static int nextAccount = 1000;
	int nextLogIndex;

	
	
	public Account(double initialBalanceOfTheAccount) {
		accountNumber = nextAccount;
		nextAccount+=1;
		currentday = YearMonth.now();
		nextLogIndex = 0;
		arrayOfTransaction = new Transaction[100];
		addTrans(currentBalance,'D',initialBalanceOfTheAccount);
		currentBalance =initialBalanceOfTheAccount ; 
	}
	

	private void addTrans(double d, char typeOfTransaction, double initBalance2) {
		arrayOfTransaction[nextLogIndex] = new Transaction(d,typeOfTransaction,initBalance2)  ;
		nextLogIndex++;
		
	}
	public void StandartDeposit(double amountOfDeposit) {
		addTrans(currentBalance,'D',amountOfDeposit);
		currentBalance=currentBalance + amountOfDeposit;

	}

	void Withdraw(double amountOfWithdraw) {
		if (amountOfWithdraw > currentBalance) {
			System.out.println("Account: " + accountNumber + " requested a withdrawal of " + amountOfWithdraw + " but only " + currentBalance + " is available.");
			addTrans(currentBalance,'W',currentBalance);
	
		
		}else {
			addTrans(currentBalance,'W',amountOfWithdraw);
			currentBalance=currentBalance - amountOfWithdraw;
		}

	}
	void report() {
		System.out.println("Account: " + accountNumber + " Current balance: " +
			String.format("%f", currentBalance));
	}
	public void printStatement() {
		double interest=currentBalance*(0.05/12);
		arrayOfTransaction[nextLogIndex++]=new Transaction(currentBalance,'I',interest);
		currentBalance=currentBalance+ interest ;
		Transaction.reportHeader(accountNumber,currentday);
		for(int i=0; i<nextLogIndex; i++) {
			arrayOfTransaction[i].printLine();
		}
		Transaction.printDivider();
		currentday = currentday.plusMonths(1);
		nextLogIndex = 0;

		}
}


