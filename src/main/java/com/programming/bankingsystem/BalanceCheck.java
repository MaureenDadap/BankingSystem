package com.programming.bankingsystem;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BalanceCheck {
	public static void balanceCheck() {
		MakeJSON makeJSON = new MakeJSON();
		Scanner input = new Scanner(System.in);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
		int ch;

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
				System.out.print("\n\tCheck balance of (type in the number): ");
				try {
					ch = Integer.parseInt(input.next());
					if (!(ch > makeJSON.countUsers()) && !(ch <= 0))
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
			System.out.println("\t\t|| > ACCOUNT HOLDER NAME: " + makeJSON.getUsers().get(ch - 1));
			System.out.println("\t\t||________________________________________________||");

			System.out.println("\n\tACCOUNT NUMBER\t\tACCOUNT HOLDER NAME\tBALANCE\t\tAGE\t\tADDRESS\n");
			System.out.println("\t      " + ch + " ----------------- " + makeJSON.getUsers().get(ch - 1)
					+ " ---------- Php " + makeJSON.getBalance(ch) + " -------------- " + makeJSON.getAge(ch)
					+ " ---------- " + makeJSON.getAddress(ch));

			// lists their transaction history
			System.out.println("\n\t\t  ________________________________________________");
			System.out.println("\t\t||                                                ||");
			System.out.println("\t\t|| > TRANSACTION HISTORY                          ||");
			System.out.println("\t\t||________________________________________________||");
			System.out.println("\n\t\t  " + makeJSON.getTransactions(ch));
		}
	}
}
