# hw03
## Homework 03 - Due Sunday, Sept 17 at 11:59 PM

This week, let's do one final exercise with simple object oriented design using arrays. Let's model a savings account, but we are going to get a little more sophisticated than previous bank account models. For instance, we will keep track of each transaction that occurs on the account, and print monthly reports of all transactions. In order to do so, we need to design a transaction class.

:warning: Please put all classes for this homework assignment in package *hw03*.

##  The Transaction Class

Make a Java class called *Transaction* which has four fields, the *startingBalance*, the *transactionAmount*, the *endingBalance*, and a *transactionType*. The amount fields can all be doubles to allow dollars and cents, but the transactionType field should be a single character. (Actually, the transactionType should be an enumerated field, but we haven't learned about those yet, so we'll just use a single character, using 'D' to represent a deposit transaction, 'I' to represent an interest transaction, and a 'W' to indicate a withdrawal transaction.)

Code a creator for the Transaction class that takes three arguments... the starting balance, the type of transaction, and the amount of the transaction. (Your creator should be able to calculate the ending balance.)

A transaction should also have a *printLine* method which prints the current transaction to the screen, formatted correctly. Since we haven't learned about the String.format static method, I'll provide this code for you:
```java
public void printLine() {
	System.out.println(String.format(" | $%10.02f | %c | $%10.02f | $%10.02f |",
  	startingBalance,transactionType,transactionAmount,endingBalance));
}
```
The String.format method acts very similarly to the C "printf" function - every % starts a new field whose value is provided by an argument. The %10.02f specification says take a floating point argument, format it so that it takes at least 10 characters, right justified, with two decimal places and the decimals are padded on the right with zeroes.

Since the Transaction class knows the format of the printing of one specific transaction, provide two static methods, one to print a report header called *reportHeader*. The reportHeader method should take two arguments - an integer account number, and an instance of the Library class *java.time.YearMonth*. The reportHeader method should print out the account, and a formated year and month. (In order to format the year and month, import the following:
```java
import java.time.format.DateTimeFormatter;
```
... then you can format print out information with the java code:
```java
System.out.println(" Transactions for account: " + acct + " for " + 
	month.format( DateTimeFormatter.ofPattern("yyyy - LLL")));
```
After this information line, print out column headers, separated by vertical bars. Look at the println statemetn for printLine to figure out how the column headers should be spaced. Then invoke the static printDivider method...

Also, provide a static printDivider method which just prints a dividing line of '-' and '+' (dashes in the columns, and plus signs as column separators.)

## Coding an Account Class

Next, code an account class. Your account class should have fields for account number (an integer is good enough), for the current balance of the account, an array of Transactions so you can remember the transactions, an integer to keep track of where the next unused transaction exists, and an instance of java.time.YearMonth.

Also, provide a static integer called "nextAccount", initialized to the value of 1000. This will be used to keep track of the next available account number. (We will start account numbers at 1000, just so noboday has to have account 1, account 2, etc.)

Code a creator for a new account that takes a single argument - a double value that indicates the intial balance of the account. The Creator should assign the account number using the "nextAccount" static varaible, and then increment nextAccount (so the next time the creator is invoked, the next person will get a different account number... does the concept of static variables make any more sense after trying this out?) The creator should instantiate the array of transactions. If you provide space for 100 transations, that will be plenty for this application. Once you have allocated the array, invoke the *addTrans* private method. The addTrans method will add a new transaction to the account, and will take, as arguments, the starting balance, the transaction type, and the transaction amount. From the creator, invoke this with a starting balance of 0.0, a transaction type of 'D' for deposit, and an amount of initBalance. The creator should also initialize the YearMonth field by invoking the static method "YearMonth.now()", which sets the field to the current year and month. Don't forget to set the balance field to the value of the parameter.

I've already mentioned a private addTrans method that takes three parameters --- the starting balance, the transaction type, and the amount. Invoke the Transaction object creator using those parameters, and assign the result to the array of transactions, using the transaction index value. (It gets intialized to zero when an Account object is created.) Then increment the transaction index. (The transaction index always points at the next available transaction in the transaction array.) The addTrans method should be private because you don't want anyone else making a transaction for you... that would mess up your statement!

Next, code the standard deposit and withdraw methods. Both of these methods should take a single parameter - the amount to deposit or withdraw. Both should invoke addTrans with the correct arguments, and both should update the balance. The withdraw method should check to make sure that you are not trying to withdraw more than the current balance in the account. If the user tries to withdraw more than the current balance, print a message of the form:
```java
System.out.println("Account: " + acctNumber + " requested a withdrawal of " + String.format("$%.02f",amt) + 
  " but only " + String.format("$%.02f",balance) + " is available.");
```
Then, allow the user to withdraw all of the current balance, but no more than the current balance.  If the amount of the withdrawal is less than the current balance, withdraw the amount requested.

Provide a *report* method that prints the account number, and the current balance (formatted correctly as dollars and cents).

Finally, provide a *printStatement* method that takes no arguments. You may assume this method is invoked one every month. This method should first calculate the amount of interest earned this month. This account earns 5% interest per year, so the monthly interest is the current balance, times ( 0.05 / 12 ) (one twelfth of the annual interest.)  Create a transaction of type 'I' to represent this interest, and add the interest to the balance. Then print the transaction header, and create a for loop - start at zero and count up to (but not including) the current transaction index field value. For each index, invoke the printLine method on that transaction. After this loop, invoke the printDivider function, and print an extra new line. Finally, increment the YearMonth field by invoking the "plusMonths(1)" method on the YearMonth object, and assigning the result back to the yearMonth field. Also, reset the transaction index field back to zero. (After you have printed out transactions for the previous month, start a new set of transactions for the next month.)

## Coding a Tester Class
Create a *Tester* class that has a main method. The main method should create at least two accounts, do withdrawals and deposits from those accounts, have at least one account request a withdrawal of more than the current balance, and invoke printStatement at least a couple of times on each account.

Make sure everything is working correctly.

## Submitting your Results

Don't forget the magic git commands:
```
git add Transaction.java Account.java Tester.java
git commit -am "Everything coded and tested"
git push
git rev-parse HEAD
```
Then copy and paste the resulting hash into myCourses CS140/Content/Homework Submissions/Homework 03.

## Grading Criteria

This homework is worth 10 points. If your code does not compile, you will get an 8 point deduction. If your code gets a compiler warning, you will get a 2 point deduction. The CA's will run your hw03.Tester main function. If the tester main method does not do everything requested with correct results, you will get a 4 point deduction. The CA's will also code a Tester2 class that creates and manipulates their own accounts. If there are any problems with the Tester2 class (e.g. method names don't match, or wronge results occur), you will get up to a 3 point deduction (depending on the severity of the problem).

The CA's will create their own Tester class which will create accounts, withdraw, deposit, and create statements. If the CA's tester class does not compile correctly (because of incorrect method names or signatures), 4 points will be deducted. If the CA's tester class produces incorrect results, 3 points will be deducted.
