package zCoin;
import java.util.List;
import java.util.Scanner;
public class ZCoinManagementSystem {
    static Banking banking = new Banking();

    public static void main(String[] args) {
        Banking banking = new Banking();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1.User login");
            System.out.println("2.Agent login");
            System.out.println("3.exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    boolean end = true;
                    while (end) {
                        System.out.println("1.New User");
                        System.out.println("2.Existing user");
                        System.out.println("3.exit user login");
                        int ch = scanner.nextInt();
                        switch (ch) {
                            case 1: {
                                //System.out.println("create account");
                                System.out.println("enter name");
                                scanner.nextLine();
                                String name = scanner.nextLine();
                                System.out.println("enter emil id");
                                String email = scanner.nextLine();
                               /* while (!banking.validateEmail(email)) ;
                                {
                                    System.out.println("please enter valid email id");
                                    email = scanner.nextLine();
                                }*/
                                System.out.println("enter mobile no");
                                long mobileNo = scanner.nextLong();
                                System.out.println("enter password");
                                String password = scanner.next();
                                System.out.println("enter initial real currency");
                                double rc = scanner.nextDouble();
                                User user = banking.getUserObject(name, email, password, mobileNo, rc);
                                banking.createAccount(user);
                            }
                            break;
                            case 2: {
                                System.out.println("enter email");
                                String email = scanner.next();
                                System.out.println("enter password");
                                String password = scanner.next();
                                int k = 1;
                                while (k > 0) {
                                    if (!banking.isCheckExistingUser(email, password)) {
                                        System.out.println("please enter valid email && password");
                                        scanner.nextLine();
                                        email = scanner.nextLine();
                                        password = scanner.nextLine();
                                    } else {
                                        k = 0;
                                    }
                                }
                                if (banking.isCheckApprovalUser(email, password)) {

                                    System.out.println("Login success fully");

                                } else {
                                    System.out.println("you are unable to login....your are not in approval list");
                                }
                            }
                            break;
                            case 3:
                                end = false;
                                break;
                        }
                    }
                }
                break;
                case 2: {
                    boolean end = true;
                    while (end) {
                        System.out.println("1.Assign approval status");
                        System.out.println("2.Transaction details");
                        System.out.println("3.exit admin login");
                        int c = scanner.nextInt();
                        switch (c) {
                            case 1: {
                                List<User> list = banking.getCreationAccountList();
                                for (User user : list) {
                                    System.out.println(user);
                                    System.out.println("1.accepted");
                                    System.out.println("2.rejected");
                                    int ch = scanner.nextInt();
                                    switch (ch) {
                                        case 1:
                                            banking.generateZid(user);
                                            System.out.println("user approved successfully");
                                            break;
                                        case 2:
                                            System.out.println("rejected");
                                            break;
                                    }

                                }
                            }
                            break;
                            case 2:
                                break;
                            case 3:
                                end = false;
                                break;
                        }
                    }
                }
                break;
                case 3:
                    System.exit(0);
            }
        }

    }

    private static void UserManagementPanel(User user) {
        Scanner scanner = new Scanner(System.in);
        boolean end = true;
        while (end) {
            System.out.println("1.Account Details");
            System.out.println("2.Transaction History");
            System.out.println("3.Transaction");
            System.out.println("4.changePassword");
            System.out.println("5.exit");

            int ch = scanner.nextInt();
            switch (ch) {
                case 1:
                    banking.getAccountDetails(user);
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("1.Rc Transaction");
                    System.out.println("2.Zc Transaction");
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                        {
                            boolean endRcTransaction = true;
                            while (endRcTransaction) {
                                System.out.println("1.withdraw");
                                System.out.println("2.deposit");
                                System.out.println("3.Exit rc Transaction");
                                int a = scanner.nextInt();
                                switch (a) {
                                    case 1: {
                                        System.out.println("enter withdraw amount");
                                        double amount = scanner.nextDouble();
                                        int k = 1;
                                        while (k == 1) {
                                            if (!banking.isValidateWithDrawAmount(user.getZ_Id(), amount)) {
                                                System.out.println("insufficient balance...." + "saving amount:" + user.getRc());
                                                System.out.println("please enter valid amount");
                                                amount = scanner.nextDouble();
                                            } else {
                                                k = 0;
                                            }
                                        }
                                        System.out.println("withdraw successfully");
                                        banking.updateWithDrawAmount(user, amount);
                                        RCTransaction transaction = new RCTransaction();
                                        transaction.setTransactionType("withdraw");
                                        transaction.setAmount(amount);
                                        banking.addRcTransaction(user.getZ_Id(), transaction);
                                    }
                                    break;
                                    case 2: {
                                        System.out.println("enter deposit amount");
                                        double amount = scanner.nextDouble();
                                        banking.updateDepositAmount(user, amount);
                                        System.out.println("deposit successfully");
                                        RCTransaction transaction = new RCTransaction();
                                        transaction.setTransactionType("deposit");
                                        transaction.setAmount(amount);
                                        banking.addRcTransaction(user.getZ_Id(), transaction);
                                    }
                                    break;
                                    case 3:
                                        endRcTransaction = false;
                                        break;
                                }
                            }

                        }
                        break;
                        case 2:
                        case 4:

                    }

            }
        }
    }
}
