package hw03;
import java.time.format.DateTimeFormatter;


public class Transaction {
	private double startingBalance ;
	private double transactionAmount;
	private double endingBalance;
	private char transactionType ;//Either D for deposit or W for withdrawal or I for Interest 

	
	public Transaction(double startingBalance,char transactionType,double transactionAmount) {
		this.startingBalance = startingBalance;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		
		if(transactionType == 'W' ) {
			endingBalance =  	startingBalance - transactionAmount   ;
		}else {
			endingBalance =  	startingBalance + transactionAmount   ;

		}
	}
	
	public void printLine() {
		System.out.println(String.format(" | $%10.02f | %c | $%10.02f | $%10.02f |",
				startingBalance,transactionType,transactionAmount,endingBalance));
		printDivider();
	}
	
	
	public static void reportHeader(int accountNumber, java.time.YearMonth month) {
	
		System.out.println(" Transactions for account: " + accountNumber + " for " + month.format( DateTimeFormatter.ofPattern("yyyy - LLL")));
	
	}
	public static void printDivider() {
		System.out.println(" | ----------- + - + ----------- + ----------- | ");
	}

}


