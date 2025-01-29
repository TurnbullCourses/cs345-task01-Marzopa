package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (!isAmountValid(startingBalance)){
            throw new IllegalArgumentException("Invalid amount");
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
        if (amount > balance) {
            throw new InsufficientFundsException("Not enough money");
        }

        else if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount is invalid");
        }
        else balance -= amount;

    }
    public void withdrawDiana (double amount) throws InsufficientFundsException{
        
        if (false == isAmountValid(amount)){
            throw new IllegalArgumentException("Not valid input");
        }
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
    
    /**
     * @param amount amount to test for
     * @return true if amount is valid for BankAccoount (at most two decimal places), false otherwise
     */
    public static boolean isAmountValid(double amount){
        if (amount < 0) {
            return false; // Amount should be positive or zero
        }
        
        String amountString = Double.toString(amount);
        int decimalIndex = amountString.indexOf('.');
        
        if (decimalIndex == -1){ // No point/integer
            return true;
        } 
        
        else {
            String decimalPart = amountString.substring(decimalIndex + 1);
            return decimalPart.length() <= 2; // At most 2 decimal places
        }
    }

    /**
     * @post increases the balance by amount if amount is non-negative
     * @throws IllegalArgumentException if amount is invalid
     */
    public void deposit(double amount) {
        if (!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount is invalid");
        }
        balance += amount;
    }

    /**
     * @post reduces the balance by amount if amount is valid and smaller or equal to balance on this account, increases it to other account
     * @throws InsufficientFundsException if amount is greater than balance
     * @throws IllegalArgumentException if amount is invalid or zero
     */
    public void transfer(BankAccount other, double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Not enough money");
        }
        else if (!isAmountValid(amount) || amount == 0){
            throw new IllegalArgumentException("Amount is invalid");
        }
        else {
            withdraw(amount); // Based on code review, instead of subtrating from balance
            other.deposit(amount);
        }
    }

}