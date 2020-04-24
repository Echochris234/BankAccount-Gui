# BankAccount-Gui




<h1 align="center">
  <br>
 Bank Account Gui
  <br>
</h1>

<h4 align="center">A simple application that implements Java swing to impliment the functions of a bank tellers machine     
  
  <br><br>
  
  <p align="center">'
 
</p>

<br><br>

Key Features
<br>

* The application inital start up reads from a text file labled "Initial_Data.txt
 <br><br>
    This file contains information about the account holder.
     <br><br>
        * first and last name
         <br><br>
        * 9 digit SSN
         <br><br>
        * 6 digit account number
         <br><br>
        * current balance
         <br><br>
        * account type
         <br><br>
        * date opened/last accessed

  <br> <br><br>
    * Depending on the function selected the program request different parameters from the User:
     <br><br>
      * Deposit(D)- deposits a balance within the account [requires account number, balance to be deposited]
       <br><br>
      * Withdrawal(W) withdrawals a specified balance from an account, if the balance is below $2,500 a $1.50 is charged         
        [requires account number, balance to be Withdraw]
         <br><br>
      * Balance(B) - displays the current balance within the specified account [requires account number]
       <br><br>
      * Account_Info(I)displays all the accounts and information pertaining to a specified social[requres a valid ssn]
       <br><br>
      * Trans_History(H)- displays all the account information and transaction history pertaining to a specified social[requires a vald ssn]
       <br><br>
      * Close_Account(S)- closes a specified account, transactions cannot be performed[requires account number]
       <br><br>
      * Re_openAccount(R)- opens an account that has been closed, transactions can be perfomed again[requires account number]
       <br><br>
      * Clear_Check(C)- Deposits a check given the parameters charged a $2.50 if check bounces because of date experation  checks if the check is being deposited too early or late (6months)[account being deposited to, check date, check amount]
      <br><br>
      * New Account(N) - creates a new account given an account number that isnt in the system/social/account type
      <br><br>
      * Delete Account(D) - deletes an account if the account is in the system has a balance of 0 and is closed
      <br><br>
      * Quit(Q)- closes the application and prints a document with all the accounts/ standing balance and transactions performed
  <br>


<br><br>


How to use
<br>


* Open program using command line for best results*
  <br>
* Main Java file found in directory path Bank/src/atmMain.java
  <br>
* javac atmMain.java->java atmMain
  <br>
* a pop up window opens up displaying the options ( on the gui only deposit and withdrawal work atm more to come)
  <br>

    
 <br><br>
 
 <h2> Project Details </h2>
 Below is a demonstration of the project:

  
  
  
 <p align= "center">
  <img src = "src/Interface.png">  
<img src = "src/DepoAccount.png">
    <img src = "src/DepoReciept.png">  
<img src = "src/DepoAccount.png">
    <img src = "src/Withdrawal.png">  
<img src = "src/WithReciept.png">
</p>

