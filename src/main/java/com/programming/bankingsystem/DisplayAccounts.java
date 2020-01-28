package com.programming.bankingsystem;
import java.text.SimpleDateFormat;
import java.util.*;

public class DisplayAccounts {
	public static void displayAcc(List<String> holdersList, List<Integer> holdersBalance, List<Date> dateList) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
		if (mainClass.userListChecker(holdersList)) {
			System.out.println("\n\t\t  ________________________________________________");
			System.out.println("\t\t||                                                ||");
			System.out.println("\t\t|| > There are no registered account holders yet. ||");
			System.out.println("\t\t||________________________________________________||");
		} else {
			System.out.println("\n\tACCOUNT NUMBER\t\tACCOUNT HOLDER NAME\tBALANCE\t\tDATE JOINED\n");
			for (int x = 0; x < holdersList.size(); x++) {
				System.out.println("\t      " + (x + 1) + " -------------- " + holdersList.get(x) + " ---------- Php "
						+ holdersBalance.get(x) + " ---------- " + formatter.format(dateList.get(x)));
			}
		}

	}
}
