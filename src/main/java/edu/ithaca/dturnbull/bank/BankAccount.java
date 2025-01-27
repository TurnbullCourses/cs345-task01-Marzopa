package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (startingBalance < 0){
            throw new IllegalArgumentException("Cannot create account with negative balance");
        }

        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @throws InsufficientFundsException if amount is negative or greater than balance
     * @throws IllegalArgumentException if amount is negative
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        //Diana Lazo
        //used conditions below
        // https://help.xmatters.com/ondemand/trial/valid_email_format.htm 
        
        // checks if and @ symbol is in the email
        if (email.indexOf('@') == -1){
            return false;
        }

        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);
            //checks if valid characters exist in the email
            if (false == Character.isLetterOrDigit(c) && c != '.' && c != '-' && c != '@' && c != '_') {
                return false; 
            }
            //checks to make sure digit is after certain symbols . - and _
            if (c == '-' || c == '.' || c == '_') {
                if (i == email.length() - 1 || false == Character.isLetterOrDigit(email.charAt(i + 1))) {
                    return false;
                }
            }
        }
        //checks lenght conditions of prefix and domain 
        String[] split = email.split("@");
        if (split.length != 2 ||  split[0].length() < 1 || split[1].length() < 3 || split[1].indexOf('.') == -1) {
            return false;
        }

        //must start email with Letter or Digit
        if (false == Character.isLetterOrDigit(split[0].charAt(0))){
            return false;
        }
                   

        //domain conditions
        int lastperiod = split[1].lastIndexOf('.');
        if (lastperiod == -1 || lastperiod == split[1].length() - 1 || split[1].length() - lastperiod - 1 < 2) {
            return false;
        }
    
        return true;

    }
    
}