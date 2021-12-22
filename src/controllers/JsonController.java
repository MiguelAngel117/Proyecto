package controllers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import models.User;

public class JsonController {
    private final String path;

    public JsonController() {
        path = "src/persistence/Users.Json";
    }

    public String personsToJson(ArrayList<User> user) {
        Gson gson = new Gson();
        return gson.toJson(user);
    }

    public ArrayList<User> jsonToPersons(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<ArrayList<User>>() {
        }.getType());
    }

    public void save(ArrayList<User> persons) throws IOException {
        FileWriter file = new FileWriter(path);
        file.write(personsToJson(persons));
        file.close();
    }

    public ArrayList<User> read() throws IOException, ClassNotFoundException {
        FileReader file = new FileReader(path);
        Scanner scanner = new Scanner(file);
        String json = "";
        while (scanner.hasNextLine()) {
            json += scanner.nextLine();
        }
        ArrayList<User> persons = jsonToPersons(json);
        file.close();
        return persons;
    }
}
