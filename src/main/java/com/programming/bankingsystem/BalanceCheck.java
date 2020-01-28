package com.programming.bankingsystem;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BalanceCheck {
	public static void balanceCheck(List<String> holdersList, List<Integer> holdersBalance, List<Date> dateList,
			List<String> ageList, List<String> addressList) {
		Scanner input = new Scanner(System.in);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
		int ch;

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
				System.out.print("\n\tCheck balance of (type in the number): ");
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
			System.out.println("\t\t|| > ACCOUNT HOLDER NAME: " + holdersList.get(ch - 1));
			System.out.println("\t\t||________________________________________________||");

			System.out.println("\n\tACCOUNT NUMBER\t\tACCOUNT HOLDER NAME\tBALANCE\t\tDATE JOINED\t\tAGE\t\tADDRESS\n");
			System.out.println("\t      " + ch + " -------------- " + holdersList.get(ch - 1) + " ---------- Php "
					+ holdersBalance.get(ch - 1) + " ---------- " + formatter.format(dateList.get(ch - 1))
					+ " ---------- " + ageList.get(ch - 1) + " ---------- " + addressList.get(ch - 1));

			// lists their transaction history
			System.out.println("\n\t\t  ________________________________________________");
			System.out.println("\t\t||                                                ||");
			System.out.println("\t\t|| > TRANSACTION HISTORY                          ||");
			System.out.println("\t\t||________________________________________________||");
			System.out.println("\n\t\t  " + mainClass.transactionsMap.get(ch));
		}
	}
}
