package com.programming.bankingsystem;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MakeJSON {
    final String directory = "C:/Users/truel/Desktop/2nd term programming project/programming-project-banking/banking-users.json";

    Object readJsonFile(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }

    void writeToJson(JSONArray toBeAdded) {
        try {
            FileWriter file = new FileWriter(directory);
            file.write(toBeAdded.toJSONString());
            file.flush();
            file.close();
        } catch (Exception e) {
            System.out.println("\t\t  ________________________________________________");
            System.out.println("\t\t||                                                ||");
            System.out.println("\t\t|| > REGISTRATION FAILED                          ||");
            System.out.println("\t\t||________________________________________________||");
        }
    }

    void registerUser(String name, String age, String address, int deposit, String transactionString) {
        try {
            Object obj = readJsonFile(directory);
            JSONArray existingJson = new JSONArray();
            existingJson.add(obj); //existing values stored in json 
            JSONObject user = new JSONObject(); // new entries to be added

            user.put("name", name);
            user.put("age", age);
            user.put("address", address);
            user.put("balance", deposit);

            JSONArray transactions = new JSONArray();
            transactions.add(transactionString);
            user.put("transactions", transactions);

            existingJson.add(user);
            writeToJson(existingJson);
        } catch (Exception e) {
            System.out.println("\t\t  ________________________________________________");
            System.out.println("\t\t||                                                ||");
            System.out.println("\t\t|| > REGISTRATION FAILED                          ||");
            System.out.println("\t\t||________________________________________________||");
        }
    }

    void writeTransaction(JSONArray jsonArray, String id) {
        
    }

    void getUsers() {

    }
}