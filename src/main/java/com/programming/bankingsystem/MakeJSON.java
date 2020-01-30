package com.programming.bankingsystem;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
            JSONArray jsonArray = (JSONArray) obj;
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
            user.put("id", countUsers() + 1);
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

    void writeTransaction(int idSearch, String transaction) {
        try {
            Object obj = parseJsonFile(directory);
            JSONArray existingJson = (JSONArray) obj;

            for (int j = 0; j < existingJson.size(); j++) {
                JSONObject records = (JSONObject) existingJson.get(j);
                String id = records.get("id").toString();

                if (Integer.parseInt(id) == idSearch) {
                    JSONArray existingTransactions = (JSONArray) records.get("transactions");
                    JSONArray newTransactions = existingTransactions;
                    newTransactions.add(transaction);
                    records.remove("transactions");
                    records.put("transactions", newTransactions);
                    break;
                }
            }
            writeToJson(existingJson);

        } catch (Exception e) {
            // excption handling
        }
    }

    int getBalance(int idSearch) {
        int balance = 0;
        try {
            Object obj = parseJsonFile(directory);
            JSONArray existingJson = (JSONArray) obj;
            for (int i = 0; i < existingJson.size(); i++) {
                JSONObject records = (JSONObject) existingJson.get(i);
                String id = records.get("id").toString();

                if (Integer.parseInt(id) == idSearch) {
                    String balanceString = records.get("balance").toString();
                    balance = Integer.parseInt(balanceString);
                }
            }
        } catch (Exception e) {
            // exception handling
        }
        return balance;
    }

    void changeBalance(int idSearch, int newBalance) {
        try {
            Object obj = parseJsonFile(directory);
            JSONArray existingJson = (JSONArray) obj;

            for (int i = 0; i < existingJson.size(); i++) {
                JSONObject records = (JSONObject) existingJson.get(i);
                String id = records.get("id").toString();

                if (Integer.parseInt(id) == idSearch) {
                    records.remove("balance");
                    records.put("balance", newBalance);
                    break;
                }
            }
            writeToJson(existingJson);
        } catch (Exception e) {
            // Exception handling
        }
    }

    ArrayList<String> getUsers() {
        ArrayList<String> userList = new ArrayList<>();
        try {
            Object obj = parseJsonFile(directory);
            JSONArray existingJson = (JSONArray) obj;

            for (int i = 0; i < existingJson.size(); i++) {
                JSONObject records = (JSONObject) existingJson.get(i);
                String userName = records.get("name").toString();
                userList.add(userName);
            }

        } catch (Exception e) {

        }
        return userList;
    }

    String getAge(int idSearch) {
        String age = "";
        try {
            Object obj = parseJsonFile(directory);
            JSONArray existingJson = (JSONArray) obj;

            for (int i = 0; i < existingJson.size(); i++) {
                JSONObject records = (JSONObject) existingJson.get(i);
                age = records.get("age").toString();
            }

        } catch (Exception e) {
            // handle
        }
        return age;
    }

    String getAddress(int idSearch) {
        String address = "";
        try {
            Object obj = parseJsonFile(directory);
            JSONArray existingJson = (JSONArray) obj;

            for (int i = 0; i < existingJson.size(); i++) {
                JSONObject records = (JSONObject) existingJson.get(i);
                address = records.get("address").toString();
            }

        } catch (Exception e) {
            // handle
        }
        return address;
    }

    ArrayList<String> getTransactions(int idSearch) {
        ArrayList<String> transactions = new ArrayList<>();
        try {
            Object obj = parseJsonFile(directory);
            JSONArray existingJson = (JSONArray) obj;

            for (int i = 0; i < existingJson.size(); i++) {
                JSONObject records = (JSONObject) existingJson.get(i);
                JSONArray jsonTransactions = (JSONArray) records.get("transactions");

                for (int j = 0; j < jsonTransactions.size(); j++)
                    transactions.add((String) jsonTransactions.get(j));
            }
        } catch (Exception e) {
            // handle
        }
        return transactions;
    }
}