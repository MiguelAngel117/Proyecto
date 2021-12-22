package views.information;

import java.awt.Color;

import javax.swing.*;

import controllers.UserController;
import models.User;
import views.home.JFrameHome;

public class JFrameInformation extends JFrame {
    private InformationListener informationListener;
    private UserController userController;
    private User user;

    private JTextField jTextFieldName;
    private JTextField jTextFieldSecondName;
    private JTextField jTextFieldNumberDocument;
    private JTextField jTextFieldAge;

    private JComboBox jComboBoxTypeDocument;
    private JComboBox jComboBoxYear;
    private JComboBox jComboBoxMonth;
    private JComboBox jComboBoxDay;

    private JLabel jLabelName;
    private JLabel jLabelSecondName;
    private JLabel jLabelTypeDocument;
    private JLabel jLabelNumberDocument;
    private JLabel jLabelDate;
    private JLabel jLabelYear;
    private JLabel jLabelMonth;
    private JLabel jLabelDay;
    private JLabel jLabelAge;

    private JButton jButtonUpdate;
    private JButton jButtonClose;
    private JButton jButtonDelete;

    public JFrameInformation(UserController userController, JFrameHome jFrameHome) {
        informationListener = new InformationListener(this, userController, jFrameHome);
        this.userController = userController;
        setLayout(null);
        this.setBounds(0, 0, 660, 360);
        this.getContentPane().setBackground(new Color(186, 200, 211));
        this.setTitle("Información");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        jLabelName = new JLabel("Nombres");
        jLabelName.setBounds(30, 40, 60, 30);
        this.add(jLabelName);

        jLabelSecondName = new JLabel("Apellidos");
        jLabelSecondName.setBounds(30, 70, 60, 30);
        this.add(jLabelSecondName);

        jLabelTypeDocument = new JLabel("Tipo de Documento");
        jLabelTypeDocument.setBounds(30, 110, 120, 30);
        this.add(jLabelTypeDocument);

        jLabelNumberDocument = new JLabel("Numero de documento");
        jLabelNumberDocument.setBounds(30, 150, 130, 30);
        this.add(jLabelNumberDocument);

        jLabelDate = new JLabel("Fecha de nacimiento");
        jLabelDate.setBounds(30, 190, 130, 30);
        this.add(jLabelDate);

        jLabelYear = new JLabel("Año");
        jLabelYear.setBounds(170, 190, 30, 30);
        this.add(jLabelYear);

        jLabelMonth = new JLabel("Mes");
        jLabelMonth.setBounds(290, 190, 30, 30);
        this.add(jLabelMonth);

        jLabelDay = new JLabel("Día");
        jLabelDay.setBounds(410, 190, 30, 30);
        this.add(jLabelDay);

        jLabelAge = new JLabel("Edad (años)");
        jLabelAge.setBounds(30, 230, 130, 30);
        this.add(jLabelAge);

        jTextFieldName = new JTextField();
        jTextFieldName.setBounds(170, 40, 450, 30);
        this.add(jTextFieldName);

        jTextFieldSecondName = new JTextField();
        jTextFieldSecondName.setBounds(170, 70, 450, 30);
        this.add(jTextFieldSecondName);

        jTextFieldNumberDocument = new JTextField();
        jTextFieldNumberDocument.setBounds(170, 150, 450, 30);
        this.add(jTextFieldNumberDocument);

        jTextFieldAge = new JTextField();
        jTextFieldAge.setBounds(170, 230, 450, 30);
        jTextFieldAge.setEditable(false);
        this.add(jTextFieldAge);

        jComboBoxTypeDocument = new JComboBox<String>();
        jComboBoxTypeDocument.addItem("CC - Cedula de Ciudadania");
        jComboBoxTypeDocument.addItem("CE - Cedula de extranjeria");
        jComboBoxTypeDocument.addItem("PA - Pasaporte");
        jComboBoxTypeDocument.setBounds(170, 110, 450, 30);
        jComboBoxTypeDocument.setSelectedItem("CC - Cedula de Ciudadania");
        this.add(jComboBoxTypeDocument);

        jComboBoxYear = new JComboBox<Integer>();
        for (int i = informationListener.getInstanceDate().getYearMin(); i <= informationListener.getInstanceDate()
                .getYearMax(); i++) {
            jComboBoxYear.addItem(i);
        }
        jComboBoxYear.setSelectedItem(2000);
        jComboBoxYear.setBounds(200, 190, 80, 30);
        jComboBoxYear.addActionListener(informationListener);
        jComboBoxYear.setActionCommand(ActionsInformation.YEAR.toString());
        this.add(jComboBoxYear);

        jComboBoxMonth = new JComboBox<String>();
        for (int i = 1; i <= informationListener.getInstanceDate().getMonths(); i++) {
            if (i < 10)
                jComboBoxMonth.addItem("0" + i);
            else
                jComboBoxMonth.addItem(i + "");

        }
        jComboBoxMonth.setBounds(320, 190, 80, 30);
        jComboBoxMonth.addActionListener(informationListener);
        jComboBoxMonth.setActionCommand(ActionsInformation.MONTH.toString());
        this.add(jComboBoxMonth);

        jComboBoxDay = new JComboBox<Integer>();
        addDaysJComboBoxDay(informationListener.getInstanceDate().getDaysInMonth(), "01");
        jComboBoxDay.setBounds(440, 190, 80, 30);
        jComboBoxDay.addActionListener(informationListener);
        jComboBoxDay.setActionCommand(ActionsInformation.DAY.toString());
        this.add(jComboBoxDay);

        jButtonDelete = new JButton("Eliminar");
        jButtonDelete.setBounds(265, 270, 110, 40);
        jButtonDelete.addActionListener(informationListener);
        jButtonDelete.setBackground(new Color(253, 106, 83));
        jButtonDelete.setActionCommand(ActionsInformation.DELETE.toString());
        this.add(jButtonDelete);

        jButtonUpdate = new JButton("Actualizar");
        jButtonUpdate.setBounds(390, 270, 110, 40);
        jButtonUpdate.addActionListener(informationListener);
        jButtonUpdate.setBackground(new Color(49, 153, 139));
        jButtonUpdate.setActionCommand(ActionsInformation.UPDATE.toString());
        this.add(jButtonUpdate);

        jButtonClose = new JButton("Cerrar");
        jButtonClose.setBounds(510, 270, 110, 40);
        jButtonClose.addActionListener(informationListener);
        jButtonClose.setBackground(new Color(49, 153, 139));
        jButtonClose.setActionCommand(ActionsInformation.CLOSE.toString());
        this.add(jButtonClose);
    }

