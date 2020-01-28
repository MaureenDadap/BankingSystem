package com.programming.bankingsystem;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MakeJSON {
    final String directory = "banking-users.json";

    Object parseJsonFile(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }

    int countUsers() {
        int count = 0;
        try {
            Object obj = parseJsonFile(directory);
            JSONArray jsonArray= (JSONArray) obj;
            count = jsonArray.size();
        } catch (Exception e) {
        }
        return count;
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
            Object obj = parseJsonFile(directory);
            JSONArray existingJson = (JSONArray) obj;

            JSONObject user = new JSONObject(); // new entries to be added
            user.put("name", name);
            user.put("age", age);
            user.put("address", address);
            user.put("balance", deposit);

            JSONArray transaction = new JSONArray(); // transaction added
            transaction.add(transactionString);
            user.put("transactions", transaction);
            existingJson.add(user);

            writeToJson(existingJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void writeTransaction() {

    }

    void getUsers() {

    }
}