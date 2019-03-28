/**
 * Write a description of class BankAccount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BankAccount extends Account implements Comparable<BankAccount>
{
    private double balance;
    private String name;
    private int accountNumber;

    private static int totalAccounts = 0;

    public BankAccount( String n, double b )
    {
        balance = b;
        name = n;
        accountNumber = totalAccounts + 1;
        totalAccounts++;
    }
    
    public BankAccount( String n, double b, int num)
    {
        balance = b;
        name = n;
        accountNumber = num;
        totalAccounts++;
    }

    public int getAccountNumber()
    {
        return accountNumber;
    }

    public String getName()
    {
        return name;
    }

    public void withdraw( double amount )
    {
        balance -= amount;
    }

    public void deposit( double amount )
    {
        balance += amount;
    }

    public double getBalance()
    {
        return balance;
    }

    public int compareTo( BankAccount otherObject)
    {
        // sort by balance
        if(this.balance - otherObject.balance < 0)
        {
            return -1;
        }
        else if( this.balance - otherObject.balance > 0 )
        {
            return 1;
        }
        else if( this.balance - otherObject.balance == 0)
        {
            // sort by name
            return this.name.compareTo( otherObject.name );
        }

        return 0;
    }

    public String toString()
    {
        return "Name=" + this.name + " Balance=" + this.balance + " Account Number =" + accountNumber;
    }

}