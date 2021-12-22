package views.home;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.*;

import controllers.UserController;
import models.User;
import views.information.JFrameInformation;
import views.register.JFrameRegister;

public class JFrameHome extends JFrame {

    private static final String NAME_USER = "MIGUEL ANGEL CHOCONTA MOTAVITA - 201924931";

    private HomeListener homeListener;

    private JFrameRegister jFrameRegister;
    private JFrameInformation jFrameInformation;
    private UserController userController;

    private JTextField jTextFieldSearch;
    private JLabel jLabelTitle;
    private JLabel jLabelTitleList;

    private JButton jButtonSearch;
    private JButton jButtonRegister;

    private JList jList;

    public JFrameHome(UserController userController) {
        this.userController = userController;
        homeListener = new HomeListener(this);
        jFrameRegister = new JFrameRegister(this, userController);
        jFrameInformation = new JFrameInformation(userController, this);
        setLayout(null);
        this.setBounds(0, 0, 630, 690);
        this.setTitle(NAME_USER);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        jLabelTitle = new JLabel("APLICATIVO USUARIOS", SwingConstants.CENTER);
        jLabelTitle.setBounds(233, 50, 152, 20);
        this.add(jLabelTitle);

        jTextFieldSearch = new JTextField();
        jTextFieldSearch.setBounds(20, 110, 470, 30);
        this.add(jTextFieldSearch);

        jButtonSearch = new JButton("Buscar");
        jButtonSearch.setBounds(510, 110, 90, 30);
        jButtonSearch.addActionListener(homeListener);
        jButtonSearch.setBackground(new Color(49, 153, 139));
        jButtonSearch.setActionCommand(ActionsHome.SEARCH.toString());
        this.add(jButtonSearch);

        jLabelTitleList = new JLabel("Registro", SwingConstants.CENTER);
        jLabelTitleList.setBounds(20, 150, 580, 30);
        jLabelTitleList.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        this.add(jLabelTitleList);

        jList = new JList();
        reload(userController.getList());
        jList.addListSelectionListener(homeListener);
        jList.setBounds(20, 180, 580, 410);
        jList.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        this.add(jList);

        jButtonRegister = new JButton("Registrar");
        jButtonRegister.setBounds(510, 600, 90, 30);
        jButtonRegister.setBackground(new Color(49, 153, 139));
        jButtonRegister.addActionListener(homeListener);
        jButtonRegister.setActionCommand(ActionsHome.REGISTER.toString());
        this.add(jButtonRegister);
    }

    public void showUserFind(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public JTextField getjTextFieldSearch() {
        return jTextFieldSearch;
    }

    public JList getjList() {
        return jList;
    }

    public JFrameRegister getJFrameRegister() {
        return jFrameRegister;
    }

    public JFrameInformation getJFrameInformation() {
        return jFrameInformation;
    }

    public void reload() {
        DefaultListModel listModel = new DefaultListModel();
        userController.getList().forEach(user -> listModel.addElement(user));
        jList.setModel(listModel);
    }

    public void reload(ArrayList<User> list) {
        DefaultListModel listModel = new DefaultListModel();
        list.forEach(user -> listModel.addElement(user));
        jList.setModel(listModel);
    }

    public UserController getUserController() {
        return userController;
    }
}
