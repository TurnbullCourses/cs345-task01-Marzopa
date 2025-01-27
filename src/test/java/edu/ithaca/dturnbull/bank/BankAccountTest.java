package edu.ithaca.dturnbull.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertTrue(BankAccount.isEmailValid( "abc-d@mail.com"));
        assertTrue(BankAccount.isEmailValid( "abc-d@m.com"));
        assertTrue(BankAccount.isEmailValid( "abc.def@mail-archive.com"));
        assertTrue(BankAccount.isEmailValid( "abc.def@mail-archive.co"));

        assertFalse( BankAccount.isEmailValid(""));         // empty string
        assertFalse( BankAccount.isEmailValid("abc..d@mail.com"));
        assertFalse( BankAccount.isEmailValid("abc-@mail.com"));
        assertFalse( BankAccount.isEmailValid(".abc@mail.com"));
        assertFalse( BankAccount.isEmailValid("abc!d@mail.com"));
        
        assertFalse( BankAccount.isEmailValid("abcmail.com"));
        assertFalse( BankAccount.isEmailValid("abcdef@mail#arc.com"));
        assertFalse( BankAccount.isEmailValid("abcdef@mail..com"));
        assertFalse( BankAccount.isEmailValid("abcdef@mail"));
        assertFalse( BankAccount.isEmailValid("abcdef@mail.c"));

        
    }
    @Test
    void isEmailValidTestnotes(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        // Valid email address format
        assertTrue(BankAccount.isEmailValid( "abc-d@mail.com"));
        // Valid email address with letters and dash. Border case? Valid lenght of prefix and domain, with letter or digit after the -
        assertTrue(BankAccount.isEmailValid( "abc-d@m.com"));
        // Valid email with a 1 character domain, checking format. Border case? The domain portion consist of a 1 letter domain.
        assertTrue(BankAccount.isEmailValid( "abc.def@mail-archive.com"));
        //Valid email with period and dash in both prefix and domain. Border case? More complex domain, but still valid.
        assertTrue(BankAccount.isEmailValid( "abc.def@mail-archive.co"));
        //Valid email address, format, has - and letters. Two letters in .co  .Border case? Valid .co , lenght of 2 which is lowest possible
        
        assertFalse( BankAccount.isEmailValid(""));
         // Invalid email address format, Border? Completely empty string, clearly invalid
        assertFalse( BankAccount.isEmailValid("abc..d@mail.com"));
        // Invalid email format with double periods in the prefix. Extra Character. Border? Having period after a period is invalid.
        assertFalse( BankAccount.isEmailValid("abc-@mail.com"));
        // Invalid email format with a dash just before @. Border? Need a letter or digit after. - or _
        assertFalse( BankAccount.isEmailValid(".abc@mail.com"));
        // Invalid email format with a period at the beginning of the prefix. Border? Period cannot be the first character of the prefix.
        assertFalse( BankAccount.isEmailValid("abc!d@mail.com"));
        // Invalid email format with invalid character. Border? Invalid character ! in the email.
        
        assertFalse( BankAccount.isEmailValid("abcmail.com"));
        // Invalid email format, missing character. Border case: Missing @ symbol 
        assertFalse( BankAccount.isEmailValid("abcdef@mail#arc.com"));
        // Invalid email format with invalid character. Border? # is not allowed in domain 
        assertFalse( BankAccount.isEmailValid("abcdef@mail..com"));
        // Invalid email format.  Border? Two periods after the other in the domain name are not allowed.
        assertFalse( BankAccount.isEmailValid("abcdef@mail"));
        // Invalid email format with no (TLD) Border? Missing a TLD,no perid and two letters
        assertFalse( BankAccount.isEmailValid("abcdef@mail.c"));
        // Invalid email with invalid TLD format. Border? .com in TLD  must be at least 2 characters long 
    }

    

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}