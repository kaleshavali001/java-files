package javaProject;

import java.util.Scanner;

class BankAccount {
    
    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 10000f;
    int transaction = 0;
    String transactionHistory = "";
    
    public void register(Scanner sc) {
        System.out.println("\nEnter your Name: ");
        this.name = sc.nextLine(); 
        
        System.out.println("\nEnter your Username: ");
        this.userName = sc.nextLine(); 
        
        System.out.println("\nEnter your Password: ");
        this.password = sc.nextLine(); 
        
        System.out.println("\nEnter your Account Number: ");
        this.accountNo = sc.nextLine(); 
        
        System.out.println("\nRegistration Successful. Please Log in to your Bank account ");
    }

    public boolean login(Scanner sc) {
        boolean ialogin = false;
        while (!ialogin) {
            System.out.println("\nEnter your username: ");
            String Username = sc.nextLine();
            if (Username.equals(userName)) {
                while (!ialogin) {
                    System.out.println("\nEnter your password: ");
                    String Password = sc.nextLine();
                    if (Password.equals(password)) {
                        System.out.println("\nLogin Successfully");
                        ialogin = true;
                    } else {
                        System.out.println("\nIncorrect Password");
                    }
                }
            } else {
                System.out.println("\nUsername not found");
            }
        }
        return ialogin;
    }

    public void withdraw(Scanner sc) {
        System.out.println("\nEnter Amount to Withdraw: ");
        float amount = sc.nextFloat();
        sc.nextLine(); 
        try {
            if (amount <= balance) {
                transaction++;
                balance -= amount;
                System.out.println("\nWithdrawal Successful.");
                String str = amount + "Rs Withdrawn\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("Insufficient balance.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deposit(Scanner sc) {
        System.out.println("\nEnter Amount to Deposit: ");
        float amount = sc.nextFloat();
        sc.nextLine(); 
        try {
            if (amount <= 10000f) {
                transaction++;
                balance += amount;
                System.out.println("\nDeposit Successful.");
                String str = amount + "Rs deposited\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("Sorry. The limit is 10000.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void transfer(Scanner sc) {
        System.out.println("\nEnter Recipient's Name: ");
        String recipient = sc.nextLine();
        
        System.out.println("\nEnter Amount to transfer: ");
        float amount = sc.nextFloat();
        sc.nextLine(); 
        try {
            if (balance >= amount) {
                if (amount <= 50000f) {
                    transaction++;
                    balance -= amount;
                    System.out.println("\nSuccessfully transferred to " + recipient);
                    String str = amount + "Rs transferred to " + recipient + "\n";
                    transactionHistory = transactionHistory.concat(str);
                } else {
                    System.out.println("Sorry. The limit is 50000.");
                }
            } else {
                System.out.println("\nInsufficient balance.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkBalance() {
        System.out.println("\n" + balance + "Rs");
    }

    public void transactionHistory() {
        if (transaction == 0) {
            System.out.println("No Transactions happened");
        } else {
            System.out.print("\n" + transactionHistory);
        }
    }
}

public class ATMinterface {

    public static int takenIntegerInput(int limit, Scanner sc) {
        int input = 0;
        boolean flag = false;
        
        while (!flag) {
            try {
                input = sc.nextInt();
                sc.nextLine(); 
                flag = true;
                
                if (input > limit || input < 1) {
                    System.out.println("Choose the number between 1 to " + limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter only integer value.");
                sc.nextLine(); 
                flag = false;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  
        System.out.println("\n*********************WELCOME TO OPTIYX ATM INTERFACE********************");
        
        System.out.println("\n1.Register \n2.Exit");
        System.out.println("Choose one option");
        int choose = takenIntegerInput(2, sc);
        
        if (choose == 1) {
            BankAccount b = new BankAccount();
            b.register(sc);  
            while (true) {
                System.out.println("\n1.Login \n2.Exit");
                System.out.println("Choose one option");
                int ch = takenIntegerInput(2, sc);
                if (ch == 1) {
                    if (b.login(sc)) {
                        System.out.println("\n*********************WELCOME BACK " + b.name + " ********************");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1.Withdraw \n2.Deposit \n3.Transfer \n4.Check Balance \n5.Transaction History \n6.Exit");
                            System.out.println("Choose one option");
                            int c = takenIntegerInput(6, sc);
                            switch (c) {
                                case 1:
                                    b.withdraw(sc);
                                    break;
                                case 2:
                                    b.deposit(sc);
                                    break;
                                case 3:
                                    b.transfer(sc);
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break;
                                case 5:
                                    b.transactionHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }

        sc.close();  
    }
}
