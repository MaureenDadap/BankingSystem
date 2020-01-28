package com.programming.bankingsystem;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Deposit {
	public static void deposit(List<String> holdersList, List<Integer> holdersBalance) {
		Scanner input = new Scanner(System.in);
		int ch = 0;
		int amount = 0;
		String transactionString;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");

		if (mainClass.userListChecker(holdersList)) {
			System.out.println("\n\t\t  ________________________________________________");
			System.out.println("\t\t||                                                ||");
			System.out.println("\t\t|| > There are no registered account holders yet. ||");
			System.out.println("\t\t||________________________________________________||");
		} else {
			System.out.println("\n\tACCOUNT HOLDERS");
			for (int x = 0; x < holdersList.size(); x++) {
				System.out.println("\t\t" + (x + 1) + ". " + holdersList.get(x));
			}

			while (true) {
				System.out.print("\n\tWho would like to deposit? (type in the number): ");
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
			System.out.println("\t\t|| > ACCOUNT HOLDER: " + holdersList.get(ch - 1));
			System.out.println("\t\t||________________________________________________||");

			do {
				System.out.print(
						"\n\t  --> Amount to be deposited (NOTE: Golden Coin Bank does NOT accept decimal values): ");
				try {
					amount = Integer.parseInt(input.next());
					if (amount == 0) {
						System.out.println("\t\t  ________________________________________________");
						System.out.println("\t\t||                                                ||");
						System.out.println("\t\t|| > INVALID AMOUNT. Php 0 cannot be accepted as  ||");
						System.out.println("\t\t||   deposit. Enter a valid amount.               ||");
						System.out.println("\t\t||________________________________________________||");
					} else if (amount < 0) {
						System.out.println("\t\t  ________________________________________________");
						System.out.println("\t\t||                                                ||");
						System.out.println("\t\t|| > INVALID AMOUNT. A negative amount cannot be  ||");
						System.out.println("\t\t||   accepted as deposit. Enter a valid amount.   ||");
						System.out.println("\t\t||________________________________________________||");
					}

				} catch (NumberFormatException n) {
					System.out.println("\t\t  ________________________________________________");
					System.out.println("\t\t||                                                ||");
					System.out.println("\t\t|| > INVALID INPUT                                ||");
					System.out.println("\t\t||________________________________________________||");
				}

			} while (amount == 0 || amount < 0);

			holdersBalance.set((ch - 1), (holdersBalance.get(ch - 1) + amount));

			transactionString = formatter.format(new Date()) + " - Deposited Php " + amount;
			mainClass.putTransactions(ch, transactionString);

			// RECEIPT
			System.out.println("\t\t  ________________________________________________");
			System.out.println("\t\t||                                                ||");
			System.out.println("\t\t|| > SUCCESSFULLY DEPOSITED!                      ||");
			System.out.println("\t\t||________________________________________________||");

			System.out.println("\n\t\t# # # DEPOSIT RECEIPT # # # #");
			System.out.println("\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\t\tInitial Balance: Php " + (holdersBalance.get(ch - 1) - amount));
			System.out.println("\t\tDeposited Amount: Php " + amount);
			System.out.println("\t\t----------------------");
			System.out.println("\t\tCurrent Balance: Php " + holdersBalance.get(ch - 1));
			System.out.println("\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("\t\t# # # # # # # # # # # # # # #");

		}
	}
}
