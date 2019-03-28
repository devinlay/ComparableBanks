import java.io.*;
import java.util.*;
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tester
{ 
    public static void main(String[] args) throws IOException
    {
        String[] x = ReadFromFile.ReadFile("C:\\ProjectFiles\\Bank of OSSM.txt");
        int y = x[0].indexOf("=");
        String name = x[0].substring(y+1);
        y = x[1].indexOf("=");
        int employees = Integer.parseInt(x[1].substring(y+1));
        Bank b = new Bank( name, employees );
        int count = 2;

        while(count<x.length)
        {
            if(x[count].startsWith("BankAccount"))
            {   
                int limit = count+3;
                while(count<limit)
                {
                    y = x[count].indexOf("=");
                    String acctName = x[count].substring(y+1);
                    count++;
                    y = x[count].indexOf("=");
                    int acctNum = Integer.parseInt(x[count].substring(y+1));
                    count++;
                    y = x[count].indexOf("=");
                    double acctBal = Double.parseDouble(x[count].substring(y+1));
                    count++;
                    BankAccount accountToAdd = new BankAccount(acctName, acctBal, acctNum);
                    b.addAccount(accountToAdd);

                }
            }
            else if(x[count].startsWith("SavingsAccount"))
            {
                int limit = count+4;
                while(count<limit)
                {
                    y = x[count].indexOf("=");
                    String acctName = x[count].substring(y+1);
                    count++;
                    y = x[count].indexOf("=");
                    int acctNum = Integer.parseInt(x[count].substring(y+1));
                    count++;
                    y = x[count].indexOf("=");
                    double acctBal = Double.parseDouble(x[count].substring(y+1));
                    count++;
                    y = x[count].indexOf("=");
                    double acctRate = Double.parseDouble(x[count].substring(y+1));
                    count++;
                    SavingsAccount accountToAdd = new SavingsAccount(acctName, acctBal, acctRate, acctNum);
                    b.addAccount(accountToAdd);

                }
            }
        }
        System.out.println("Hello!");
        Scanner kb = new Scanner(System.in);
        int entry;
        do
        {
            System.out.println("If you would like to create a new bank account, please enter 1.");  
            System.out.println("If you would like to create a new savings account, please enter 2.");
            System.out.println("If you do not want to create an account, please enter 0.");
            entry = kb.nextInt();
            switch(entry)
            {
                case 0:
                break;
                case 1:
                System.out.println("What name would you like to save your bank account under?");
                String toEnter = kb.next();
                System.out.println("How much do you wish to initially deposit?");
                double num = kb.nextDouble();
                b.addAccount(new BankAccount(toEnter, num));
                break;
                case 2:
                System.out.println("What name would you like to save your savings account under?");
                toEnter = kb.next();
                System.out.println("How much do you wish to initially deposit?");
                num = kb.nextDouble();
                System.out.println("What is the rate for this account?");
                double r = kb.nextDouble();
                b.addAccount(new SavingsAccount(toEnter, num, r));
                break;
                default:
                System.out.println("Invalid number entered, please try again.");

            }
        }while(entry != 0);
        System.out.println("If you wish to organize accounts by account number, enter 1.");
        System.out.println("If you wish to organize accounts by account name, enter 2.");
        System.out.println("If you wish to organize accounts by account type (Savings Accounts first), enter 3.");
        
        entry = kb.nextInt();
        switch(entry)
        {
            case 1:
            boolean swap = true;
            while(swap)
            {
                swap = false;
                for(int k = 0; k<b.getList().size()-1; k++)
                {
                    if(b.getList().get(k).getAccountNumber()>b.getList().get(k+1).getAccountNumber())
                    {
                        BankAccount temp = b.getList().get(k);
                        b.getList().set(k, b.getList().get(k+1));  
                        b.getList().set(k+1, temp);
                        swap = true;

                    }

                }
            }
            break;
            
            case 2:
            swap = true;
            while(swap)
            {
                swap = false;
                for(int k = 0; k<b.getList().size()-1; k++)
                {
                    if(b.getList().get(k).getName().compareTo(b.getList().get(k+1).getName()) > 0)
                    {
                        BankAccount temp = b.getList().get(k);
                        b.getList().set(k, b.getList().get(k+1));  
                        b.getList().set(k+1, temp);
                        swap = true;

                    }
                    else if(b.getList().get(k).getName().compareTo(b.getList().get(k+1).getName()) == 0)
                    {
                        if(b.getList().get(k).getBalance()>b.getList().get(k+1).getBalance())
                        {
                            BankAccount temp = b.getList().get(k);
                            b.getList().set(k, b.getList().get(k+1));  
                            b.getList().set(k+1, temp);
                            swap = true;
                        }
                        else if(b.getList().get(k).getBalance()==b.getList().get(k+1).getBalance())
                        {
                            if(b.getList().get(k).getAccountNumber()>b.getList().get(k+1).getAccountNumber())
                            {
                                BankAccount temp = b.getList().get(k);
                                b.getList().set(k, b.getList().get(k+1));  
                                b.getList().set(k+1, temp);
                                swap = true;

                            }
                        }

                    }
                    
                    
                }
                

            }
            break;
            
            case 3:
            BankAccount[] n = new BankAccount[b.getList().size()]; 
            AccountTypeComparator comp = new AccountTypeComparator();
            ArrayList<BankAccount> tempList = b.getList();
            n = tempList.toArray(n);
            
            
            Arrays.sort(n, comp);
            b.updateAccounts(n);
            
            break;
            
            default:
            System.out.println("Invalid choice");
            
        }

        FileWriter fw = new FileWriter("C:\\ProjectFiles\\SortedAccounts.txt");
        PrintWriter pw = new PrintWriter(fw);
        String s = b.toString();
        Scanner sc = new Scanner(s);
        String[] tempA = new String[1000];
        int i = -1;
        while(sc.hasNext())
        {
            i++;
            tempA[i] = sc.nextLine();
        }
        String[] tempB = new String[i+1];
        for(int f = 0; f<tempB.length; f++)
        {
           tempB[f] = tempA[f];
           pw.println(tempB[f]);
        }
        pw.close();
        fw.close();
    }
}
