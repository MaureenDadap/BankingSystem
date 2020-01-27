import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BalanceTransfer {
    public static void balanceTransfer(List<String> holdersList, List<Integer> holdersBalance) {
        Scanner input = new Scanner(System.in);
        int ch, ch2 = 0;
        int amount;
        String transactionString;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");

        if (mainClass.userListChecker(holdersList)) {
            System.out.println("\n\t\t  ________________________________________________");
            System.out.println("\t\t||                                                ||");
            System.out.println("\t\t|| > There are no registered account holders yet. ||");
            System.out.println("\t\t||________________________________________________||");
        } else {
            if (mainClass.regCount == 1) {
                System.out.println("\n\t\t  ________________________________________________");
                System.out.println("\t\t||                                                ||");
                System.out.println("\t\t|| > There is only one registered account holder. ||");
                System.out.println("\t\t||   Balance transfer cannot be done with only    ||");
                System.out.println("\t\t||   one account in the system.                   ||");
                System.out.println("\t\t||________________________________________________||");
            } else if (mainClass.regCount > 1) {
                System.out.println("\n\tACCOUNT HOLDERS");
                for (int x = 0; x < holdersList.size(); x++) {
                    System.out.println("\t\t" + (x + 1) + ". " + holdersList.get(x));
                }

                while (true) {
                    System.out.print("\n\tWho would like to transfer balance? (type in the number): ");
                    try {
                        ch = Integer.parseInt(input.next());
                        if (!(ch > holdersList.size()) && !(ch <= 0))
                            break;
                        else if (ch > holdersList.size() || ch <= 0) {
                            System.out.println("\t\t  ________________________________________________");
                            System.out.println("\t\t||                                                ||");
                            System.out.println("\t\t|| > INVALID INPUT. The account you are trying    ||");
                            System.out.println("\t\t||   to enter does not exist.                     ||");
                            System.out.println("\t\t||________________________________________________||");
                        }

                    } catch (NumberFormatException n) {
                        System.out.println("\t\t  ________________________________________________");
                        System.out.println("\t\t||                                                ||");
                        System.out.println("\t\t|| > INVALID INPUT                                ||");
                        System.out.println("\t\t||________________________________________________||");
                    }
                }

                System.out.println("\t\t  ________________________________________________");
                System.out.println("\t\t||                                                ||");
                System.out.println("\t\t|| > SENDER: " + holdersList.get(ch - 1));
                System.out.println("\t\t||________________________________________________||");

                System.out.println("\n\tACCOUNT HOLDERS");
                for (int x = 0; x < holdersList.size(); x++) {
                    System.out.println("\t\t" + (x + 1) + ". " + holdersList.get(x));
                }

                while (true) {
                    System.out.print("\n\t  --> Balance transfer recipient account: ");
                    try {
                        ch2 = Integer.parseInt(input.next());
                        if (ch2 == ch) {
                            System.out.println("\t\t  ________________________________________________");
                            System.out.println("\t\t||                                                ||");
                            System.out.println("\t\t|| > INVALID RECIPIENT. The recipient must be     ||");
                            System.out.println("\t\t||   different from the sender account.           ||");
                            System.out.println("\t\t||________________________________________________||");
                        } else if (!(ch2 > holdersList.size()) && !(ch2 <= 0) && ch2 != ch)
                            break;
                        else if (ch2 > holdersList.size() || ch2 <= 0) {
                            System.out.println("\t\t  ________________________________________________");
                            System.out.println("\t\t||                                                ||");
                            System.out.println("\t\t|| > INVALID INPUT. The account you are trying    ||");
                            System.out.println("\t\t||   to enter does not exist.                     ||");
                            System.out.println("\t\t||________________________________________________||");
                        }

                    } catch (NumberFormatException n) {
                        System.out.println("\t\t  ________________________________________________");
                        System.out.println("\t\t||                                                ||");
                        System.out.println("\t\t|| > INVALID INPUT                                ||");
                        System.out.println("\t\t||________________________________________________||");
                    }
                }

                System.out.println("\t\t  ________________________________________________");
                System.out.println("\t\t||                                                ||");
                System.out.println("\t\t|| > RECIPIENT: " + holdersList.get(ch2 - 1));
                System.out.println("\t\t||________________________________________________||");

                while (true) {
                    System.out.print(
                            "\n\t  --> Enter the amount to be transfered (**NOTE: Golden Coin Bank does NOT accept decimal values): ");
                    try {
                        amount = Integer.parseInt(input.next());
                        if (amount == 0) {
                            System.out.println("\t\t  ________________________________________________");
                            System.out.println("\t\t||                                                ||");
                            System.out.println("\t\t|| > INVALID AMOUNT. Php 0 cannot be accepted.    ||");
                            System.out.println("\t\t||   Enter a valid amount.                        ||");
                            System.out.println("\t\t||________________________________________________||");
                        } else if (amount < 0) {
                            System.out.println("\t\t  ________________________________________________");
                            System.out.println("\t\t||                                                ||");
                            System.out.println("\t\t|| > INVALID AMOUNT. A negative amount cannot be  ||");
                            System.out.println("\t\t||   accepted. Enter a valid amount.              ||");
                            System.out.println("\t\t||________________________________________________||");
                        } else
                            break;
                    } catch (NumberFormatException n) {
                        System.out.println("\t\t  ________________________________________________");
                        System.out.println("\t\t||                                                ||");
                        System.out.println("\t\t|| > INVALID INPUT                                ||");
                        System.out.println("\t\t||________________________________________________||");
                    }
                }

                if ((holdersBalance.get(ch - 1) - amount) < 0) {
                    System.out.println("\t\t  ________________________________________________");
                    System.out.println("\t\t||                                                ||");
                    System.out.println("\t\t|| > INVALID AMOUNT. The sender does not have     ||");
                    System.out.println("\t\t||   enough funds to make the transaction.        ||");
                    System.out.println("\t\t||________________________________________________||");
                } else {

                    holdersBalance.set((ch - 1), (holdersBalance.get(ch - 1) - amount)); // money taken from sender
                                                                                         // account
                    holdersBalance.set((ch2 - 1), (holdersBalance.get(ch2 - 1) + amount)); // money added to
                                                                                           // recepient
                                                                                           // account

                    // Recording of transaction
                    transactionString = formatter.format(new Date()) + " - Transfered Php " + amount + " to "
                            + holdersList.get(ch2 - 1);
                    mainClass.putTransactions(ch, transactionString);

                    // RECEIPT
                    System.out.println("\t\t  ________________________________________________");
                    System.out.println("\t\t||                                                ||");
                    System.out.println("\t\t|| > SUCCESSFULLY TRANSFERED BALANCE!             ||");
                    System.out.println("\t\t||________________________________________________||");


                    System.out.println("\n\t\t# # # BALANCE TRANSFER RECEIPT # # # ");
                    System.out.println("\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("\t\tSender: " + holdersList.get(ch - 1) + ", sent Php " + amount + " to ");
                    System.out.println("\t\tRecipient: " + holdersList.get(ch2 - 1));

                    // balance of sender, before and after
                    System.out.println("\n\t\t" + holdersList.get(ch - 1) + "'s initial balance: Php "
                            + (holdersBalance.get(ch - 1) + amount));
                    System.out.println("\t\t" + holdersList.get(ch - 1) + "'s current balance: Php "
                            + (holdersBalance.get(ch - 1)));
                    // balance of recipient, before and after
                    System.out.println("\n\t\t" + holdersList.get(ch - 2) + "'s initial balance: Php "
                            + (holdersBalance.get(ch2 - 1) - amount));
                    System.out.println("\t\t" + holdersList.get(ch - 2) + "'s current balance: Php "
                            + (holdersBalance.get(ch2 - 1)));

                    System.out.println("\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.println("\t\t# # # # # # # # # # # # # # # # # #");
                }

            }
        }
    }
}
