import java.util.*;
import java.io.*;
/**
 * Write a description of class Bank here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bank 
{ 
    private String name;
    private int employees;
    private ArrayList<BankAccount> listOf;
    public Bank(String n, int e) throws IOException
    {
        employees = e;
        name = n;
        listOf = new ArrayList<BankAccount>();
    }

    public void addAccount(BankAccount b)
    {
        listOf.add(b);
    }

    public String getName()
    {
        return name; 
    }

    public int getEmployees()
    {
        return employees; 
    }

    public ArrayList<BankAccount> getList()
    {
        return listOf;
    }
    
    public void updateAccounts(BankAccount[] b)
    {
        listOf = new ArrayList<BankAccount>();
        
        for(int i = 0; i<b.length; i++)
        {
            listOf.add(b[i]);
        }
    }
    
    public String toString()
    {
        String a = "";

        a+="BankName="+name+"\n";
        a+="NumberofEmployees="+employees+"\n";
        for( BankAccount b : listOf)
        {
            if(b instanceof SavingsAccount)
            {

                a+="SavingsAccount="+b.getName()+"\n";
                a+="AccountNumber="+b.getAccountNumber()+"\n";
                a+="Balance="+b.getBalance()+"\n";
                a+="Rate="+((SavingsAccount)b).getRate()+"\n";

            }
            else
            {
                a+="BankAccount="+b.getName()+"\n";
                a+="AccountNumber="+b.getAccountNumber()+"\n";
                a+="Balance="+b.getBalance()+"\n";  
            }
        }
        a=a.trim();
        return a;
    }
}
