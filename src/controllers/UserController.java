package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

import models.User;

public class UserController {
    private JsonController jsonController;
    private ArrayList<User> listUsers;

    public UserController() {
        jsonController = new JsonController();
        listUsers = new ArrayList<>();
        readData();
        saveData();
    }

    public boolean validateAge(GregorianCalendar date) throws Exception {
        if (getAge(date) > 17) {
            return true;
        }
        throw new Exception();
    }

    public int getAge(GregorianCalendar date) {
        GregorianCalendar dateNow = new GregorianCalendar();
        Period periodo = Period.between(convertToLocalDate(date), convertToLocalDate(dateNow));
        return periodo.getYears();
    }

    private LocalDate convertToLocalDate(GregorianCalendar dateToConvert) {
        return dateToConvert.toZonedDateTime().toLocalDate();
    }

    public ArrayList<User> getList() {
        Collections.sort(listUsers, (o1, o2) -> o1.getNames().compareTo(o2.getNames()));
        return listUsers;
    }

    public void delete(User user) {
        listUsers.removeIf(userP -> userP.getDocumentNumber().equals(user.getDocumentNumber()));
    }

    public ArrayList<User> obtainUser(String name, String lastName) {
        ArrayList<User> newList = new ArrayList<>();
        for (User user : listUsers) {
            if (user.getLastName().equals(lastName) || user.getNames().equals(name)) {
                newList.add(user);
            }
        }
        Collections.sort(newList, (o1, o2) -> o1.getNames().compareTo(o2.getNames()));
        return newList;
    }

    public void reloadData(ArrayList<User> users) {
        listUsers = users;
    }

    public void addUser(User user) {
        listUsers.add(user);
    }

    public void deleteUser(User user) {
        listUsers.removeIf(i -> (i.getDocumentNumber().equals(user.getDocumentNumber())));
    }

    public void saveData() {
        try {
            jsonController.save(this.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readData() {
        try {
            this.reloadData(jsonController.read());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean checkIdentifyNumber(String typeDocument, String numberDocument) throws Exception {
        boolean result = true;
        int count = 0;
        for (int i = 0; i < listUsers.size(); i++) {
            if (listUsers.get(i).getDocumentNumber().equals(numberDocument)
                    && listUsers.get(i).getTypeDocument().equals(typeDocument)) {
                count++;
            }
        }
        if (count == 0) {
            return result;
        }
        throw new Exception();
    }

    public boolean checkNotEmpty(String word, int length) throws Exception {
        if (!word.isEmpty() && word.length() <= length) {
            return true;
        }
        throw new Exception();
    }

    public boolean checkIdentifyNumberUpdate(String typeDocument, String newNumberDocument, String numberOlder)
            throws Exception {
        int count = 0;
        for (int i = 0; i < listUsers.size(); i++) {
            if (listUsers.get(i).getDocumentNumber().equals(newNumberDocument)
                    && listUsers.get(i).getTypeDocument().equals(typeDocument)
                    && !numberOlder.equals(newNumberDocument))
                count++;
        }
        if (numberOlder.equals(newNumberDocument))
            count++;
        if (count == 1)
            return true;
        throw new Exception();
    }

}
