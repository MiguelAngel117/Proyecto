package models;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class User {
    private String names;
    private String lastName;
    private String typeDocument;
    private String documentNumber;
    private GregorianCalendar date;

    public User(String names, String lastName, String typeDocument, String documentNumber, GregorianCalendar date) {
        this.names = names;
        this.lastName = lastName;
        this.typeDocument = typeDocument;
        this.documentNumber = documentNumber;
        this.date = date;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public int getYear() {
        return date.get(Calendar.YEAR);
    }

    public int getMonth() {
        return date.get(Calendar.MONTH);
    }

    public int getDay() {
        return date.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public String toString() {
        return names + " - " + lastName + " - " + typeDocument.substring(0, 2) + ": " + documentNumber;
    }
}