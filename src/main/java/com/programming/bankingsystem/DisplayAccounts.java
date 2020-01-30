package com.programming.bankingsystem;

public class DisplayAccounts {
	public static void displayAcc() {
		MakeJSON makeJSON = new MakeJSON();
		if (makeJSON.countUsers() == 0) {
			System.out.println("\n\t\t  ________________________________________________");
			System.out.println("\t\t||                                                ||");
			System.out.println("\t\t|| > There are no registered account holders yet. ||");
			System.out.println("\t\t||________________________________________________||");
		} else {
			System.out.println("\n\tACCOUNT NUMBER\t\tACCOUNT HOLDER NAME\tBALANCE\n");
			for (int x = 0; x < makeJSON.countUsers(); x++) {
				System.out.println("\t      " + (x + 1) + " -------------- " + makeJSON.getUsers().get(x)
						+ " ---------- Php " + makeJSON.getBalance(x+1));
			}
		}

	}
}