    public void addDaysJComboBoxDay(int limit, String select) {
        for (int i = jComboBoxDay.getItemCount() - 1; i > 0; i--)
            jComboBoxDay.removeItemAt(i);

        for (int i = 1; i <= limit; i++) {
            if (i < 10)
                jComboBoxDay.addItem("0" + i);
            else
                jComboBoxDay.addItem("" + i);
        }
        if (jComboBoxDay.getItemAt(1) == "01")
            jComboBoxDay.removeItemAt(0);
        jComboBoxDay.setSelectedItem(select);
    }

    public JTextField getjTextFieldName() {
        return jTextFieldName;
    }

    public JTextField getjTextFieldSecondName() {
        return jTextFieldSecondName;
    }

    public JTextField getjTextFieldNumberDocument() {
        return jTextFieldNumberDocument;
    }

    public JComboBox getjComboBoxTypeDocument() {
        return jComboBoxTypeDocument;
    }

    public JComboBox getJComboBoxYear() {
        return jComboBoxYear;
    }

    public JComboBox getJComboBoxMonth() {
        return jComboBoxMonth;
    }

    public JComboBox getJComboBoxDay() {
        return jComboBoxDay;
    }

    public JTextField getjTextFieldAge() {
        return jTextFieldAge;
    }

    public String setSelectedString(int number) {
        if (number < 10)
            return "0" + number;
        else
            return "" + number;
    }

    public void loadData(User user) {
        if (user != null) {
            this.user = user;
            jTextFieldName.setText(user.getNames());
            jTextFieldSecondName.setText(user.getLastName());
            jComboBoxTypeDocument.setSelectedItem(user.getTypeDocument());
            jTextFieldNumberDocument.setText(user.getDocumentNumber());
            jComboBoxYear.setSelectedItem(user.getYear());
            jComboBoxMonth.setSelectedItem(setSelectedString(user.getMonth()));
            jComboBoxDay.setSelectedItem(setSelectedString(user.getDay()));
            jTextFieldAge.setText(userController.getAge(user.getDate()) + "");
        }
    }

    public void assignedEmpty() {
        jTextFieldName.setText("");
        jTextFieldSecondName.setText("");
        jComboBoxTypeDocument.setSelectedItem("CC - Cedula de Ciudadania");
        jTextFieldNumberDocument.setText("");
        jComboBoxYear.setSelectedItem("");
        jComboBoxMonth.setSelectedItem("");
        jComboBoxDay.setSelectedItem("");
        jTextFieldAge.setText("");
    }

    public void showMessageConfirm(String message) {
        JOptionPane.showMessageDialog(null, message, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showMessageError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public User getUser() {
        return user;
    }
}
