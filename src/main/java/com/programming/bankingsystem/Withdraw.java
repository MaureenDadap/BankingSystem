package com.programming.bankingsystem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Withdraw {
	public static void withdraw() {
		MakeJSON makeJSON = new MakeJSON();
		Scanner input = new Scanner(System.in);
		int ch;
		int amount = 0;
		String transactionString;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");

		if (makeJSON.countUsers() == 0) {
			System.out.println("\n\t\t  ________________________________________________");
			System.out.println("\t\t||                                                ||");
			System.out.println("\t\t|| > There are no registered account holders yet. ||");
			System.out.println("\t\t||________________________________________________||");
		} else {
			System.out.println("\n\tACCOUNT HOLDERS");
			for (int x = 0; x < makeJSON.countUsers(); x++) {
				System.out.println("\t\t" + (x + 1) + ". " + makeJSON.getUsers().get(x));
			}

			while (true) {
				System.out.print("\n\tWho would like to withdraw? (type in the number): ");
				try {
					ch = Integer.parseInt(input.next());
					if (!(ch > makeJSON.countUsers() && !(ch <= 0)))
						break;

					else if (ch > makeJSON.countUsers() || ch <= 0) {
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
			System.out.println("\t\t|| > ACCOUNT HOLDER: " + makeJSON.getUsers().get(ch - 1));
			System.out.println("\t\t||________________________________________________||");

			do {
				System.out.print(
						"\n\t  --> Amount to be withdrawn (NOTE: Golden Coin Bank does NOT accept decimal values): ");
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
					} else if ((makeJSON.getBalance(ch) - amount) < 0) {
						System.out.println("\t\t  ________________________________________________");
						System.out.println("\t\t||                                                ||");
						System.out.println("\t\t|| > INVALID AMOUNT. This account does not have   ||");
						System.out.println("\t\t||   sufficient funds to make that transaction.   ||");
						System.out.println("\t\t||________________________________________________||");
					} else {
						System.out.println("\t\t  ________________________________________________");
						System.out.println("\t\t||                                                ||");
						System.out.println("\t\t|| > SUCCESSFULLY WITHDRAWN!                      ||");
						System.out.println("\t\t||________________________________________________||");

						makeJSON.changeBalance((ch), makeJSON.getBalance(ch) - amount);
						transactionString = formatter.format(new Date()) + " - Withdrew Php " + amount;
						makeJSON.writeTransaction(ch, transactionString);

						// RECEIPT
						System.out.println("\n\t\t# # # WITHDRAWAL RECEIPT # # # ");
						System.out.println("\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("\t\tInitial Balance: Php " + (makeJSON.getBalance(ch) + amount));
						System.out.println("\t\tWithdrawn Amount: Php " + amount);
						System.out.println("\t\t----------------------");
						System.out.println("\t\tCurrent Balance: Php " + makeJSON.getBalance(ch));
						System.out.println("\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
						System.out.println("\t\t# # # # # # # # # # # # # # #");
					}

				} catch (NumberFormatException n) {
					System.out.println("\t\t  ________________________________________________");
					System.out.println("\t\t||                                                ||");
					System.out.println("\t\t|| > INVALID INPUT                                ||");
					System.out.println("\t\t||________________________________________________||");
				}

			} while (amount == 0 || amount < 0);

		}
	}
}
