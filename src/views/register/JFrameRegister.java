package views.register;

import java.awt.Color;

import javax.swing.*;

import controllers.UserController;
import views.home.JFrameHome;

public class JFrameRegister extends JFrame {
    private RegisterListener registerListener;

    private JTextField jTextFieldName;
    private JTextField jTextFieldSecondName;
    private JTextField jTextFieldNumberDocument;

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

    private JButton jButtonSave;

    public JFrameRegister(JFrameHome jFrameHome, UserController userController) {
        registerListener = new RegisterListener(this, userController, jFrameHome);
        setLayout(null);
        this.setBounds(0, 0, 660, 340);
        this.setTitle("Registro");
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

        jTextFieldName = new JTextField();
        jTextFieldName.setBounds(170, 40, 450, 30);
        this.add(jTextFieldName);

        jTextFieldSecondName = new JTextField();
        jTextFieldSecondName.setBounds(170, 70, 450, 30);
        this.add(jTextFieldSecondName);

        jTextFieldNumberDocument = new JTextField();
        jTextFieldNumberDocument.setBounds(170, 150, 450, 30);
        this.add(jTextFieldNumberDocument);

        jComboBoxTypeDocument = new JComboBox<String>();
        jComboBoxTypeDocument.addItem("CC - Cedula de Ciudadania");
        jComboBoxTypeDocument.addItem("CE - Cedula de extranjeria");
        jComboBoxTypeDocument.addItem("PA - Pasaporte");
        jComboBoxTypeDocument.setBounds(170, 110, 450, 30);
        jComboBoxTypeDocument.setSelectedItem("CC - Cedula de Ciudadania");
        this.add(jComboBoxTypeDocument);

        jComboBoxYear = new JComboBox<Integer>();
        for (int i = registerListener.getInstanceDate().getYearMin(); i <= registerListener.getInstanceDate()
                .getYearMax(); i++) {
            jComboBoxYear.addItem(i);
        }
        jComboBoxYear.setSelectedItem(2000);
        jComboBoxYear.setBounds(200, 190, 80, 30);
        jComboBoxYear.addActionListener(registerListener);
        jComboBoxYear.setActionCommand(ActionsRegister.YEAR.toString());// CAMBIAR
        jComboBoxYear.setSelectedItem(1);
        this.add(jComboBoxYear);

        jComboBoxMonth = new JComboBox<String>();
        for (int i = 1; i <= registerListener.getInstanceDate().getMonths(); i++) {
            if (i < 10)
                jComboBoxMonth.addItem("0" + i);
            else
                jComboBoxMonth.addItem(i);

        }
        jComboBoxMonth.setBounds(320, 190, 80, 30);
        jComboBoxMonth.setSelectedItem("01");
        jComboBoxMonth.addActionListener(registerListener);
        jComboBoxMonth.setActionCommand(ActionsRegister.MONTH.toString());// CAMBIAR
        jComboBoxMonth.setSelectedItem(1);
        this.add(jComboBoxMonth);

        jComboBoxDay = new JComboBox<Integer>();
        addDaysJComboBoxDay(registerListener.getInstanceDate().getDaysInMonth());
        jComboBoxDay.setBounds(440, 190, 80, 30);
        jComboBoxDay.addActionListener(registerListener);
        jComboBoxDay.setActionCommand(ActionsRegister.DAY.toString());
        jComboBoxDay.setSelectedItem(01);
        this.add(jComboBoxDay);

        jButtonSave = new JButton("Guardar");
        jButtonSave.setBounds(510, 250, 110, 40);
        jButtonSave.addActionListener(registerListener);
        jButtonSave.setBackground(new Color(49, 153, 139));
        jButtonSave.setActionCommand(ActionsRegister.SAVE.toString());
        this.add(jButtonSave);

    }

    public void addDaysJComboBoxDay(int limit) {
        for (int i = jComboBoxDay.getItemCount() - 1; i > 0; i--)
            jComboBoxDay.removeItemAt(i);
        for (int i = 1; i <= limit; i++) {
            if (i < 10)
                jComboBoxDay.addItem("0" + i);
            else
                jComboBoxDay.addItem(i);
        }
        if (jComboBoxDay.getItemAt(1) == "01")
            jComboBoxDay.removeItemAt(0);
    }

    public JTextField getjTextFieldName() {
        return jTextFieldName;
    }

    public JTextField getJTextFieldLastName() {
        return jTextFieldSecondName;
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

    public JTextField getJComboBoxNumberDocument() {
        return jTextFieldNumberDocument;
    }

    public JComboBox getJComboBoxTypeDocument() {
        return jComboBoxTypeDocument;
    }

    public void showMessageConfirm(String message) {
        JOptionPane.showMessageDialog(null, message, "Información", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showMessageError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void assignedEmpty() {
        jTextFieldName.setText("");
        jTextFieldSecondName.setText("");
        jComboBoxTypeDocument.setSelectedItem("CC - Cedula de Ciudadania");
        jTextFieldNumberDocument.setText("");
        jComboBoxYear.setSelectedItem(2000);
        jComboBoxMonth.setSelectedItem("01");
        jComboBoxDay.setSelectedItem(01);
    }
}
