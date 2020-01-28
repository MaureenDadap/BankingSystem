package com.programming.bankingsystem;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class Register {
	static MakeJSON makeJSON = new MakeJSON();
	public static void regMethod(List<String> holdersList) {
		Scanner input = new Scanner(System.in);
		String name, transactionString, age, address;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
		int initDeposit = 0;
		int ageInt = 0;

		// asks for the name of account holder
		System.out.println("\n\t**NOTE: Golden Coin Bank requires a minimum deposit of Php 500 upon registration.** ");
		do {
			System.out.print("\n\tEnter the name of the account holder: ");
			name = input.nextLine();
			if (name.trim().equals("")) {
				System.out.println("\t\t  ________________________________________________");
				System.out.println("\t\t||                                                ||");
				System.out.println("\t\t|| > INVALID INPUT. Name cannot be blank.         ||");
				System.out.println("\t\t||________________________________________________||");
			}
		} while (name.trim().equals(""));

		do {
			System.out.print("\tEnter the age of the account holder: ");
			age = input.nextLine();
			if (age.trim().equals("")) {
				System.out.println("\t\t  ________________________________________________");
				System.out.println("\t\t||                                                ||");
				System.out.println("\t\t|| > INVALID INPUT. Age cannot be blank.          ||");
				System.out.println("\t\t||________________________________________________||");
			} else {
				ageInt = Integer.parseInt(age);

				if (ageInt > 150) {
					System.out.println("\t\t  ________________________________________________");
					System.out.println("\t\t||                                                ||");
					System.out.println("\t\t|| > INVALID INPUT. It is not a valid age.        ||");
					System.out.println("\t\t||________________________________________________||");
				} else if (ageInt < 18) {
					System.out.println("\t\t  ________________________________________________");
					System.out.println("\t\t||                                                ||");
					System.out.println("\t\t|| > INVALID INPUT. 18 is the minimum age         ||");
					System.out.println("\t\t||   required for a regular bank account.         ||");
					System.out.println("\t\t||________________________________________________||");
				} else
					break;
			}
		} while (name.trim().equals("") || ageInt > 150 || ageInt < 18);

		do {
			System.out.print("\tEnter the address of the account holder: ");
			address = input.nextLine();
			if (address.trim().equals("")) {
				System.out.println("\t\t  ________________________________________________");
				System.out.println("\t\t||                                                ||");
				System.out.println("\t\t|| > INVALID INPUT. Name cannot be blank.         ||");
				System.out.println("\t\t||________________________________________________||");
			}
		} while (address.trim().equals(""));

		while (true) {
			System.out.print("\tEnter the amount of initial deposit (min. Php 500): ");
			try {
				initDeposit = Integer.parseInt(input.next());
				if (initDeposit == 0) {
					System.out.println("\t\t  ________________________________________________");
					System.out.println("\t\t||                                                ||");
					System.out.println("\t\t|| > INVALID INPUT. Golden Coin Bank does not     ||");
					System.out.println("\t\t||   accept Php 0 as initial deposit.             ||");
					System.out.println("\t\t||________________________________________________||");
				} else if (initDeposit < 500) {
					System.out.println("\t\t  ________________________________________________");
					System.out.println("\t\t||                                                ||");
					System.out.println("\t\t|| > INVALID INPUT. Golden Coin Bank requires a   ||");
					System.out.println("\t\t||   minimum initial deposit of Php 500 upon      ||");
					System.out.println("\t\t||   registration.                                ||");
					System.out.println("\t\t||________________________________________________||");

				} else if (initDeposit >= 500) {
					System.out.println("\t\t  ________________________________________________");
					System.out.println("\t\t||                                                ||");
					System.out.println("\t\t|| > SUCCESSFULLY REGISTERED!                     ||");
					System.out.println("\t\t||________________________________________________||");
					break;
				}
			} catch (NumberFormatException ignore) {
				System.out.println("\t\t  ________________________________________________");
				System.out.println("\t\t||                                                ||");
				System.out.println("\t\t|| > INVALID INPUT                                ||");
				System.out.println("\t\t||________________________________________________||");
			}
		}

		// will register the name, an initial balance of Php 0, and the date of account
		// creation to the json file 
		transactionString = formatter.format(new Date()) + " - Created a bank account";
		makeJSON.registerUser(name, age, address, initDeposit, transactionString);

		System.out.println("\n\t\t--> " + name + " deposited Php " + initDeposit
				+ " and is now registered as account holder #" + mainClass.regCount);
		System.out.println("\t\t--> There are now currently " + holdersList.size() + " registered account holder(s).");
	}
}
