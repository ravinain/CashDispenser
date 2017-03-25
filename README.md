Cash Dispenser System
-

Version 1.0.0

Description
-
This system allows user to withdraw amount in available denominations. Also, add/update denominations in the system.

Prerequisite
-
Apache Maven 3.3.1
Java Version 1.8
Eclipse IDE(optional)

What is the starting point?
-
main.ClientMain is an entry point.

How to run from command line?
-
1. open command terminal
2. execute 'mvn clean install' command (This will generate jar file in target folder)
3. execute 'java -cp "target/CashDispenser.jar; ." main.ClientMain' command
4. By default initial denomination values set from src/main/resources/Deno_Map.properties file; however, if you want to initialize with your own properties file then pass it in the first argument of the command.
For example: 'java -cp "target/CashDispenser.jar; ." main.ClientMain C:\Users\user1\Desktop\sample.properties'
5. After executing the above command it will show user list of options as mentioned below:
    Options:
    1. Withdraw
    2. Add/Update Denomination
    3. Available Cash
    4. Exit

Reports:
-
Unit Test report will be available in target/surefire-reports.
Integration Test report will be available in target/failsafe-reports.
Checkstyle reports will be available in target/checkstyle-*

Implementation Covered
-
    1. Supports all legal Australian denomination above or equal $1.
    2. Threshold notification.
    3. Allows adding/updating denomination.
    4. Dispensing reduces the amount from available cash.
    5. Initialize denomination and allows to add/update later on.
    6. Dispense legal denomination.
    7. Display a message when suitable combination of notes cannot dispense.
    8. Don't reduce available cash when any failure occurs.