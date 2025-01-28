# Banking System Console Application

A Java-based console application simulating a banking system. Customers can manage accounts and transactions, while admins view system-wide data. Data persistence is implemented using file storage.

## Features

### Customer
- **Register**: Create an account.
- **Login**: Authenticate credentials.
- **Create Account**: Open savings/checking accounts.
- **Deposit/Withdraw**: Add or withdraw funds.
- **Transfer**: Move funds between accounts.
- **View Transactions**: Check account history.

### Admin
- **Login**: Admin authentication.
- **View All Customers**: List all customers.
- **View All Accounts**: Display all accounts.
- **View All Transactions**: Access all transaction records.

## Technologies
- **Language**: Java
- **Storage**: Java Serialization
- **Input**: `Scanner`
- **Design**: MVC-inspired, Service Layer
- **Data Structures**: Lists, Enums, Streams

## Technologies
- **Language**: Java
- **Storage**: Java Serialization
- **Input**: `Scanner`
- **Design**: MVC-inspired, Service Layer
- **Data Structures**: Lists, Enums, Streams

  
## Running the Application

### Prerequisites
- JDK 8 or higher.
- Terminal/IDE.

### Steps
1. **Clone Repository**:
   ```bash
   git clone https://github.com/your-username/bank-system.git
   cd bank-system

   Compile:
   javac src/main/java/com/bank/BankApp.java

   Run:
   java -cp src/main/java com.bank.BankApp
