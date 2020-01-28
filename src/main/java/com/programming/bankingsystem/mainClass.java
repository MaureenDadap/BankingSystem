package com.programming.bankingsystem;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class mainClass {
	static MakeJSON makeJSON = new MakeJSON();
	//map <account number, transaction list>
	public static Map<Integer, List<String>> transactionsMap = new HashMap<Integer, List<String>>(); 
	public static int regCount = 0;

	public static Boolean userListChecker(List<String> holdersList) {
		boolean holdersListEmpty = false;
		if (holdersList.isEmpty())
			return holdersListEmpty = true;
		else
			return holdersListEmpty;
	}

	public static void putTransactions(int key, String transString) {
		if (transactionsMap.get(key) == null) {
			transactionsMap.put(key, new ArrayList<String>());
		}
		transactionsMap.get(key).add(transString);
	}

	public static void main(String[] args) {
		//makeJSON.checkJSON();

		Scanner input = new Scanner(System.in);
		// Lists and Collections
		List<String> acctHoldersList = new ArrayList<String>();
		List<String> acctHolderAgeList = new ArrayList<String>();
		List<String> acctHolderAddressList = new ArrayList<String>();
		List<Integer> holdersBalance = new ArrayList<Integer>();
		List<Date> dateCreateAcc = new ArrayList<Date>();

		// variable for menu
		int ch = 0;

		System.out.print(
				"=============================================================================================\n\n\t\t\t\tE-Bank System\n\n=============================================================================================");
		System.out.println("\n\t\t\t\t    Welcome, Teller #01");

		do {
			while (true) {
				System.out.println(
						"---------------------------------------------------------------------------------------------");
				System.out.println("\n\t\t   >> MENU");
				System.out.println("\t\t\t(1) Register Accounts");
				System.out.println("\t\t\t(2) Display Registered Accounts");
				System.out.println("\t\t\t(3) Deposit");
				System.out.println("\t\t\t(4) Withdraw");
				System.out.println("\t\t\t(5) Transfer Balance");
				System.out.println("\t\t\t(6) Check Balance");
				System.out.println("\t\t\t(0) EXIT");
				System.out.print("\n\t\t\t>> CHOICE: ");

				try {
					ch = Integer.parseInt(input.next());

					if (ch == 1) {
						// account registration module
						System.out.print(
								"=============================================================================================\n\n\t\t\t\tREGISTER ACCOUNTS\n\n=============================================================================================");
						Register.regMethod(acctHoldersList);

					} else if (ch == 2) {
						// display accounts module
						System.out.print(
								"=============================================================================================\n\n\t\t\t\tDISPLAY REGISTERED ACCOUNTS\n\n=============================================================================================");
						DisplayAccounts.displayAcc(acctHoldersList, holdersBalance, dateCreateAcc);

					} else if (ch == 3) {
						// deposit module
						System.out.print(
								"=============================================================================================\n\n\t\t\t\t\tDEPOSIT\n\n=============================================================================================");
						Deposit.deposit(acctHoldersList, holdersBalance);

					} else if (ch == 4) {
						// money withdrawal module
						System.out.print(
								"=============================================================================================\n\n\t\t\t\tMONEY WITHDRAWAL\n\n=============================================================================================");
						Withdraw.withdraw(acctHoldersList, holdersBalance);

					} else if (ch == 5) {
						// balance transfer module
						System.out.print(
								"=============================================================================================\n\n\t\t\t\tTRANSFER BALANCE\n\n=============================================================================================");
						BalanceTransfer.balanceTransfer(acctHoldersList, holdersBalance);

					} else if (ch == 6) {
						// balance checker module
						System.out.print(
								"=============================================================================================\n\n\t\t\t\tCHECK BALANCE\n\n=============================================================================================");
						BalanceCheck.balanceCheck(acctHoldersList, holdersBalance, dateCreateAcc, acctHolderAgeList, acctHolderAddressList);

					} else if (ch == 0)
						System.exit(0);
					else {
						System.out.println("\t\t  ________________________________________________");
						System.out.println("\t\t||                                                ||");
						System.out.println("\t\t|| > INVALID INPUT                                ||");
						System.out.println("\t\t||________________________________________________||");
					}
				} catch (NumberFormatException n) {
					System.out.println("\t\t  ________________________________________________");
					System.out.println("\t\t||                                                ||");
					System.out.println("\t\t|| > INVALID INPUT                                ||");
					System.out.println("\t\t||________________________________________________||");
				}
			}
		} while (ch != 0);
		// input.close();
	}
}
